package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Test t = new Test();
        t.test2();
        t.test3();
    }
}

class Test {
    public void test2() {
        String[] k = {"빨", "파", "빨", "파", "파", "파", "빨", "파"};
        List<String> A = new ArrayList<String>();
        List<String> B = new ArrayList<String>();
        for (int i = 0; i < k.length; i++) {
            if ("빨".equals(k[i])) {
                A.add(k[i]);
            } else {
                B.add(k[i]);
            }
        }
        System.out.println(A.toString());
        System.out.println(B.toString());
    }

    public void test3() {
        int a = 1;
        int b = 4;
        a = a + b; // 1 + 4 = 5;
        b = a - b; // 5 - 4 = 1;
        a = a - b; // 5 - 1 = 4;
        System.out.println("a : " + a);
        System.out.println("b : " + b);
    }
}