package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;

import java.util.List;
import java.util.Optional;

public interface LibraryEntityRepository {

    Optional<LibraryEntity> findLibraryById(Long libraryId);
    List<LibraryEntity> findLibraryAll();
    Optional<LibraryEntity> saveLibrary(LibraryEntity libraryEntity);
    Optional<LibraryEntity> updateLibrary(LibraryEntity libraryEntity);
    Long deleteLibrary(Long libraryId);

}
