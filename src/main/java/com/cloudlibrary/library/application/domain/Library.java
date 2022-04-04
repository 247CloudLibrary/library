package com.cloudlibrary.library.application.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Library {

    private final long id;
    private final String name;
    private final String address;
    private final String email;
    private final String tel;
    private final String holiday;

}
