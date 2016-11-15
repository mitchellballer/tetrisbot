package tetrisBot;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class PieceTest {

	@Test
	public void test() throws IOException {
		
		
		Piece testPiece = new Piece("nextPieceImg.jpg");
		Piece lPiece = new Piece("l.jpg");
		BufferedImage black = ImageIO.read(new File("black.jpg"));
		double[] avgRgb = new double[3];
		int[] rgb = testPiece.breakDown(black.getRGB(1,1));
		avgRgb = testPiece.avgColor(black);
		//ensure breakDown correctly breaks down the rgb components of the black pixel
		assertEquals(rgb[0], 0);
		assertEquals(rgb[1], 0);
		assertEquals(rgb[2], 0);
		assertEquals((int)avgRgb[0], 0);
		assertEquals((int)avgRgb[1], 0);
		assertEquals((int)avgRgb[2], 0);
		
		BufferedImage white = ImageIO.read(new File("white.jpg"));
		rgb = testPiece.breakDown(white.getRGB(1,1));
		avgRgb = testPiece.avgColor(white);
		//ensure breakDown correctly breaks down the rgb components of the white pixel
		assertEquals(rgb[0], 255);
		assertEquals(rgb[1], 255);
		assertEquals(rgb[2], 255);
		assertEquals((int)avgRgb[0], 254);
		assertEquals((int)avgRgb[1], 254);
		assertEquals((int)avgRgb[2], 254);
		
		BufferedImage green = ImageIO.read(new File("green.jpg"));
		rgb = testPiece.breakDown(green.getRGB(1, 1));
		avgRgb = testPiece.avgColor(green);
		assertEquals(rgb[0], 35);
		assertEquals(rgb[1], 177);
		assertEquals(rgb[2], 77);
		assertEquals((int)avgRgb[0], 34);
		assertEquals((int)avgRgb[1], 176);
		assertEquals((int)avgRgb[2], 76);
		
		BufferedImage yellow = ImageIO.read(new File("yellow.jpg"));
		rgb = testPiece.breakDown(yellow.getRGB(1, 1));
		avgRgb = testPiece.avgColor(yellow);
		assertEquals(rgb[0], 254);
		assertEquals(rgb[1], 242);
		assertEquals(rgb[2], 0);
		assertEquals((int)avgRgb[0], 253);
		assertEquals((int)avgRgb[1], 241);
		assertEquals((int)avgRgb[2], 0);
		
		BufferedImage blue = ImageIO.read(new File("blue.jpg"));
		rgb = testPiece.breakDown(blue.getRGB(1, 1));
		avgRgb = testPiece.avgColor(blue);
		assertEquals(rgb[0], 63);
		assertEquals(rgb[1], 71);
		assertEquals(rgb[2], 204);
		assertEquals((int)avgRgb[0], 62);
		assertEquals((int)avgRgb[1], 70);
		assertEquals((int)avgRgb[2], 203);
		
		
		BufferedImage l = ImageIO.read(new File("l.jpg"));
		avgRgb = testPiece.avgColor(l);
		System.out.println("l = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		//testPiece.addColumnToControl(0, avgRgb);
		assertTrue("l".equals(testPiece.classify(avgRgb).getShape()));
		
		BufferedImage i = ImageIO.read(new File("i.jpg"));
		avgRgb = testPiece.avgColor(i);
		System.out.println("i = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("i".equals(testPiece.classify(avgRgb).getShape()));
		
		BufferedImage s = ImageIO.read(new File("s.jpg"));
		avgRgb = testPiece.avgColor(s);
		System.out.println("s = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("s".equals(testPiece.classify(avgRgb).getShape()));
		
		
		BufferedImage seven = ImageIO.read(new File("7.jpg"));
		avgRgb = testPiece.avgColor(seven);
		System.out.println("7 = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("7".equals(testPiece.classify(avgRgb).getShape()));
		
		BufferedImage square = ImageIO.read(new File("square.jpg"));
		avgRgb = testPiece.avgColor(square);
		System.out.println("square = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("square".equals(testPiece.classify(avgRgb).getShape()));
		
		BufferedImage t = ImageIO.read(new File("t.jpg"));
		avgRgb = testPiece.avgColor(t);
		System.out.println("t = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("t".equals(testPiece.classify(avgRgb).getShape()));
		
		BufferedImage z = ImageIO.read(new File("z.jpg"));
		avgRgb = testPiece.avgColor(z);
		System.out.println("z = " + avgRgb[0] + ", " + avgRgb[1] + ", " + avgRgb[2]);
		assertTrue("z".equals(testPiece.classify(avgRgb).getShape()));
	}

}
