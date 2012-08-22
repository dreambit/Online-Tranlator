import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;

import components.JLanguageComboBox;
import components.JMessagerComboBox;
import components.JTranslatorComboBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;


import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 *
 * @author dreambit
 */
public class SettingsFrame extends javax.swing.JDialog {

    /**
     * Creates new form SettingsFrame
     */
    public SettingsFrame(Settings settings, JFrame owner) {
        super(owner, "Settings");
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        this.settings = settings;
        initComponents();
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        translation = new JTranslatorComboBox(settings.getTranslator());
        jLabel2 = new javax.swing.JLabel();
        messager = new JMessagerComboBox(settings.getMessager());
        jLabel3 = new javax.swing.JLabel();
        ln_2 = new JLanguageComboBox(settings.getTl());
        ln_1 = new JLanguageComboBox(settings.getSl());
        copyCheckBox = new javax.swing.JCheckBox("Copy translation to clipboard", settings.isCopy());
        saveButton = new javax.swing.JButton();
        saveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		settings.setMassager(messager.getSelectedItem().toString());
				settings.setTranslator(translation.getSelectedItem().toString());
				settings.setSl(ln_1.getSelectedItem().toString());
				settings.setTl(ln_2.getSelectedItem().toString());
				settings.setCopy(copyCheckBox.isSelected());
				settings.saveSettings();
				dispose();
        	}
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Translation Service");

        jLabel2.setText("Messager");

        jLabel3.setText("Languages");

        saveButton.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1)
        						.addComponent(jLabel2)
        						.addComponent(jLabel3))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(ln_1, 0, 108, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(ln_2, 0, 92, Short.MAX_VALUE))
        						.addComponent(messager, 0, 206, Short.MAX_VALUE)
        						.addComponent(translation, 0, 206, Short.MAX_VALUE)))
        				.addComponent(copyCheckBox)
        				.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(translation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel2)
        				.addComponent(messager, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(ln_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(ln_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(copyCheckBox)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(saveButton)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }

    private javax.swing.JCheckBox copyCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox ln_1;
    private javax.swing.JComboBox ln_2;
    private javax.swing.JComboBox messager;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox translation;
    private Settings settings;
}
