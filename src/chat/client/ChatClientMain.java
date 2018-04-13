package chat.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.server.ChatServerIF;

public class ChatClientMain {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String chatServeurURL="rmi://localhost/ChatServer";
		ChatServerIF chatServer =(ChatServerIF) Naming.lookup(chatServeurURL);
		ChatClient chatclient =new ChatClient(args[0],chatServer);
		System.out.println("client "+chatclient.getName()+" est connecté");
		new Thread(chatclient).start();
	}

}
