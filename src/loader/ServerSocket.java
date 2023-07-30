// 단순 수신 확인용

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


public class ServerSocket {
    public static void main(String[] args) {
        int port = 9010;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            

            System.out.println("ServerApplication 실행 중, 포트 : " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");

                // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                byte[] buffer = new byte[1024];
                int readBytes;

                while ((readBytes = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                
                }
                System.out.println("서버가 수신한 메세지: " + buffer);           
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}