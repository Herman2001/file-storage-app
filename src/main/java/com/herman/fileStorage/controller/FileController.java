package com.herman.fileStorage.controller;

import com.herman.fileStorage.entity.FileEntity;
import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.security.SecurityUtils;
import com.herman.fileStorage.service.FileService;
import com.herman.fileStorage.service.FolderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final FolderService folderService;

    public FileController(FileService fileService, FolderService folderService) {
        this.fileService = fileService;
        this.folderService = folderService;
    }

    /**
     * Upload a file to a folder owned by the authenticated user.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileEntity> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("folderId") Long folderId
    ) throws IOException {

        User user = SecurityUtils.getAuthenticatedUser();

        Folder folder = folderService.getFolderById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // Säkerhetskontroll: mappen måste tillhöra användaren
        if (!folder.getOwner().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        FileEntity savedFile = fileService.uploadFile(
                file.getOriginalFilename(),
                file.getBytes(),
                folder,
                user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
    }

    /**
     * Download a file owned by the authenticated user.
     */
    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {

        User user = SecurityUtils.getAuthenticatedUser();

        FileEntity file = fileService.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        if (!file.getOwner().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getContent());
    }

    /**
     * Delete a file owned by the authenticated user.
     */
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) {

        User user = SecurityUtils.getAuthenticatedUser();

        FileEntity file = fileService.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        if (!file.getOwner().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        fileService.deleteFile(fileId);
        return ResponseEntity.noContent().build();
    }
}
