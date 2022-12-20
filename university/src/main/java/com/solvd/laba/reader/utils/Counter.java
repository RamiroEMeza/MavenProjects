package com.solvd.laba.reader.utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static java.nio.charset.Charset.defaultCharset;

public abstract class Counter {
    public static void countWords(String inputPath, String outputPath) throws IOException {
        HashMap<String, Integer> wordCounter = new HashMap<>();

        for (String word : StringUtils.lowerCase(FileUtils.readFileToString(new File(inputPath),
                defaultCharset())).split("\\W+"))
            wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1);

        FileUtils.write(new File(outputPath), wordCounter.toString().replace(", ", ",\n"),
                defaultCharset());
    }
}
