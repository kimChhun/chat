package chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import chat.client.ChatClient;
import chat.client.ChatClientIF;

public interface ChatServerIF extends Remote {
	void registerChatClient(ChatClientIF chatClient) throws RemoteException;
	void broadcastMessage(String message) throws RemoteException;
	
	String connexionStart(ChatClientIF chatClient) throws RemoteException;
    String connexionStop(ChatClientIF chatClient) throws RemoteException;
    
	String chatSessionRequest(ChatClientIF emeteur, ChatClientIF dest) throws RemoteException;
    String chatSessionAccept() throws RemoteException;
    String chatSessionDecline() throws RemoteException;
    void chatSessionSendMessage(ChatClientIF dest, String message ) throws RemoteException ;
	String chatSessionQuit() throws RemoteException;

	ArrayList<ChatClientIF> memberListRequest() throws RemoteException;

}
