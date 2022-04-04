package com.cloudlibrary.library.ui.controller;


import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import com.cloudlibrary.library.ui.requestBody.LibraryCreateRequest;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import com.cloudlibrary.library.ui.view.library.LibraryView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PostMapping("")
    public ResponseEntity<LibraryView> createLibrary(@RequestBody LibraryCreateRequest request){
        if(ObjectUtils.isEmpty(request)){
            // TODO 예외 처리
        }
        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .holiday(request.getHoliday())
                .build();

        var result = libraryOperationUseCase.createLibrary(command);
        return ResponseEntity.ok(new LibraryView(result));

    }

    //TODO 도서관 LIST 조회
    @GetMapping("")
    public ResponseEntity<List<LibraryView>> getLibraries(){


        List<LibraryReadUseCase.FindLibraryResult> libraries = libraryReadUseCase.getLibraryListAll();
        ArrayList<LibraryView> libraryViews = new ArrayList<>();
        for (LibraryReadUseCase.FindLibraryResult library : libraries) {
            libraryViews.add(new LibraryView(library));
        }
        return ResponseEntity.ok(libraryViews);
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

        var created = libraryOperationUseCase.createLibrary(command);

        // 임시 생성된 도서관을 단일 조회
        LibraryReadUseCase.LibraryFindQuery query = new LibraryReadUseCase.LibraryFindQuery(id);
        LibraryReadUseCase.FindLibraryResult result = libraryReadUseCase.getLibrary(query);

        return ResponseEntity.ok(new LibraryView(result));

    }

    //TODO 도서관 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<LibraryView> deleteLibrary(@PathVariable("id") long id){
        var command = LibraryOperationUseCase.LibraryDeleteCommand.builder()
                .id(id)
                .build();

        LibraryReadUseCase.LibraryFindQuery libraryFindQuery = libraryOperationUseCase.deleteLibrary(command);
        var result = LibraryReadUseCase.FindLibraryResult.builder()
                .id(libraryFindQuery.getLibraryId())
                .build();

        return ResponseEntity.ok(new LibraryView(result));

    }
    //TODO 도서관 수정
    @PutMapping("/{id}")
    public ResponseEntity<LibraryView> updateLibrary(@RequestBody LibraryUpdateRequest request, @PathVariable("id") long id){

        var command = LibraryOperationUseCase.LibraryUpdateCommand.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .holiday(request.getHoliday())
                .build();

        LibraryEntity libraryEntity = libraryOperationUseCase.updateLibrary(command);
        var query = new LibraryReadUseCase.LibraryFindQuery(id);
        LibraryReadUseCase.FindLibraryResult result = libraryReadUseCase.getLibrary(query);
        return ResponseEntity.ok(new LibraryView(result));
    }
}
