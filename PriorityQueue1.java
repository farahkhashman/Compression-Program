import java.util.ArrayList;

public class PriorityQueue1<E> {
	private ArrayList<Node<E>> queue = new ArrayList<Node<E>>();
	
	public void add(E info, int p) {
		Node<E> n = new Node<E>(info, p);
		
		int start = 0;
		int end = queue.size()-1;
		int mid = 0;
		
		if(queue.isEmpty()) {
			queue.add(n);
			return;
		}
		
		if(queue.get(0).GetP()<n.GetP()) {
			queue.add(0, n);
			return;
		}
		if(queue.get(queue.size()-1).GetP()>n.GetP()) {
			queue.add(n);
			return;
		}
		

		while(start<=end) {
			mid = (start+end)/2;
			if(queue.get(mid).GetP() > n.GetP()) {
				start = mid+1;
			}
			else if(queue.get(mid).GetP() < n.GetP()) {
				end = mid-1;
			}
			else {
				queue.add(mid, n);
				return;
			}
		}
			queue.add(start, n);
		
		
	}
	
	 public String toString() {
		String output = "";
		for(Node<?> r: queue) {
			output += r.GetInfo() +" "+ r.GetP();
			output += "\n";
		}
		return output;
	}
	
	public Node<E> pop(){
		return queue.remove(queue.size()-1);
		
	}
	
	public int size() {
		return queue.size();
	}
	
	

	public static void main(String[] args) {
		PriorityQueue1<String> test = new PriorityQueue1<String>();
		test.add("D", 20);
		test.add("E", 2);
		test.add("C", 30);
		test.add("A", 100);
		test.add("B", 50);
		test.add("B", 50);
		System.out.println(test.toString());
	}

}
