package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class LibraryEntityRepositoryImpl implements LibraryEntityRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<LibraryEntity> findLibraryById(Long libraryId) {

        LibraryEntity libraryEntity = entityManager.find(LibraryEntity.class, libraryId);

        // 못 찾은 경우 findLibraryEntity에는 null이 들어감
        return Optional.ofNullable(libraryEntity);

    }

    @Override
    public List<LibraryEntity> findLibraryAll() {

        List<LibraryEntity> libraryEntities = entityManager.createQuery("select libraries from LibraryEntity libraries", LibraryEntity.class)
                .getResultList();
        return libraryEntities;
    }

    @Override
    public Optional<LibraryEntity> saveLibrary(LibraryEntity libraryEntity) {

        entityManager.persist(libraryEntity);
        return Optional.of(libraryEntity);

    }

    @Override
    public Optional<LibraryEntity> updateLibrary(LibraryEntity libraryEntity) {

        // 업데이트 할 도서관 아이디로 조회
        Optional<LibraryEntity> findLibrary = findLibraryById(libraryEntity.getId());

        if(findLibrary.isPresent()){
            updateLibraryEntity(libraryEntity, findLibrary);
            entityManager.persist(findLibrary.get());
            return findLibrary;
        }

        // 조회 실패 했을경우 빈 객체 반환
        return Optional.empty();

    }


    @Override
    public Long deleteLibrary(Long libraryId) {

        // 삭제할 도서관 Entity 조회
        Optional<LibraryEntity> targetLibrary = findLibraryById(libraryId);
        if(targetLibrary.isPresent()){
            entityManager.remove(targetLibrary.get());
            return libraryId;
        }

        return null;
    }



    private void updateLibraryEntity(LibraryEntity libraryEntity, Optional<LibraryEntity> findLibrary) {
        findLibrary.get().setId(libraryEntity.getId());
        findLibrary.get().setName(libraryEntity.getName());
        findLibrary.get().setAddress(libraryEntity.getAddress());
        findLibrary.get().setEmail(libraryEntity.getEmail());
        findLibrary.get().setTel(libraryEntity.getTel());
        findLibrary.get().setHoliday(libraryEntity.getHoliday());
        findLibrary.get().setOperatingTime(libraryEntity.getOperatingTime());
        findLibrary.get().setCreatedAt(libraryEntity.getCreatedAt());
        findLibrary.get().setUpdatedAt(libraryEntity.getUpdatedAt());

        findLibrary.get().setLendingAvailableCount(libraryEntity.getLendingAvailableCount());
        findLibrary.get().setLendingAvailableDays(libraryEntity.getLendingAvailableDays());
        findLibrary.get().setOverdueCount(libraryEntity.getOverdueCount());
        findLibrary.get().setLongtermOverdueDays(libraryEntity.getLongtermOverdueDays());
        findLibrary.get().setLendingLimitsDays(libraryEntity.getLendingLimitsDays());
    }
}
