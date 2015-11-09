package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import util.ClientInfo;
import util.IGameClient;
import util.IGameServer;

public class M_GameServer extends UnicastRemoteObject implements IGameServer{

	private ArrayList<ClientInfo> clients;
	
	private C_GameServer reference;
	
	private static final long serialVersionUID = 1585177496456994883L;

	protected M_GameServer(C_GameServer server) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		reference = server;
		clients = new ArrayList<ClientInfo>();
	}

	private int findClientIdxByName(String name){
		for (int i = 0 ; i < clients.size() ; i++){
			if (clients.get(i).getName().equals(name)) return i;
		}
		return -1;
	}
	
	@Override
	public synchronized void login(String clientname, IGameClient client) throws RemoteException{
		// TODO Auto-generated method stub
		clients.add(new ClientInfo(clientname, 0, client));
		
		if (reference != null){
			reference.showClients(clients, "New player '"+clientname+"' joins the arena!!!");

			client.receiveSetting(C_GameServer.GAME_WINDOW_SIZE.x, C_GameServer.GAME_WINDOW_SIZE.y);
			Point p = reference.getFlyPosition();
			if (p == null){
				p = reference.generateFlyPosition();
			}
			client.receiveFlyPosition(p);
			
			for (int i = 0 ; i < clients.size() ; i++){
				IGameClient cl = clients.get(i).getClientInterface();
				cl.receiveClientsUpdate(clients);
			}
		}
	}

	@Override
	public void logout(String clientname) throws RemoteException {
		// TODO Auto-generated method stub
		int idx = findClientIdxByName(clientname);
		if (idx != -1){
			clients.remove(idx);
			
			for (int i = 0 ; i < clients.size() ; i++){
				IGameClient client = clients.get(i).getClientInterface();
				client.receiveClientsUpdate(clients);
			}
			
			if (reference != null){
				reference.showClients(clients, "Player '"+clientname+"' has logged out!");
			}
		}
	}
	
	public void shutdownServer() throws RemoteException{
		for (int i = 0 ; i < clients.size() ; i++){
			IGameClient client = clients.get(i).getClientInterface();
			client.serverShutDown();
		}
	}

	@Override
	public void addPoint(String clientname) throws RemoteException {
		// TODO Auto-generated method stub
		int idx = findClientIdxByName(clientname);
		if (idx != -1){
			clients.get(idx).addPoint(1);
			for (int i = 0 ; i < clients.size() ; i++){
				IGameClient client = clients.get(i).getClientInterface();
				client.receiveClientsUpdate(clients);
			}
			
			if (reference != null){
				reference.showClients(clients, "Player '"+clientname+"' get 1 point!");
				
				//automatically update fly position
				Point newPos = reference.generateFlyPosition();
				
				for (int i = 0 ; i < clients.size() ; i++){
					IGameClient client = clients.get(i).getClientInterface();
					client.receiveFlyPosition(newPos);
				}
			}
		}
	}

}
