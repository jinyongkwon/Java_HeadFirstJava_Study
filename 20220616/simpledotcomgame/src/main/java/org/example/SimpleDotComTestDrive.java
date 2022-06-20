package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        int numOfGuesses = 0; // 사용자가 추측한 횟수를 추적하기 위한 변수를 만듬
        GameHelper helper = new GameHelper(); // 사용자로부터 입력을 받기위한 메서드가 들어있는 클래스
        SimpleDotCom theDotCom = new SimpleDotCom(); // 닷컴 객체를 만듬
        // random함수는 값이 double로 return되기 때문에 int로 캐스트를 해줌.
        // 결과값이 0 ~ 0.99.. 사이의 값이 나오기때문에 5를 곱하여 0~4.999.. 까지 나옴 => 0~4의 정수를 반환
        int randomNum = (int) (Math.random() * 5); // 첫번째 셀 위치를 정하기 위한 난수
        int[] locations = {randomNum, randomNum + 1, randomNum + 2}; // 나온 셀 위치를 기준으로 배열 생성 = DotCom
        theDotCom.setLocationCells(locations); // 닷컴의 위치를 지정
        boolean isAlive = true; // 닷컴이 살아있는지 추적하기 위한 부울변수

        while (isAlive == true) { // 살아있으면 계속 반복
            String guess = helper.getUserInput("enter a number"); // 사용자가 입력한 String을 받아옴
            String result = theDotCom.checkYourself(guess); // 닷컴 객체를 통해 추측한 값이 맞는 지확인하고 return값은 String에 저장
            numOfGuesses++; // 추측 횟수를 증가
            if (result.equals("kill")) {
                isAlive = false; // result가 "kill"일경우 false로 바꿔서 순환문을 벗어남
                System.out.println(numOfGuesses + " guess"); // 사용자가 추측한 횟수를 출력
            }
        }
    }
}

class SimpleDotCom {
    int[] locationCells; // DotCom의 위치를 담는 배열
    int numOfHits = 0; // 사용자가 입력한 횟수를 담는 변수

    public void setLocationCells(int[] locs) {
        locationCells = locs; // 받아온 DotCom을 인스턴스변수에 담기위한 메서드
    }

    public String checkYourself(String stringGuess) {
        int guess = Integer.parseInt(stringGuess); // String을 int로 변환
        String result = "miss"; // return할 결과를 저장할 변수(못맞추는 것을 기본으로 가정해서 miss)
        for (int i = 0; i < locationCells.length; i++) { // 배열의 크기만큼 반복
            if (guess == locationCells[i]) { // 사용자가 추측한 값을 배열에 들어있는 원소와 비교
                result = "hit"; // 맞음
                numOfHits++; // 맞음
                break; // 순환문을 빠져 나옴
            }
        }
        if (numOfHits == locationCells.length) {
            result = "kill"; // 객체가 죽었는지 확인을 해보고 그경우에는 result를 "kill"로 바꿈
        }
        System.out.println(result); // 사용자에게 결과를 보여줌
        return result; // 이 메서드를 호출한 메서드로 결과를 리턴
    }
}

class GameHelper {
    public String getUserInput(String prompt) {
        String inputLine = null; // 사용자가 입력한 값을 넣을 변수
        System.out.println(prompt + " "); // 값을 출력
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in)); // 입력한 값을 담는 객체
            inputLine = is.readLine(); // 입력한 값을 변수에 넣어줌
            if (inputLine.length() == 0) return null; // 아무것도 입력안할경우 null을 return
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputLine; // 입력한 값을 return
    }
}

