package tetrisBot;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Well {
	int[][] well;
	String emptyWell = "abcdefg \n" + '\n' + "jklmnop";
	public static void main(String[] args){
		Well well = new Well();
		
		well.wellVisualize();
	}
	
	
	
	public Well(){
		well = new int[20][10];
		for(int i = 0; i < well.length; i ++){
			for(int j = 0; j < well[i].length; j ++){
				well[i][j] = 0;
			}
		}
	}
	
	public void wellVisualize(){
		JFrame frame = new JFrame("current well");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(20,10));
		JLabel emptyLabel = new JLabel(emptyWell);
		
		//frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		for(int i = 0; i < 200; i ++){
			if(well[i/20][i%10] == 0)
				frame.add(new JLabel("0"));
			else
				frame.add(new JLabel("1"));
			
		}
		//4. Size the frame.
		frame.pack();

		frame.setSize(300, 400);

		//5. Show it.
		frame.setVisible(true);
		
	}
	
	
	public void printWell(){
		for(int i = 0; i < well.length; i ++){
			for(int j = 0; j < well[i].length; j ++){
				System.out.print(" " + well[i][j]);
			}
			System.out.println();
		}
	}
	
	public int[][] getWell(){
		return well;
	}
	
	public boolean manuallyAdd(int x, int y){
		if(well[x][y] == 0){
			well[x][y] = 1;
			return true;
		}
		else
			return false;
		
	}
	

}
