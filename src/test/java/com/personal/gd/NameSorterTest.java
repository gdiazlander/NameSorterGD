package com.personal.gd;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameSorterTest {
    private List<String> listSample;
    private List <String> listSampleSorted;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @BeforeEach
    public void loadTestValue(){

        listSample = new ArrayList<>();
        listSample.add("Janet Parsons");
        listSample.add("Vaughn Lewis");
        listSample.add("Adonis Julius Archer");
        listSample.add("Shelby Nathan Yoder");
        listSample.add("Marin Alvarez");
        listSample.add("London Lindsey");
        listSample.add("Beau Tristan Bentley");
        listSample.add("Leo Gardner");
        listSample.add("Hunter Uriah Mathew Clarke");
        listSample.add("Mikayla Lopez");
        listSample.add("Frankie Conner Ritter");

        listSampleSorted = new ArrayList<>();
        listSampleSorted.add("Marin Alvarez");
        listSampleSorted.add("Adonis Julius Archer");
        listSampleSorted.add("Beau Tristan Bentley");
        listSampleSorted.add("Hunter Uriah Mathew Clarke");
        listSampleSorted.add("Leo Gardner");
        listSampleSorted.add("Vaughn Lewis");
        listSampleSorted.add("London Lindsey");
        listSampleSorted.add("Mikayla Lopez");
        listSampleSorted.add("Janet Parsons");
        listSampleSorted.add("Frankie Conner Ritter");
        listSampleSorted.add("Shelby Nathan Yoder");

        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @Test
    public void testNameSorterSimple() {

        List <String> orderedList = NameSorter.sorter(listSample);

        assertEquals(listSampleSorted, orderedList);
    }

    @Test
    public void testNameSorterSimple_VersionUpgrade() {

        List <String> orderedList = NameSorter.sorter(listSample);

        assertEquals(listSampleSorted, orderedList);
    }

    @Test
    public void testloadFile() {

        String fileName = "unsorted-names-list.txt";

        List<String> loadList = NameSorter.
                loadFile(fileName);

        assertEquals(listSample,loadList);
    }


    @Test
    public void testloadFile_GivenNull() {

        List<String> loadList = NameSorter.
                loadFile(null);

        assertNotNull(loadList);
    }

    @Test
    public void testloadFile_GivenWrongPath() {

        List<String> loadList = NameSorter.
                loadFile("-123");

        assertNotNull(loadList);
    }

    @Test
    public void testWriteFile() throws IOException {
        NameSorter.writeFile(listSampleSorted,"sorted-names-list.txt");

        List<String> read = Files.readAllLines(Path.of("sorted-names-list.txt"));

        assertEquals(listSampleSorted,read);
    }

    @Test
    public void testWriteFile_GivenWrongPath() throws IOException {

        NameSorter.writeFile(listSampleSorted,"F:");

    }

    @Test
    public void testNameSorter_GivingCorrectInput() throws IOException {
        String[] args = {"unsorted-names-list.txt"};
        NameSorter.main(args);
        assertNotNull(outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void testNameSorter_GivingNoInput() throws IOException {
        String[] args = new String[]{};
        NameSorter.main(args);
        assertEquals("Please provide the input file path.",outputStreamCaptor.toString()
                .trim());
    }


}