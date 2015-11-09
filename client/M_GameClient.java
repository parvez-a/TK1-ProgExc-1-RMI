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

	/**
	 * 
	 */
	private static final long serialVersionUID = -2144957160425748971L;

	private String name;
	
	private C_GameClient reference;
	
	private IGameServer ifaceServer;
	
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
	public void receiveFlyPosition(Point p) throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives new fly position from the server!!: ("+p.x+","+p.y+")");
		if (reference != null){
			reference.updateFlyPosition(p);
		}
	}
	
	public void login(String name, IGameClient client) throws MalformedURLException, RemoteException, NotBoundException{
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.login(name, client);
	}
	
	public void logout(String name) throws MalformedURLException, RemoteException, NotBoundException {
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.logout(name);
	}
	
	public void sendFlyHitConfirmation(String name) throws MalformedURLException, RemoteException, NotBoundException{
		ifaceServer = (IGameServer) Naming.lookup(C_GameClient.SERVER_STUB_LOC);
		ifaceServer.addPoint(name);
	}

	@Override
	public void receiveClientsUpdate(ArrayList<ClientInfo> arr) {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives client list from the server!!");
		if (reference != null){
			reference.updateClientList(arr);
		}
	}

	@Override
	public void receiveSetting(int gameWidth, int gameHeight) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives server's recommended settings for gameplay!!");
		if (reference != null){
			reference.updateWindowSetting(gameWidth, gameHeight);
		}
	}

	@Override
	public void serverShutDown() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("'"+name+"' receives server's shutdown signal!! disabling all gameplay..");
		if (reference != null){
			reference.promptServerShutdown();
		}
	}
	

}
