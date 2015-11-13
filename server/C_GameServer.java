package server;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import util.ClientInfo;

public class C_GameServer extends JFrame implements WindowListener {

	/**This is the Controller component of the Fly Game Server that handles user interaction.
   	   It reads data from the view, controls user input(like Mouse clicked),
   	   and sends input data to the model.
	 * 
	 */
	private static final long serialVersionUID = 44117252607469952L;

	private M_GameServer model;
	private V_GameServer view;
	
	public static final Point GAME_WINDOW_SIZE = new Point(400,400);
	public static final String PATH_TO_STUB = "../Util";
	
	private Point currentFlyPos;
	
	public BufferedImage imgFly;
	
	//To generate the Game server
	public C_GameServer() throws IOException{
		model = new M_GameServer(this);
		Naming.rebind(PATH_TO_STUB, model);
		
		view = new V_GameServer();
		
		setSize(500, 400);
		setVisible(true);
		add(view);
		setTitle("Game Server");
		this.addWindowListener(this);
		
		currentFlyPos = null;
		imgFly = ImageIO.read(getClass().getResourceAsStream("images/fly.png"));
		
		view.setStatus("server successfully registered its remote methods!");
	}
	
	public Point getFlyPosition(){
		return currentFlyPos;
	}
	
	//To generate random fly coordinates
	public Point generateFlyPosition(){
		Point p = new Point(0,0);
		
		//randomization here...
		Point flyDim = (imgFly != null) ? new Point(imgFly.getWidth(), imgFly.getHeight()) : new Point(60,60);
		p.x = (int)(Math.random() * (GAME_WINDOW_SIZE.x - flyDim.x));
		p.y = (int)(Math.random() * (GAME_WINDOW_SIZE.y - flyDim.y));
		
		currentFlyPos = p;
		return p;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			new C_GameServer();
			System.out.println("main(): server successfully registered its remote method!");
		}catch(Exception ex){
			System.out.println("server failed to register its remote method!");
			ex.printStackTrace();
		}
	}
	
	//In order to show the list of logged in clients
	public void showClients(ArrayList<ClientInfo> clients, String statToShow){
		view.setClients(clients);
		view.setStatus(statToShow);
	}

	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Server: window closed!! send shutdown signals to all clients!!");
		try{
			model.shutdownServer();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			System.exit(0);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e) {	}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
}
