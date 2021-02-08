package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.StyledDocument;

import main.ConsoleListeners.Action;

public class Console {

	public JFrame frame;
	public JTextPane console;
	public JTextField input;
	public JScrollPane scrollpane;
	
	public StyledDocument document;
	public AutoClicker clicker;
	
	ConsoleListeners listener;
	boolean trace = false;
	
	public Console() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
				
			e.printStackTrace();
		} 
		
		listener = new ConsoleListeners();
		clicker = new AutoClicker();
		
		frame = new JFrame();
		frame.setTitle("Auto Klicker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		frame.add(input, BorderLayout.SOUTH);
		frame.add(scrollpane, BorderLayout.CENTER);
		frame.setSize(400, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setVisible(true);
	}
	
	public void BackgroundTask() {
		
		SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {

			@Override
			protected Object doInBackground() throws Exception {
				
				while(true) {
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
		};
		
		worker.execute();
	}
}
