package main.ChoiceWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import main.Commands;

public class OverwriteFile extends YesOrNoWindow {

	private File savefile;
	
	public OverwriteFile(String WindowName, String text, File saveFile) {
		
		super(WindowName, text);
		this.savefile = saveFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == yButtom) {
			
			try {
				
				Commands.PostSave(savefile);
				dispose();
			} 
			catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		else {
			
			try {
				
				dispose();
				Commands.PreSave();
			} 
			catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
	}
}
