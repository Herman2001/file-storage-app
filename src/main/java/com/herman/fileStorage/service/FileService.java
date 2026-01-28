package com.herman.fileStorage.service;

import com.herman.fileStorage.entity.FileEntity;
import com.herman.fileStorage.entity.Folder;
import com.herman.fileStorage.entity.User;
import com.herman.fileStorage.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing FileEntity operations.
 */
@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Uploads a new file to a folder with an owner
     *
     * @param filename the name of the file
     * @param content the content of the file as bytes
     * @param folder the folder to upload the files to
     * @param owner the user that owns the file
     * @return the saved File
     */
    public FileEntity uploadFile(String filename, byte[] content, Folder folder, User owner) {
        FileEntity file = new FileEntity();
        file.setFilename(filename);
        file.setContent(content);
        file.setFolder(folder);
        file.setOwner(owner);
        return fileRepository.save(file);
    }

    /**
     * Finds a file by its id
     *
     * @param id the id of the file
     * @return an Optional containeing the File if found, else empty
     */
    public Optional<FileEntity> findById(Long id) {
        return fileRepository.findById(id);
    }

    /**
     * Retrieves all files owned by a user.
     *
     * @param owner the owner user
     * @return list of files
     */
    public List<FileEntity> findAllByOwner(User owner) {
        return fileRepository.findAllByOwner(owner);
    }

    /**
     * Deletes a file by its id.
     *
     * @param id the id of the file to delete
     */
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}

