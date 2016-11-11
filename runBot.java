package tetrisBot;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class runBot {
	
	private Well well = new Well();
	
	public static void main(){
		try {
			click(690,560);
		} catch (AWTException e){
		}
		
	}
		
	
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
