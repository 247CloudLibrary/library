package com.cloudlibrary.library.infrastructure.persistance.memory.entity;


import com.cloudlibrary.library.application.domain.Library;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LibraryEntity implements Serializable {
    private long id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private String holiday;

    public LibraryEntity(long id, String name, String address, String email, String tel, String holiday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.holiday = holiday;
    }

    public Library toLibrary(){
        return Library.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .email(this.email)
                .tel(this.tel)
                .holiday(this.holiday)
                .build();
    }
}
