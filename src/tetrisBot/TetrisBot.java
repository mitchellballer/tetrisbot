package tetrisBot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TetrisBot {

    public TetrisBot(){
        prepare();
    }

    private JFrame mFrame;
    private JLabel runLabel;
    private JPanel controlPanel;
    private JLabel stopLabel;
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
        //controlPanel2 = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        //controlPanel2.setLayout(new FlowLayout());
        mFrame.add(controlPanel);
        //mFrame.add(controlPanel2);
        runLabel = new JLabel("",JLabel.CENTER);
        stopLabel = new JLabel("", JLabel.CENTER);
        JButton run = new JButton("Run Bot");
        JButton stop = new JButton("Stop");
        mFrame.add(runLabel);
        mFrame.add(stopLabel);
        runLabel.setSize(300,100);
        runLabel.setVisible(true);
        stopLabel.setVisible(true);
        //stopLabel.setSize(300, 100);
        mFrame.setVisible(true);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        run.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                runBot.main();
                runLabel.setText("Bot is running");

            }

        });

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                runBot.toggleCont();
            }
        });
        controlPanel.add(run);
        controlPanel.add(stop);

    }

}
