package com.cloudlibrary.library.application.service;

import com.cloudlibrary.library.infrastructure.persistance.Memory.repository.MemoryEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public FindLibraryResult getLibrary(LibraryFindQuery query) {
        return null;
    }
}
