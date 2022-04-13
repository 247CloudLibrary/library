package com.cloudlibrary.library.ui.controller;


import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.exception.CloudLibraryException;
import com.cloudlibrary.library.exception.MessageType;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import com.cloudlibrary.library.ui.requestBody.LibraryCreateRequest;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import com.cloudlibrary.library.ui.view.ApiResponseView;
import com.cloudlibrary.library.ui.view.library.LibraryView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "도서관 API")
@RequestMapping(value = "/v1/libraries")
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
    public ResponseEntity<ApiResponseView<LibraryView>> createLibrary(@RequestBody LibraryCreateRequest request){
        if(ObjectUtils.isEmpty(request)){
            // TODO 예외 처리
            throw new CloudLibraryException(MessageType.BAD_REQUEST);
        }

        var command = LibraryOperationUseCase.LibraryCreatedCommand.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .holiday(request.getHoliday())
                .operatingTime(request.getOperatingTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(request.getLendingAvailableCount())
                .lendingAvailableDays(request.getLendingAvailableDays())
                .overdueCount(request.getOverdueCount())
                .longtermOverdueDays(request.getLongtermOverdueDays())
                .lendingLimitDays(request.getLendingLimitDays())
                .build();

        var result = libraryOperationUseCase.createLibrary(command);
        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(result)));

    }

    //TODO 도서관 LIST 조회
    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LibraryView>>> getLibraries(){

        List<LibraryView> libraryViews = new ArrayList<>();

        for (Long i=1L; i <= 10L; ++i){
            Library library = Library.builder()
                    .id(i)
                    .name("테스트 도서관 입니다. " + i)
                    .address("테스트 도서관 주소 입니다.")
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
            libraryViews.add(new LibraryView(LibraryReadUseCase.FindLibraryResult.findByLibrary(library)));
        }


        return ResponseEntity.ok(new ApiResponseView<>(libraryViews));
    }

    //TODO 특정 도서관 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> getLibraries(@PathVariable("id") Long id){

        Library library = Library.builder()
                .id(id)
                .name("테스트 도서관 입니다." + id)
                .address("테스트 도서관 주소 입니다.")
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

        return ResponseEntity.ok(new ApiResponseView<>
                (new LibraryView(LibraryReadUseCase.FindLibraryResult.findByLibrary(library))));

    }

    //TODO 도서관 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> deleteLibrary(@PathVariable("id") Long id){
        var command = LibraryOperationUseCase.LibraryDeleteCommand.builder()
                .id(id)
                .build();

        LibraryReadUseCase.LibraryFindQuery libraryFindQuery = libraryOperationUseCase.deleteLibrary(command);
        var result = LibraryReadUseCase.FindLibraryResult.builder()
                .id(libraryFindQuery.getLibraryId())
                .build();

        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(result)));

    }

    //TODO 도서관 수정
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> updateLibrary(@RequestBody LibraryUpdateRequest request, @PathVariable("id") Long id){

        var responseBody = LibraryReadUseCase.FindLibraryResult.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .tel(request.getTel())
                .holiday(request.getHoliday())
                .operatingTime(request.getOperatingTime())
                .updatedAt(LocalDateTime.now())
                .lendingAvailableCount(request.getLendingAvailableCount())
                .lendingAvailableDays(request.getLendingAvailableDays())
                .overdueCount(request.getOverdueCount())
                .longtermOverdueDays(request.getLongtermOverdueDays())
                .lendingLimitDays(request.getLendingLimitDays())
                .build();

//        LibraryEntity libraryEntity = libraryOperationUseCase.updateLibrary(command);
//        var query = new LibraryReadUseCase.LibraryFindQuery(id);
//        LibraryReadUseCase.FindLibraryResult result = libraryReadUseCase.getLibrary(query);
        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(responseBody)));
    }
}
