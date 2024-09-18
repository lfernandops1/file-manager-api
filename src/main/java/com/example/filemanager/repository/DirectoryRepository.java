package com.example.filemanager.repository;

import com.example.filemanager.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
}
