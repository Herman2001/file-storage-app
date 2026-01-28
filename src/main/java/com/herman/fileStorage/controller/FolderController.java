package com.herman.fileStorage.controller;

import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.security.SecurityUtils;
import com.herman.fileStorage.service.FolderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public Folder createFolder(@RequestParam String name) {
        User user = SecurityUtils.getAuthenticatedUser();
        return folderService.createFolder(name, user);
    }

    @GetMapping
    public List<Folder> getFolders() {
        User user = SecurityUtils.getAuthenticatedUser();
        return folderService.findAllByOwner(user);
    }
}
