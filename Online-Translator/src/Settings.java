

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Settings {
	private String messager;
	private String translator;
	private boolean enabled;
	private boolean copy;
	private String sl;
	private String tl;
	
	public Settings() {
		this.enabled = true;
	}
	
	public void setSl(String language) {
		this.sl = language;
	}
	
	public void setTl(String language) {
		this.tl = language;
	}
	
	public String getSl() {
		return this.sl;
	}
	
	public String getTl() {
		return this.tl;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean state) {
		this.enabled = state;
	}
	
	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public void setMassager(String messager) {
		this.messager = messager;
	}

	public String getTranslator() {
		return this.translator;
	}

	public String getMessager() {
		return this.messager;
	}
	
	public void setCopy(boolean state) {
		this.copy = state;
	}
	
	public boolean isCopy() {
		return this.copy;
	}

	public void saveSettings() {
		Properties properties = new Properties();
		properties.setProperty("Messager", this.messager);
		properties.setProperty("Translator", this.translator);
		properties.setProperty("Sl", this.sl);
		properties.setProperty("Tl", this.tl);
		properties.setProperty("Copy", String.valueOf(this.copy));
		try {
			properties.store(new FileOutputStream("data//properties"), "");
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(null, e.getStackTrace(),
							"Ошибка при сохранении настроек",
							JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadSettings() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("data//properties")));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace(),
					"Ошибка при загрузке настроек", JOptionPane.ERROR_MESSAGE);
		}
		this.messager = properties.getProperty("Messager");
		this.translator = properties.getProperty("Translator");
		this.sl = properties.getProperty("Sl");
		this.tl = properties.getProperty("Tl");
		this.copy = Boolean.valueOf(properties.getProperty("Copy"));
	}
}
