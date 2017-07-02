package sodoku;

public class Sodokutest {
	public static void main(String[] args) {
		Integer[][] Test = new Integer[9][9];
		Test[1][1] = 1;
		Test[6][3] = 5;
		Sodoku testsod = new Sodoku(Test);
		testsod.print();
	}
	
	
}
