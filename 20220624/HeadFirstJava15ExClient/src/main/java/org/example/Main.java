package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    static BufferedReader br;
    static PrintWriter pw;
    static Socket sock;

    public static void main(String[] args) throws IOException {

        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        while (true) {
            sendMsg();
        }
    }

    private static void setUpNetworking() throws IOException {
        sock = new Socket("127.0.0.1", 5000); // Server에 연결
        br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // 받은것을 읽기위한 br
        pw = new PrintWriter(sock.getOutputStream()); // 쓰기위한 pw
        System.out.println("네트워크 설정 완료");
    }

    public static void sendMsg() throws IOException {
        BufferedReader brMsg = new BufferedReader(new InputStreamReader(System.in));
        String msg = brMsg.readLine();
        pw.println(msg);
        pw.flush();
    }

    public static class IncomingReader implements Runnable {

        public void run() {
            String msg;
            try {
                while ((msg = br.readLine()) != null) {
                    System.out.println("내용 : " + msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}