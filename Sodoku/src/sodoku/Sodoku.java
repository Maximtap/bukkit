package sodoku;

public class Sodoku {
	
	Integer[][] Zahlen = new Integer[9][9];
	
	public Sodoku() {
		
	}
	
	public Sodoku(Integer[][] zahlen) {
		this.Zahlen = zahlen;
	}
	
	public void setZahl(int x, int y, int Zahl){
		this.Zahlen[x][y] = Zahl;
	}
	
	public int getZahl(int x, int y) {
		return this.Zahlen[x][y];
	}
	
	public void print() {
		Integer printzahl;
		for(int i = 0; i<9; i++) {
			for(int b = 0; b<9; b++) {
				printzahl = Zahlen[i][b];
				if(printzahl == null) {
					System.out.print("+");
				} else {
				System.out.print(Zahlen[i][b]);
				}
			}
			System.out.println();
		}
	}
	
	public int[][] getKasten(int xpos, int ypos) {
		
		int[][] Kasten = new int[3][3];
		int newx = xpos * 3 -3;
		int newy = ypos * 3 -3;
		
		for(int y = 0; y<3; y++) {
			for(int x = 0; x<3; x++) {
				Kasten[x][y] = this.Zahlen[newx + x][newy + y];
			}
		}
		return Kasten;
	}
	
}
