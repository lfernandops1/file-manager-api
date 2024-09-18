package com.example.filemanager.controller;

import com.example.filemanager.domain.Directory;
import com.example.filemanager.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directories")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @GetMapping("/all")
    public List<Directory> getAllDirectories() {
        return directoryService.getAllDirectories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Directory> getDirectoryById(@PathVariable Long id) {
        return directoryService.getDirectoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Directory createDirectory(@RequestBody Directory directory) {
        return directoryService.createDirectory(directory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directory> updateDirectory(@PathVariable Long id, @RequestBody Directory directoryDetails) {
        return directoryService.updateDirectory(id, directoryDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirectory(@PathVariable Long id) {
        if (directoryService.deleteDirectory(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
