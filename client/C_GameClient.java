package client;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;

import util.ClientInfo;

/* This is the Controller of the Fly Game Client that handles user interaction.
   It reads data from the view, controls user input(like Mouse clicked),
   and sends input data to the model.
 * Program to demonstrate window listeners and event handlers 
   The WindowListener interface listens for events associated with Window objects, 
   such as closing a window and responds in corresponding methods.
 */
public class C_GameClient extends JFrame implements WindowListener {

	private static final long serialVersionUID = 4842866886891221040L;
	
	public static String SERVER_STUB_LOC = "";
	
	private M_GameClient model;
	private V_GameClient view;
	
	public static enum STATE {
		CONNECTED, DISCONNECTED
	};
	private STATE state;
	private String name;
	
	//Interface for receiving mouse events(release, press, exit, enter, click)
	private MouseListener gameMouseListener = new MouseListener() {
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (state == STATE.DISCONNECTED){
				System.out.println("server is disconnected!");
				return;
			}
			Rectangle rect = view.getFlyDimension();
			if (rect.contains(e.getPoint())){
				System.out.println(name.toString()+" hit the fly!!");
				try{
					model.sendFlyHitConfirmation(name);
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}
	};
	
	//Constructor to initialize client name and server location
	public C_GameClient(String name, String serverLoc) throws RemoteException, MalformedURLException, NotBoundException{
		model = new M_GameClient(name, this);
		Naming.rebind("client", model);
		this.name = name;
		SERVER_STUB_LOC = serverLoc;

		setSize(400, 400);
		setVisible(true);
		setTitle("Game Client - "+this.name);
		addWindowListener(this);
		
		view = new V_GameClient();
		view.setName(name);
		view.setMouseListener(gameMouseListener);
		add(view);
		
		model.login(name, model);
		
		state = STATE.CONNECTED;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2){
			System.out.println("please supply a string argument (name) and the server's stub file location!!");
			return;
		}
		
		if (args[0].isEmpty()){
			System.out.println("please supply the server's location!!");
			return;
		}
		if (args[1].isEmpty()){
			System.out.println("please supply a string argument (name)!!");
			return;
		}
		
		try{
			new C_GameClient(args[1], args[0]);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void updateWindowSetting(int gameWidth, int gameHeight){
		setSize(gameWidth+150, gameHeight+50);
		view.repaint();
	}
	
	public void updateFlyPosition(Point p){
		view.setFlyPosition(p);
	}
	
	public void updateClientList(ArrayList<ClientInfo> clients){
		for (int i = 0 ; i < clients.size() ; i++){
			ClientInfo ci = clients.get(i);
			if (ci.getName().equals(name)){
				view.setName(name+" - ("+ci.getPoints()+")");
				break;
			}
		}
		view.setClientList(clients);
	}

	public void promptServerShutdown(){
		state = STATE.DISCONNECTED;
		view.disableGame();
		view.setStatus("Server is disconnected!");
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(name+": window closed!! logging out...");
		try {
			model.logout(name);
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
}
