package com.cloudlibrary.library.ui.requestBody;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LibraryUpdateRequest {
    private long id;
    private String name;
    private String address;
    private String email;
    private String tel;
    private String holiday;
}
