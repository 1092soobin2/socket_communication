package loader;
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
        int port = 9010;
        try (Socket socket = new Socket("localhost", port)) {
            System.out.println("클라이언트에서 서버에 접속하였습니다.");

            String message;

            // 서버에 메시지 보내고 받기
            try (
                    Scanner scanner = new Scanner(System.in);
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
                        System.out.println("서버와의 연결이 종료되습니다.");
                        break;
                    }

                    // 받은 메세지 출력.
                    String receivedMessage = new String(buffer, 0, readBytes, StandardCharsets.UTF_8);
                    System.out.println("서버로부터 받은 (에코)메시지 출력 : " + receivedMessage);

                    // Thread.sleep(5000);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        scanner.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}