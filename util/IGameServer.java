package util;
import java.rmi.Remote;
import java.rmi.RemoteException;


/* By extending the interface java.rmi.Remote, IGameClient interface methods can be invoked 
 * from another Java client. 
 * Any object that implements this interface can be a remote object.
 * 
 */
public interface IGameServer extends Remote{

	public void login(String clientname, IGameClient client) throws RemoteException;
	public void logout(String clientname) throws RemoteException;
	public void addPoint(String clientname) throws RemoteException;
	
}
