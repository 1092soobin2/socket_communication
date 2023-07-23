package com.soob.study.socketConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
// 자바 기본 `java.net` 패키지
//import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class ClientApplication {

    public static void main(String[] args) {
        int port = 8081;
        try (Socket socket = new Socket("localhost", port)) {
            System.out.println("클라이언트에서 서버에 접속하였습니다.");

            Scanner scanner = new Scanner(System.in);
            String message;

            // 서버에 메시지 보내고 받기
            try (
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream()) {
                while (true) {

                    message = scanner.nextLine();

                    // 서버에 메시지 보내기
                    outputStream.write(message.getBytes(StandardCharsets.UTF_8));

                    // 서버로부터 메시지 받음.
                    byte[] buffer = new byte[1024];
                    int readBytes = inputStream.read(buffer);
                    if (readBytes == -1) {
                        System.out.println("서버와의 연결이 종료되었스빈다.");
                        break;
                    }

                    // 받은 메세지 출력.
                    String receivedMessage = new String(buffer, 0, readBytes, StandardCharsets.UTF_8);
                    System.out.println("서버로부터 받은 메시지 출력 : " + receivedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
