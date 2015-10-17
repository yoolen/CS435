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
		Node curr;
		// start with rows
		if(rowhead[node.getR()] == null){			// if the head pointer is null (empty list so far)
			rowhead[node.getR()] = node;			// make the row header for that row point to the
			node.setNextr(rowhead[node.getR()]);	// node and then point the node back to itself.
		} else {
			curr = rowhead[node.getR()];			// need to traverse the row until at correct location
			while(curr.getNextr() != rowhead[node.getR()] && node.getC() < curr.getC()){
				curr = curr.getNextr();
			}
			node.setNextr(curr.getNextr());
			curr.setNextr(node);
		}
		// deal with columns
		if(colhead[node.getC()] == null){
			
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
