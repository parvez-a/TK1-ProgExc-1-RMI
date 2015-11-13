package server;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.ClientInfo;
import util.ClientListCellRenderer;

public class V_GameServer extends JPanel{

	/**This is the View component of the Server that generates the output presentation to the user 
	 * based on the changes in the Model component.
	 * 
	 */
	private static final long serialVersionUID = 4796139289136818970L;
	private BorderLayout layout;
	private JLabel lblStatus;
	
	private JPanel pnlWestArea;
	private JLabel lblClientList;
	
	private JScrollPane scrollingTextArea;
	private JTextArea txtAreaStatuses;
	
	private JList<ClientInfo> listClients;
	private DefaultListModel<ClientInfo> mdlLstClients;
	
	//Constructor which sets the layouts
	public V_GameServer(){
		layout = new BorderLayout();
		this.setLayout(layout);
		
		lblStatus = new JLabel("Status: ");
		lblStatus.setHorizontalAlignment(JLabel.LEFT);
		this.add(lblStatus, BorderLayout.SOUTH);

		setupWestArea();
		setupMiddleArea();
		
		setVisible(true);
	}
	
	//To crete left panel of Server window where list of logged in clients appears
	private void setupWestArea(){
		pnlWestArea = new JPanel();
		pnlWestArea.setLayout(new BoxLayout(pnlWestArea, BoxLayout.Y_AXIS));
		this.add(pnlWestArea, BorderLayout.WEST);
		
		lblClientList = new JLabel("Client list");
		lblClientList.setHorizontalTextPosition(JLabel.LEFT);
		pnlWestArea.add(lblClientList);
		
		mdlLstClients = new DefaultListModel<ClientInfo>();
		mdlLstClients.addElement(new ClientInfo("<empty>", 0, null));
		listClients = new JList<ClientInfo>();
		listClients.setVisibleRowCount(5);
		listClients.setCellRenderer(new ClientListCellRenderer());
		listClients.setModel(mdlLstClients);
		pnlWestArea.add(listClients);
	}
	
	//To set up middle panel
	private void setupMiddleArea(){
		txtAreaStatuses = new JTextArea();
		txtAreaStatuses.setEditable(false);
		txtAreaStatuses.setRows(20);
		scrollingTextArea = new JScrollPane(txtAreaStatuses);
		add(scrollingTextArea, BorderLayout.CENTER);
	}
	
	//In order to show the list of logged in clients
	public void setClients(ArrayList<ClientInfo> clients){
		mdlLstClients.clear();
		for (ClientInfo ci : clients){
			mdlLstClients.addElement(ci);
		}
	}
	
	//To show the status message
	public void setStatus(String stat){
		lblStatus.setText("Status: "+stat);
		txtAreaStatuses.append(stat+"\n");
	}
	
}
