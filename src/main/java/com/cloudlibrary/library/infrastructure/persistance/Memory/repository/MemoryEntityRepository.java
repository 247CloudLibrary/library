package com.cloudlibrary.library.infrastructure.persistance.Memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.infrastructure.persistance.Memory.Entity.LibraryEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoryEntityRepository implements LibraryEntityRepository{

    Map<Long, LibraryEntity> store = new HashMap<>();

    @Override
    public LibraryEntity create(LibraryOperationUseCase.LibraryCreatedCommand command) {


        LibraryEntity libraryEntity = new LibraryEntity(command.getId(), command.getName(), command.getAddress(),
                command.getEmail(), command.getTel(), command.getHoliday());

        store.put(command.getId(), libraryEntity);

        return libraryEntity;

    }

    @Override
    public Optional<Library> findLIbraryById(long libraryId) {
        return Optional.empty();
    }

    @Override
    public List<Library> findLibraryAll() {
        return null;
    }
}
