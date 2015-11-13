package client;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import util.ClientInfo;

public class V_GameClient extends JPanel{
	
	/**V_GameClient is the View component of the Client that generates the output presentation to the user 
	 * based on the changes in the model. This class handles the settings necessary to onboard new client. 
	 * It uses canvas object of GameCanvas class to perform tasks like setting client name, setting fly position, 
	 * setting mouse listners etc.
	 * 
	 */
	private static final long serialVersionUID = -8003806448600269380L;
	
	private JLabel lblClientName, lblStatus;
	
	private JList<ClientInfo> listClients;
	private DefaultListModel<ClientInfo> defModClients;
	
	private GameCanvas canvas;
	
	//Constructor to initialize the layouts in the canvas.
	public V_GameClient(){
		setLayout(new BorderLayout());
		
		lblClientName = new JLabel("");
		add(lblClientName, BorderLayout.NORTH);
		
		lblStatus = new JLabel("Status: ");
		add(lblStatus, BorderLayout.SOUTH);
	
		listClients = new JList<ClientInfo>();
		defModClients = new DefaultListModel<ClientInfo>();
		listClients.setModel(defModClients);
		listClients.setVisibleRowCount(5);
		listClients.setCellRenderer(new util.ClientListCellRenderer());
		add(listClients, BorderLayout.EAST);
		
		canvas = new GameCanvas();
		add(canvas, BorderLayout.CENTER);
	}
	
	//To end game
	public void disableGame(){
		canvas.toggleCanDraw(false);
	}
	
	//To set client status
	public void setStatus(String status){
		lblStatus.setText("Status: "+status);
	}
	
	//To set client name
	public void setName(String name){
		lblClientName.setText(name);
	}
	
	//To set fly position by calling setFlyPosition method using GameCanvas class's object
	public void setFlyPosition(Point p){
		canvas.setFlyPosition(p);
	}
	
	//To set fly dimension by calling setFlyDimension method using GameCanvas class's object
	public Rectangle getFlyDimension(){
		return canvas.getFlyDimension();
	}
	
	//Method to set mouse listeners using GameCanvas class's object
	public void setMouseListener(MouseListener listener){
		canvas.addMouseListener(listener);
	}
	
	//Method to add more than one client
	public void setClientList(ArrayList<ClientInfo> clients){
		defModClients.clear();
		for (ClientInfo cl : clients){
			defModClients.addElement(cl);
		}
	}

}
