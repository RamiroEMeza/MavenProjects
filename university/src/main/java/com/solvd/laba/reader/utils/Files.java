package com.solvd.laba.reader.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import static java.util.Collections.min;

public final class Files {
    public static File getLastModified(String directoryPath) {
        // Using TrueFileFilter.INSTANCE to match all subdirectories.
        Iterator<File> filesIterator = FileUtils.iterateFiles(new File(directoryPath), FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE);
        ArrayList<File> files = new ArrayList<>();

        while (filesIterator.hasNext())
            files.add(filesIterator.next());

        // Sort files by its lastModified value
        return min(files, (file1, file2) -> {
            try {
                long file1date = FileUtils.lastModified(file1);
                long file2date = FileUtils.lastModified(file2);

                return Long.compare(file2date, file1date);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void renameFile(String path, String newName) throws IOException {
        File file = new File(path);
        FileUtils.copyFile(file, new File(file.getParentFile() + "/" + newName));
        FileUtils.delete(file);
    }

    public static void createRandomFiles(String path, int numberOfFiles) throws IOException {
        for (int i = 0; i < numberOfFiles; i++)
            FileUtils.write(new File(path + RandomStringUtils.randomAlphanumeric(8) + ".txt"), "", Charset.defaultCharset());
    }

    /**
     * Rotates all the occurrences of a word in a string.
     */
    public static String rotateWord(String text, String word, int shift) {
        return StringUtils.replace(text, word, StringUtils.rotate(word, shift));
    }

    /**
     * Checks if the given string contains any of the strings in the list.
     */
    public static boolean containsAny(String text, String[] strings) {
        for (String string : strings)
            if (StringUtils.contains(text, string))
                return true;

        return false;
    }
}