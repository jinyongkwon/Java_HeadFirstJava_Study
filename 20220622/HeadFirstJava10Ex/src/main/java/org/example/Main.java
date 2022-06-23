package org.example;

import static java.time.LocalDateTime.now;

public class Main {
    public static void main(String[] args) {
        Cal cal = new Cal();
        System.out.println((int) (Math.random() * 5)); // 전역 메서드 이기 때문에 Class명을 가지고 메서드 호출
        cal.add(10, 5); // 일반 메서드를 호출할때는 레퍼런스 변수명으로 호출
        Date.now(); // static 메서드라서 클래스명으로 호출가능
        Date time = new Time(); // 다형성
        time.minute(); // 오버라이드된 일반메서드 호출

        int i = 288;
        Integer iWrap = new Integer(i); // Integer로 포장하는 옛날 방식
        int unWrapped = iWrap.intValue(); // 포장을 벗겨내는 옛날방식
        Integer iWrap2 = i; // 오토박싱때문에 자동 변환
        int unWrapped2 = iWrap; // 포장을 벗겨낼때도 자동 변환

        // String을 원시변수로 변환 (파싱이라함)
        String s = "2";
        int x = Integer.parseInt(s);
        // 원시변수를 String으로 변환 (파싱)
        double d = 42.5;
        String doubleString = "" + d;
        String doubleString2 = Double.toString(d);

        // 숫자 포매팅
        System.out.println(String.format("%,d", 1000000)); // 옛날 방식
        System.out.printf("%,d%n", 1000); // 요즘 사용 방식

        // 날짜 포매팅
        System.out.println(String.format("%tr", now())); // 옛날 방식 // 정적임포트를 사용해 now()를 바로 사용
        System.out.printf("%tr%n", now()); // 요즘 사용 방식
    }
}

final class Cal { // 확장 불가능

    public static final int NUM = 10; // 상수라서 변경 불가

    public void add(int n1, int n2) {
        System.out.println(n1 + n2);
    }
}

class Date {
    public static int time;
    int minute;

    public static void now() { // 오버라이드 불가
        time = 10;
        System.out.println("now : " + time);
        // minute // 일반 변수 사용 불가
    }

    public final void second() {
    } // 오버라이드 불가능

    public void minute() { // 오버라이드 가능
    }
}

class Time extends Date {
    public void minute() {
        int minute = 30;
        System.out.println("minute : " + minute);
    }


}

