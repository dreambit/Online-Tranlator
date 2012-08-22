import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.*;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Settings settings;
	private SystemTray systemTray;
	private TrayIcon trayIcon;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("Online translator");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(0, 0);
		setLocationRelativeTo(null);
		setVisible(true);

		settings = new Settings();
		settings.loadSettings();
		systemTray = SystemTray.getSystemTray();
		trayIcon = null;

		try {
			trayIcon = new TrayIcon(ImageIO.read(new File("data//icon.png")),
					"Translator");
			systemTray.add(trayIcon);

		} catch (Exception e) {

		}
		//-------------------------------------------------------
		final PopupMenu jpopup = new PopupMenu();
		trayIcon.setPopupMenu(jpopup);
		MenuItem settingsItem = new MenuItem("Settings");
		settingsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingsFrame(settings, MainFrame.this).setVisible(true);
			}
		});
		//-------------------------------------------------------
		final CheckboxMenuItem enableItem = new CheckboxMenuItem("Enabled",
				true);
		enableItem.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				settings.setEnabled(enableItem.getState());
			}
		});
		//-------------------------------------------------------
		MenuItem aboutItem = new MenuItem("About");
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AboutFrame(MainFrame.this).setVisible(true);
				jpopup.setEnabled(false);
			}
		});
		//-------------------------------------------------------
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		//-------------------------------------------------------
		MenuItem showHide = new MenuItem("Show");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		//--------------------------------------------------------
		MenuItem module = new MenuItem("Modules");
		module.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ModuleFrame(MainFrame.this).setVisible(true);
			}
		});
		//jpopup.add(showHide);
		jpopup.add(enableItem);
		jpopup.add(module);
		jpopup.add(settingsItem);
		jpopup.add(aboutItem);
		jpopup.add(exitItem);

//		trayIcon.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
//					jpopup.setLocation(e.getX(), e.getY());
//					jpopup.setInvoker(jpopup);
//					jpopup.setVisible(true);
//				}
//			}
//		});
		

		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});

		Owner owner = new Owner();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(owner);
		clipboard.setContents(contents, owner);

	}

	public class Owner implements ClipboardOwner {

		@Override
		public void lostOwnership(Clipboard clipboard, Transferable contents) {
			System.out.println("Changed");
			Transferable newContents = clipboard.getContents(this);
			String translation = "";
			try {
				if (settings.isEnabled()) {
					System.out.println(settings.getTranslator());
					String word = (String) newContents
							.getTransferData(DataFlavor.stringFlavor);
					FileOutputStream fout = new FileOutputStream("words.txt", true);
					
					translation = ReflectionInvoker.getTranslation(
							settings.getTranslator(), word, settings.getSl(),
							settings.getTl());
					fout.write(("[" + word.substring(0, word.length() - 1) + "]" + " - " + translation).getBytes());
					fout.close();
					ReflectionInvoker.showMessage(settings.getMessager(),
							"Translation by " + settings.getTranslator(),
							translation);
				}

				if (settings.isCopy() && settings.isEnabled()) {
					// newContents = clipboard.getContents(translation);
					clipboard.setContents(new StringSelection(translation),
							this);
				} else {
					newContents = clipboard.getContents(this);
					clipboard.setContents(newContents, this);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace(),
						"Clipboard error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(false);
	}

}
