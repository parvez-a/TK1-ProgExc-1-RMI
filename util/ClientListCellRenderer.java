package util;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ClientListCellRenderer extends JLabel implements ListCellRenderer<ClientInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8067622259123373101L;

	@Override
	public Component getListCellRendererComponent(JList<? extends ClientInfo> list, ClientInfo value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		setText(value.getName()+" ("+value.getPoints()+")");
		if (isSelected){
			setBackground(Color.CYAN);
		} else {
			setBackground(Color.WHITE);
		}
		return this;
	}

}
