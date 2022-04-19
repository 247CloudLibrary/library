package com.cloudlibrary.library.infrastructure.feign.lending.service;

import com.cloudlibrary.library.infrastructure.feign.lending.responsebody.LibraryRulesUpdateResponse;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class FeignLendingService {

    @Autowired LendingFeignClient lendingFeignClient;

    public LibraryRulesUpdateResponse patchLibraryRules(LibraryUpdateRequest request) {

        LibraryRulesUpdateResponse updatedRules = lendingFeignClient.createRules(request);

        // TODO: 2022/04/12 예외처리 상황 생각해 보기
        return updatedRules;
    }


}
