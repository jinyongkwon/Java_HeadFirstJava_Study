package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    static List<PrintWriter> clientList; // 연결된 클라이언트를 담는 리스트

    public static void main(String[] args) throws IOException {
        clientList = new ArrayList<PrintWriter>();
        ServerSocket serverSocket = new ServerSocket(5000); // ServerSocket 생성, 포트번호 5000
        Socket clientSocket = serverSocket.accept(); // 연결되면 해당 클라이언트 정보저장 및 새로운 포트번호를 클라이언트에게 전달
        PrintWriter pw = new PrintWriter(clientSocket.getOutputStream()); // 클라이언트에게 받은 글을 써서 보내기 위해 PrintWriter객체에 담음
        clientList.add(pw); // 객체 저장

        Thread t = new Thread(new ClientHandler(clientSocket)); // 새로운 쓰레드로 연결대기 및 문자 보내기 대기
        t.start();
        System.out.println("연결되었습니다.");
    }

    public static class ClientHandler implements Runnable {
        Socket sock;
        BufferedReader br;

        public ClientHandler(Socket clientSocket) throws IOException {
            sock = clientSocket; // 들어온 클라이언트소켓을 소켓에 담음
            br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // 클라이언트의 글을 읽기 위해 BufferedReader에 담음
        }

        public void run() {
            String msg; // msg를 보관할 String 객체
            try {
                while ((msg = br.readLine()) != null) {
                    System.out.println("내용 : " + msg); // 내용 확인
                    tellEveryone(msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void tellEveryone(String msg) {
        Iterator<PrintWriter> it = clientList.iterator(); // Iterator는 리스트를 순회화는 객체
        while(it.hasNext()){
            PrintWriter pw = (PrintWriter) it.next();
            pw.println(msg); // msg를 씀
            pw.flush(); // msg를 보냄
        }
    }
}