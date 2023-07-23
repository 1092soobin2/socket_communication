package com.soob.study.socketConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
// 자바 기본 `java.net` 패키지
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

        int port = 8081;

        try(ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("ServerApplication 실행 중, 포트 : " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트가 접속했습니다. (소켓이 생성되었습니다.");

                // echo 기능 구현. 클라이언트로부터 메시지 읽어오고, 그대로 돌려주기.
                new Thread(() -> {
                    try (
                            InputStream inputStream = clientSocket.getInputStream();
                            OutputStream outputStream = clientSocket.getOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int readBytes;

                        outputStream.write("return message :".getBytes(StandardCharsets.UTF_8));

                        while ((readBytes = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, readBytes);
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                            System.out.println("클라이언트가 종료되었습니다.. (소켓이 닫습니다.");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
