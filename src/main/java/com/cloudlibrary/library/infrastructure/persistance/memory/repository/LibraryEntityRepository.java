package com.cloudlibrary.library.infrastructure.persistance.memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;

import java.util.List;
import java.util.Optional;

public interface LibraryEntityRepository {

    LibraryEntity create(LibraryOperationUseCase.LibraryCreatedCommand command);
    LibraryEntity update(LibraryOperationUseCase.LibraryUpdateCommand command);
    LibraryReadUseCase.LibraryFindQuery delete(LibraryOperationUseCase.LibraryDeleteCommand command);

    Optional<Library> findLibraryById(long libraryId);
    List<Library> findLibraryAll();


}
