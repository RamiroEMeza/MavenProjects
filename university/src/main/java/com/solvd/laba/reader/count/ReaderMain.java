package com.solvd.laba.reader.count;

import com.solvd.laba.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderMain {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        File lorem = new File("src/main/resources/lorem.txt");
        File loremCount = new File("src/main/resources/lorem-count.txt");
        try {
            LOGGER.info("Counting unique words from: ...src/main/resources/lorem.txt");
            Count.countUniqueWords(lorem.getAbsolutePath(),
                    loremCount.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }


        String directory = "../university/src/main/resources";
        LOGGER.info("Last modified file in " + directory + ": ");
        LOGGER.info(Files.getLastModified(directory).getName());


        try {
            Files.renameFile("src/main/resources/changeMyName.txt", "changed.txt");
        } catch (FileNotFoundException e) {
            Files.renameFile("src/main/resources/changed.txt", "changeMyName.txt");
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
