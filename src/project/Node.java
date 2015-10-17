package project;

public class Node {
	private int row;
	private int col;
	private int value;
	private Node nextr;
	private Node nextc;
	
	public Node(int row, int col, int value, Node nextr, Node nextc){
		setRow(row);
		setCol(col);
		setValue(value);
		setNextr(nextr);
		setNextc(nextc);
	}
	
	public int getR() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getC() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNextr() {
		return nextr;
	}

	public void setNextr(Node nextr) {
		this.nextr = nextr;
	}

	public Node getNextc() {
		return nextc;
	}

	public void setNextc(Node nextc) {
		this.nextc = nextc;
	}
}
