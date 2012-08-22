package components;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JLanguageComboBox extends JComboBox {
	public JLanguageComboBox(String language) {
		super();
		List<Object> languages = getLanguages();
		setModel(new DefaultComboBoxModel(languages.toArray()));
		setSelectedItem(language);
	}

	public List<Object> getLanguages() {
		List<Object> languages = new LinkedList<Object>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"data//languages"));
			String str = "";
			while ((str = in.readLine()) != null) {
				languages.add(str);
			}
		} catch (Exception e) {
		}
		
		return languages;
	}
}
