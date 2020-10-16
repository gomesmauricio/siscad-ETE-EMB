package util;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Clear {
	
	public static void limpar(Container container) {
		
		Component[] components = container.getComponents();
		
		for (Component component : components) {
			
			if (component instanceof JTextField) {
				JTextField text = (JTextField) component;
				text.setText("");
			}else if (component instanceof JDateChooser) {
				
				JDateChooser date = (JDateChooser) component;
				date.setDate(null);
				
			}else if (component instanceof JCheckBox) {
				JCheckBox check = (JCheckBox) component;
				check.setSelected(false);
				
			}else if (component instanceof JComboBox) {
				JComboBox<?> combo = (JComboBox) component;
				combo.setSelectedIndex(0);
				
			}else if (component instanceof JRadioButton) {
				JRadioButton radio = (JRadioButton) component;
				radio.setSelected(false);
				
			}
			
		}
		
	}

}
