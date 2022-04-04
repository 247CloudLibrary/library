package com.cloudlibrary.library.ui.view.library;


import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryView {

    @ApiModelProperty(value = "library ID")
    private final long id;
    @ApiModelProperty(value = "도서관 이름")
    private final String name;
    @ApiModelProperty(value = "도서관 주소")
    private final String address;
    @ApiModelProperty(value = "도서관 관리자 email")
    private final String email;
    @ApiModelProperty(value = "도서관 전화번호")
    private final String tel;
    @ApiModelProperty("도서관 휴일")
    private final String holiday;
    @ApiModelProperty("도서관 운영 시간")
    private final String operatingTime;
    @ApiModelProperty("대출 가능 여부")
    private final String loanAvailability;
    @ApiModelProperty("도서관 첫 등록 시간")
    private final Timestamp createdAt;
    @ApiModelProperty("도서관 업데이트 시간")
    private final Timestamp updatedAt;
    @ApiModelProperty("대출 가능 횟수")
    private final int lendingAvailableCount;
    @ApiModelProperty("대출 기간")
    private final int lendingAvailableDays;
    @ApiModelProperty("연체 제한 횟수")
    private final int overdueCount;
    @ApiModelProperty("장기 연체 기준 일수")
    private final int longtermOverdueDays;
    @ApiModelProperty("연체 제한 일수")
    private final int lendingLimitDays;

    public LibraryView(LibraryReadUseCase.FindLibraryResult result) {
        this.id = result.getId();
        this.name = result.getName();
        this.address = result.getAddress();
        this.email = result.getEmail();
        this.tel = result.getTel();
        this.holiday = result.getHoliday();
        this.operatingTime = result.getOperatingTime();
        this.loanAvailability = result.getLoanAvailability();
        this.createdAt = result.getCreatedAt();
        this.updatedAt = result.getUpdatedAt();
        this.lendingAvailableCount = result.getLendingAvailableCount();
        this.lendingAvailableDays = result.getLendingAvailableDays();
        this.overdueCount = result.getOverdueCount();
        this.longtermOverdueDays = result.getLongtermOverdueDays();
        this.lendingLimitDays = result.getLendingLimitDays();
    }
}
