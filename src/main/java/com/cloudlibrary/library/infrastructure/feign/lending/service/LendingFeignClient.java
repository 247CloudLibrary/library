package com.cloudlibrary.library.infrastructure.feign.lending.service;

import com.cloudlibrary.library.infrastructure.feign.lending.doc.FeignInfo;
import com.cloudlibrary.library.infrastructure.feign.lending.requestbody.LibraryRulesUpdateRequest;
import com.cloudlibrary.library.infrastructure.feign.lending.responsebody.LibraryRulesUpdateResponse;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = FeignInfo.VALUE, url = FeignInfo.URI)
public interface LendingFeignClient {

    @PatchMapping(FeignInfo.PATCH_LIBRARY_RULES_URI)
    void createRules(@RequestBody LibraryRulesUpdateRequest request);

    @PatchMapping(FeignInfo.PATCH_WITHDRAW_LIBRARY_RULES_URI)
    void widthdrawRules(@RequestBody Long libraryId);
}
