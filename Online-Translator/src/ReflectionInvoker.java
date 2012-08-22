

import javax.swing.JOptionPane;

public class ReflectionInvoker {
	public static String getTranslation(String messager, String text, String sl, String tl) {
		String translation = "";
		try {
			Class clss = Class.forName("translators." + messager);
			Object object = clss.getConstructor(String.class, String.class).newInstance(sl, tl);
			translation = (String)clss.getMethod("translate", String.class).invoke(object, text);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "Ошибка перевода", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return translation;
	}
	
	public static void showMessage(String messager, String title, String text) {
		try {
			Class clss = Class.forName("messagers." + messager);
			Object object = clss.newInstance();
			clss.getMethod("showMessage", String.class, String.class).invoke(object, title, text);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "Ошибка перевода", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static String getAboutMessage(String plagin) {
		String message = "";
		try {
			Class clss = Class.forName(plagin);
			
			if (clss.isInterface()) {
				return "";
			}
			
			message = (String) clss.getMethod("about").invoke(null);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e.getStackTrace(), "Plugin error", JOptionPane.ERROR_MESSAGE);
//			System.out.println(e.getStackTrace());
		}
		return message;
	}
}
