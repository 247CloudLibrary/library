package com.cloudlibrary.library.infrastructure.persistance.Memory.repository;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.infrastructure.persistance.Memory.Entity.LibraryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class MemoryEntityRepositoryTest {
    LibraryEntityRepository memoryEntityRepository = new MemoryEntityRepository();

    @Test
    public void createTest() {
        //given
        // id를 갖는 임의 도서관 도메인 생성
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(10)
                .name(10 + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();
        //when
        LibraryEntity result = memoryEntityRepository.create(command);

        //then
        assertThat(result.getId()).isEqualTo(10);


    }


}