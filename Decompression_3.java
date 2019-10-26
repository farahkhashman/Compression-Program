import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Decompression_3 {
	HashMap<Character, String> directory = new HashMap<Character, String>();
	HashMap<String, Character> reversed = new HashMap<String, Character>();
	
	
	public void decompress() throws IOException {
		BufferedWriter decompression = new BufferedWriter(new FileWriter("DecompressedMap"));
		Character key = null;
		String code = "";
		int position = 1;
		boolean t = false;
		boolean d = false;
		
		BufferedReader readmap = new BufferedReader(new FileReader("Map"));
		for(int c = readmap.read(); c!= -1; c= readmap.read()) {
			if(position == 1) {
				key = (char) readmap.read();
				//System.out.print(key);
				position++;
				d = true;
				continue;
			}
			
			if(c == 44 && readmap.read()==32) {
				c = readmap.read();
				//System.out.println((char) c);
				t = true;
				d = false;
				directory.put(key, code);
			}
			
			if(d) {
				if(c == 61) {
					c = readmap.read();
				}
				code += (char) c;
			}
		
			
			if(t) {
				key = (char) c;
				d = true;
				t = false;
				code = "";
			}
			

		}
		
		System.out.println(directory.toString());
		
	}
	
	public static void main(String[] args) throws IOException {
		Decompression_3 test = new Decompression_3();
		test.decompress();

	}

}
