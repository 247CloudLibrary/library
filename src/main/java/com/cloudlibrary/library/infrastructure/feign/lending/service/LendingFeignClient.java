package com.cloudlibrary.library.infrastructure.feign.lending.service;

import com.cloudlibrary.library.infrastructure.feign.lending.responsebody.LibraryRulesUpdateResponse;
import com.cloudlibrary.library.ui.requestBody.LibraryUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "lending-service", url = "ecs-alb-167470959.us-east-1.elb.amazonaws.com/v1")
public interface LendingFeignClient {

    @PatchMapping("/lending/libraries-rules")
    LibraryRulesUpdateResponse createRules(@RequestBody LibraryUpdateRequest request);


}
