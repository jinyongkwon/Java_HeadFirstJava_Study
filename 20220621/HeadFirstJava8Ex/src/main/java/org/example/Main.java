package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Wolf());
        Object o = objects.get(0);
        System.out.println(o.hashCode()); // 객체는 Wolf여도 Wolf의 레퍼런스유형이 Object이므로 Wolf의 메서드 사용불가
        if (o instanceof Wolf) { // 확실하지 않을경우 부모클래스와 자식클래스가 맞는지 확인하는 연산자
            Wolf wolf = (Wolf) o; // List에 Wolf를 집어넣어도 return할때는 Object이기 때문에 캐스트 연산자를 사용해서 Wolf 레퍼런스변수에 집어넣을수 있다.
            wolf.eat(); // 레퍼런스유형에 대한 메서드만 사용이 가능
            System.out.println(wolf.hashCode()); // Object는 모든 객체의 최상위 부모이기 때문에 Object에 있는 메서드도 사용이 가능
        }
    }
}

abstract class Animal { // new 해서 객체를 띄울수 없음

    abstract public void eat();// 추상 메서드

    abstract public void roam();
}

abstract class Feline extends Animal {
    public void roam() {
        System.out.println("고양이과 동물이 돌아다닌다");
    } // 추상클래스 내에 일반 메서드도 가능
}

class Lion extends Feline {

    public void eat() {
        System.out.println("사자가 먹는다");
    }
}

class Cat extends Feline implements Pet { // implements로 구현

    public void eat() {
        System.out.println("고양이가 먹는다");
    }

    public void play() {
        System.out.println("고양이가 주인과 논다다");
    }
}

class Tiger extends Feline {

    public void eat() {
        System.out.println("호랑이가 먹는다");
    }
}

class Hipo extends Animal { // 구상클래스

    public void eat() {
        System.out.println("하마가 먹는다");
    }

    public void roam() { // Animal에서 추상메서드로 만들어서 무조건 구현해야함
        System.out.println("하마가 돌아다닌다");
    }
}

abstract class Canine extends Animal {

    public void roam() {
        System.out.println("강아지과 동물이 돌아다닌다");
    }
}

class Dog extends Canine implements Pet { // Animal상속트리에서 Pet 상속
    public void play() {
        System.out.println("강아지가 주인과 논다");
    }

    public void eat() {
        System.out.println("강아지가 먹는다");
    }
}

class Wolf extends Canine {

    public void eat() {
        System.out.println("늑대가 먹는다");
    }
}

abstract class Robot {
    abstract void move();
}

class humanoid extends Robot {

    void move() {
        System.out.println("휴머노이드가 움직이다");
    }
}

class RoboDog extends Robot implements Pet { // Robot 상속트리에서 Pet 상속

    void move() {
        System.out.println("강아지로봇이 움직이다");
    }

    public void play() {
        System.out.println("강아지로봇이 주인과 논다");
    }
}

interface Pet {
    void play();
}