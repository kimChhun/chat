package chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.client.ChatClient;
import chat.client.ChatClientIF;

public interface ChatServerIF extends Remote {
	void registerChatClient(ChatClientIF chatClient) throws RemoteException;
	void broadcastMessage(String message) throws RemoteException;

}
