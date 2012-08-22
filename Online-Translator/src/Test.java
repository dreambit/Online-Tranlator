import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Test extends JFrame {
	JPanel content = new JPanel();

	public Test() {
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		setContentPane(content);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bb, bb2;
		bb = new JButton("Go");
		bb.setAlignmentX(Component.CENTER_ALIGNMENT);
		bb2 = new JButton("Go2");
		bb2.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(bb);
		getContentPane().add(bb2);
		
		pack();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test().setVisible(true);
	}

}
