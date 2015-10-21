package project;

import java.util.Scanner;

public class Matrix{
	private Node[] rowhead;
	private Node[] colhead;
	
	public Matrix(int n){
		rowhead = new Node[n];
		colhead = new Node[n];
	}
	
	public Node[] getRowheader(){
		return this.rowhead;
	}
	
	public Node[] getColheader(){
		return this.colhead;
	}
	
	public void insertNode(Node node){
		if(node.getR() < 0 || node.getR() >= rowhead.length || node.getC() < 0 || node.getC() >= colhead.length){
			throw new ArrayIndexOutOfBoundsException();
		} else if (node.getValue() == 0) {
			throw new Invalid
		}
		Node curr;
		// start with rows
		if(rowhead[node.getR()] == null){			// if the head pointer is null (empty row so far)
			rowhead[node.getR()] = node;			// make the row header for that row point to the
			node.setNextr(node);	// node and then point the node back to itself.
		} else {
			curr = rowhead[node.getR()];			// need to traverse the row until at correct location
			while(curr.getNextr() != rowhead[node.getR()] && node.getC() < curr.getC()){
				curr = curr.getNextr();				// if we reach the end, append to the end
			}
			node.setNextr(curr.getNextr());
			curr.setNextr(node);
		}
		// deal with columns
		if(colhead[node.getC()] == null){			// if the head pointer is null (empty col so far)
			colhead[node.getC()] = node;
			node.setNextc(node);
		} else {
			curr = colhead[node.getC()];			// need to traverse the row until at correct location
			while(curr.getNextc() != colhead[node.getC()] && node.getR() < curr.getR()){
				curr = curr.getNextc();				// if we reach the end, append to the end
			}
			node.setNextc(curr.getNextc());
			curr.setNextc(node);
		}
	}
	
	public void initializeByInput(){
		int rid, cid, val;
		String input;
		Scanner scan = new Scanner(System.in);
		do{
			System.out.print("Enter array element as a comma-separated 3-tuple (row, column, value) no parentheses or commas:");
			
		} while(true);
	}
	
	public int[][] Matrix2Array(){
//		if(this.rowhead == null || this.colhead == null){
//			return null;
//		}
		int[][] array = new int[rowhead.length][colhead.length];
		for(int i = 0; i < rowhead.length; i++){
			Node parser = this.rowhead[i];
			if(parser == null){ continue; }
			while(parser.getNextr() != this.rowhead[i]){
				array[i][parser.getC()] = parser.getValue();
				parser = parser.getNextr();
			}	
			array[i][parser.getC()] = parser.getValue();
		}
		return array;
	}
	
	
	@Override
	public String toString(){ // Print method
		int[][] array = this.Matrix2Array();
		String output = "";
		if(array == null){ output = "Empty array"; }
		else{
			for(int i = 0; i < rowhead.length; i++){
				output += "[";
				for(int j = 0; j < colhead.length; j++){
					output += String.format("%3d", array[i][j]);
				}
				output += "  ]\n";
			}
		}
		return output;
	}
	
}
