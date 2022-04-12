package com.cloudlibrary.library.infrastructure.feign.lending.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;




public interface LendingOperationUseCase {

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LendingUpdateCommand{
        //  Lending 에서 libraryRules update에 사용 할 객체
        private final Long libraryId;
        private final String libraryName;

        private final int lendingAvailableCount;
        private final int lendingAvailableDays;
        private final int overdueCount;
        private final int longtermOverdueDays;
        private final int lendingLimitDays;
    }


}
