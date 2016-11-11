package tetrisBot;

import java.util.*;
import java.io.*;
import java.awt.*;

public class Piece {

	private int x; //x dimension size
	private int y; //y dimension size
	private String shape; //the shape of piece
	private int[][] space; //which spaces are occupied by the shape
	
	public Piece(String s){
		if (s.equals("l")){
			shape = "l";
			x = 3;
			y = 2;
			space = new int[][]{{1,1,1},{1,0,0}};
		}else if (s.equals("t")){
			shape = "t";
			x = 3;
			y = 2;
			space = new int[][]{{1,1,1},{0,1,0}};
		}else if (s.equals("i")){
			shape = "i";
			x = 4;
			y = 1;
			space = new int[][]{{1,1,1,1}};
		}else if (s.equals("square")){
			shape = "square";
			x = 2;
			y = 2;
			space = new int[][]{{1,1},{1,1}};
		}else if (s.equals("s")){
			shape = "s";
			x = 3;
			y = 2;
			space = new int[][]{{1,1,0},{0,1,1}};
		}else if (s.equals("z")){
			shape = "z";
			x = 3;
			y = 2;
			space = new int[][]{{0,1,1},{1,1,0}};
		}else if (s.equals("7")){
			shape = "7";
			x = 3;
			y = 2;
			space = new int[][]{{1,1,1},{0,0,1}};
		}
	}
}
