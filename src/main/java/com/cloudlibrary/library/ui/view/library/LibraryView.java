package com.cloudlibrary.library.ui.view.library;


import com.cloudlibrary.library.application.service.LibraryReadUseCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public LibraryView(LibraryReadUseCase.FindLibraryResult result) {
        this.id = result.getId();
        this.name = result.getName();
        this.address = result.getAddress();
        this.email = result.getEmail();
        this.tel = result.getTel();
        this.holiday = result.getHoliday();
    }
}
