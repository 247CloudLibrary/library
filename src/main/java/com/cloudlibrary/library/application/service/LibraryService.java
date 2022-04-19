package com.cloudlibrary.library.application.service;


import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import com.cloudlibrary.library.infrastructure.persistance.mysql.repository.LibraryEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        var library = Library.builder()
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

        var libraryEntity = new LibraryEntity(library);
        var result = libraryRepository.save(libraryEntity);


        return FindLibraryResult.findByLibrary(result.toLibrary());
    }

    @Override
    public FindLibraryResult getLibrary(LibraryFindQuery query) {

        var libraryEntity = libraryRepository.findById(query.getLibraryId());

        if(libraryEntity.isPresent()){
            return FindLibraryResult.findByLibrary(libraryEntity.get().toLibrary());
        }

        
        return notFoundLibrary();
    }



    @Override
    public List<FindLibraryResult> getLibraryListAll() {

        var libraryEntities = libraryRepository.findAll();

        var libraries = StreamSupport.stream(libraryEntities.spliterator(), false)
                .map(LibraryEntity::toLibrary)
                .collect(Collectors.toList());

        if (libraries.isEmpty()) {
            return null;
        }
        
        return libraries.stream().map(FindLibraryResult::findByLibrary).collect(Collectors.toList());
        
    }

    @Override
    public FindLibraryResult updateLibrary(LibraryUpdateCommand command) {


        var oldLibrary = libraryRepository.findById(command.getId());
        if(oldLibrary.isPresent()){
            var newLibrary = Library.builder()
                    .id(oldLibrary.get().getId())
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

            var result = libraryRepository.save(new LibraryEntity(newLibrary));

            return FindLibraryResult.findByLibrary(result.toLibrary());

        }


        return notFoundLibrary();
    }

    @Override
    public LibraryFindQuery deleteLibrary(LibraryDeleteCommand command) {

        var query = libraryRepository.findById(command.getId());

        if(query.isPresent()){
            var libraryFindQuery = new LibraryFindQuery(query.get().getId());
            libraryRepository.delete(query.get());

            return libraryFindQuery;
        }


        return new LibraryFindQuery(-1L);
    }


    private FindLibraryResult notFoundLibrary() {
        return FindLibraryResult.findByLibrary(Library.builder()
                .id(-1L)
                .build());
    }

}
