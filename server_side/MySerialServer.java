package server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {
	
	int port;
	ClientHandler ch;
	volatile boolean stop;
	
	public MySerialServer(int port) { //constructor
		this.port=port;
		stop=false;
	}
	
	//begin connection with the clients + can handle multiply clients
	public void start(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		stop = false;  
		new Thread(()->{
			try {
				runServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	   
	@Override
	public void runServer() throws Exception {
		ServerSocket serverSoc=new ServerSocket(port);
		serverSoc.setSoTimeout(1000);
		System.out.println("waiting for a client..");
		
		while(!stop) {   
		try {
			Socket aClient = serverSoc.accept();
			try {
				System.out.println("client is connected");
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				//aClient.close();
			}
			catch (IOException e) {/* ... */}
		}catch (SocketTimeoutException e) {/* ... */}
		}	
		 serverSoc.close();
	}

	@Override
	public void stopServer() {
		stop=true;
	}
}