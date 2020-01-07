package com.zelda.panel;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainPanel extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private static JFrame frame;
	
	public static final int WIDTH = 250;
	
	public static final int HEIGHT = 120;
	
	public static final int SCALE = 3;
	
	public MainPanel() {
		
		setPreferredSize(new Dimension(MainPanel.WIDTH * MainPanel.SCALE, MainPanel.HEIGHT * MainPanel.SCALE));
		frame = new JFrame("Zelda");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
