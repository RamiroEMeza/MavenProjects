package com.solvd.laba.reader.count;

import java.io.IOException;

public class ReaderMain {
    public static void main(String[] args) throws IOException {
        Count.countUniqueWords("university/src/main/resources/lorem.txt",
                "university/src/main/resources/lorem-count.txt");
    }
}
