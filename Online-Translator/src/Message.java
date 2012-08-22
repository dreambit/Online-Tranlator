

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Message extends JFrame
{
  private SystemTray systemTray = SystemTray.getSystemTray();
  private TrayIcon trayIcon;
  public Message() throws IOException
  {
    super("Tray test");
    getContentPane().add(new JColorChooser());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    trayIcon = new TrayIcon(ImageIO.read(new File("123.png")), "Tray test application");
    trayIcon.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        setVisible(true);
        setState(JFrame.NORMAL);
        removeTrayIcon();
      }
    });
    addWindowStateListener(new WindowStateListener()
    {
      public void windowStateChanged(WindowEvent e)
      {
        if(e.getNewState() == JFrame.ICONIFIED)
        {
          setVisible(false);
          addTrayIcon();
        }
      }
    });
  }
  private void removeTrayIcon()
  {
    systemTray.remove(trayIcon);
  }
  private void addTrayIcon()
  {
    try
    {
      systemTray.add(trayIcon);
      trayIcon.displayMessage("<b>Tray test</b>", "<html><b>Window</b><html/> minimised to tray, double click to show", TrayIcon.MessageType.INFO);
    }
    catch(AWTException ex)
    {
      ex.printStackTrace();
    }
  }
  public static void main(String[] args) throws IOException
  {
	Message trayTest = new Message();
    trayTest.pack();
    trayTest.setLocationRelativeTo(null);
    trayTest.setVisible(true);
  }
}
