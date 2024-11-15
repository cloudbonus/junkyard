package com.github.poi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Raman Haurylau
 */
public interface DocumentService {
    ByteArrayOutputStream createDocument(Integer id) throws URISyntaxException, IOException;
}
