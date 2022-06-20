package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class DotCombust {
    public static void main(String[] args) {
        DotCombust game = new DotCombust(); // 게임 객체를 만든다
        game.setUpGame(); // 게임 객체에 게임을 설장하라는 명령을 내린다
        game.startPlaying(); // 게임 객체에서 주 게임을 시작하라는 명령을 내린다.
    }

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>(); // DotCom객체로 이루어진 ArrayList를 만든다.
    private int numofGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom(); //DotComt 객체 3개를 만들고
        one.setName("Pets.com"); // 각각 이름을 부여하고
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComsList.add(one); // ArrayList에 저장한다
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("당신의 목표는 3개의 닷컴을 가라앉히는 것입니다."); // 사용자에게 간단한 게임 방법을 설명한다
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("최대한 적은 횟수로 닷컴을 가라앉히세요");

        for (DotCom dotcomToset : dotComsList) { // 목록에 있는 각 DotCom에 대해 반복한다
            ArrayList<String> newLocation = helper.placeDotCom(3); // DotCom의 위치를 지정하기 위한 보조메서드를 호출한다
            dotcomToset.setLocationCells(newLocation); // 이 DotCom객체의 setter메서드를 호출해서 보조메서드에서 받아온 위치를 지정한다
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) { // DotCom 목록이 비어있지 않으면
            String userGuess = helper.getUserInput("추측한 위치를 입력해주세요."); // 사용자의 입력을 받는다.
            checkUserGuess(userGuess); // checkUserGuess 메서드를 호출한다
        }
        finishGame(); // finishGame 메서드를 호출한다
    }

    private void checkUserGuess(String userGuess) {
        numofGuesses++; // 사용자가 추측한 횟수를 증가시킨다.
        String result = "틀렸습니다"; // 따로 바꾸지 않으면 'miss'라고 가정
        for (DotCom dotComToTest : dotComsList) { // 목록에 들어있는 모든 DotCom객체에 대해 반복한다.
            result = dotComToTest.checkYourself(userGuess); // DotCom객체에 사용자가 입력한위치가 맞는지 물어본다
            if (result.equals("맞췄습니다")) {
                break; // 나머지는 확인하지 않아도 되므로 순환문에서 빠져나온다.
            }
            if (result.equals("죽였습니다")) {
                dotComsList.remove(dotComToTest); // 죽은 DotCom을 목록에서 빼고 순환문을 빠져 나간다.
                break;
            }
        }
        System.out.println(result); // 결과를 출력한다
    }

    private void finishGame() {
        System.out.println("모든 닷컴을 죽였습니다! "); // 게임 결과를 알려주는 메시지를 출력한다.
        if (numofGuesses <= 18) {
            System.out.println("당신이 추측한 횟수는 " + numofGuesses + "회 입니다");
            System.out.println("당신이 먼저 가라 앉기 전에 닷컴을 가라앉혔습니다!!");
        } else {
            System.out.println("너무 오래 걸렸습니다 총" + numofGuesses + "회 추측하였습니다.");
            System.out.println("당신은 이미 가라 앉아 물고기와 함께 있습니다.");
        }
    }
}

class DotCom {
    // DotCom의 인스턴스변수
    private ArrayList<String> locationCells; // 셀 위치가 들어있는 ArrayList객체
    private String name; // DotCom의 이름

    public void setLocationCells(ArrayList loc) {
        locationCells = loc; // DotCom의 위치를 갱신하는 setter 메서드 (무작위로 만든 위치)
    }

    public void setName(String n) { // 기초적인 setter 메서드
        name = n;
    }

    public String checkYourself(String userInput) {
        System.out.println(locationCells.toString());
        String status = "틀렸습니다";
        int index = locationCells.indexOf(userInput); // 사용자가 추측한 위치가 ArrayList에 들어있는지 확인, 있으면 인덱스번호가 없으면 -1이 리턴
        if (index >= 0) { // 인덱스가 0이상이면
            locationCells.remove(index); // 사용자가 추측한 위치가 목록에 들어있으므로 제거
            if (locationCells.isEmpty()) { // 목록이 비어있으면 닷컴이 죽었다는 것
                status = "죽였습니다";
                System.out.println("당신이 " + name + "닷컴을 가라앉혔습니다 :( "); // 닷컴이 가라 앉았음을 알려줌
            } else {
                status = "맞췄습니다";
            }
        }
        return status; // 이 메서드를 호출한 메서드로 결과를 리턴
    }
}

class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;


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
        return inputLine.toLowerCase(); // 입력한 값을 return
    }

    public ArrayList<String> placeDotCom(int comSize) {

        ArrayList<String> alphaCells = new ArrayList<String>(); // 'f6'과 같은 좌표가 들어감
        String temp = null; // 나중에 연결하기 위한 임시 String 배열
        int[] coords = new int[comSize]; // 현재 후보 좌표
        int attempts = 0; // 시도 횟수를 세기위한 카운터
        boolean success = false; // 적당한 위치를 찾았는지 표시하기 위한 플래그
        int location = 0; // 현재 시작 위치

        comCount++; // n번째 닷컴
        int incr = 1; // 수평방향으로 증가시킬 값 설정
        if ((comCount % 2) == 1) { // 홀수번째 닷컴인 경우
            incr = gridLength;  // 수직방향으로 증가시킬 값 설정
        }

        while (!success & attempts++ < 200) { // 주 검색 순환문
            location = (int) (Math.random() * 49); // 임의 시작 위치 구함
            System.out.println("시작 위치는  : " + location);  // 시작위치 확인
            int x = 0; // 위치시킬 닷컴의 n번째 위치
            success = true; // 성공할 것으로 가정
            while (success && x < comSize) { // 닷컴이 들어갈 자리가 비었는지 확인
                if (grid[location] == 0) { // 아직 사용하지 않은 위치면
                    coords[x++] = location; // 위치 저장
                    location += incr; // 다음칸 확인
                    if (location >= gridSize) { // 그리드의 크기를 벗어날경우
                        System.out.println("그리드의 크기보다 큼");
                        success = false; // 실패
                    }
                    if (x > 0 & (location % gridLength == 0)) { // 그리드의 길이를 넘긴경우
                        System.out.println("그리드의 길이를 넘김");
                        success = false; // 실패
                    }
                } else { // 이미 사용중인 위치인 경우
                    System.out.println("이미 사용중인 번호 : " + location);
                    success = false; // 실패
                }
            }
        }

        // 위치를 알파벳 좌표로 변경
        int x = 0;
        int row = 0;
        int column = 0;
        System.out.println("\n");
        while (x < comSize) { // x가 닷컴의 셀의 크기와 같거나 클때까지 반복
            grid[coords[x]] = 1; // 기본 그리드 좌표를 '사용 중'으로 표시
            row = (int) (coords[x] / gridLength); // 행값을 구함
            column = coords[x] % gridLength; // 열값을 구함
            temp = String.valueOf(alphabet.charAt(column)); // 숫자된 열을  알파벳으로 변환
            alphaCells.add(temp.concat(Integer.toString(row))); // 알파벳과 숫자를 합쳐서 CellList에 추가
            x++;
            System.out.println("닷컴의 " + x + "번째 위치 = " + alphaCells.get(x - 1));
        }
        System.out.println("\n?");
        return alphaCells;
    }

}




