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


public class Compression {


	static HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
	HashMap<Character, String> newMap = new HashMap<Character, String>();
	HashMap<Character, String> directory = new HashMap<Character, String>();
	HashMap<String, Character> reversed = new HashMap<String, Character>();
	PriorityQueue1<Branch> q = new PriorityQueue1<Branch>();
	Scanner scan = new Scanner(System.in);
	private String file = "Avengers";
	private String compressedfile = "Compressed";
	static String contents = "";
	int allbytes = 0;


	
	private void readFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			for (int c = in.read(); c!= -1; c= in.read()) {
				if(!myMap.containsKey((char) c)) {
					myMap.put((char)c, 1);
				}
				else{
					myMap.put((char)c, myMap.get((char) c)+1);
				}
			}		
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found :( make sure file is in the project (not source code) and "
					+ "has the correct name");
		} 
		catch (IOException e) {}
		
	}
	
	private void mostcommon() {
		for(char s: myMap.keySet()) {
			Branch c = new Branch(s);
			q.add(c, myMap.get(s));
		}
		//System.out.println(q);
	}
	
	private void tree() {
		while(q.size()>1) {
			Node<Branch> one = q.pop();
			Node<Branch> two = q.pop();
			Branch third = new Branch(one.GetInfo(), two.GetInfo());	
			q.add(third, (one.GetP()+two.GetP()));
		}
		binary(q.pop().GetInfo(), "");
		System.out.println(directory);
	}
	
	public void binary(Branch b, String code) {
		if(b.isLeaf) {
			directory.put(b.c, code);
		}
		else {
			binary(b.right, code+"1");
			binary(b.left, code+"0");
		}
	}
	
	public void compress() throws IOException {
		//BitWriter BitTrial = new BitWriter("Compressed_02");
		BufferedBitWriter bit = new BufferedBitWriter(compressedfile);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			for (int c = in.read(); c!= -1; c= in.read()) {
				String str = directory.get((char) c);
				for(int i = 0; i<str.length(); i++) {
					if(str.charAt(i)== '0')
						bit.writeBit(false);
					else if(str.charAt(i)=='1')
						bit.writeBit(true);
				}
				allbytes++;
		}
		//BitTrial.end();
		in.close();
	}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found :( make sure file is in the project (not source code) and "
					+ "has the correct name");
		} 
		catch (IOException e) {}
		bit.close();
	}
	
	public int howmany() {
		return allbytes;
	}
	
	public void decompress() throws IOException {
		BufferedWriter decompression = new BufferedWriter(new FileWriter("Decompressed"));
		String code = "";
		
		for(char w : directory.keySet()) {
			reversed.put(directory.get(w), w);
		}
		try {
			BufferedBitReader read = new BufferedBitReader(compressedfile);
			while(read.hasNext()) {
				boolean bit = read.readBit();
				if(bit)
					code+="1";
				else if(!bit)
					code+="0";
				
				if(reversed.containsKey(code)) {
					char c = reversed.get(code);
					decompression.write(c);
					code = "";
				}
			}
			read.close();
		}
		catch(FileNotFoundException e) {}
		decompression.close();
	}

	public static void main(String[] args) throws IOException {
		Compression test = new Compression();
		test.readFile();
		test.mostcommon();
		System.out.println();
		test.tree();
		test.compress();
		//System.out.print(myMap);
		//System.out.println();
		//System.out.println(contents);
		//test.decompress();
	}

}
