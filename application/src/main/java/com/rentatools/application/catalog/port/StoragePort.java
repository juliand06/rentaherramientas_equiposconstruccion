package com.rentatools.application.catalog.port;

import java.io.InputStream;

public interface StoragePort {
    String storeFile(String filename, InputStream content);
}
