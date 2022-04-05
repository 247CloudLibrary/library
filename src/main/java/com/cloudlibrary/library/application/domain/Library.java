package com.cloudlibrary.library.application.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Library {

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
