package com.cloudlibrary.library.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface LibraryOperationUseCase {

    LibraryReadUseCase.FindLibraryResult createLibrary(LibraryCreatedCommand command);
    void updateLibrary(LibraryUpdateCommand command);
    void deleteLibrary(LibraryDeleteCommand command);

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryCreatedCommand{
        private final long id;
        private final String name;
        private final String address;
        private final String email;
        private final String tel;
        private final String holiday;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryUpdateCommand{
        private final long id;
        private final String name;
        private final String address;
        private final String email;
        private final String tel;
        private final String holiday;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class LibraryDeleteCommand{
        private final long id;
    }





}
