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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String holiday;
    @Column(nullable = false)
    private String  operatingTime;
    @Column(nullable = true)
    private LocalDateTime createdAt;
    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int lendingAvailableCount;
    @Column(nullable = false)
    private int lendingAvailableDays;
    @Column(nullable = false)
    private int overdueCount;
    @Column(nullable = false)
    private int longtermOverdueDays;
    @Column(nullable = false)
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
