package Enderschwert;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class enderschwert {
	public void sd() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("Test.file"));
			out.write("test");
			out.newLine();
		} catch(Exception ex) {
        
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception ex) {
               
				}
			}
		}
	}
}