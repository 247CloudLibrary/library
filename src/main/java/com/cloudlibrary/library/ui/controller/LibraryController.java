package com.cloudlibrary.library.ui.controller;


import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.application.service.LibraryOperationUseCase;
import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.cloudlibrary.library.exception.CloudLibraryException;
import com.cloudlibrary.library.exception.MessageType;

import com.cloudlibrary.library.infrastructure.feign.lending.requestbody.LibraryRulesUpdateRequest;
import com.cloudlibrary.library.infrastructure.feign.lending.responsebody.LibraryRulesUpdateResponse;
import com.cloudlibrary.library.infrastructure.feign.lending.service.FeignLendingService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value = "도서관 API")
@RequestMapping(value = "/v1/libraries")
public class LibraryController {

    private final LibraryReadUseCase libraryReadUseCase;
    private final LibraryOperationUseCase libraryOperationUseCase;
    private final FeignLendingService feignLendingService;

    @Autowired
    public LibraryController(LibraryReadUseCase libraryReadUseCase,
                             LibraryOperationUseCase libraryOperationUseCase,
                             FeignLendingService feignLendingService) {
        this.libraryReadUseCase = libraryReadUseCase;
        this.libraryOperationUseCase = libraryOperationUseCase;
        this.feignLendingService = feignLendingService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("health-check");
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseView<LibraryView>> createLibrary(@RequestBody LibraryCreateRequest request){

        if(ObjectUtils.isEmpty(request)){
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

        if(result.getId() == -1){
            throw new CloudLibraryException(MessageType.INTERNAL_SERVER_ERROR);
        }

        var libraryRules = LibraryRulesUpdateRequest.builder()
                .libraryId(request.getId())
                .libraryName(request.getName())
                .lendingAvailableCount(request.getLendingAvailableCount())
                .lendingAvailableDays(request.getLendingAvailableDays())
                .overdueCount(request.getOverdueCount())
                .longtermOverdueDays(request.getLongtermOverdueDays())
                .lendingLimitDays(request.getLendingLimitDays())
                .build();

        feignLendingService.patchLibraryRules(libraryRules);

        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(result)));

    }
    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<LibraryView>>> getLibraries(){

        List<LibraryReadUseCase.FindLibraryResult> libraries = libraryReadUseCase.getLibraryListAll();

        if(libraries == null) {
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }
     

        List<LibraryView> libraryViews = libraries.stream().map(LibraryView::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponseView<>(libraryViews));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> getLibraries(@PathVariable("id") Long id){


        LibraryReadUseCase.LibraryFindQuery command = new LibraryReadUseCase.LibraryFindQuery(id);

        LibraryReadUseCase.FindLibraryResult result = libraryReadUseCase.getLibrary(command);

        if(result.getId() == -1){
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }


        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(result)));

    }

    //TODO 도서관 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> deleteLibrary(@PathVariable("id") Long id){
        var command = LibraryOperationUseCase.LibraryDeleteCommand.builder()
                .id(id)
                .build();

        LibraryReadUseCase.LibraryFindQuery libraryFindQuery = libraryOperationUseCase.deleteLibrary(command);

        if(libraryFindQuery.getLibraryId() == -1){
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }

        var result = LibraryReadUseCase.FindLibraryResult.builder()
                .id(libraryFindQuery.getLibraryId())
                .build();

        feignLendingService.withDrawLibraryRules(id);

        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(result)));
    }

    //TODO 도서관 수정
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseView<LibraryView>> updateLibrary(@RequestBody LibraryUpdateRequest request, @PathVariable("id") Long id){

        var updateLibrary = LibraryOperationUseCase.LibraryUpdateCommand.builder()
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

        LibraryReadUseCase.FindLibraryResult updatedLibrary = libraryOperationUseCase.updateLibrary(updateLibrary);
        if(updatedLibrary.getId() == -1){
            throw new CloudLibraryException(MessageType.INTERNAL_SERVER_ERROR);
        }

        var libraryRules = LibraryRulesUpdateRequest.builder()
                .libraryId(request.getId())
                .libraryName(request.getName())
                .lendingAvailableCount(request.getLendingAvailableCount())
                .lendingAvailableDays(request.getLendingAvailableDays())
                .overdueCount(request.getOverdueCount())
                .longtermOverdueDays(request.getLongtermOverdueDays())
                .lendingLimitDays(request.getLendingLimitDays())
                .build();

        feignLendingService.patchLibraryRules(libraryRules);

        return ResponseEntity.ok(new ApiResponseView<>(new LibraryView(updatedLibrary)));
    }

}
