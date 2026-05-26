/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {

    private static final String DATA_FOLDER = "data/";

    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(DATA_FOLDER + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                if (!line.trim().equals("")) {
                    lines.add(line);
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Cannot read file: " + fileName);
        }

        return lines;
    }

    public static void writeFile(String fileName, ArrayList<String> lines) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FOLDER + fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < lines.size(); i++) {
                bufferedWriter.write(lines.get(i));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Cannot write file: " + fileName);
        }
    }

    public static void appendToFile(String fileName, String line) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FOLDER + fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(line);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Cannot append file: " + fileName);
        }
    }
}
