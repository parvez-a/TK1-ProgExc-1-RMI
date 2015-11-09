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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8003806448600269380L;
	
	private JLabel lblClientName, lblStatus;
	
	private JList<ClientInfo> listClients;
	private DefaultListModel<ClientInfo> defModClients;
	
	private GameCanvas canvas;
	
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
	
	public void disableGame(){
		canvas.toggleCanDraw(false);
	}
	
	public void setStatus(String status){
		lblStatus.setText("Status: "+status);
	}
	
	public void setName(String name){
		lblClientName.setText(name);
	}
	
	public void setFlyPosition(Point p){
		canvas.setFlyPosition(p);
	}
	
	public Rectangle getFlyDimension(){
		return canvas.getFlyDimension();
	}
	
	public void setMouseListener(MouseListener listener){
		canvas.addMouseListener(listener);
	}
	
	public void setClientList(ArrayList<ClientInfo> clients){
		defModClients.clear();
		for (ClientInfo cl : clients){
			defModClients.addElement(cl);
		}
	}

}
