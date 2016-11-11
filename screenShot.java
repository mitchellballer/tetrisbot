package tetrisBot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;   
import javax.imageio.ImageIO;

public class screenShot {

    public static void main(String[] args) {
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
}