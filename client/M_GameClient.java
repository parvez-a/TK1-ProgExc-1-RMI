package client;
import java.awt.Point;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import util.ClientInfo;
import util.IGameClient;
import util.IGameServer;

public class M_GameClient extends UnicastRemoteObject implements IGameClient,Serializable{

	/** This is the Model component of the Client that stores data that is retrieved according to commands(like login, logout)
	 *  from the controller and displays in the view.
	 * 
	 * Used for exporting a remote object and obtaining a stub that communicates 
	 * to the remote object. Serializable interface associates each serializable class with a version number,
	 * calling a VersionUID which is used to verify the sender and receiver serialized object have loaded 
	 * classes for that object that are compatible with respect to serialization.
	 * 
	 */
	private static final long serialVersionUID = -2144957160425748971L;

	private String name;
	
	private C_GameClient reference;
	
	private IGameServer ifaceServer;
	
	//Constructor to initialize client name and its object
	protected M_GameClient(String name, C_GameClient client) throws RemoteException, MalformedURLException, NotBoundException {
		super();
		this.name = name;
		this.reference = client;
		
		// TODO Auto-generated constructor stub
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
	}

	@Override
	//Method to receive and update new fly position
	public void receiveFlyPosition(Point p) throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives new fly position from the server!!: ("+p.x+","+p.y+")");
		if (reference != null){
			reference.updateFlyPosition(p);
		}
	}
	
	//Login method allows new client to login to the server
	public void login(String name, IGameClient client) throws MalformedURLException, RemoteException, NotBoundException{
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.login(name, client);
	}
	
	//Logout method allows logged in client to logout from the server
	public void logout(String name) throws MalformedURLException, RemoteException, NotBoundException {
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.logout(name);
	}
	
	//Method to send a hit confirmation when mouse is clicked on the fly
	public void sendFlyHitConfirmation(String name) throws MalformedURLException, RemoteException, NotBoundException{
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.addPoint(name);
	}

	@Override
	//To fetch list of clients
	public void receiveClientsUpdate(ArrayList<ClientInfo> arr) {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives client list from the server!!");
		if (reference != null){
			reference.updateClientList(arr);
		}
	}

	@Override
	//Fetch window settings (width, height) and update the same
	public void receiveSetting(int gameWidth, int gameHeight) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives server's recommended settings for gameplay!!");
		if (reference != null){
			reference.updateWindowSetting(gameWidth, gameHeight);
		}
	}

	@Override
	//Method to shutdown the server and end game 
	public void serverShutDown() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives server's shutdown signal!! disabling all gameplay..");
		if (reference != null){
			reference.promptServerShutdown();
		}
	}
	

}
