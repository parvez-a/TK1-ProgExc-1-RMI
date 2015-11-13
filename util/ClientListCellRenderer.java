package util;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ClientListCellRenderer extends JLabel implements ListCellRenderer<ClientInfo>{

	/**
	 * Produces a component to output one item in a List. For example, here it shows the score whose background
	 * turns cyan when selected and otherwise remains white.
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
