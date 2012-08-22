package components;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JTranslatorComboBox extends JComboBox {
	public JTranslatorComboBox(String translator) {
		super();
		List<Object> modules = getModules();
		setModel(new DefaultComboBoxModel(modules.toArray()));
		setSelectedIndex(modules.indexOf(translator));
	}

	private List<Object> getModules() {
		String[] files = new File("translators").list();
		List<Object> modules = new LinkedList<Object>();

		for (String file : files) {
			if (file.endsWith(".class") && !file.equals("Translator.class")) {
				modules.add(file.substring(0, file.indexOf(".class")));
			}
		}
		return modules;
	}
}
