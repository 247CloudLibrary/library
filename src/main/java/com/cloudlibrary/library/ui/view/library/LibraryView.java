package com.cloudlibrary.library.ui.view.library;


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

    public LibraryView(long id, String name, String address, String email, String tel, String holiday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.holiday = holiday;
    }
}
