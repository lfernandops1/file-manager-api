package com.example.filemanager.controller;

import com.example.filemanager.domain.File;
import com.example.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileEntityService;

    @GetMapping
    public List<File> getAllFiles() {
        return fileEntityService.getAllFiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        return fileEntityService.getFileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public File createFile(@RequestBody File fileEntity) {
        return fileEntityService.createFile(fileEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<File> updateFile(@PathVariable Long id, @RequestBody File fileDetails) {
        return fileEntityService.updateFile(id, fileDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        if (fileEntityService.deleteFile(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
