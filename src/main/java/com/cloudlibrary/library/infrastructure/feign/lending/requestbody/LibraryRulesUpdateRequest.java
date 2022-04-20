package com.cloudlibrary.library.infrastructure.feign.lending.requestbody;


import lombok.*;

@Getter
@Builder
@ToString
public class LibraryRulesUpdateRequest {

//  Lending 에서 libraryRules update에 사용 할 객체
    private Long libraryId;
    private String libraryName;

    private int lendingAvailableCount;
    private int lendingAvailableDays;
    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;

}
