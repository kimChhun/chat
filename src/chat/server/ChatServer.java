package chat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import chat.client.ChatClient;
import chat.client.ChatClientIF;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientIF> chatClients;

	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>();
	}

	public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.add(chatClient);
	}
	
	public void chatSessionSendMessage(ChatClientIF dest, String message ) throws RemoteException {
		int i=0;
		while (i < chatClients.size()) {
			if(chatClients.get(i++).getName()==dest.getName()){
				chatClients.get(i++).retriveMessage(message);
			}
		}
	}

	public void broadcastMessage(String message) throws RemoteException {
		int i=0;
		while (i < chatClients.size()) {
			chatClients.get(i++).retriveMessage(message);
		}
	}
	
	public String connexionStart(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.add(chatClient);
		return ((ChatClientIF) chatClients).getName();
	}

	public String connexionStop(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.remove(chatClient);
		return ((ChatClientIF) chatClients).getName();
	}

	@Override
	public ArrayList<ChatClientIF> memberListRequest() throws RemoteException {
		return chatClients;
	}
	
	@Override
	public String chatSessionRequest(ChatClientIF emeteur, ChatClientIF dest) throws RemoteException {
		int i=0;
		boolean emeteur_busy=true;
		boolean dest_busy=true;
		String chatSessionRequest="KO";
		while (i < chatClients.size()) {
			if (chatClients.get(i++).getName()==emeteur.getName() 
					&& chatClients.get(i++).getBusyFlag()==false)
			{ emeteur_busy=false;}
			if (chatClients.get(i++).getName()==dest.getName() 
					&& chatClients.get(i++).getBusyFlag()==false)
			{ dest_busy=false;}
		}
		if (emeteur_busy==false && dest_busy==false) {
			emeteur.setBusyFlag();
			dest.setBusyFlag();
			chatSessionRequest="OK";
		}
		return chatSessionRequest;
	}

	@Override
	public String chatSessionAccept() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String chatSessionDecline() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String chatSessionQuit() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
