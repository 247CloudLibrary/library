package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class LibraryEntityRepositoryImpl implements LibraryEntityRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Library> findLibraryById(Long libraryId) {

        LibraryEntity findLibraryEntity = entityManager.find(LibraryEntity.class, libraryId);

        // 못 찾은 경우 findLibraryEntity는 null이 들어감
        if(findLibraryEntity == null){
            return Optional.empty();
        }

        Library library = findLibraryEntity.toLibrary();
        return Optional.of(library);

    }

    @Override
    public List<Library> findLibraryAll() {
        return null;
    }

    @Override
    public Optional<Library> saveLibrary(LibraryEntity libraryEntity) {

        entityManager.persist(libraryEntity);
        return Optional.ofNullable(libraryEntity.toLibrary());
    }

    @Override
    public Optional<Library> updateLibrary(LibraryEntity libraryEntity) {
        return Optional.empty();
    }

    @Override
    public Long deleteLibrary(LibraryEntity libraryEntity) {
        return null;
    }
}
