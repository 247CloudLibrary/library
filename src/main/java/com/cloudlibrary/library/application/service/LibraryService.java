package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.application.domain.Library;
import com.cloudlibrary.library.infrastructure.persistance.Memory.repository.MemoryEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LibraryService implements LibraryOperationUseCase, LibraryReadUseCase {
    private final MemoryEntityRepository memoryLibraryRepository;


    @Autowired
    public LibraryService(MemoryEntityRepository memoryLibraryRepository) {
        this.memoryLibraryRepository = memoryLibraryRepository;
    }

    @Override
    public FindLibraryResult createLibrary(LibraryCreatedCommand command) {

        var created = memoryLibraryRepository.create(command);
        return FindLibraryResult.findByLibrary(created.toLibrary());

    }

    @Override
    public List<FindLibraryResult> getLibraryListAll() {

        ArrayList<FindLibraryResult> results = new ArrayList<>();
        List<Library> libraries = memoryLibraryRepository.findLibraryAll();

        for (Library library : libraries) {
            results.add(FindLibraryResult.findByLibrary(library));
        }
        return results;
    }

    @Override
    public FindLibraryResult getLibrary(LibraryFindQuery query) {

        Optional<Library> result = memoryLibraryRepository.findLIbraryById(query.getLibraryId());
        return FindLibraryResult.findByLibrary(result.get());

    }
}
