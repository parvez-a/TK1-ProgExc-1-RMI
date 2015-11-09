package util;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGameClient extends Remote {

	public void receiveSetting(int gameWidth, int gameHeight) throws RemoteException;
	public void receiveFlyPosition(Point p) throws RemoteException;
	public void receiveClientsUpdate(ArrayList<ClientInfo> arr) throws RemoteException;
	public void serverShutDown() throws RemoteException;
}
