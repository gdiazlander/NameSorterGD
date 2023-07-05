package com.personal.gd;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameSorter {
    public static List<String> sorter (List<String> list) {

        return list.stream().
                sorted(new SorterString()).
                collect(Collectors.toList());
    }

    public static List<String> loadFile(String fileName) {

        List<String> records = new ArrayList<>();
        try (Stream<String> inputStream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            records = inputStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return records;
    }

    public static void writeFile(List<String> list , String nameFile){
        Path filePath = Path.of(nameFile);
        try {
            Files.write(filePath, list, StandardCharsets.UTF_8);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

            List<String> namesList = loadFile("unsorted-names-list.txt");
            namesList = sorter(namesList);
            namesList.stream().forEach(System.out::println);
            writeFile(namesList,"sorted-names-list.txt");





    }
}
