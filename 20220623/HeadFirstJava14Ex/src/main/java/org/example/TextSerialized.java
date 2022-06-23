package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextSerialized {
    public static void textSerializedEx() throws IOException {
        File file = new File("test.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        String[] textList = {"가나다라마바사", "아자차카타파하", "ABCDEFG", "HIJKLMN", "OPQRSTU", "VWXYZ"};
        for (String text : textList) {
            bw.write(text);
            bw.newLine();
        }
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("text.txt 한줄씩 읽은 내용 : " + line);
        }
        br.close();
    }
}
