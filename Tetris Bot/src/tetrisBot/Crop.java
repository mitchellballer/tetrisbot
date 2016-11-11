package tetrisBot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Crop {
	public static void main(String args[]){
		BufferedImage nextPiece = null;
		try{
			nextPiece = ImageIO.read(new File("z.jpg"));
		} catch (IOException E) {
		}
		nextPiece = nextPiece.getSubimage(815,400,45,45);
	    try {
			ImageIO.write(nextPiece, "jpg", new File("z.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
