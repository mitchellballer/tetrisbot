package tetrisBot;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.*;

public class runBot {
		
	private Well well = new Well();
	private static boolean cont;
	
	
	public static void main(){
		//click the start button
		try {
			click(370,598);
		} catch (AWTException e){
		}
		//wait for game to start
		try {
			TimeUnit.SECONDS.sleep(9);
		} catch (InterruptedException e) {
		}
		//while(cont){
		
		int count = 0;
		while(count < 20){
		evaluateNextPiece();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}
		count ++;
		}
		
		//System.out.println(nextPiece.shape);
		
	}
	
	public static void evaluateNextPiece(){
		screenShot();
		BufferedImage nextPieceImg = null;
		//try to load screenshot
		try{
			nextPieceImg = ImageIO.read(new File("ss.jpg"));
		} catch (IOException E) {
		}
		
		//nextPieceImg = nextPieceImg.getSubimage(840,420,45,45);
		nextPieceImg = nextPieceImg.getSubimage(524, 450, 45, 45);
		//write cropped screenshot to new file called nextpieceimg.jpg
		try {
			ImageIO.write(nextPieceImg, "jpg", new File("nextPieceImg.jpg"));
		} catch (IOException e) {
		}
		
		Piece nextPiece = toPiece(nextPieceImg);
	}
	public static Piece toPiece(BufferedImage img){
		BufferedImage var = null;
		Piece currPiece = new Piece("");
		currPiece = currPiece.classify(currPiece.avgColor(img));
		/*
		try{
			var = ImageIO.read(new File("i.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("i");
		}
		try{
			var = ImageIO.read(new File("l.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("l");
		}
		try{
			var = ImageIO.read(new File("7.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("7");
		}
		try{
			var = ImageIO.read(new File("square.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("square");
		}
		try{
			var = ImageIO.read(new File("t.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("t");
		}
		try{
			var = ImageIO.read(new File("s.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("s");
		}
		try{
			var = ImageIO.read(new File("z.jpg"));
		} catch (IOException E) {
		}
		if (Piece.Compare(img,var)){
			return new Piece("z");
		}
		*/
		return null;
	}
	
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public static void screenShot() {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "ss." + format;

            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage ss = robot.createScreenCapture(screenSize);
            ImageIO.write(ss, format, new File(fileName));
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }
	
	
	public static boolean toggleCont(){
		if(cont)
			cont = false;
		else
			cont = true;
		
		return true;
	}
	
	public boolean getCont(){
		return cont;
	}
	
	
}
