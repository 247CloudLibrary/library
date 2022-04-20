package com.cloudlibrary.library.infrastructure.feign.lending.responsebody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class LibraryRulesUpdateResponse {

    private Long libraryId;
    private String libraryName;

    private int lendingAvailableCount;
    private int lendingAvailableDays;
    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;
}
