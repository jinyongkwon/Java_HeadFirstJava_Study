package org.example;

public class Main {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        Thread thread1 = new Thread(t1);
        thread1.setName("1번스레드");
        Thread thread2 = new Thread(t1);
        thread2.setName("2번스레드");
        thread1.start();
        thread2.start();
    }
}

class Test1 implements Runnable {

    private int num;

    public void run() {
        for (int i = 0; i < 50; i++) {
            plusNum();
            System.out.println(Thread.currentThread().getName() + "의 숫자 : "+ num);
        }
    }

    public void plusNum() { // 해당 메서드를 2개의 스레드가 접근하기 때문에 병행성 문제가 생김
        int i = num;
        num = i + 1;
    }

    public synchronized void plusNum2() { // 해당 메서드를 동기화 시켜서 해결
        int i = num;
        num = i + 1;
    }
}
