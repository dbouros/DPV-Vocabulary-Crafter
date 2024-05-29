package com.DPV_Vocabulary_Crafter.Client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputValidator {

    // For 'Save' and 'Load' methods.
    public boolean validateFolder(String folder_path){
        Path folderPath = Paths.get(folder_path);
        return Files.exists(folderPath) && Files.isDirectory(folderPath);
    }

    // For 'Save' method.
    public boolean validateFilename(String filename){
        return filename.endsWith(".rdf");
    }

    // For 'Load' method.
    public boolean validateFile(String folder_path, String filename){
        Path filePath = Paths.get(folder_path, filename);
        return Files.exists(filePath) && Files.isRegularFile(filePath) && filename.endsWith(".rdf");
    }

    // For 'Search' methods.
    public boolean validateTerm(String term){
        return !term.isEmpty();
    }

    // Looping through invalid input until [T/t] is pressed.
    public void pressT(Scanner input){
        String button = input.nextLine();
        while (!button.equals("T") && !button.equals("t")){
            System.out.print("Press [T/t] to try again: ");
            button = input.nextLine();
        }
    }

    // Looping through invalid input until [C/c] is pressed.
    public void pressC(Scanner input){
        String button = input.nextLine();
        while (!button.equals("C") && !button.equals("c")){
            System.out.print("Press [C/c] to continue: ");
            button = input.nextLine();
        }
    }

}
