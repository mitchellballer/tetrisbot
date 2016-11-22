package tetrisBot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class runBot implements Runnable{

    //private Well well = new Well();
    private static boolean cont;
    private Piece nextP;
    private Piece thisP;

    public static void main(){

        (new Thread(new runBot())).start();
        //System.out.println(nextPiece.shape);

    }

    public static Piece evaluateNextPiece(){
        screenShot();
        BufferedImage nextPieceImg = null;
        //try to load screenshot
        try{
            nextPieceImg = ImageIO.read(new File("ss.jpg"));
        } catch (IOException E) {
        }

        //nextPieceImg = nextPieceImg.getSubimage(840,420,45,45);
        //nextPieceImg = nextPieceImg.getSubimage(524, 450, 45, 45);
        nextPieceImg = nextPieceImg.getSubimage(815, 395, 45, 45);
        //write cropped screenshot to new file called nextpieceimg.jpg
        try {
            ImageIO.write(nextPieceImg, "jpg", new File("nextPieceImg.jpg"));
        } catch (IOException e) {
        }

        return 	toPiece(nextPieceImg);
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
        return currPiece;
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

    @Override
    public void run() {
        Well well = new Well();
        Piece next;
        int count = 0;
        cont = true;

        //click the start button
        //abstract this with global constant for screen location of start button
        //potentially just creatae "execute" method with keyword ex clickStart, drop, etc
        try {
            //Jesse's screen:click(700,560);
            //Mitchell's screen
            click(580, 370);
        } catch (AWTException e){
        }
        //wait for game to start
        try {
            TimeUnit.SECONDS.sleep(9);
        } catch (InterruptedException e) {}

        //drop first piece
        dropPiece();
        //read first piece and add it to well.
        Piece first = findFirstPiece();
        if(first.getShape().equals("square"))
            well.drop(first, 4, false);
        else
            well.drop(first, 3, false);
        //execution loop

        while(count < 40 && cont){
            //read next piece
            next = evaluateNextPiece();
            //evaluate what best move is
            //execute move
            well.getBestMove(next);
        }
    }

    private Boolean dropPiece(){
        Thread.currentThread();
        Boolean error = false;
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            error = true;
            e.printStackTrace();
        }
        bot.keyPress(KeyEvent.VK_SPACE);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            error = true;
            e.printStackTrace();
        }
        bot.keyRelease(KeyEvent.VK_SPACE);
        return !error;



    }
    //do a starting move before looping
    /**
    public void startMove() throws AWTException, InterruptedException{
        screenShot();
        Thread.currentThread();
        Robot bot = new Robot();
        thisP = evaluateNextPiece();
        bot.keyPress(KeyEvent.VK_NUMPAD8);
        Thread.sleep(100);
        bot.keyRelease(KeyEvent.VK_NUMPAD8);
        Thread.sleep(500);
        screenShot();
        Piece p = findFirstPiece();
        if (p.shape.equals("square"))
            well.drop(p,4,true);
        else
            well.drop(p, 3, true);
    }
**/
    //identifies and executes the best possible move;
    /**
    public void getBestMove(Piece p) throws AWTException, InterruptedException{
        Thread.currentThread();
        int rotations = 4;
        int idealR = 0;
        int idealX = 0;
        int max = -1000000000;
        if (p.shape.equals("s")|| p.shape.equals("z") || p.shape.equals("i"))
            rotations = 2;
        if (p.shape.equals("square"))
            rotations = 1;
        for (int i = 0; i < rotations; i++){
            for (int j = 0; j < 11-p.x; j++){
                int temp = well.hypDrop(p, j).getStrength();
                if (temp > max){
                    max = temp;
                    idealR = i;
                    idealX = j;
                }
            }
            p.rotate();
        }

        p.rotate();
        Robot bot = new Robot();
        for (int i = 0; i < idealR; i++){
            p.rotate();
            bot.keyPress(KeyEvent.VK_NUMPAD5);
            Thread.sleep(100);
            bot.keyRelease(KeyEvent.VK_NUMPAD5);
            Thread.sleep(500);
        }
        int xInit;
        if (p.shape.equals("square")){
            xInit = 4;
        } else {
            if (p.shape.equals("s") || p.shape.equals("z")){
                if (idealR == 0)
                    xInit = 3;
                else
                    xInit = 4;
            } else if (p.shape.equals("i")){
                if (idealR == 0)
                    xInit = 3;
                else
                    xInit = 5;

            } else {
                if (idealR == 1)
                    xInit = 4;
                else
                    xInit = 3;
            }
        }

        while (idealX != xInit){
            if (xInit > idealX){
                xInit--;
                bot.keyPress(KeyEvent.VK_NUMPAD4);
                Thread.sleep(100);
                bot.keyRelease(KeyEvent.VK_NUMPAD4);
                Thread.sleep(500);
            } else {
                xInit++;
                bot.keyPress(KeyEvent.VK_NUMPAD6);
                Thread.sleep(100);
                bot.keyRelease(KeyEvent.VK_NUMPAD6);
                Thread.sleep(500);
            }
        }
        bot.keyPress(KeyEvent.VK_NUMPAD8);
        Thread.sleep(100);
        bot.keyRelease(KeyEvent.VK_NUMPAD8);
        Thread.sleep(500);
        well.drop(p, idealX, true);
    }
     **/

    //very similar to findPiece. Should combine these.
    public static Piece findFirstPiece(){
        //control order is i,t,z,s,l,square,7

        BufferedImage bf = null;
        double[][] control = new double[][]{{ 52.23099415204679, 179.2280701754384, 214.3976608187133 },
                { 195.79824561403495, 71.03216374269006, 174.85964912280704 },
                { 221.32748538011697, 69.79532163742695, 97.87134502923969 },
                { 113.52631578947359, 196.15789473684202, 48.08771929824562 },
                { 64.39766081871339, 109.15789473684208, 211.03801169590636 },
                { 224.4152046783625, 186.92397660818725, 56.28947368421055 },
                { 223.1140350877193, 134.05555555555566, 48.1549707602339 }};
        try{
            bf = ImageIO.read(new File("ss.jpg"));
        } catch (IOException E) {
        }
        //jesse's computer bf = bf.getSubimage(677, 699, 18, 18);
        //Mitchell's computer:
        bf = bf.getSubimage(554, 472, 18, 18);
        try {
            ImageIO.write(bf, "jpg", new File("firstPiece.jpg"));
        } catch (IOException e) {
        }
        Piece temp = new Piece("");
        double[] avg = temp.avgColor(bf);
        double[] dif = new double[7];
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 3; j++){
                dif[i] += Math.abs(avg[j] - control[i][j]);
            }
        }
        int a = 0;
        double min = dif[0];
        for (int i = 1; i <7; i++){
            if (dif[i] < min){
                min = dif[i];
                a = i;
            }
        }
        if (a == 0)
            return new Piece("i");
        if (a == 1)
            return new Piece("t");
        if (a == 2)
            return new Piece("z");
        if (a == 3)
            return new Piece("s");
        if (a == 4)
            return new Piece("l");
        if (a == 5)
            return new Piece("square");
        if (a == 6)
            return new Piece("7");
        return null;

    }

}
