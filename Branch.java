
public class Branch {
	char c;
	boolean isLeaf; 
	Branch left, right;
	
	//leaf
	public Branch(char letter) {
		isLeaf = true;
		c = letter;
	}
	
	//non-leaf
	public Branch(Branch b1, Branch b2) {
		isLeaf = false;
		left = b1;
		right = b2;
	}
	
	
	public String toString() {
		String content = "";
		content += c;
		return content;
	}

}
