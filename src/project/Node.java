package project;

public class Node {
	int row;
	int col;
	int value;
	Node nextr;
	Node nextc;
	
	public Node(int row, int col, int value, Node nextr, Node nextc){
		this.row = row;
		this.col = col;
		this.value = value;
		this.nextr = nextr;
		this.nextc = nextc;
	}
}
