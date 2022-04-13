package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryEntityRepositoryImplTest {

    @Autowired LibraryEntityRepositoryImpl libraryEntityRepository;

    @Test
    @Transactional
    void findLibraryById() {
        //given
        Library library = Library.builder()
                .name("테스트 도서관 ")
                .address("테스트 도서관 주소")
                .email("테스트 도서관 이메일")
                .tel("02-1111-2222")
                .holiday("월 수")
                .operatingTime("09:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();
        LibraryEntity libraryEntity = new LibraryEntity(library);
        //when
        Optional<LibraryEntity> result = libraryEntityRepository.saveLibrary(libraryEntity);
        //then
        assertThat(libraryEntityRepository.findLibraryById(1L).get()).isEqualTo(libraryEntity);

    }

    @Test
    @Transactional
    void findLibraryAll() {
        //given
        Library library1 = Library.builder()
                .name("테스트 도서관1")
                .address("테스트 도서관 주소1")
                .email("테스트 도서관 이메일1")
                .tel("02-1111-2222")
                .holiday("월 수")
                .operatingTime("09:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();

        Library library2 = Library.builder()
                .name("테스트 도서관2")
                .address("테스트 도서관 주소2")
                .email("테스트 도서관 이메일2")
                .tel("02-1111-2222")
                .holiday("목")
                .operatingTime("06:00 ~ 13:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(5)
                .lendingAvailableDays(10)
                .overdueCount(15)
                .longtermOverdueDays(20)
                .lendingLimitDays(25)
                .build();

        LibraryEntity libraryEntity1 = new LibraryEntity(library1);
        LibraryEntity libraryEntity2 = new LibraryEntity(library2);
        //when
        libraryEntityRepository.saveLibrary(libraryEntity1);
        libraryEntityRepository.saveLibrary(libraryEntity2);
        //then
        List<LibraryEntity> libraryAll = libraryEntityRepository.findLibraryAll();
        for (LibraryEntity libraryEntity : libraryAll) {
            System.out.println(libraryEntity.toString());
        }

    }

    @Test
    @Transactional
    void saveLibrary() {
        //given
        Library library = Library.builder()
                .name("테스트 도서관 ")
                .address("테스트 도서관 주소")
                .email("테스트 도서관 이메일")
                .tel("02-1111-2222")
                .holiday("월 수")
                .operatingTime("09:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();
        LibraryEntity libraryEntity = new LibraryEntity(library);
        //when
        Optional<LibraryEntity> result = libraryEntityRepository.saveLibrary(libraryEntity);
        //then
        assertThat(libraryEntityRepository.findLibraryById(1L).get().toString()).
                isEqualTo(result.get().toString());
    }

    @Test
    @Transactional
    void updateLibrary() {
        //given
        Library library = Library.builder()
                .name("테스트 도서관 ")
                .address("테스트 도서관 주소")
                .email("테스트 도서관 이메일")
                .tel("02-1111-2222")
                .holiday("월 수")
                .operatingTime("09:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();
        LibraryEntity libraryEntity = new LibraryEntity(library);
        libraryEntityRepository.saveLibrary(libraryEntity);
        //when
        Library updatedLibrary = Library.builder()
                .id(1L)
                .name("테스트 수정된 도서관 ")
                .address("테스트 수정된 도서관 주소")
                .email("테스트 수정된 도서관 이메일")
                .tel("02-2222-2222")
                .holiday("월 금")
                .operatingTime("01:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();

        LibraryEntity updatedLibraryEntity = new LibraryEntity(updatedLibrary);
        Optional<LibraryEntity> libraryEntity1 = libraryEntityRepository.
                updateLibrary(updatedLibraryEntity);
        //then
        assertThat(libraryEntityRepository.findLibraryById(1L).get().toLibrary().toString()).
                isEqualTo(updatedLibrary.toString());

    }

    @Test
    @Transactional
    void deleteLibrary() {
        //given
        Library library = Library.builder()
                .name("테스트 도서관 ")
                .address("테스트 도서관 주소")
                .email("테스트 도서관 이메일")
                .tel("02-1111-2222")
                .holiday("월 수")
                .operatingTime("09:00 ~ 17:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(10)
                .lendingAvailableDays(20)
                .overdueCount(30)
                .longtermOverdueDays(40)
                .lendingLimitDays(50)
                .build();
        LibraryEntity libraryEntity = new LibraryEntity(library);
        libraryEntityRepository.saveLibrary(libraryEntity);
        //when
        libraryEntityRepository.deleteLibrary(1L);
        //then
        assertThat(libraryEntityRepository.findLibraryById(1L)).isEqualTo(Optional.empty());
    }
}