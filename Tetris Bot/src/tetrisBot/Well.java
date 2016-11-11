package tetrisBot;

public class Well {
	int[][] well;
	
	public Well(){
		well = new int[20][10];
		for(int i = 0; i < well.length; i ++){
			for(int j = 0; j < well[i].length; j ++){
				well[i][j] = 0;
			}
		}
	}
	
	public void printWell(){
		for(int i = 0; i < well.length; i ++){
			for(int j = 0; j < well[i].length; j ++){
				System.out.print(" " + well[i][j]);
			}
			System.out.println();
		}
	}
	
	public int[][] getWell(){
		return well;
	}
	

}
