package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.application.domain.Library;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LibraryReadUseCase {

    List<FindLibraryResult> getLibraryListAll();
    FindLibraryResult getLibrary(LibraryFindQuery query);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class LibraryFindQuery{
        private long libraryId;

        public LibraryFindQuery(long libraryId){
            this.libraryId = libraryId;
        }
    }

    @Getter
    @ToString
    @Builder
    class FindLibraryResult{
        private final long id;
        private final String name;
        private final String address;
        private final String email;
        private final String tel;
        private final String holiday;

        public static FindLibraryResult findByLibrary(Library library){
            return FindLibraryResult.builder()
                    .id(library.getId())
                    .name(library.getName())
                    .address(library.getAddress())
                    .email(library.getEmail())
                    .tel(library.getTel())
                    .holiday(library.getHoliday())
                    .build();
        }
    }

}
