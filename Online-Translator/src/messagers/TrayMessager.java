package messagers;

import java.awt.SystemTray;
import java.awt.TrayIcon;
/**
 * Класс который показывает сообщение в трее
 * Class which shows message at tray
 * @author dreambit
 *
 */
public class TrayMessager implements Messager {

	@Override
	public void showMessage(String title, String message) {
		TrayIcon trayIcon = SystemTray.getSystemTray().getTrayIcons()[0];
		trayIcon.displayMessage(title, message, TrayIcon.MessageType.NONE);
	}

	public static String about() {
		return "TrayMessager by (Rezvan aka Dreambitc aka John_Pa9JIbHuK)";
	}
	
	

}
