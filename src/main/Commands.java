package main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import main.ChoiceWindow.OverwriteFile;
import main.ChoiceWindow.YesOrNoWindow;

public class Commands {
	
	public static void print(String s, boolean trace) {
		
		print(s, trace, Color.WHITE);
	}
	
	public static void print(String s, boolean trace, Color c) {
		
		Style style = Programm.console.console.addStyle("Style", null);
		StyleConstants.setForeground(style, c);
		
		if(trace) {
			
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			String caller = elements[0].getClassName();
			
			s = caller + " -> " + s;
		}
		
		try {
			
			Programm.console.document.insertString(Programm.console.document.getLength(), s, style);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	public static void println(String s, boolean trace) {
		
		println(s, trace, Color.WHITE);
	}
	
	public static void println(String s, boolean trace, Color c) {
		
		print(s + "\n", trace, c);
	}
	
	public static void printEmptyln() {
		
		println("", false);
	}
	
	public static void clear() {
		
		try {
			
			Programm.console.document.remove(0, Programm.console.document.getLength());
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void printProduct(boolean clear) {
		
		if(clear) {
			
			clear();
		}
		print("Auto Klicker ", false, Color.ORANGE); print("v0.2-alpha ", false); println("© 2021 Sinistre_Vince", false, new Color(170, 170, 170));
		printEmptyln();
		println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SS")), false);
		printEmptyln();
		
	}
	
	public static void clearInputCommand() {
		
		Programm.console.input.setText("");
	}
	
	public static void Help() {
		
		print("help ", false); println("~ show all commands", false, Color.LIGHT_GRAY);
		print("setup ", false); println("~ setup the AutoClick Configuration", false, Color.LIGHT_GRAY);
		print("run ", false); println("~ start Auto Clicker", false, Color.LIGHT_GRAY);
		print("stop ", false); println("~ stop Auto Clicker", false, Color.LIGHT_GRAY);
		print("save ", false); println("~ saving current setup", false, Color.LIGHT_GRAY);
		print("load ", false); println("~ load a setup from a file", false, Color.LIGHT_GRAY);
	}
	
	public static void SetupFirst() {
		
		println("Press mouse key for simulated click", false);
		Programm.mode = Mode.Setup;
		Programm.console.input.setEditable(false);
		Programm.console.clicker.simulatedButtom = -1;
		Programm.console.clicker.triggerButtom = -1;
		Programm.setupProgress = 1;
	}
	
	public static void SetupSecond() {
		
		printProduct(true);
		println("Press mouse key for trigger", false);
		Programm.setupProgress = 2;
	}
	
	public static void SetupThird() {
		
		printProduct(true);
		println("Enter the amount of the clicks per second", false);
		Programm.setupProgress = 3;
		Programm.console.input.setEditable(true);
		Programm.console.clicker.isSetupped = true;
	}
	
	public static void Run() {
		
		if(Programm.console.clicker.isSetupped) {
			
			println("Simulated mouse key: " + Programm.console.clicker.simulatedButtom, false);
			println("Trigger mouse key: " + Programm.console.clicker.triggerButtom, false);
			println("Clicks per second: " + Programm.console.clicker.clickAmount, false);
			Programm.mode = Mode.Idle;
		}
		else {
			
			println("Can't start Auto Clicker. \nReason: Empty Setup", false, Color.RED);
		}
	}
	
	public static void Stop() {
		
		if(Programm.mode == Mode.Idle || Programm.mode == Mode.Run) {
			
			println("Auto Clicker was stopped.", false, Color.GREEN);
			Programm.mode = Mode.Edit;
		}
		else {
			
			println("Can't stop Auto Clicker. \nReason: Auto Clicker has not started.", false, Color.RED);
		}
	}
	
	public static void PreSave() throws IOException {
		
		if(!Programm.console.clicker.isSetupped) {
			
			print("You haven't a Setup to save!", false, Color.RED);
			return;
		}
		
		if(Programm.console.fileChooser.showSaveDialog(Programm.console.console) == JFileChooser.APPROVE_OPTION) {
			
			
			File file = Programm.console.fileChooser.getSelectedFile();
			String filename = file.toString();
			
			if(!filename.endsWith(".datsetup")) {
				
				filename += ".datsetup";
				file = new File(filename);
			}
			if(file.exists()) {
				
				new OverwriteFile("Overwrite File", "Would you overwrite the file?", file);
				return;
			}
			PostSave(file);
		}
		else {
			
			print("Save progress was canceled.", false, Color.RED);
		}
	}
	
	public static void PostSave(File savefile) throws IOException {
		
		FileWriter writer = new FileWriter(savefile);
		writer.write(Programm.console.clicker.simulatedButtom);
		writer.write(Programm.console.clicker.triggerButtom);
		writer.write(Programm.console.clicker.clickAmount);
		writer.close();
		print("Successfully saved.", false, Color.GREEN);
	}
	
	public static void Load() throws IOException  {
		
		if(Programm.console.fileChooser.showOpenDialog(Programm.console.console) == JFileChooser.APPROVE_OPTION) {
			
			File file = Programm.console.fileChooser.getSelectedFile();
			
			if(file.toString().endsWith(".datsetup")) {
				
				FileReader reader = new FileReader(file);
				Programm.console.clicker.simulatedButtom = reader.read();
				Programm.console.clicker.triggerButtom = reader.read();
				Programm.console.clicker.clickAmount = reader.read();
				reader.close();
				
				Programm.console.clicker.isSetupped = true;
				
				print("Sucessfully loaded.", false, Color.GREEN);
			}
			else {
				
				print("Data format is not supported!", false, Color.RED);
			}
		}
	}
}
