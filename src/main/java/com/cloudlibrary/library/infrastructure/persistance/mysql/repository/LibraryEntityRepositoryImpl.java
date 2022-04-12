package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibraryEntityRepositoryImpl implements LibraryEntityRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<LibraryEntity> findLibraryById(Long libraryId) {


        return Optional.empty();

    }

    @Override
    public List<LibraryEntity> findLibraryAll() {

        return null;
    }

    @Override
    public Optional<LibraryEntity> saveLibrary(LibraryEntity libraryEntity) {

        return Optional.empty();

    }

    @Override
    public Optional<LibraryEntity> updateLibrary(LibraryEntity libraryEntity) {


        return Optional.empty();

    }

    @Override
    public Long deleteLibrary(LibraryEntity libraryEntity) {


        return null;
    }
}
