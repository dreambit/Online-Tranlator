import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import components.JCheckList;


public class ModuleFrame extends JDialog {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ModuleFrame(JFrame owner) {
		super(owner, "Modules");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350, 450);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JScrollPane northScroll = new JScrollPane(new JCheckList(new JCheckList.CheckListItem[] {
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module2"),
				new JCheckList.CheckListItem("Module3"),
				new JCheckList.CheckListItem("Module4"),
				new JCheckList.CheckListItem("Module5"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1"),
				new JCheckList.CheckListItem("Module1")
				
		}));
		contentPane.add(northScroll);
	}

}
