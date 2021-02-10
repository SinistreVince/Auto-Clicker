package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.MenuBarUI;
import javax.swing.plaf.MenuItemUI;
import javax.swing.text.JTextComponent;

import main.Buttons.ColoredButton;

import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

public class Test extends JFrame {

	private JPanel contentPane;
	private JTextPane console;
	private FileNameExtensionFilter filter;
	private JFileChooser fileChooser;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		getContentPane().setBackground(Color.DARK_GRAY);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		setTitle("Auto Klicker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		filter = new FileNameExtensionFilter("*.datsetup (Setup Data)", "datsetup");
		
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Auto Klicker");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(0, 402, 307, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		ColoredButton btnNewButton = new ColoredButton("New button", Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(307, 402, 89, 20);
		getContentPane().add(btnNewButton);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setEditable(false);
		textPane.setBounds(0, 21, 396, 376);
		getContentPane().add(textPane);
		
		setVisible(true);
		setSize(400, 450);
	}
}
