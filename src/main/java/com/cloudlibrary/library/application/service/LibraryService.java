package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.application.domain.Library;


import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import com.cloudlibrary.library.infrastructure.persistance.mysql.repository.LibraryEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibraryService implements LibraryOperationUseCase, LibraryReadUseCase {
    private final LibraryEntityRepository libraryRepository;


    @Autowired
    public LibraryService(LibraryEntityRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public FindLibraryResult createLibrary(LibraryCreatedCommand command) {

        var newLibrary = Library.builder()
                .id(command.getId())
                .name(command.getName())
                .address(command.getAddress())
                .email(command.getEmail())
                .tel(command.getTel())
                .holiday(command.getHoliday())
                .operatingTime(command.getOperatingTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(command.getLendingAvailableCount())
                .lendingAvailableDays(command.getLendingAvailableDays())
                .overdueCount(command.getOverdueCount())
                .longtermOverdueDays(command.getLongtermOverdueDays())
                .lendingLimitDays(command.getLendingLimitDays())
                .build();

        var savedLibraryEntity = libraryRepository.saveLibrary(new LibraryEntity(newLibrary));

        // Library Post Success
        if(savedLibraryEntity.isPresent()){
            return FindLibraryResult.findByLibrary(savedLibraryEntity.get().toLibrary());
        }

        // Library Post Failed
        return libraryServiceFailedSequence();

    }


    @Override
    public FindLibraryResult getLibrary(LibraryFindQuery query) {

        var result = libraryRepository.findLibraryById(query.getLibraryId());
        if(result.isPresent()){
            return FindLibraryResult.findByLibrary(result.get().toLibrary());
        }

        return libraryServiceFailedSequence();
    }


    @Override
    public List<FindLibraryResult> getLibraryListAll() {


        var libraryEntities = libraryRepository.findLibraryAll();
        if(libraryEntities.isEmpty()){
            List<FindLibraryResult> failed = new ArrayList<>();
            failed.add(libraryServiceFailedSequence());
            return failed;
        }

        var libraries = libraryEntities.stream()
                                        .map(LibraryEntity::toLibrary)
                                        .collect(Collectors.toList());

        return libraries.stream().map(FindLibraryResult::findByLibrary).collect(Collectors.toList());

    }



    @Override
    public FindLibraryResult updateLibrary(LibraryUpdateCommand command) {

        var newLibrary = Library.builder()
                .id(command.getId())
                .name(command.getName())
                .address(command.getAddress())
                .email(command.getEmail())
                .tel(command.getTel())
                .holiday(command.getHoliday())
                .operatingTime(command.getOperatingTime())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(command.getLendingAvailableCount())
                .lendingAvailableDays(command.getLendingAvailableDays())
                .overdueCount(command.getOverdueCount())
                .longtermOverdueDays(command.getLongtermOverdueDays())
                .lendingLimitDays(command.getLendingLimitDays())
                .build();

        var updatedLibraryEntity = libraryRepository.updateLibrary(new LibraryEntity(newLibrary));

        if(updatedLibraryEntity.isPresent()){
            return FindLibraryResult.findByLibrary(updatedLibraryEntity.get().toLibrary());
        }

        return libraryServiceFailedSequence();
    }

    @Override
    public LibraryFindQuery deleteLibrary(LibraryDeleteCommand command) {
        var deleteLibraryId = libraryRepository.deleteLibrary(command.getId());

        if(deleteLibraryId != null){
            LibraryFindQuery libraryFindQuery = new LibraryFindQuery(deleteLibraryId);
            return libraryFindQuery;
        }

        return new LibraryFindQuery(libraryServiceFailedSequence().getId());
    }



    private FindLibraryResult libraryServiceFailedSequence() {
        return FindLibraryResult.findByLibrary(Library.builder()
                .id(-1L)
                .build());
    }
}
