package tetrisBot;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBot {
	
	public TetrisBot(){
		prepare();
	}
	
	private JFrame mFrame;
	private JLabel runLabel;
	private JPanel controlPanel;
	
	public static void main(String args[]){
		 TetrisBot tetrisBot = new TetrisBot();
	}
	
	private void prepare(){
		mFrame = new JFrame("Tetris Bot");
		mFrame.setSize(300, 300);
		mFrame.setLayout(new GridLayout(3,1));
		mFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		controlPanel = new JPanel();
	    controlPanel.setLayout(new FlowLayout());
	    mFrame.add(controlPanel);
		runLabel = new JLabel("",JLabel.CENTER); 
		mFrame.add(runLabel);
		runLabel.setSize(350,100);
		runLabel.setVisible(true);
		mFrame.setVisible(true);
		JButton run = new JButton("Run Bot");
		run.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				runLabel.setText("Bot is running");
				
			}
		});
		controlPanel.add(run);
		
		
	}
	
}
