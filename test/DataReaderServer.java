package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReaderServer {
	
	int port, rate;
	public ArrayList<String> varNames;
	public volatile static boolean stop;
	
	
	public DataReaderServer(int port , int rate) {
		stop=false;
		this.port=port;
		this.rate=rate;
		this.varNames = new ArrayList<>();
		try {
			Scanner scanner= new Scanner(new BufferedReader(new FileReader("simulator_vars.txt")));
			while (scanner.hasNext()) {
				varNames.add(scanner.next());
			}
		} catch (FileNotFoundException e) {}
		for(String s : varNames) {
			MyInterpreter.pathToDoubleValueTable.put(s, 0.0);
			
		}
	}

	void runServer() {
		//System.out.println("run server");
		try {
			ServerSocket dataReaderServer = new ServerSocket(port);
			System.out.println("Server is alive and waiting for a client..");
			Socket sendingDataClient = dataReaderServer.accept();
			Thread.sleep(110000);
			System.out.println("Client connected..");
			MyInterpreter.flag = true;
			BufferedReader in = new BufferedReader(new InputStreamReader(sendingDataClient.getInputStream()));
			int i;
			while ((in.readLine())!=null /*&& MyInterpreter.bool.equals(true)*/) {
				i = 0;
				System.out.println("from simulator: "+in.readLine());
				String[] inputFromClient = in.readLine().split(",");
				for(String s : inputFromClient) {
					//System.out.println(s);
					Double d = Double.parseDouble(s);
					//System.out.println( MyInterpreter.symbolTable.get(varNames.get(i)).getValue());
					MyInterpreter.pathToDoubleValueTable.put(varNames.get(i), d);
					//System.out.println(MyInterpreter.symbolTable.get(varNames.get(i)).getValue());
					i++;
				}
				Thread.sleep(rate);
			}
			in.close();
			sendingDataClient.close();
			dataReaderServer.close();
			System.out.println("server is off");
		}
		catch (IOException | InterruptedException e) {}
	}
}
