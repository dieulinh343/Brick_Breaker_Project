package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;


public class SettingGame extends JFrame implements ActionListener{
	public SettingGame(String title){
		
        super(title);
        addKeyListener(new PressingKeys());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel(new GridLayout(0, 1));
        JPanel bottomPanel = new JPanel();
        
        JPanel paddleColorPanel = new JPanel();
        JPanel ballColorPanel = new JPanel();
        JPanel displayModePanel = new JPanel();
        JPanel difficultyPanel = new JPanel();
        
        //The settings title
        JLabel settingsLabel = new JLabel("<html><b><u>SETTINGS</u></b></html>");
        settingsLabel.setFont(new Font("Ariel", Font.BOLD, 40));
        
        //Creates the paddle color settings
        JLabel paddleColorLabel = new JLabel("Paddle colour: ");
        paddleColorLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        JRadioButton whitePaddleColorButton = new JRadioButton("White");
        JRadioButton yellowPaddleColorButton = new JRadioButton("Yellow");
        JRadioButton bluePaddleColorButton = new JRadioButton("Blue");
        JRadioButton redPaddleColorButton = new JRadioButton("Red");
        ButtonGroup paddleColorGroup = new ButtonGroup();
        
        paddleColorGroup.add(whitePaddleColorButton);
        paddleColorGroup.add(yellowPaddleColorButton);
        paddleColorGroup.add(bluePaddleColorButton);
        paddleColorGroup.add(redPaddleColorButton);
        paddleColorPanel.add(paddleColorLabel, BorderLayout.WEST);
        paddleColorPanel.add(whitePaddleColorButton, BorderLayout.EAST);
        paddleColorPanel.add(yellowPaddleColorButton, BorderLayout.EAST);
        paddleColorPanel.add(bluePaddleColorButton, BorderLayout.EAST);
        paddleColorPanel.add(redPaddleColorButton, BorderLayout.EAST);
        
      //Ball color settings
        JLabel ballColorLabel = new JLabel("Ball color:");
        ballColorLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        JRadioButton greyBallButton = new JRadioButton("Grey");
        JRadioButton redBallButton = new JRadioButton("Red");
        JRadioButton whiteBallButton = new JRadioButton("White");
        JRadioButton yellowBallButton = new JRadioButton("Yellow");
        JRadioButton blueBallButton = new JRadioButton("Blue");
        ButtonGroup ballColorGroup = new ButtonGroup();
        ballColorGroup.add(greyBallButton);
        ballColorGroup.add(redBallButton);
        ballColorGroup.add(whiteBallButton);
        ballColorGroup.add(yellowBallButton);
        ballColorGroup.add(blueBallButton);
        ballColorPanel.add(ballColorLabel, BorderLayout.WEST);
        ballColorPanel.add(greyBallButton, BorderLayout.EAST);
        ballColorPanel.add(redBallButton, BorderLayout.EAST);
        ballColorPanel.add(whiteBallButton, BorderLayout.EAST);
        ballColorPanel.add(yellowBallButton, BorderLayout.EAST);
        ballColorPanel.add(blueBallButton, BorderLayout.EAST);
	
	//display mode settings
		JLabel displayModeLabel = new JLabel("Display mode:");
	    displayModeLabel.setFont(new Font("Ariel", Font.BOLD, 20));
	    JRadioButton fullScreenButton = new JRadioButton("Fullscreen");
	    JRadioButton windowedButton = new JRadioButton("Windowed");
	    ButtonGroup displayModeGroup = new ButtonGroup();
	    displayModeGroup.add(fullScreenButton);
	    displayModeGroup.add(windowedButton);
	    displayModePanel.add(displayModeLabel, BorderLayout.WEST);
	    displayModePanel.add(fullScreenButton, BorderLayout.EAST);
	    displayModePanel.add(windowedButton, BorderLayout.EAST);
	
	    //difficulty settings
	    JLabel difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        JRadioButton easyButton = new JRadioButton("Easy");
        JRadioButton normalButton = new JRadioButton("Normal");
        JRadioButton hardButton = new JRadioButton("Hard");
        ButtonGroup difficulties = new ButtonGroup();
        difficulties.add(easyButton);
        difficulties.add(normalButton);
        difficulties.add(hardButton);
        difficultyPanel.add(difficultyLabel, BorderLayout.WEST);
        difficultyPanel.add(easyButton, BorderLayout.EAST);
        difficultyPanel.add(normalButton, BorderLayout.EAST);
        difficultyPanel.add(hardButton, BorderLayout.EAST);
        
        //Main menu at the bottom
        JButton menuButton = new JButton("MAIN MENU");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu.main(null);
            }
        });
        
        //add settings to JPanel

        topPanel.add(settingsLabel);
        middlePanel.add(paddleColorPanel);
        middlePanel.add(ballColorPanel);
        middlePanel.add(displayModePanel);
        middlePanel.add(difficultyPanel);
        bottomPanel.add(menuButton);
        
        //Setting the size of frame
        
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(topPanel, constraints);
        

        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        middlePanel.setBorder(BorderFactory.createCompoundBorder(raisedBevel, loweredBevel));
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(middlePanel, constraints);

        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(bottomPanel, constraints);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(525, 380);
        setVisible(true);
        setLocationRelativeTo(null);
        repaint();
    }
        
        public void actionPerformed(ActionEvent e) {

        }

        public void stateChanged(ChangeEvent e) {
            //choose what happens with the sliders
        }

        private class PressingKeys extends KeyAdapter
        {
            @Override
            public void keyPressed(KeyEvent e) {
                int action = e.getKeyCode();

                if(action == KeyEvent.VK_ESCAPE)
                {
                    dispose();
                    MainMenu.main(null);
                }
            }
        }
    }