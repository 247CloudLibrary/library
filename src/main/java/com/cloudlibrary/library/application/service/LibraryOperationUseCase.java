package com.cloudlibrary.library.application.service;


import com.cloudlibrary.library.infrastructure.persistance.mysql.entity.LibraryEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface LibraryOperationUseCase {

    LibraryReadUseCase.FindLibraryResult createLibrary(LibraryCreatedCommand command);
    LibraryReadUseCase.FindLibraryResult updateLibrary(LibraryUpdateCommand command);
    LibraryReadUseCase.LibraryFindQuery deleteLibrary(LibraryDeleteCommand command);

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryCreatedCommand{
        // library Info
        private final Long id;
        private final String name;
        private final String address;
        private final String email;
        private final String tel;
        private final String holiday;
        private final String operatingTime;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;


        // library rules
        private final int lendingAvailableCount;
        private final int lendingAvailableDays;
        private final int overdueCount;
        private final int longtermOverdueDays;
        private final int lendingLimitDays;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryUpdateCommand{
        // library Info
        private final Long id;
        private final String name;
        private final String address;
        private final String email;
        private final String tel;
        private final String holiday;
        private final String operatingTime;
        private final String loanAvailability;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;


        // library rules
        private final int lendingAvailableCount;
        private final int lendingAvailableDays;
        private final int overdueCount;
        private final int longtermOverdueDays;
        private final int lendingLimitDays;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryDeleteCommand{
        private final Long id;
    }





}
