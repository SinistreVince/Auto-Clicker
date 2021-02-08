package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConsoleListeners {

	private Mouse mouse;
	private Key key;
	private Action action;
	
	public ConsoleListeners() {
		
		mouse = new Mouse();
		key = new Key();
		action = new Action();
	}
	
	public Mouse getMouseListener() {
		
		return mouse;
	}
	
	public Key getKeyListener() {
		
		return key;
	}
	
	public Action getActionListener() {
		
		return action;
	}
	
	public class Mouse implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Programm.mode == Mode.Setup) {
				if(Programm.setupProgress == 1) {
					Programm.console.clicker.simulatedButtom = e.getButton();
				}
				if(Programm.setupProgress == 2) {
					Programm.console.clicker.triggerButtom = e.getButton();
				}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Key implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Action implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String text = Programm.console.input.getText();
			if(Programm.mode != Mode.Setup) {
				
				if(text.length() > 1) {
					
					Programm.doCommand(text);
					Commands.scrollButtom();
					Commands.clearInputCommand();
				}
			}
			else {
				
				String[] s = text.split(" ");
				if(Programm.setupProgress == 3) {
						
						Programm.console.clicker.clickAmount = Integer.parseInt(s[0]);
						Commands.clearInputCommand();
						Programm.mode = Mode.Edit;
						Programm.setupProgress = -1;
						Commands.printProduct(true);
						Commands.println("Setup done.", false, Color.GREEN);
				}
			}
		}
	}
}
