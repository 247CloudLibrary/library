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
        Library library = Library.builder()
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

        LibraryEntity libraryEntity = new LibraryEntity(library);
        //when
        Optional<Library> library1 = libraryEntityRepository.saveLibrary(libraryEntity);

        //then
        assertThat(libraryEntityRepository.
                findLibraryById(libraryEntity.getId()).get().getId()).isEqualTo(library1.get().getId());

    }
}