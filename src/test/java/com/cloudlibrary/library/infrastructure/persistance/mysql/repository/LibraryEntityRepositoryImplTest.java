package com.cloudlibrary.library.infrastructure.persistance.mysql.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
class LibraryEntityRepositoryImplTest {

    @Autowired
    LibraryEntityRepositoryImpl libraryEntityRepository;

    @Test
    @Transactional
    public void findByIdTest() throws Exception
    {
        //given
        Library library1 = Library.builder()
                .name("test 도서관")
                .address("test 도서관 주소")
                .email("test 도서관 이메일")
                .tel("02-1111-2222")
                .holiday("월 금")
                .operatingTime("07:00 ~ 18:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(4)
                .lendingAvailableDays(3)
                .overdueCount(2)
                .longtermOverdueDays(7)
                .lendingLimitDays(3)
                .build();

        Library library2 = Library.builder()
                .name("test 도서관2")
                .address("test 도서관 주소2")
                .email("test 도서관 이메일2")
                .tel("02-1111-33333")
                .holiday("월 목")
                .operatingTime("01:00 ~ 12:00")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(3)
                .lendingAvailableDays(2)
                .overdueCount(1)
                .longtermOverdueDays(6)
                .lendingLimitDays(2)
                .build();

        LibraryEntity libraryEntity1 = new LibraryEntity(library1);
        LibraryEntity libraryEntity2 = new LibraryEntity(library2);
        //when
        Optional<Library> libraryResult1 = libraryEntityRepository.saveLibrary(libraryEntity1);
        Optional<Library> libraryResult2 = libraryEntityRepository.saveLibrary(libraryEntity2);

        //then
        assertThat(libraryEntityRepository.
                findLibraryById(libraryEntity1.getId()).get().getId()).isEqualTo(libraryResult1.get().getId());
        assertThat(2L).isEqualTo(libraryResult2.get().getId());


    }
}