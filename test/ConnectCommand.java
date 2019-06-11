package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ShuntingYard.ShuntingYardAlg;

public class ConnectCommand implements Command {
	public static PrintWriter out;
	
	public ConnectCommand() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({ "unused", "resource" })
	@Override
	public double doCommand(String[] parameters, int commandIndex) {
		//set parameters:
		String ip= parameters[commandIndex+1];
		int port = (int)ShuntingYardAlg.calc(parameters[commandIndex+2]);
		System.out.println("connect command");
		//validation check:
		if(port<=0 || ip==null) {
			System.out.println("Invalid ip or port");
		}	
		//do command:
//		Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {  
		try {
			Socket theServer = new Socket(ip, port);
            System.out.println("Client connected to Server..");
            out = new PrintWriter(theServer.getOutputStream(),true);
//            while (true) {
//                   	System.out.println("in the while");
//                    if (!MyInterpreter.commands.isEmpty()) {
//                            out.println(MyInterpreter.commands.poll());
//                            out.flush();
//                        }
//                    }
		} catch (IOException e) {}
//                 
//                } catch (IOException e) {}
//            }
//        });
//
//		thread.start();

		return parameters.length;
	}
}