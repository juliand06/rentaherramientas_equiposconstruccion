package com.rentatools.infrastructure.catalog.adapters.out.storage;

import com.rentatools.application.catalog.port.StoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class LocalStorageAdapter implements StoragePort {

    private final String uploadsDir;

    public LocalStorageAdapter(@Value("${app.storage.uploads-dir:./uploads}") String uploadsDir) {
        this.uploadsDir = uploadsDir;
        new File(uploadsDir).mkdirs();
    }

    @Override
    public String storeFile(String filename, InputStream content) {
        String cleanName = UUID.randomUUID() + "_" + filename.replaceAll("[^a-zA-Z0-9._-]", "_");
        File destination = new File(uploadsDir, cleanName);
        try (FileOutputStream out = new FileOutputStream(destination)) {
            content.transferTo(out);
            return destination.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Error guardando archivo multimedia: " + e.getMessage(), e);
        }
    }
}
