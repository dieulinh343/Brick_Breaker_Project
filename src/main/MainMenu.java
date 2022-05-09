package main;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public static void main(String[] args) {
		JFrame menuFrame = new JFrame("OOP Project");
		
		JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        JLabel nameJLabel = new JLabel("OOP Project");
        nameJLabel.setFont(new Font("Courier", Font.BOLD, 40));
        
        middlePanel.add(nameJLabel);
        
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Courier", Font.BOLD, 12));
        JButton scoreBoardButton = new JButton("SCOREBOARD");
        scoreBoardButton.setFont(new Font("Courier", Font.BOLD, 12));
        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setFont(new Font("Courier", Font.BOLD, 12));
        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Courier", Font.BOLD, 12));
        
        bottomPanel.add(BorderLayout.NORTH, playButton);
        bottomPanel.add(BorderLayout.CENTER, scoreBoardButton);
        bottomPanel.add(BorderLayout.CENTER, settingsButton);
        bottomPanel.add(BorderLayout.SOUTH, exitButton);

        menuFrame.add(BorderLayout.NORTH, topPanel);
        menuFrame.add(BorderLayout.CENTER, middlePanel);
        menuFrame.add(BorderLayout.SOUTH, bottomPanel);
        
        playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuFrame.dispose();
				JFrame frame = new JFrame("Brick Breaker");
				GamePanel panel = new GamePanel();
				Thread thread = new Thread(panel);
				
				frame.setLocation(500, 200);
				frame.setResizable(false);
				frame.setSize(WIDTH, HEIGHT);
				frame.add(panel);
				thread.start();
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
        
//        scoreBoardButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                menuFrame.dispose();
//                try {
//                    new ScoreboardMenu();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                new SettingGame("OOP Project");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //These are the settings for the frame to have
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setBackground(Color.BLACK);
        menuFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        menuFrame.pack();
        menuFrame.setVisible(true);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.repaint();
    }
}
