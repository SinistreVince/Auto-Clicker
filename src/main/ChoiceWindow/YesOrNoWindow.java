package main.ChoiceWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import main.Commands;
import main.Programm;
import main.Buttons.ColoredButton;

public abstract class YesOrNoWindow extends JDialog implements ActionListener {
	
	public JLabel textField;
	public JButton yButtom;
	public JButton nButtom;
	
	public YesOrNoWindow(String WindowName, String text) {
		
		setTitle(WindowName);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);

		
		textField = new JLabel(text);
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		
		nButtom = new ColoredButton("No", Color.RED);
		nButtom.addActionListener(this);
		nButtom.setSelected(false);
		
		yButtom = new ColoredButton("Yes", Color.GREEN);
		yButtom.addActionListener(this);
		
		add(new Panel(this));
		
		setVisible(true);
		pack();
		setLocationRelativeTo(Programm.console);
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	class Panel extends JPanel {
		
		public Panel(YesOrNoWindow dialog) {
			
			GridBagLayout gridLayout = new GridBagLayout();
			setLayout(gridLayout);
			setOpaque(false);
			GridBagConstraints grid = new GridBagConstraints();
			grid.gridx = 0;
			grid.gridy = 0;
			grid.insets = new Insets(10, 10, 10, 10);
			
			grid.gridwidth = 2;
			gridLayout.setConstraints(dialog.textField, grid);
			add(textField);
			
			grid.gridwidth = 1;
			grid.gridy = 1;
			grid.weightx = 1;
			grid.anchor = GridBagConstraints.EAST;
			gridLayout.setConstraints(nButtom, grid);
			add(nButtom);
			
			grid.gridx = 1;
			grid.anchor = GridBagConstraints.WEST;
			gridLayout.setConstraints(yButtom, grid);
			add(yButtom);
		}
	}
}
