package main.Buttons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class ColoredButton extends JButton implements MouseListener {

	private Color primaryColor;
	private Color secondNormalColor = Color.GRAY;
	private Color secondHoverColor = Color.GRAY.brighter();
	
	private boolean isHover;
	
	public ColoredButton(String text, Color c1) {
		
		super(text);
		setContentAreaFilled(false);
		setFocusable(false);
		setOpaque(false);
		isHover = false;
		primaryColor = c1;
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics graphic) {
		
		Color color2 = ((isHover) ? secondHoverColor : secondNormalColor);
		
		Graphics2D graphic2d = (Graphics2D) graphic.create();
		graphic2d.setPaint(new GradientPaint(
				new Point(0, 0), color2, new Point(0, getHeight()), primaryColor));
		graphic2d.fillRect(0, 0, getWidth(), getHeight());
		graphic2d.dispose();
		super.paintComponent(graphic);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {

		isHover = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {

		isHover = false;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		primaryColor = secondNormalColor;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
