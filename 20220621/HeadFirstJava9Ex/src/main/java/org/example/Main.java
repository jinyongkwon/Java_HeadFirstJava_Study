package org.example;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog(); // 인자가 없는 생성자를 호출
        Cat cat = new Cat(30); // 인자가 없는 생성자가 없어서 인자를 넣어줘야함
        cat.play();
        dog = new Dog(30); // 첫번째 Dog객체는 가비지컬렉터의 대상이됨 // 인자가 있는 생성자를 호출
        dog = null; // 두번째 Dog객체도 가비지 컬렉터의 대상이 됨
    }
}

class Animal {
    public Animal() {
        System.out.println("동물 생성");
    }
}

class Dog extends Animal {
    int size; // 해당 객체가 사라지면 죽음.

    public Dog() {
        this(30); // this를 활용해서 인자가 있는 생성자 먼저 호출
        System.out.println("강아지 생성");
        size = 10; // 객체의 상태를 생성자에서 초기화
        System.out.println("강아지 객체의 size : " + size);
    }

    public Dog(int dogSize) {
        size = dogSize;
        System.out.println("오버로드된 생성자의 강아지 객체의 size : " + size);
    }
}

class Cat extends Animal { // 인자를 받는 생성자를 만들경우 컴파일러가 인자가 없는 생성자를 만들어 주지 않음.
    int size;

    public Cat(int catSize) {
        super(); // 부모의 생성자 호출
        size = catSize;
        System.out.println("고양이 생성자의 size : " + size);
    }

    public void play() {
        int num = 3; // 메서드가 끝날경우 가비지컬렉터의 대상이됨
        System.out.println("고양이가 " + num + "만큼 논다");
        Dog dog = new Dog(); // 해당 메서드가 끝나면 가비지 컬렉터의 대상이됨
    }
}