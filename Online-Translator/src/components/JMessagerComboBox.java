package components;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JMessagerComboBox extends JComboBox {
	public JMessagerComboBox(String messager) {
		super();
		List<Object> modules = getModules();
		setModel(new DefaultComboBoxModel(modules.toArray()));
		setSelectedIndex(modules.indexOf(messager));
	}
	
	private static List<Object> getModules() {
		String[] files = new File("messagers").list();
		List<Object> modules = new LinkedList<Object>();

		for (String file : files) {
			if (file.endsWith(".class") && !file.equals("Messager.class")) {
				modules.add(file.substring(0, file.indexOf(".class")));
			}
		}
		return modules;
	}
}
