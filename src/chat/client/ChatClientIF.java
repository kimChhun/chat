package chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientIF extends Remote  {
	void retriveMessage(String message) throws RemoteException;
	String getName()throws RemoteException;
	Boolean getBusyFlag() throws RemoteException;
	void setBusyFlag() throws RemoteException;
}
