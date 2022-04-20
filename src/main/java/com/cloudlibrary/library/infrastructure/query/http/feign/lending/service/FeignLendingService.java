package com.cloudlibrary.library.infrastructure.query.http.feign.lending.service;

import com.cloudlibrary.library.infrastructure.query.http.feign.lending.requestbody.LibraryRulesUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignLendingService {

    @Autowired LendingFeignClient lendingFeignClient;

    public void patchLibraryRules(LibraryRulesUpdateRequest request) {
        lendingFeignClient.createRules(request);
    }

    public void withDrawLibraryRules(Long libraryId){
        lendingFeignClient.widthdrawRules(libraryId);
    }


}
