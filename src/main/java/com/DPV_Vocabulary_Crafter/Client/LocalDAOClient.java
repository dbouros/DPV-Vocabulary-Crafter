package com.DPV_Vocabulary_Crafter.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalDAOClient {

    public void writeVocabularyToFile(byte[] fileBytes, String folder_Path, String filename){

        // Combines 'folder_Path' and 'filename' into a single 'Path' variable.
        Path filePath = Paths.get(folder_Path, filename);

        /*
         1) This try-catch will automatically close the OutputStream at the end of the block.
         2) The .toFile() method converts the Path object represented by filePath to a File object. In other
         words, it gets the File representation of the path.
        */
        try (OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            if (fileBytes != null) {
                /*
                 This method writes the content of the fileBytes array to the file specified by filePath.
                 If the file does not exist, it will be created. If the file already exists, its content
                 will be overwritten with the new data.
                */
                outputStream.write(fileBytes);
            }else throw new IOException();
        }catch (IOException e){
            System.out.println("File Not Found! IOException!");
        }

        System.out.println("File downloaded successfully! Path: " + filePath);
    }

    public byte[] readVocabularyFromFile(String folder_Path, String filename){

        // Combines 'folder_Path' and 'filename' into a single 'Path' variable.
        Path filePath = Paths.get(folder_Path, filename);

        try {
            System.out.println("Read bytes from RDF file successfully!");
            // Read the file's content from the "Path" variable and return it as "byte[]" (ByteArray).
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            System.out.println("IOException occurred when reading vocabulary from file!");
            System.out.println(" File is either empty or does not exist! File could not be loaded!");
            // Returns empty ByteArray "byte[]".
            return new byte[0];
        }
    }

    public String readTextFromResources(String filename){

        // Combines 'folder_Path' and 'filename' into a single 'Path' variable.
        Path resourcesPath = Paths.get("src/main/resources/HelpTexts", filename);

        try{
            return Files.readString(resourcesPath);
        } catch (IOException e){
            return "IOException occurred! Please check the 'txt' files in the 'resources' folder.";
        }
    }

}