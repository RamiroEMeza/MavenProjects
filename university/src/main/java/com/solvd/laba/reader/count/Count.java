package com.solvd.laba.reader.count;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.Charset.defaultCharset;

public abstract class Count {
    public static void countUniqueWords(String readable, String result) throws IOException {
        TreeMap<String, Integer> countImproved = new TreeMap<>();
        Arrays.stream(StringUtils.lowerCase(FileUtils.readFileToString(new File(readable), defaultCharset())).split("\\W+")).forEach(w -> {
            Integer integer = (countImproved.containsKey(w)) ? countImproved.remove(w) : countImproved.put(w, w.length());
        });
        FileUtils.write(new File(result), countImproved.toString().replace(", ", ",\n"), defaultCharset());
    }
}
