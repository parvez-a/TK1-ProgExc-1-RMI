package util;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameServer extends Remote{

	public void login(String clientname, IGameClient client) throws RemoteException;
	public void logout(String clientname) throws RemoteException;
	public void addPoint(String clientname) throws RemoteException;
	
}
