package com.example.Products.controllers;

import com.example.Products.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/create-file")
    public String createFile(@RequestParam String fileName) throws IOException {
        return fileService.createFile(fileName);
    }
    @GetMapping ("/create-and-write-file")
    public void createAndWriteFile(@RequestParam String fileName, @RequestParam String content) {
        try {
            fileService.createFile(fileName);
            fileService.writeToFile(fileName, content);
        } catch (IOException e) {
            System.out.println("File already exists ");

        }
    }
}
