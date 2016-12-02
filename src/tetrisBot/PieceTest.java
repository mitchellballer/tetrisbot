package tetrisBot;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PieceTest {

    @Test
    public void test() throws IOException {
        int pixelX, pixelY;
        //Mitchell's computer, tetris.exe
        //different tests need to be written for jesse's computer
        pixelX = 353;
        pixelY = 446;

        Piece testPiece = new Piece("nextPieceImg.png");
        //Piece lPiece = new Piece("l.jpg");
        BufferedImage lk = ImageIO.read(new File("l.png"));
        System.out.println(testPiece.breakDown(lk.getRGB(356, 446))[0]);
        //BufferedImage black = ImageIO.read(new File("black.jpg"));
        double[] avgRgb = new double[3];
        int[] rgb = testPiece.breakDown(lk.getRGB(pixelX,pixelY));
        //avgRgb = testPiece.avgColor(black);
        //ensure breakDown correctly breaks down the rgb components of the black pixel
        assertEquals(rgb[0], 173);
        assertEquals(rgb[1], 173);
        assertEquals(rgb[2], 8);
        //assertEquals((int)avgRgb[0], 0);
        //assertEquals((int)avgRgb[1], 0);
        //assertEquals((int)avgRgb[2], 0);

        BufferedImage five = ImageIO.read(new File("5.png"));
        rgb = testPiece.breakDown(five.getRGB(pixelX, pixelY));
        //ensure breakDown correctly breaks down the rgb components of the white pixel
        assertEquals(rgb[0], 0);
        assertEquals(rgb[1], 0);
        assertEquals(rgb[2], 173);
        //assertEquals((int)avgRgb[0], 254);
        //assertEquals((int)avgRgb[1], 254);
        //assertEquals((int)avgRgb[2], 254);

        BufferedImage s = ImageIO.read(new File("s.png"));
        rgb = testPiece.breakDown(s.getRGB(pixelX, pixelY));
        //avgRgb = testPiece.avgColor(green);
        assertEquals(rgb[0], 0);
        assertEquals(rgb[1], 173);
        assertEquals(rgb[2], 0);
        //assertEquals((int)avgRgb[0], 34);
        //assertEquals((int)avgRgb[1], 176);
        //assertEquals((int)avgRgb[2], 76);

        BufferedImage j = ImageIO.read(new File("j.png"));
        rgb = testPiece.breakDown(j.getRGB(pixelX, pixelY));
        //avgRgb = testPiece.avgColor(j);
        assertEquals(rgb[0], 173);
        assertEquals(rgb[1], 0);
        assertEquals(rgb[2], 173);
        //assertEquals((int)avgRgb[0], 253);
        //assertEquals((int)avgRgb[1], 241);
        //assertEquals((int)avgRgb[2], 0);

        BufferedImage i = ImageIO.read(new File("i.png"));
        rgb = testPiece.breakDown(i.getRGB(pixelX, pixelY));
        //avgRgb = testPiece.avgColor(blue);
        assertEquals(rgb[0], 173);
        assertEquals(rgb[1], 0);
        assertEquals(rgb[2], 0);
        //assertEquals((int)avgRgb[0], 62);
        //assertEquals((int)avgRgb[1], 70);
        //assertEquals((int)avgRgb[2], 203);


        /*
        BufferedImage i = ImageIO.read(new File("i.png"));
        avgRgb = testPiece.avgColor(i);
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

        */
    }

}
