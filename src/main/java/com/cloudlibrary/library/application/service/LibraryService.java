package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.memory.entity.LibraryEntity;
import com.cloudlibrary.library.infrastructure.persistance.memory.repository.MemoryEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LibraryService implements LibraryOperationUseCase, LibraryReadUseCase {
    private final MemoryEntityRepository libraryRepository;


    @Autowired
    public LibraryService(MemoryEntityRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }



    @Override
    public FindLibraryResult createLibrary(LibraryCreatedCommand command) {

        var created = libraryRepository.create(command);
        return FindLibraryResult.findByLibrary(created.toLibrary());

    }

    @Override
    public List<FindLibraryResult> getLibraryListAll() {

        ArrayList<FindLibraryResult> results = new ArrayList<>();
        List<Library> libraries = libraryRepository.findLibraryAll();

        for (Library library : libraries) {
            results.add(FindLibraryResult.findByLibrary(library));
        }
        return results;
    }

    @Override
    public FindLibraryResult getLibrary(LibraryFindQuery query) {

        Optional<Library> result = libraryRepository.findLibraryById(query.getLibraryId());
        return FindLibraryResult.findByLibrary(result.get());

    }

    @Override
    public LibraryEntity updateLibrary(LibraryUpdateCommand command) {

        LibraryEntity update = libraryRepository.update(command);
        // for debugging
        return update;
    }

    @Override
    public LibraryFindQuery deleteLibrary(LibraryDeleteCommand command) {
        LibraryFindQuery query = libraryRepository.delete(command);
        // for debugging

        return query;
    }

}
