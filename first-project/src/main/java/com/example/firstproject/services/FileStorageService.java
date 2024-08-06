package com.example.firstproject.services;

import com.example.firstproject.config.FileStorageConfig;
import com.example.firstproject.exceptions.FileNotFoundException;
import com.example.firstproject.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class FileStorageService {
    private final Logger logger = Logger.getLogger(FileStorageService.class.getName());
    private final Path folderPath;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.folderPath = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.folderPath);
        } catch (Exception e) {
            throw new FileStorageException("Could not create directory to upload files", e);
        }
    }

    public String storeFile(MultipartFile file) {
        logger.info("Storing a file");

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..") || fileName.contains(" ")) throw new FileStorageException("Sorry! File name contains invalid path sequence " + fileName);

        try {
            Path targetLocation = folderPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }
        catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = folderPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) throw new FileNotFoundException("File not found");

            return resource;
        }
        catch (Exception e) {
            throw new FileNotFoundException("File not found", e);
        }
    }
}
