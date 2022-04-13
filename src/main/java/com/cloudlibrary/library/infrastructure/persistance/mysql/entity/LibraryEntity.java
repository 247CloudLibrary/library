package com.cloudlibrary.library.infrastructure.persistance.mysql.entity;

import com.cloudlibrary.library.application.domain.Library;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "library")
public class LibraryEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private String holiday;
    private String  operatingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(name = "lending_available_count")
    private int lendingAvailableCount;
    @Column(name = "lending_available_days")
    private int lendingAvailableDays;
    @Column(name = "overdue_count")
    private int overdueCount;
    @Column(name = "longterm_overdue_days")
    private int longtermOverdueDays;
    @Column(name = "lending_limit_days")
    private int lendingLimitsDays;


    public Library toLibrary(){
        return Library.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .email(this.email)
                .tel(this.tel)
                .holiday(this.holiday)
                .operatingTime(this.operatingTime)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .lendingAvailableCount(this.lendingAvailableCount)
                .lendingAvailableDays(this.lendingAvailableDays)
                .overdueCount(this.overdueCount)
                .longtermOverdueDays(this.longtermOverdueDays)
                .lendingLimitDays(this.lendingLimitsDays)
                .build();
    }

    public LibraryEntity(Library library){
        this.id = library.getId();
        this.name = library.getName();
        this.address = library.getAddress();
        this.email = library.getEmail();
        this.tel = library.getTel();
        this.holiday = library.getHoliday();
        this.operatingTime = library.getOperatingTime();
        this.createdAt = library.getCreatedAt();
        this.updatedAt = library.getUpdatedAt();

        this.lendingAvailableCount = library.getLendingAvailableCount();
        this.lendingAvailableDays = library.getLendingAvailableDays();
        this.overdueCount = library.getOverdueCount();
        this.longtermOverdueDays = library.getLongtermOverdueDays();
        this.lendingLimitsDays = library.getLendingLimitDays();
    }

}
