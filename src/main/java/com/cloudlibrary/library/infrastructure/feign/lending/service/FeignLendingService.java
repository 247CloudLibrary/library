package com.cloudlibrary.library.infrastructure.feign.lending.service;

import com.cloudlibrary.library.infrastructure.feign.lending.responsebody.LibraryRulesUpdateResponse;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class FeignLendingService {

    @Autowired LendingFeignClient lendingFeignClient;

    public LibraryRulesUpdateResponse patchLibraryRules(LibraryUpdateRequest request) {

        LibraryRulesUpdateResponse updatedRules = lendingFeignClient.createRules(request);


        return updatedRules;
    }


}
