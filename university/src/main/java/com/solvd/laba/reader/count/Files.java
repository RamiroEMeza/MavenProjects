package com.solvd.laba.reader.count;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static java.util.Collections.min;

public final class Files {
    public static File getLastModified(String directoryPath) {
        Iterator<File> filesIterator = FileUtils.iterateFiles(new File(directoryPath), FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE);
        ArrayList<File> files = new ArrayList<>();

        while (filesIterator.hasNext())
            files.add(filesIterator.next());

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

    public static String rotateWord(String text, String word, int shift) {
        return StringUtils.replace(text, word, StringUtils.rotate(word, shift));
    }

    public static boolean containsAny(String text, String[] strings) {
        for (String string : strings)
            if (StringUtils.contains(text, string))
                return true;

        return false;
    }
}