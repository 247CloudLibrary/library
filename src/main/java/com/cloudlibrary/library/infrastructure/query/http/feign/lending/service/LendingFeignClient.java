package com.cloudlibrary.library.infrastructure.query.http.feign.lending.service;

import com.cloudlibrary.library.infrastructure.query.http.feign.lending.configuration.FeignConfiguration;
import com.cloudlibrary.library.infrastructure.query.http.feign.lending.doc.FeignInfo;
import com.cloudlibrary.library.infrastructure.query.http.feign.lending.requestbody.LibraryRulesUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = FeignInfo.VALUE, url = FeignInfo.URI, configuration = FeignConfiguration.class)
@Component
public interface LendingFeignClient {

    @RequestMapping(value = FeignInfo.PATCH_LIBRARY_RULES_URI,
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createRules(@RequestBody LibraryRulesUpdateRequest request);

    @RequestMapping(value = FeignInfo.PATCH_WITHDRAW_LIBRARY_RULES_URI,
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void widthdrawRules(@RequestBody Long libraryId);
}
