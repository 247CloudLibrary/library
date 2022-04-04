package com.cloudlibrary.library.infrastructure.persistance.memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class MemoryEntityRepository implements LibraryEntityRepository{

    Map<Long, LibraryEntity> store = new HashMap<>();

    @Override
    public LibraryEntity create(LibraryOperationUseCase.LibraryCreatedCommand command) {

        var libraryDomain = Library.builder()
                .id(command.getId())
                .name(command.getName())
                .address(command.getAddress())
                .email(command.getEmail())
                .tel(command.getTel())
                .holiday(command.getHoliday())
                .operatingTime(command.getOperatingTime())
                .loanAvailability(command.getLoanAvailability())
                .createdAt(command.getCreatedAt())
                .updatedAt(command.getUpdatedAt())
                .lendingAvailableCount(command.getLendingAvailableCount())
                .lendingAvailableDays(command.getLendingAvailableDays())
                .overdueCount(command.getOverdueCount())
                .longtermOverdueDays(command.getLongtermOverdueDays())
                .lendingLimitDays(command.getLendingLimitDays())
                .build();

        LibraryEntity libraryEntity = new LibraryEntity(libraryDomain);
        store.put(command.getId(), libraryEntity);

        return libraryEntity;

    }

    @Override
    public Optional<Library> findLIbraryById(long libraryId) {

        LibraryEntity result = store.get(libraryId);
        return Optional.of(result.toLibrary());
    }

    @Override
    public List<Library> findLibraryAll() {

        ArrayList<Library> libraries = new ArrayList<>();

        for (Long id : store.keySet()) {
            libraries.add(store.get(id).toLibrary());
        }

        return libraries;
    }

    @Override
    public LibraryEntity update(LibraryOperationUseCase.LibraryUpdateCommand command) {

        // check command's libraryId whether it exist in memory
        Optional<Library> isExist = findLIbraryById(command.getId());
        if(isExist.isPresent()){
            var libraryDomain = Library.builder()
                    .id(command.getId())
                    .name(command.getName())
                    .address(command.getAddress())
                    .email(command.getEmail())
                    .tel(command.getTel())
                    .holiday(command.getHoliday())
                    .operatingTime(command.getOperatingTime())
                    .loanAvailability(command.getLoanAvailability())
                    .createdAt(isExist.get().getCreatedAt())
                    .updatedAt(command.getUpdatedAt())
                    .lendingAvailableCount(command.getLendingAvailableCount())
                    .lendingAvailableDays(command.getLendingAvailableDays())
                    .overdueCount(command.getOverdueCount())
                    .longtermOverdueDays(command.getLongtermOverdueDays())
                    .lendingLimitDays(command.getLendingLimitDays())
                    .build();

            LibraryEntity updatedLibraryEntity = new LibraryEntity(libraryDomain);
            store.replace(command.getId(), updatedLibraryEntity);
            return updatedLibraryEntity;
        }
        else{
            // error
            return null;
        }


    }

    @Override
    public LibraryReadUseCase.LibraryFindQuery delete(LibraryOperationUseCase.LibraryDeleteCommand command) {

        // check command's libraryId whether it exist in memory
        Optional<Library> isExist = findLIbraryById(command.getId());
        if(isExist.isPresent()){

            LibraryReadUseCase.LibraryFindQuery query = new LibraryReadUseCase.LibraryFindQuery(command.getId());
            store.remove(command.getId());
            return query;
        }
        else{

            return null;
        }



    }


}
