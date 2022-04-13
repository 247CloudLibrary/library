package com.cloudlibrary.library.application.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LibraryServiceTest {

    @Autowired LibraryService libraryService;


    @Test
    @Order(1)
    @Transactional
    @DisplayName("도서관 등록 서비스 테스트")
    public void createLibraryTest() throws Exception
    {
        //given
        var command1 = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .name("request.getName()")
                .address("request.getAddress()")
                .email("request.getEmail()")
                .tel("request.getTel()")
                .holiday("request.getHoliday()")
                .operatingTime("request.getOperatingTime()")
                .lendingAvailableCount(1)
                .lendingAvailableDays(2)
                .overdueCount(3)
                .longtermOverdueDays(4)
                .lendingLimitDays(5)
                .build();
        var command2 = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .name("request.getName() 2")
                .address("request.getAddress() 2")
                .email("request.getEmail() 2")
                .tel("request.getTel() 2")
                .holiday("request.getHoliday() 2")
                .operatingTime("request.getOperatingTime() 2")
                .lendingAvailableCount(2)
                .lendingAvailableDays(3)
                .overdueCount(4)
                .longtermOverdueDays(5)
                .lendingLimitDays(6)
                .build();
        //when
        LibraryReadUseCase.FindLibraryResult library1 = libraryService.createLibrary(command1);
        LibraryReadUseCase.FindLibraryResult library2 = libraryService.createLibrary(command2);
        //then
        assertThat(library1.getId()).isEqualTo(1L);

    }

    @Test
    @Order(2)
    @Transactional
    @DisplayName("특정 도서관 조회 테스트")
    public void getLibraryTest() throws Exception
    {
        //given

        //when
        LibraryReadUseCase.FindLibraryResult foundLibrary = libraryService.getLibrary(new LibraryReadUseCase.LibraryFindQuery(1L));

        //then
        assertThat(foundLibrary.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @DisplayName("등록되어 있는 모든 도서관 조회 테스트")
    public void getLibraryListAllTest() throws Exception
    {
        //given

        //when
        List<LibraryReadUseCase.FindLibraryResult> libraryListAll = libraryService.getLibraryListAll();

        //then
        assertThat(libraryListAll.get(0).getId()).isEqualTo(1L);
        assertThat(libraryListAll.get(1).getId()).isEqualTo(2L);
        for (LibraryReadUseCase.FindLibraryResult findLibraryResult : libraryListAll) {
            System.out.println(findLibraryResult.toString());
        }
    }

    @Test
    @Order(4)
    @Transactional
    @DisplayName("도서관 정보 수정 서비스 테스트")
    public void updateLibraryTest() throws Exception
    {
        //given
        var command1 = LibraryOperationUseCase.LibraryUpdateCommand.builder()
                .id(1L)
                .name("수정된 도서관 이름")
                .address("수정된 도서관 주소")
                .email("수정된 도서관 이메일")
                .tel("수정된 도서관 전화번호")
                .holiday("request.getHoliday()")
                .operatingTime("request.getOperatingTime()")
                .lendingAvailableCount(3)
                .lendingAvailableDays(4)
                .overdueCount(5)
                .longtermOverdueDays(6)
                .lendingLimitDays(7)
                .build();
        //when

        LibraryReadUseCase.FindLibraryResult findLibraryResult = libraryService.updateLibrary(command1);
        LibraryReadUseCase.FindLibraryResult compare = libraryService.getLibrary(new LibraryReadUseCase.LibraryFindQuery(1L));
        //then
        assertThat(compare.toString()).isEqualTo(findLibraryResult.toString());

    }

    @Test
    @Order(5)
    @Transactional
    @DisplayName("도서관 삭제 서비스 테스트")
    public void deleteLibraryTest() throws Exception
    {
        //given
        var command = LibraryOperationUseCase.LibraryDeleteCommand.builder()
                .id(1L)
                .build();
        //when
        LibraryReadUseCase.FindLibraryResult beforeDelete = libraryService.getLibrary(new LibraryReadUseCase.LibraryFindQuery(1L));
        LibraryReadUseCase.LibraryFindQuery deletedLibraryId = libraryService.deleteLibrary(command);
        //then
        assertThat(beforeDelete.toString()).isNotEqualTo(libraryService.getLibrary(new LibraryReadUseCase.LibraryFindQuery(1L)).toString());
    }
}