package com.solvd.laba.reader.count;

import com.solvd.laba.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderMain {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        try {
            Count.countUniqueWords("C:/Users/Administrador/IdeaProjects/newmaven/university/src/main/resources/lorem.txt",
                    "C:/Users/Administrador/IdeaProjects/newmaven/university/src/main/resources/lorem-count.txt");
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }


        String directory = "C:/Users/Administrador/IdeaProjects/newmaven/university/src/main/resources";
        LOGGER.info("Last modified file in " + directory + ": ");
        LOGGER.info(Files.getLastModified(directory).getName());
        try {
            Files.renameFile("C:/Users/Administrador/IdeaProjects/newmaven/university/src/main/resources/changeMyName.txt", "changed.txt");
        } catch (FileNotFoundException e) {
            Files.renameFile("C:/Users/Administrador/IdeaProjects/newmaven/university/src/main/resources/changed.txt", "changeMyName.txt");
        }

        String wordToRotate = "rotateMe";
        // System.out.printf("Rotating word \"%s\" ", wordToRotate);
        LOGGER.info("Rotating word: " + wordToRotate);
        LOGGER.info(Files.rotateWord("\nCar rotateMe Car car", wordToRotate, 2));

        String text = "Many men, many, many, many, many men";
        LOGGER.info("Looking for (false) in the text:" + text);
        LOGGER.info(Files.containsAny(text, new String[]{"false"}));
        LOGGER.info("Looking for (men) in the text:" + text);
        LOGGER.info(Files.containsAny(text, new String[]{"men"}));
    }
}
