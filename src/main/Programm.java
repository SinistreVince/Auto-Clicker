package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.SwingUtilities;

import main.ChoiceWindow.YesOrNoWindow;

public class Programm {

	public static Mode mode = Mode.Edit;
	public static Console console;
	public static byte setupProgress;
	
	public static void main(String[] args) throws Exception {
		
		console = new Console();
		Commands.printProduct(false);
		Commands.println("Enter 'help' for the command list.", false);
		
		while(true) {
			
			console.BackgroundTask();
		} 
	}
	
	public static void doCommand(String command) throws IOException {
		
		Commands.printProduct(true);
		Commands.println(command, false);
		Commands.printEmptyln();
		String[] s = command.split(" ");
		
		if(mode != Mode.Setup) {
			switch (s[0].toLowerCase()) {
				case "help":
					Commands.Help();
					break;
				case "setup":
					Commands.SetupFirst();
					break;
				case "run":
					Commands.Run();
					break;
				case "stop":
					Commands.Stop();
					break;
				case "save":
					Commands.PreSave();
					break;
				case "load":
					Commands.Load();
					break;
				default:
					Commands.println("Unkown Command", false, Color.RED);
					break;
			}
		}
	}
}
