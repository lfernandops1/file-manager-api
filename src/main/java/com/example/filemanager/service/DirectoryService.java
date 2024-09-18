package com.example.filemanager.service;

import com.example.filemanager.domain.Directory;
import com.example.filemanager.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    public List<Directory> getAllDirectories() {
        return directoryRepository.findAll();
    }

    public Optional<Directory> getDirectoryById(Long id) {
        return directoryRepository.findById(id);
    }

    public Directory createDirectory(Directory directory) {
        return directoryRepository.save(directory);
    }

    public Optional<Directory> updateDirectory(Long id, Directory directoryDetails) {
        return directoryRepository.findById(id).map(directory -> {
            directory.setName(directoryDetails.getName());
            directory.setParentDirectory(directoryDetails.getParentDirectory());
            return directoryRepository.save(directory);
        });
    }

    public boolean deleteDirectory(Long id) {
        return directoryRepository.findById(id).map(directory -> {
            directoryRepository.delete(directory);
            return true;
        }).orElse(false);
    }
}
