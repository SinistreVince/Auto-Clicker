package main;

import java.awt.Color;

import javax.swing.SwingUtilities;

public class Programm {

	public static Mode mode = Mode.Edit;
	public static Console console;
	public static byte setupProgress;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				console = new Console();
				Commands.printProduct(false);
				Commands.println("Enter 'help' for the command list.", false);
				console.BackgroundTask();
			}
		});
	}
	
	public static void doCommand(String command) {
		
		Commands.printProduct(true);
		Commands.println(command, false);
		Commands.printEmptyln();
		String[] s = command.split(" ");
		
		if(mode != Mode.Setup) {
			switch (s[0]) {
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
				default:
					Commands.println("Unkown Command", false, Color.RED);
					break;
			}
		}
	}
	
	
}
