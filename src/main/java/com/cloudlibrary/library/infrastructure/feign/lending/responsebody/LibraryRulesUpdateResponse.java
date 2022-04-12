package com.cloudlibrary.library.infrastructure.feign.lending.responsebody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class LibraryRulesUpdateResponse {
    @ToString
    @Getter
    @NoArgsConstructor
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
}
