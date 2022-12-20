package com.solvd.laba.reader.count;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.nio.charset.Charset.defaultCharset;

public abstract class Count {
    public static void countUniqueWords(String readable, String result) throws IOException {
        TreeMap<String, Integer> count = new TreeMap<>();
        ArrayList<String> notUnique = new ArrayList<>();
        ArrayList<String> bannThisKeys = new ArrayList<>();
        boolean isUnique = true;

        for (String word : StringUtils.lowerCase(FileUtils.readFileToString(new File(readable),
                defaultCharset())).split("\\W+")) {
            if (notUnique.contains(word)) {
                isUnique = false;
                //System.out.println(word + " is in the banned list");
            } else {
                //System.out.println(word + " is not in the banned list");
                isUnique = true;
            }

            if (isUnique) {
                try {
                    for (String key : count.keySet()) {
                        if (key.equals(word)) {
                            //System.out.println(word + " was already in the map, so it goes to the banned list");
                            notUnique.add(word);
                        }
                    }
                } catch (Exception e) {
                    //System.out.println(e.getMessage() + " / " + e.getCause() + " / " + e.toString());
                }

                if (!notUnique.contains(word)) {
                    count.put(word, word.length());
                } else {
                    count.remove(word);
                }
            }
        }
        FileUtils.write(new File(result), count.toString().replace(", ", ",\n"),
                defaultCharset());
    }
}
