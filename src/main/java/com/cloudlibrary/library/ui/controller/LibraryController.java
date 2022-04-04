package com.cloudlibrary.library.ui.controller;


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


@Slf4j
@RestController
@Api(value = "도서관 API")
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryReadUseCase libraryReadUseCase;

    @Autowired
    public LibraryController(LibraryReadUseCase libraryReadUseCase) {
        this.libraryReadUseCase = libraryReadUseCase;
    }
    //TODO 도서관 등록

    //TODO 도서관 LIST 조회

    //TODO 특정 도서관 조회
    @GetMapping("/{id}")
    public ResponseEntity<LibraryView> getLibraries(@PathVariable("id") long id){
        return null;
    }

    //TODO 도서관 삭제

    //TODO 도서관 수정
}
