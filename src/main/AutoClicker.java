package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class AutoClicker implements NativeMouseListener {

	private Robot robot;
	public int clickAmount;
	
	public int simulatedButtom = -1;
	public int triggerButtom = -1;
	public boolean isSetupped;
	
	public AutoClicker() {
		
		try {
			robot = new Robot();
			clickAmount = 0;
			isSetupped = false;
			
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
			logger.setUseParentHandlers(false);
			
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeMouseListener(this);
		} 
		catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void autoMouseClick(int buttom) throws InterruptedException {
		if(buttom == 1) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			//Thread.sleep(1);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	}
	
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Programm.mode == Mode.Idle) {
			if(arg0.getButton() == triggerButtom) {
				Programm.mode = Mode.Run;
			}
		}
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Programm.mode == Mode.Run) {
			if(arg0.getButton() == triggerButtom) {
				Programm.mode = Mode.Idle;
			}
		}
	}
}
