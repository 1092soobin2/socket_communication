package loader;

import java.io.IOException;
import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.BufferedReader;

import java.io.OutputStream;

// 자바 기본 `java.net` 패키지
import java.net.ServerSocket;
import java.net.Socket;
// import java.nio.charset.StandardCharsets;


public class ServerApplication {
    public static void main(String[] args) {
        int port = 9010;

        try(ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("ServerApplication 실행 중, 포트 : " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");

                // TODO: 흠 굳이 쓰레드로 뺼 필요가 있나??
                // echo 기능 구현. 클라이언트로부터 메시지 읽어오고, 그대로 돌려주기.
                // new Thread(() -> {
                //     try (
                //             InputStream inputStream = clientSocket.getInputStream();
                //             Outp``utStream outputStream = clientSocket.getOutputStream()) {
                //         byte[] buffer = new byte[1024];
                //         int readBytes;

                //         outputStream.write("return message :".getBytes(StandardCharsets.UTF_8));

                //         while ((readBytes = inputStream.read(buffer)) != -1) {
                //             outputStream.write(buffer, 0, readBytes);
                //         }
                //     } catch (IOException e){
                //         e.printStackTrace();
                //     } finally {
                //         try {
                //             clientSocket.close();
                //             System.out.println("client close");

                //         } catch (IOException e) {
                //             e.printStackTrace();
                //         }
                //     }
                // }).start();
                
                // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                byte[] buffer = new byte[1024];
                int readBytes;

                while ((readBytes = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                
                }
                System.out.println("서버가 수신한 메세지: " + buffer);           
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}