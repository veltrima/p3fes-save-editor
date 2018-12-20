package com.example.editor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
	
	String fileName;

    public FileReader(String inFileName) {
        fileName = inFileName;
    }

    byte[] readFile() throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }
    
    void writeFile(byte[] data) throws IOException {
    	
    }
}
