package Tetris;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderTest {
    public static final String delimiter = ",";

    CSVReaderTest() {
    }

    public static String[][] read(String csvFile) throws IOException {
        File file = new File(csvFile);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        br.mark(1000);

        int count;
        for(count = 0; br.readLine() != null; ++count) {
        }

        br.reset();
        String[][] database = new String[count][12];

        for(count = 0; (line = br.readLine()) != null; ++count) {
            String[] tempArr = line.split(",");
            database[count] = tempArr;
        }

        return database;
    }
}