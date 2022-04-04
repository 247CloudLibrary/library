package com.cloudlibrary.library.infrastructure.persistance.memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class MemoryEntityRepositoryTest {
    LibraryEntityRepository memoryEntityRepository = new MemoryEntityRepository();


    @Test
    public void createTest() {
        //given
        // id를 갖는 임의 도서관 도메인 생성
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(11)
                .name(10 + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();
        //when
        LibraryEntity result = memoryEntityRepository.create(command);

        //then
        assertThat(result.getId()).isEqualTo(11);

    }

    @Test
    public void findLibraryByIdTest(){

        // id를 갖는 임의 도서관 도메인 생성
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(13)
                .name(13 + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();
        //when
        LibraryEntity result = memoryEntityRepository.create(command);

        Optional<Library> find = memoryEntityRepository.findLIbraryById(13);
        assertThat(find.get().toString()).isEqualTo(result.toLibrary().toString());

    }

    @Test
    public void getLibraryListAll(){

        // given
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(13)
                .name(13 + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();
        //when
        LibraryEntity result = memoryEntityRepository.create(command);
        var command2 = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(15)
                .name(15 + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();
        //when
        LibraryEntity result2 = memoryEntityRepository.create(command2);
        // when
        List<Library> libraries = memoryEntityRepository.findLibraryAll();


        // then
        System.out.println("libraries = " + libraries);
    }

}