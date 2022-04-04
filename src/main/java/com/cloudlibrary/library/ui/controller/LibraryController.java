package com.cloudlibrary.library.ui.controller;


import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.ui.view.library.LibraryView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@Api(value = "도서관 API")
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryReadUseCase libraryReadUseCase;
    private final LibraryOperationUseCase libraryOperationUseCase;

    @Autowired
    public LibraryController(LibraryReadUseCase libraryReadUseCase, LibraryOperationUseCase libraryOperationUseCase) {
        this.libraryReadUseCase = libraryReadUseCase;
        this.libraryOperationUseCase = libraryOperationUseCase;
    }
    //TODO 도서관 등록

    //TODO 도서관 LIST 조회
    @GetMapping("")
    public ResponseEntity<List<LibraryView>> getLibraries(){

        return null;
    }

    //TODO 특정 도서관 조회
    @GetMapping("/{id}")
    public ResponseEntity<LibraryView> getLibraries(@PathVariable("id") long id){

        // id를 갖는 임의 도서관 도메인 생성
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(id)
                .name(id + " 테스트 생성된 도서관")
                .address("서울시 강서구 공항동 444-3")
                .email("test@google.com")
                .tel("02-1111-2222")
                .holiday("월")
                .build();

        var result = libraryOperationUseCase.createLibrary(command);
        return ResponseEntity.ok(new LibraryView(result));

    }

    //TODO 도서관 삭제

    //TODO 도서관 수정
}
