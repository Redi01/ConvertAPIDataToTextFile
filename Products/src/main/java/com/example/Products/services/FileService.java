package com.example.Products.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {
    public String createFile(String fileName) throws IOException {
        // todo create an empty file and save it in Downloads
        // fileName = "sampleText.txt";
        String downloadsDirectory = System.getProperty("user.home") + File.separator + "Downloads";
        String filePath = downloadsDirectory + File.separator + fileName;
        // C:\Users\LENOVO\Downloads
        File file = new File(filePath);
        boolean created = file.createNewFile();

        if (!created) {
            return "File already exists!";
        } else {
            return "File created by success!";
        }

    }

    /**
     * Formats title in order to have 50 characters
     *
     * @param title
     * @return
     */
    public static String formatTitle(String title) {
        int targetLength = 50;
        if (title.length() >= targetLength) {
            return title.substring(0, targetLength);
        } else {
            StringBuilder paddedTitle = new StringBuilder(title);
            while (paddedTitle.length() < targetLength) {
                paddedTitle.append(' ');
            }
            return paddedTitle.toString();
        }
    }

    public static void writeToFile(String fileName, String content) throws IOException {
        String downloadsDirectory = System.getProperty("user.home") + File.separator + "Downloads";
        String filePath = downloadsDirectory + File.separator + fileName;
        // C:\Users\LENOVO\Downloads
        File file = new File(filePath);
        boolean created = file.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            writer.flush();
        }
    }
}
