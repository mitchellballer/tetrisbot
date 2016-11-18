package tetrisBot;

import java.awt.BorderLayout;
import java.lang.*;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Well {
	public int[][] well; //the board
	String emptyWell = "abcdefg \n" + '\n' + "jklmnop";
	public static void main(String[] args){
		Well well = new Well();
		
		well.wellVisualize();
	}
	
	
	
	public Well(){
		well = new int[20][10];
		/*
		for(int i = 0; i < well.length; i ++){
			for(int j = 0; j < well[i].length; j ++){
				well[i][j] = 0;
			}
		}
		*/
	}
	
	public Well(Well w){
		well = w.well;
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
	
	//gets the occupied space for a given x
	public int getHighest(int x){
		for (int i = 19; i >= 0; i--){
			if (well[i][x] == 1){
				return i+1;
			}
		}
		return 0;
	}
	
	//checks if a line is complete for a given y
	public boolean isComplete(int y){
		for (int i = 0; i < 10; i++){
			if (well[y][i] == 0)
				return false;
		}
		return true;
	}
	
	//removes a line
	public void remove(int y){
		outer:
		for (int i = y; i < 20; i++){
			boolean empty = true;
			for (int j = 0; j < 10; j++){
				well[i][j] = well[i+1][j];
				if (well[i][j] == 1)
					empty = false;
			}
			if (empty)
				break outer;
			if (i == 19){
				for (int j = 0; j < 10; j++){
					well[i][j] = 0;
				}
			}
		}
	}
	
	//drops a piece into the well at position x and removes any complete lines afterwards
	public void drop(Piece p, int x, boolean remove){
		int[] lowest = p.getLowestY();
		int firstContact = 0;
		int pieceContact = 0;
		for (int i = 0; i < p.x; i++){
			int contact = this.getHighest(x+i)-lowest[i];
			if (firstContact < contact){
				firstContact = contact;
				pieceContact = lowest[i];
			}
		}

		firstContact -= pieceContact;
		for (int i = 0; i < p.x; i++){
			for (int j = 0; j < p.y; j++){
				well[firstContact + j][x+i]=p.space[j][i];
			}
		}
		if (remove){
			for (int j = firstContact + p.y-1; j >= firstContact; j--){
				if (isComplete(j))
					remove(j);
			}
		}
		
	}
	
	//returns what a well would look like if the piece had been dropped without changing the well itself
	public Well hypDrop(Piece p, int x){
		Well temp = new Well(this);
		temp.drop(p,x,false);
		return temp;
	}
	
	//returns the number of holes for strength calculation
	public int getHoles(){
		int count = 0;
		outer:
		for (int i = 0; i < 19; i++){
			boolean empty = true;
			for (int j = 0; j < 10; j++){
				if (well[i][j] == 0){
					if (well[i+1][j] == 1)
						count++;
				} else {
					empty = false;
				}
			}
			if (empty)
				break outer;
		}
		return count;
	}
	
	//returns the aggregate height for strength calculation
	public int getAggHeight(){
		int count = 0;
		for (int i = 0; i < 10; i++){
			count += this.getHighest(i);
		}
		return count;
	}
	
	//returns the number of completed lines for strength calculation
	public int getFullLines(){
		int count = 0;
		for (int i = 0; i < 20; i++){
			if (this.isComplete(i))
				count++;
		}
		return count;
	}
	
	//returns the bumpiness number for strength calculation
	public int getBumpiness(){
		int count = 0;
		for (int i = 0; i<9; i++){
			count+=Math.abs(this.getHighest(i)-this.getHighest(i+1));
		}
		return count;
	}
	
	//gets the strength of a well
	public int getStrength(){
		int strength;
		int a = -51;
		int b = 76;
		int c = -35;
		int d = -18;
		strength = a*getAggHeight() + b*getFullLines() + c*getHoles() + d*getBumpiness();
		return strength;
	}
	

}
