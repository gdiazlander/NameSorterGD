package com.personal.gd;

import java.util.Comparator;

public class SorterString implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] words1 = o1.split(" ");
        String[] words2 = o2.split(" ");

        return words1[words1.length-1].
                compareTo(words2[words2.length-1]);

    }
}

