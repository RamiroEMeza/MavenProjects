package com.solvd.laba.reader.count;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.nio.charset.Charset.defaultCharset;

public abstract class Count {
    public static void countUniqueWords(String readable, String result) throws IOException {
        TreeMap<String, Integer> count = new TreeMap<>();
        ArrayList<String> notUnique = new ArrayList<>();
        boolean isUnique = true;

        for (String word : StringUtils.lowerCase(FileUtils.readFileToString(new File(readable),
                defaultCharset())).split("\\W+")) {
            isUnique = !notUnique.contains(word);//is in the banned list?

            if (isUnique) {
                for (String key : count.keySet()) {
                    if (key.equals(word)) {
                        notUnique.add(word);//was already in the map, so it goes to the banned list
                    }
                }

                if (!notUnique.contains(word)) {
                    count.put(word, word.length());
                } else {
                    count.remove(word);
                }
            }
        }
        FileUtils.write(new File(result), count.toString().replace(", ", ",\n"), defaultCharset());
    }
}
