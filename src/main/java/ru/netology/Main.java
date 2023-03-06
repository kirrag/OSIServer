package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main( String[] args ) throws Exception {
		System.out.println("server started");
		int port = 8089;

		try(ServerSocket serverSocket = new ServerSocket(port)) {
			while (true) {
				try (Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				) {
					System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

					final String name = in.readLine();

					if(name.equals("Client")) {
						TimeUnit.SECONDS.sleep(4);
						out.println(String.format("Hi %s from port %d, I'm Server from port %d", name, clientSocket.getPort(), port));
					}

					final String req1 = in.readLine();
					if(req1.equals("req1")) {
						TimeUnit.SECONDS.sleep(4);
						out.println("Wow, you're too young for me, I only support http 1.0 protocol.");
					} 

					final String req2 = in.readLine();
					if (req2.equals("req2")) {
						TimeUnit.SECONDS.sleep(4);
						out.println("By!");
					}
				}
			}
		}
    }
}
