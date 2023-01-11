package com.solvd.laba.reader.count;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.nio.charset.Charset.defaultCharset;

public abstract class Count {
    public static void countUniqueWords(String readable, String result) throws IOException {
        TreeMap<String, Integer> count = new TreeMap<>();
        TreeSet<String> banned = new TreeSet<>();

        Arrays.stream(StringUtils.lowerCase(FileUtils.readFileToString(new File(readable),
                defaultCharset())).split("\\W+")).forEach(w -> {
            if (count.containsKey(w)) {
                count.remove(w);
                banned.add(w);
            } else if (!banned.contains(w)) {
                count.put(w, w.length());
            }
        });

        FileUtils.write(new File(result), count.toString().replace(", ", ",\n"), defaultCharset());
    }
}
