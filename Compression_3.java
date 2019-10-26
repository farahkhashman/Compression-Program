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

public class Compression_3 {

	static HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
	HashMap<Character, String> newMap = new HashMap<Character, String>();
	HashMap<Character, String> directory = new HashMap<Character, String>();
	PriorityQueue1<Branch> q = new PriorityQueue1<Branch>();
	Scanner scan = new Scanner(System.in);
	private String file = "Avengers";
	private String mapfile = "Map";
	
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
	
	
	public void map() throws IOException {
		BufferedWriter map = new BufferedWriter(new FileWriter(mapfile));
		map.write(directory.toString());
		map.close();
	}
	
	public static void main(String[] args) throws IOException {
		Compression_3 test = new Compression_3();
		test.readFile();
		test.mostcommon();
		test.tree();
		test.map();

	}

}

//String s = "";
//for(char c: directory.keySet()) {
//	s = c +" "+ directory.get(c) +"\n";
//	map.write(s);
//}
