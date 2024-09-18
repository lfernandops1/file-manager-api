package com.example.filemanager.service;

import com.example.filemanager.domain.Directory;
import com.example.filemanager.repository.DirectoryRepository;
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

public class DirectoryServiceTest {

    @InjectMocks
    private DirectoryService directoryService;

    @Mock
    private DirectoryRepository directoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // Inicializa os mocks
    }

    @Test
    public void testGetAllDirectories() {
        Directory dir1 = new Directory();
        dir1.setName("Root");

        Directory dir2 = new Directory();
        dir2.setName("SubDir");

        when(directoryRepository.findAll()).thenReturn(Arrays.asList(dir1, dir2));

        List<Directory> directories = directoryService.getAllDirectories();

        assertEquals(2, directories.size());
        assertEquals("Root", directories.get(0).getName());
        assertEquals("SubDir", directories.get(1).getName());
    }

    @Test
    public void testGetDirectoryById() {

        Directory directory = new Directory();
        directory.setName("TestDirectory");
        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));

        Optional<Directory> result = directoryService.getDirectoryById(1L);

        assertTrue(result.isPresent());
        assertEquals("TestDirectory", result.get().getName());
    }

    @Test
    public void testCreateDirectory() {

        Directory directory = new Directory();
        directory.setName("NewDirectory");
        when(directoryRepository.save(any(Directory.class))).thenReturn(directory);


        Directory createdDirectory = directoryService.createDirectory(directory);

        assertNotNull(createdDirectory);
        assertEquals("NewDirectory", createdDirectory.getName());
    }

    @Test
    public void testDeleteDirectory() {

        Directory directory = new Directory();
        when(directoryRepository.findById(1L)).thenReturn(Optional.of(directory));
        doNothing().when(directoryRepository).delete(directory);

        boolean isDeleted = directoryService.deleteDirectory(1L);

        assertTrue(isDeleted);
        verify(directoryRepository, times(1)).delete(directory);
    }

    @Test
    public void testUpdateDirectory() {

        Directory existingDirectory = new Directory();
        existingDirectory.setName("OldDirectory");

        Directory updatedDetails = new Directory();
        updatedDetails.setName("UpdatedDirectory");

        when(directoryRepository.findById(1L)).thenReturn(Optional.of(existingDirectory));
        when(directoryRepository.save(existingDirectory)).thenReturn(existingDirectory);


        Optional<Directory> updatedDirectory = directoryService.updateDirectory(1L, updatedDetails);

        
        assertTrue(updatedDirectory.isPresent());
        assertEquals("UpdatedDirectory", updatedDirectory.get().getName());
    }
}
