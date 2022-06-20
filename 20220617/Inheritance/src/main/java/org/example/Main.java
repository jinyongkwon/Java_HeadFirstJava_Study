package org.example;

public class Main {
    public static void main(String[] args) {
        동물[] 동물들 = new 동물[6];
        동물들[0] = new 사자(); // 다형성
        동물들[1] = new 하마();
        동물들[2] = new 호랑이();
        동물들[3] = new 강아지();
        동물들[4] = new 고양이();
        동물들[5] = new 늑대();
        동물들[0].밥먹기();
        동물들[1].밥먹기();
        동물들[2].밥먹기();
        동물들[3].밥먹기();
        동물들[3].밥먹기();
        동물들[4].밥먹기();
        동물들[5].밥먹기();
        동물들[5].잠자기(); // 부모 클래스에 있는 메서드 사용가능

        수학 수학 = new 수학();
        수학.덧셈(1, 5);
        수학.덧셈(1, 5, 8); // 오버로딩
    }
}

class 동물 { // 부모 클래스
    public void 밥먹기() {
        System.out.println("동물 밥먹기");
    }

    public void 소리치기() {
        System.out.println("동물 소리치기");
    }

    public void 돌아다니기() {
        System.out.println("동물 돌아다니기");
    }

    public void 잠자기() { // 부모 클래스에 있는 메서드 자식클래스에서 사용가능
        System.out.println("잠을잔다");
    }
}

class 고양이과 extends 동물 { // 동물 기준 자식클래스, 호랑이 기준 부모클래스
    public void 돌아다니기() { // 오버라이딩
        System.out.println("고양이과 동물 돌아다니기");
    }
}

class 호랑이 extends 고양이과 {
    public void 밥먹기() {
        System.out.println("호랑이가 밥먹음");
    } // 오버라이딩

    public void 소리치기() {
        System.out.println("호랑이가 포효함");
    }

    public void 돌아다니기() { // 오버라이딩시 가장 깊은곳에 있는 메서드가 실행
        System.out.println("호랑이가 돌아다님");
    }
}

class 고양이 extends 고양이과 {
    public void 밥먹기() {
        System.out.println("고양이가 밥먹음");
    }

    public void 소리치기() {
        System.out.println("고양이가 울음");
    }
}

class 사자 extends 고양이과 {
    public void 밥먹기() {
        System.out.println("사자가 밥먹음");
    }

    public void 소리치기() {
        System.out.println("사자가 포효함");
    }
}

class 하마 extends 동물 {
    public void 밥먹기() {
        System.out.println("하마가 밥먹음");
    }

    public void 소리치기() {
        System.out.println("하마가 포효함");
    }
}

class 강아지과 extends 동물 {
    public void 돌아다니기() {
        System.out.println("강아지과 동물 돌아다니기");
    }
}

class 늑대 extends 강아지과 {

    public void 밥먹기() {
        super.밥먹기(); // 부모클래스 메서드 실행후 본인 메서드 실행
        System.out.println("늑대가 밥먹음");
    }

    public void 소리치기() {
        System.out.println("늑대가 포효함");
    }
}

class 강아지 extends 강아지과 {
    public void 밥먹기() {
        System.out.println("강아지가 밥먹음");
    }

    public void 소리치기() {
        System.out.println("강아지가 짖음음");
    }
}

class 수학 {
    public void 덧셈(int n1, int n2) {
        System.out.println(n1 + n2);
    }

    public void 덧셈(int n1, int n2, int n3) {
        System.out.println(n1 + n2 + n3);
    }
}