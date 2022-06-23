package org.example;

import java.io.*;

public class ObjectSerialized {
    public static void objectSerializedEx() throws IOException, ClassNotFoundException {
        Dog dog1 = new Dog(60, 30, "말티즈", "강아지1");
        Dog dog2 = new Dog(50, 20, "치와와", "강아지2");
        Dog dog3 = new Dog(100, 60, "진돗개", "강아지3");

        // 직렬화
        FileOutputStream fos = new FileOutputStream("Dog.src"); //  파일에 객체를 바이트로 저장 // 연결 스트림 = 목적지(파일,소켓 등)로 연결
        ObjectOutputStream os = new ObjectOutputStream(fos); // 객체를 스트림으로 보낼수 있는 형태로 바꿔줌 // 연쇄 스트림 = 연결스트림에 이어져야함
        os.writeObject(dog1); // 객체 저장
        os.writeObject(dog2);
        os.writeObject(dog3);
        os.close(); // ObjectOutputStream을 닫음으로 밑에있는 FileOutputStream도 자동으로 닫힘

        // 역직렬화
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("Dog.src"));
        Dog dog1Restore = (Dog) is.readObject();
        Dog dog2Restore = (Dog) is.readObject();
        Dog dog3Restore = (Dog) is.readObject();

        System.out.println("dog1의 타입 : " + dog1Restore.getType());
        System.out.println("dog2의 타입 : " + dog2Restore.getType());
        System.out.println("dog3의 타입 : " + dog3Restore.getType());
        System.out.println("dog3의 이름 : " + dog3Restore.getName()); // name은 transient로 지정해서 직렬화 되지않아 역직렬화 할때 값을 읽을수 없어 기본값인 null이 들어옴
    }
}

class Animal implements Serializable{ // 직렬화 할수 있게 Serializable를 구현

}


class Dog extends Animal { // 부모가 Serializable를 구현했으므로 구현안해도됨됨
   int width;
    int height;
    String type;

    transient String name;

    public Dog(int width, int height, String type, String name) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.name = name;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }


    public String getName() {
        return name;
    }
}
