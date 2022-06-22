package org.example;

public class Main {
    public static void main(String[] args) throws Exception { // main()에서도 JVM으로 예외를 던짐
        Cal.add(10, 20);
        Cal3.add3(10, 20);
        Cal2.add2(10, 20);
    }
}

class Cal {
    public static void add(int n1, int n2) {
        try {
            n1 = Integer.parseInt(null);
            System.out.println(n1 + n2);
        } catch (Exception e) { // 다형성을 이용해 상위클래스 선언해서 하위클래스에 있는 예외를 잡아냄
            System.out.println("예외가 일어남");
        }
    }
}

class Cal2 {
    public static void add2(int n1, int n2) throws Exception { // throws를 활용해 main()으로 예외를 던짐
        try {
            n1 = Integer.parseInt(null);
            System.out.println(n1 + n2);
        } catch (NumberFormatException e) {
            throw new Exception(e); // 예외가 일어날시 Exception을 객체를 생성
        } finally {
            System.out.println("무조건 실행");
        }
    }
}

class Cal3 {
    public static void add3(int n1, int n2) {
        try {
            n1 = Integer.parseInt(null);
            System.out.println(n1 + n2);
        } catch (NumberFormatException e) {// 하위클래스 예외부터 사용
            System.out.println("여러개의 catch중 첫번째 catch");
        } catch (Exception e) {
            System.out.println("여러개의 catch중 마지막 catch");
        }
    }
}

class Cal4 {
    public static void add4(int n1, int n2) {
        try {
            n1 = Integer.parseInt(null);
            System.out.println(n1 + n2);
        } catch (Exception e) {
            System.out.println("여러개의 catch중 첫번째 catch");
        }
//        catch (NumberFormatException e) { // 하위클래스 예외를 뒤에 선언해서 에러남
//            System.out.println("여러개의 catch중 마지막 catch");
//        }
    }
}