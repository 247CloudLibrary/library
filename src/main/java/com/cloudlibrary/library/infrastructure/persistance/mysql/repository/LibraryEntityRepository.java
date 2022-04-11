package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;

import java.util.List;
import java.util.Optional;

public interface LibraryEntityRepository {

    Optional<Library> findLibraryById(Long libraryId);
    List<Library> findLibraryAll();
    Optional<Library> saveLibrary(LibraryEntity libraryEntity);
    Optional<Library> updateLibrary(LibraryEntity libraryEntity);
    Long deleteLibrary(LibraryEntity libraryEntity);

}
