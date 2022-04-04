package com.cloudlibrary.library.infrastructure.persistance.Memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;

import java.util.List;
import java.util.Optional;

public interface LibraryEntityRepository {

    LibraryReadUseCase.FindLibraryResult create(Library library);
    Optional<Library> findLIbraryById(long libraryId);
    List<Library> findLibraryAll();


}
