package chat.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatServerMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		Naming.rebind("ChatServer", new ChatServer());
		System.out.println("Server ready");
	}

}
