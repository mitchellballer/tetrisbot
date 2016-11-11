package tetrisBot;

import java.util.*;

import javax.imageio.ImageIO;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece {

	public int x; //x dimension size
	public int y; //y dimension size
	public String shape; //the shape of piece
	public int[][] space; //which spaces are occupied by the shape
	
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
	
	
	public static boolean Compare(BufferedImage img1, BufferedImage img2){
		int y = img1.getHeight();
		int x = img1.getWidth();
		int missCount = 0;
		if (x != img2.getWidth() || y != img2.getHeight())
			return false;
		for (int i = 0; i < x; i++){
			for (int j = 0; j < y; j++){
				System.out.println(img1.getRGB(i,j));

				if (img1.getRGB(i,j) != img2.getRGB(i,j))
					missCount++;
			}
		}
		if (missCount > 10){
			System.out.println(missCount);
			return false;
		} else
			return true;
	}
	
	/**
	 * method that "explodes" the integer representation of the color of a pixel into its red, green and blue components 
	 *xxxxpixel color binary form: bits 0-7 represent blue, 8- 15 represent green and 16-23 represent red. "alpha channel" bits are 24-31
	 * format visualized AAAARRRRGGGGBBBB
	 * @param pixel a particular pixel's color given in the default RBG color model: TYPE_INT_ARGB
	 * @return integer array consisting of three entries corresponding to the R, G and B values of the given pixel.
	 */
	public int[] breakDown(int pixel){
		//int red, green, blue;
		//blue = (int)(pixel % Math.pow(2, 7));
		//green = (int)(pixel % Math.pow(2, 14)/Math.pow(2, 7));
		//red = (int)(pixel % Math.pow(2, 21)/Math.pow(2, 14));
		
		int alpha = (pixel >> 24) & 0xFF;
		int red =   (pixel >> 16) & 0xFF;
		int green = (pixel >>  8) & 0xFF;
		int blue =  (pixel      ) & 0xFF;
		int[] rgb = {red, green, blue};
		return rgb;
	}
	
	public double[] avgColor(BufferedImage img){
		int height = img.getHeight();
		int width = img.getWidth();
		int[] currRgb = new int[3];
		double[] avgRgb = new double[3];
		double[] diff = new double[3];
		
		for(int i = 0; i < width; i ++){
			for(int j = 0; j < height; j ++){
				//get rgb of current pixel
				currRgb = breakDown(img.getRGB(i, j));
				for(int c = 0; c < 3; c ++){
					//add rgb of current pixel to average.
					diff[c] = currRgb[c] - avgRgb[c];
					avgRgb[c] += diff[c]/ ((i + 1) * height + j + 1);
				}
			}
		}
		return avgRgb;
	}
	
	/**
	 * typical color profiles for all shapes:
	 * 'l': R, G, B Blueish
	 * 
	 * @param avgColor
	 * @return
	 */
	public Piece classify(double[] avgColor){
		double red = avgColor[0];
		double green = avgColor[1];
		double blue = avgColor[2];
		
		//if red is less than 45, it is probably an s, i or l
		if(red < 45){
			if(blue < 45){
				System.out.println("probably an s");
				return new Piece("s");
			}
			else if(green < 40){
				System.out.println("probably an l");
				return new Piece("l");
			}
			else{
				System.out.println("probably an i");
				return new Piece("i");
			}
		}
		//if red > 45 then it is probably a 7, square, t or z
		else{
			//if green < 40 then it is probably a square
			if(green > 45){
				System.out.println("probably a square");
				return new Piece("square");
			}
			else if(green > 30){
				System.out.println("probably a 7");
				return new Piece("7");
			}
			else if(blue > 40){
				System.out.println("probably a t");
				return new Piece("t");
			}
			else{
				System.out.println("probably a z");
				return new Piece("z");
			}
		}
	}
}
