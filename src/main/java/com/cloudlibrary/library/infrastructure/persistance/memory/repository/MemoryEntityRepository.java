package com.cloudlibrary.library.infrastructure.persistance.memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import org.springframework.stereotype.Component;

import java.util.*;

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
}
