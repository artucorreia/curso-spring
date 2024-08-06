package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.UploadFileResponseDTO;
import com.example.firstproject.exceptions.FileNotFoundException;
import com.example.firstproject.services.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/file")
@Tag(name = "File", description = "Endpoints for manager files")
public class FileController {

    @Autowired
    private FileStorageService service;

    @PostMapping(value = "/upload")
    public ResponseEntity<UploadFileResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/download/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(
                new UploadFileResponseDTO(
                        fileName, fileDownloadUri, file.getContentType(), file.getSize()
                )
        );
    }

    @PostMapping(value = "/uploadFiles")
    public List<ResponseEntity<UploadFileResponseDTO>> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        return files.stream().map(file -> uploadFile(file)).toList();
    }

    @GetMapping(value = "/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName,
            HttpServletRequest request
    ) {
        Resource resource = service.loadFileAsResource(fileName);

        try {
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            if (contentType.isBlank()) contentType = "application/octet-stream";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; file=name\"" + resource.getFilename() + "\"")
                    .body(resource);

        }
        catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
