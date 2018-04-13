package chat.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import chat.server.ChatServerIF;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF,Runnable {
	private ChatServerIF chatServer;
	private String name=null;

	protected ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.name=name;
		this.chatServer=chatServer;
		chatServer.registerChatClient(this); // call server to registry
	}

	public void retriveMessage(String message) throws RemoteException {
		System.out.println(message);
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String message;
		while(true){
			message=scanner.nextLine();
			try {
				chatServer.broadcastMessage(name + ":"+message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

}
