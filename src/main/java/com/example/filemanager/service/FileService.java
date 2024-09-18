package com.example.filemanager.service;

import com.example.filemanager.domain.File;
import com.example.filemanager.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository FileRepository;

    public List<File> getAllFiles() {
        return FileRepository.findAll();
    }

    public Optional<File> getFileById(Long id) {
        return FileRepository.findById(id);
    }

    public File createFile(File File) {
        return FileRepository.save(File);
    }

    public Optional<File> updateFile(Long id, File fileDetails) {
        return FileRepository.findById(id).map(file -> {
            file.setName(fileDetails.getName());
            file.setType(fileDetails.getType());
            file.setSize(fileDetails.getSize());
            file.setDirectory(fileDetails.getDirectory());
            return FileRepository.save(file);
        });
    }

    public boolean deleteFile(Long id) {
        return FileRepository.findById(id).map(file -> {
            FileRepository.delete(file);
            return true;
        }).orElse(false);
    }
}
