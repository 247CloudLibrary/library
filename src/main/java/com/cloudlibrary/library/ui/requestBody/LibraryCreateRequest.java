package com.cloudlibrary.library.ui.requestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class LibraryCreateRequest {

    // library Info
    private Long id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private String holiday;
    private String operatingTime;


    // library rules
    private int lendingAvailableCount;
    private int lendingAvailableDays;
    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;
}
