package com.andyadc.bms.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private FileStorageSettings fileStorageSettings;

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString().replace("-", ""));
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(System.currentTimeMillis());
    }

    @Autowired
    public void setFileStorageSettings(FileStorageSettings fileStorageSettings) {
        this.fileStorageSettings = fileStorageSettings;
    }

    public FileStorageDTO store(MultipartFile file) {
        logger.info("Filename: {}, filesize: {} kb", file.getOriginalFilename(), file.getSize() / 1024F);

        String date = LocalDate.now().toString().replace("-", "");
        String dir = fileStorageSettings.getPath().getPath() + date + "\\";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String newName = file.getOriginalFilename();
        Path path = Paths.get(dir + newName);
        try {
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileStorageDTO dto = new FileStorageDTO();
        String view = FileStorageConstants.RESOURCE_PATH + date + "/" + newName;
        dto.setLocalStoragePath(dir + newName);
        dto.setResourcePath(view);

        return dto;
    }
}
