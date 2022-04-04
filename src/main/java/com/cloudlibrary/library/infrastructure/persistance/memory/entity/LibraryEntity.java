package com.cloudlibrary.library.infrastructure.persistance.memory.entity;


import com.cloudlibrary.library.application.domain.Library;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LibraryEntity implements Serializable {
    // library Info
    private long id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private String holiday;
    private String operatingTime;
    private String loanAvailability;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    // library rules
    private int lendingAvailableCount;
    private int lendingAvailableDays;
    private int overdueCount;
    private int longtermOverdueDays;
    private int lendingLimitDays;

    public LibraryEntity(Library library) {
        this.id = library.getId();
        this.name = library.getName();
        this.address = library.getAddress();
        this.email = library.getEmail();
        this.tel = library.getTel();
        this.holiday = library.getHoliday();
        this.operatingTime = library.getOperatingTime();
        this.loanAvailability = library.getLoanAvailability();
        this.createdAt = library.getCreatedAt();
        this.updatedAt = library.getUpdatedAt();

        this.lendingAvailableCount = library.getLendingAvailableCount();
        this.lendingAvailableDays = library.getLendingAvailableDays();
        this.overdueCount = library.getOverdueCount();
        this.longtermOverdueDays = library.getLongtermOverdueDays();
        this.lendingLimitDays = library.getLendingLimitDays();
    }

    public Library toLibrary(){
        return Library.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .email(this.email)
                .tel(this.tel)
                .holiday(this.holiday)
                .operatingTime(this.operatingTime)
                .loanAvailability(this.loanAvailability)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .lendingAvailableCount(this.lendingAvailableCount)
                .lendingAvailableDays(this.lendingAvailableDays)
                .overdueCount(this.overdueCount)
                .longtermOverdueDays(this.longtermOverdueDays)
                .lendingLimitDays(this.lendingLimitDays)
                .build();
    }
}
