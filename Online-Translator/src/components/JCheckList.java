package components;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class JCheckList extends JList {
	public JCheckList(Object[] data) {
		super(data);
		setLayoutOrientation(JList.VERTICAL);
		setCellRenderer(new CheckListRenderer());
	    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    addMouseListener(new MouseAdapter()
	      {
			public void mouseClicked(MouseEvent event)
	         {
	            JList list = (JList) event.getSource();

	            int index = list.locationToIndex(event.getPoint());
	            CheckListItem item = (CheckListItem)
	               list.getModel().getElementAt(index);

	            item.setSelected(! item.isSelected());

	            repaint(list.getCellBounds(index, index));
	         }
	      });   
	}

	public static class CheckListItem {
		private String label;
		private boolean isSelected = false;

		public CheckListItem(String label) {
			this.label = label;
		}

		public boolean isSelected() {
			return isSelected;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}

		public String toString() {
			return label;
		}
	}

	class CheckListRenderer extends JCheckBox implements ListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean hasFocus) {
			setEnabled(list.isEnabled());
			setSelected(((CheckListItem) value).isSelected());
			setFont(list.getFont());
			setBackground(list.getBackground());
			setForeground(list.getForeground());
			setText(value.toString());
			setToolTipText(getText());
			
			return this;
		}
	}

}
