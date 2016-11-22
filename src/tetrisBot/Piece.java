package tetrisBot;

import java.awt.image.BufferedImage;

public class Piece {

    public int x; //x dimension size
    public int y; //y dimension size
    public String shape; //the shape of piece
    public int[][] space; //which spaces are occupied by the shape
    private double[][] control;

    public String getShape(){
        return shape;
    }


    public Piece(String s){
        control = new double[7][3];
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
        double[][] control = {{20.87777777777776, 31.915942028985462, 63.32512077294696},
                {15.80338164251205, 49.170048309178796, 59.30966183574879},
                {35.544927536231846, 57.486956521739025, 19.703864734299525},
                {67.38743961352635, 39.93768115942031, 18.131400966183584},
                {66.99661835748793, 55.07874396135265, 20.842028985507202},
                {58.21111111111114, 23.185990338164274, 52.08067632850238},
                {66.94685990338162, 21.842995169082137, 29.397584541062827}};
        double[] dif = new double[7];
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 3; j++){
                dif[i] += Math.abs(avgColor[j] - control[i][j]);
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
        if (a == 0){
            //System.out.println("probably an l");
            return new Piece("l");

        }
        if (a == 1){
            //System.out.println("probably an i");
            return new Piece("i");

        }
        if (a == 2){
            //System.out.println("probably an s");
            return new Piece("s");

        }
        if (a == 3){
            //System.out.println("probably an 7");
            return new Piece("7");

        }
        if (a == 4){
            //System.out.println("probably an square");
            return new Piece("square");

        }
        if (a == 5){
            //System.out.println("probably an t");
            return new Piece("t");

        }
        if (a == 6){
            //System.out.println("probably an z");
            return new Piece("z");

        }
        return null;
		/*
		double red = avgColor[0];
		double green = avgColor[1];
		double blue = avgColor[2];

		//if red is less than 45, it is probably an s, i or l
		if(red < 50){
			if(blue < 45){
				System.out.println("probably an s");
				return new Piece("s");
			}
			else if(green < 38){
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
		*/
    }

    /**
     * control is the rgb profiles of all pieces and their order is l, i, s, 7, square, t, z
     * @param currImg rgb profile of nextPiece
     */
    public void whichPiece(double[] currImg){
        double[][] control = {{20.87777777777776, 31.915942028985462, 63.32512077294696},
                {15.80338164251205, 49.170048309178796, 59.30966183574879},
                {35.544927536231846, 57.486956521739025, 19.703864734299525},
                {67.38743961352635, 39.93768115942031, 18.131400966183584},
                {66.99661835748793, 55.07874396135265, 20.842028985507202},
                {58.21111111111114, 23.185990338164274, 52.08067632850238},
                {66.94685990338162, 21.842995169082137, 29.397584541062827}};


    }

    public void addColumnToControl(int column, double[] rgb){
        control[column] = rgb;
    }

    public double iDiff(double[] currImg){
        double r = 15 - currImg[0];
        double g = 49 - currImg[1];
        double b = 59 - currImg[2];
        double sum = r + g + b;

        return sum;
    }

    //rotates the piece
    public void rotate(){
        int temp = x;
        x = y;
        y = temp;
        int [][] newPiece = new int[y][x];
        for (int i = 0; i <x; i++){
            for (int j = 0; j <y; j++){
                newPiece[j][i] = space[i][y-1-j];
            }
        }
        space = newPiece;
    }

    //returns the lowest occupied space for each x
    public int[] getLowestY(){
        int[] lowest = new int[x];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (space[j][i] == 1){
                    lowest[i] = j;
                    break;
                }
            }
        }
        return lowest;
    }
}
