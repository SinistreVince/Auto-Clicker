package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;

import main.ConsoleListeners.Action;

public class Console extends JFrame {

	public JTextPane console;
	public JTextField input;
	public JScrollPane scrollpane;
	public JFileChooser fileChooser;
	
	public StyledDocument document;
	public AutoClicker clicker;
	
	FileNameExtensionFilter filter;
	ConsoleListeners listener;
	
	public Console() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		listener = new ConsoleListeners();
		clicker = new AutoClicker();
		
		setTitle("Auto Clicker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Courier", Font.PLAIN, 12));
		console.setCaretColor(Color.WHITE);
		console.setForeground(Color.WHITE);
		console.setOpaque(false);
		console.addMouseListener(listener.getMouseListener());
		
		document = console.getStyledDocument();
		
		input = new JTextField();
		input.setEditable(true);
		input.setBackground(Color.GRAY);
		input.setForeground(Color.WHITE);
		input.setCaretColor(Color.YELLOW);
		input.setFont(new Font("Courier", Font.PLAIN, 12));
		input.addActionListener(listener.getActionListener());
		
		scrollpane = new JScrollPane(console);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		
		filter = new FileNameExtensionFilter("*.datsetup (Setup Data)", "datsetup");
		
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Auto Klicker");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		
		add(input, BorderLayout.SOUTH);
		add(scrollpane, BorderLayout.CENTER);
		setSize(400, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
	}
	
	public void BackgroundTask() throws InterruptedException {
		
		switch (Programm.mode) {
			case Setup:
				if(Programm.setupProgress == 1 && clicker.simulatedButtom != -1) {
					Commands.SetupSecond();
				}
				if(Programm.setupProgress == 2 && clicker.triggerButtom != -1) {
					Commands.SetupThird();
				}
				Thread.sleep(1000/20);
				break;
			case Idle:
				Thread.sleep(1000/20);
				break;
			case Run:
				clicker.autoMouseClick(clicker.simulatedButtom);
				Thread.sleep(1000/clicker.clickAmount);
				break;
			default:
				Thread.sleep(1000);
				break;
		}
	}
}
