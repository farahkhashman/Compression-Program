
public class Node<E> {
	
	private E info;
	private int priority = 0;
	
	public Node(E in, int p){
		info = in;
		priority = p;
	}
	
	public int GetP() {
		return priority;
	}
	
	public E GetInfo() {
		return info;
	}

}
