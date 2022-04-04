package com.cloudlibrary.library.infrastructure.persistance.Memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.infrastructure.persistance.Memory.Entity.LibraryEntity;

import java.util.List;
import java.util.Optional;

public interface LibraryEntityRepository {

    LibraryEntity create(LibraryOperationUseCase.LibraryCreatedCommand command);
    Optional<Library> findLIbraryById(long libraryId);
    List<Library> findLibraryAll();


}
