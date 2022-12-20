package com.solvd.laba.reader.count;

import com.solvd.laba.reader.utils.Files;

import java.io.IOException;

public class ReaderMain {
    public static void main(String[] args) throws IOException {
        Count.countUniqueWords("university/src/main/resources/lorem.txt",
                "university/src/main/resources/lorem-count.txt");

        String directory = "university/src/main/resources/";
        System.out.print("Last modified file in " + directory + ": ");
        System.out.println(Files.getLastModified(directory).getName());
        try {
            Files.renameFile("university/src/main/resources/changeMyName.txt", "changed.txt");
        } catch (Exception e) {
            Files.renameFile("university/src/main/resources/changed.txt", "changeMyName.txt");
        }

        String wordToRotate = "rotateMe";
        System.out.printf("Rotating word \"%s\" ", wordToRotate);
        System.out.println(Files.rotateWord("\nCar rotateMe Car car", wordToRotate, 1));

        String text = "Many men, many, many, many, many men";
        System.out.println("Looking for (false) in the text:" + text);
        System.out.println(Files.containsAny(text, new String[]{"false"}));
        System.out.println("Looking for (men) in the text:" + text);
        System.out.println(Files.containsAny(text, new String[]{"men"}));
    }
}
