import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;  

public class BitWriter{
	
	private OutputStream stream;
	private int BitsWritten; 
	private 	int TotalBytes;
	private String str;
	
	public BitWriter(String filename) throws IOException{
			try {
				stream = new FileOutputStream(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BitsWritten=0;
			str = "";
		}
	public void total(int bits) {
		TotalBytes = bits;
	}
	
	public void writing(boolean b) throws IOException {
		BitsWritten++;
		int Zeros = 0;
		int BytesWritten = 0;
		byte y = 00000000;
		byte t = 00000001;
		
		
		if(!b) 
			str+="0";
		else if(b) 
			str+="1";

		
		//Method One: String to Integer to Byte
		/*if(BitsWritten == 8) {
		 	System.out.print("str: ");
			System.out.println(str);
			Byte a= (byte)(int)Integer.valueOf(str, 2);
			System.out.println(Integer.toBinaryString(Integer.valueOf(str, 2)));
			stream.write(a);
			numBitsWritten = 0;
			str="";
			written++;
			}
		 */
			
			
		//Method Two: Bit Manipulation	
		if(BitsWritten == 8) {
			System.out.print("str: ");
			System.out.println(str);
			for(int j = 0; j<str.length()-1; j++) {
				BytesWritten++;
				if(str.charAt(j)=='1') {
					 y = (byte) (y|t);
					 y = (byte) (y << 1);
				}
				else if(str.charAt(j)=='0') {
					y = (byte) (y << 1);
				}
			}	
			if(str.charAt(str.length()-1) =='1')
				y = (byte) (y|t);
			String s1 = String.format("%8s", Integer.toBinaryString(y & 0xFF)).replace(' ', '0');
			System.out.println(s1);
			stream.write(y);
			BitsWritten = 0;
			BytesWritten++;
			str="";
		}
	}
	
		
	public void end() throws IOException {
		int Zeros = 0;
		int BytesWritten = 0;
		byte y = 00000000;
		byte t = 00000001;
		System.out.print("str: ");
		System.out.println(str);
		for(int j = 0; j<str.length(); j++) {
			BytesWritten++;
			if(str.charAt(j)=='1') {
				 y = (byte) (y|t);
				 y = (byte) (y << 1);
			}
			else if(str.charAt(j)=='0') {
				y = (byte) (y << 1);
			}
		}
		Zeros = (8 - str.length());
		y = (byte) (y << (Zeros-1));
		String s1 = String.format("%8s", Integer.toBinaryString(y & 0xFF)).replace(' ', '0');
		System.out.println(s1);
		stream.write(y);
		stream.write(-1);
		stream.write(Zeros);
		System.out.println(Zeros);
	}
	 
	
	public void close() throws IOException {
		stream.close();
	}


}

