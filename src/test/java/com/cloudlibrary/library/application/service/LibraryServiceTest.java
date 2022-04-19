package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.infrastructure.persistance.mysql.repository.LibraryEntityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LibraryServiceTest {

    @Autowired
    LibraryService libraryService;

    @Test
    @Order(1)
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
    }

    @Test
    @Order(2)
    public void findLibraryByIdTest() throws Exception
    {
        //given
        var command1 = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(1L)
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
        //when
         var query = new LibraryReadUseCase.LibraryFindQuery(1L);

        //then
        assertThat(libraryService.getLibrary(query).getId()).isEqualTo(1L);
    }
    
    @Test
    public void getLibraryAllTest() throws Exception
    {
        //given
        
        //when
        var libraryListAll = libraryService.getLibraryListAll();
        //then
        for (LibraryReadUseCase.FindLibraryResult findLibraryResult : libraryListAll) {
            System.out.println("findLibraryResult.toString() = " + findLibraryResult.toString());
        }
    }

    @Test
    public void updateLibraryTest() throws Exception
    {
        //given
        var command1 = LibraryOperationUseCase.LibraryUpdateCommand.builder()
                .id(1L)
                .name("updated name")
                .address("updated address")
                .email("request.getEmail()")
                .tel("request.getTel()")
                .holiday("request.getHoliday()")
                .operatingTime("request.getOperatingTime()")
                .lendingAvailableCount(8)
                .lendingAvailableDays(4)
                .overdueCount(3)
                .longtermOverdueDays(2)
                .lendingLimitDays(1)
                .build();
        //when
        libraryService.updateLibrary(command1);
        //then
        assertThat(libraryService.getLibrary(new LibraryReadUseCase.LibraryFindQuery(1L)).getName()).isEqualTo("updated name");
    }

    @Test
    @Order(4)
    public void deleteLibraryTest() throws Exception
    {
        //given
        var command = LibraryOperationUseCase.LibraryDeleteCommand.builder()
                .id(2L)
                .build();
        //when
        libraryService.deleteLibrary(command);
        //then
        var result = libraryService.getLibraryListAll();
        for (LibraryReadUseCase.FindLibraryResult findLibraryResult : result) {
            System.out.println("findLibraryResult = " + findLibraryResult);
        }
    }
}