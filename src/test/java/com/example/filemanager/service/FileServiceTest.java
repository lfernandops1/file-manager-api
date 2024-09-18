package com.example.filemanager.service;

import com.example.filemanager.domain.File;
import com.example.filemanager.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    @InjectMocks
    private FileService FileService;

    @Mock
    private FileRepository FileRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllFiles() {

        File file1 = new File();
        file1.setName("File1.txt");

        File file2 = new File();
        file2.setName("File2.txt");

        when(FileRepository.findAll()).thenReturn(Arrays.asList(file1, file2));

        List<File> files = FileService.getAllFiles();

        assertEquals(2, files.size());
        assertEquals("File1.txt", files.get(0).getName());
        assertEquals("File2.txt", files.get(1).getName());
    }

    @Test
    public void testGetFileById() {

        File File = new File();
        File.setName("TestFile.txt");

        when(FileRepository.findById(1L)).thenReturn(Optional.of(File));

        Optional<File> result = FileService.getFileById(1L);

        assertTrue(result.isPresent());
        assertEquals("TestFile.txt", result.get().getName());
    }

    @Test
    public void testCreateFile() {

        File File = new File();
        File.setName("NewFile.txt");

        when(FileRepository.save(any(File.class))).thenReturn(File);


        File createdFile = FileService.createFile(File);


        assertNotNull(createdFile);
        assertEquals("NewFile.txt", createdFile.getName());
    }

    @Test
    public void testDeleteFile() {

        File File = new File();
        when(FileRepository.findById(1L)).thenReturn(Optional.of(File));
        doNothing().when(FileRepository).delete(File);

        boolean isDeleted = FileService.deleteFile(1L);

        assertTrue(isDeleted);
        verify(FileRepository, times(1)).delete(File);
    }

    @Test
    public void testUpdateFile() {

        File existingFile = new File();
        existingFile.setName("OldFile.txt");

        File updatedDetails = new File();
        updatedDetails.setName("UpdatedFile.txt");

        when(FileRepository.findById(1L)).thenReturn(Optional.of(existingFile));
        when(FileRepository.save(existingFile)).thenReturn(existingFile);
        
        Optional<File> updatedFile = FileService.updateFile(1L, updatedDetails);

        assertTrue(updatedFile.isPresent());
        assertEquals("UpdatedFile.txt", updatedFile.get().getName());
    }
}
