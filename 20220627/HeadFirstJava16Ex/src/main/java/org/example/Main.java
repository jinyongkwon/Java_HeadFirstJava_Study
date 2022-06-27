package org.example;

import java.awt.print.Book;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // List에 강아지 담기
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Dog dog1 = new Dog("강아지1", "치와와", 4);
        Dog dog2 = new Dog("강아지2", "말티즈", 2);
        Dog dog3 = new Dog("강아지3", "진돗개", 8);
        Dog dog4 = dog2;
        Dog dog5 = dog1;
        animals.add(dog1);
        animals.add(dog2);
        animals.add(dog3);
        animals.add(dog4);
        animals.add(dog5);

        // HashSet에 강아지담기
        HashSet<Animal> animals2 = new HashSet<Animal>();
        animals2.addAll(animals);

        // TreeSet에 Comparable을 구현한 강아지 담기
        TreeSet<Animal> animals3 = new TreeSet<Animal>();
        animals3.addAll(animals);

        // TreeSet에 Comparator를 구현한 노래 담기
        Song s1 = new Song("노래제목 3");
        Song s2 = new Song("노래제목 1");
        Song s3 = new Song("노래제목 2");
        SongCompare songCompare = new SongCompare();
        ArrayList<Song> songs = new ArrayList<>();
        TreeSet<Song> songs1 = new TreeSet<>(songCompare);
        songs.add(s1);
        songs.add(s2);
        songs.add(s3);

        // HashMap 사용
        HashMap<String, Integer> map = new HashMap<>();
        map.put("num1", 1);
        map.put("num2", 2);
        map.put("num3", 3);

        for (Animal dog : animals) {
            System.out.println("ArrayList의 강아지 이름 : " + dog.getName());
        }
        System.out.println("===============================");
        for (Animal dog : animals2) {
            System.out.println("HashSet의 강아지 이름 : " + dog.getName());
        }
        System.out.println("===============================");
        for (Animal dog : animals3) {
            System.out.println("TreeSet의 강아지 이름 : " + dog.getName());
        }

        System.out.println("===============================");
        Collections.sort(animals); // TreeSet을 사용하지 않고 정렬
        for (Animal dog : animals) {
            System.out.println("ArrayList를 정렬한 강아지 이름 : " + dog.getName());
        }
        System.out.println("===============================");
        for (Song song : songs1) {
            System.out.println("TreeSet의 노래 제목 : " + song.getTitle());
        }
        System.out.println("HashMap의 숫자들 : " + map);
        System.out.println("===============================");

        dog1.type(animals);
        System.out.println("===============================");
        dog1.animal(animals);
        System.out.println("===============================");
    }


}

class Animal implements Comparable<Animal> {
    String name;
    String type;
    Integer age;

    public <T extends Animal> void type(List<T> list) {}
    public  void animal(List<? extends Animal> list)  {}

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public int compareTo(Animal o) { // name을 기준으로 잡고 정렬하기위한 CompareTo메서드
        Animal animal = o;
        return name.compareTo(animal.name);
    }
}

class Dog extends Animal {

    public Dog(String name, String type, Integer age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public <T extends Animal> void type(List<T> list) { // 제네릭 타입으로 받고 Animal의 하위유형만 받음
        for(T animal : list){
            System.out.println("동물의 타입은 : "+animal.type);
        }
    }

    public  void animal(List<? extends Animal> list) { // 위와 같은데 와일드 카드를 사용해서 인자 내에서 선언
        for(Animal animal : list){
            System.out.println("동물의 나이는 : "+animal.age);
        }
    }
}

class Song {
    String title;

    public Song(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class SongCompare implements Comparator<Song> {

    public int compare(Song o1, Song o2) { // 내부 compareTo메서드를 사용한 compare메서드
        return o1.title.compareTo(o2.title);
    }
}