package project;

import java.util.Scanner;

public class Matrix{
	private Node[] rowhead;
	private Node[] colhead;
	private int size = 0;

	public Matrix(int n){
		rowhead = new Node[n];
		colhead = new Node[n];
		size = n;
	}
	
	public int getSize(){
		return this.size;
	}

	public void insertNode(Node node){
		if (node.getValue() == 0) {
			// do nothing
		} else {
			Node curr;
			// start with rows
			if(rowhead[node.getR()] == null){			// if the head pointer is null (empty row so far)
				rowhead[node.getR()] = node;			// make the row header for that row point to the
				node.setNextr(node);					// node and then point the node back to itself.
			} else {
				curr = rowhead[node.getR()];			// need to traverse the row until at correct location
				while(curr.getNextr() != rowhead[node.getR()] && node.getC() < curr.getC()){
					curr = curr.getNextr();				// if we reach the end, append to the end
				}
				if(curr.getC()==node.getC()){ 			// if duplicate location is found then we set the
					curr.setValue(node.getValue()); 	// value of the current node to that of the inserted
					node = null;						// node and clear the node
				} else {
					node.setNextr(curr.getNextr());		// otherwise simply insert the node
					curr.setNextr(node);
				}
			}
			if(node == null){
				// we found a duplicate and updated the node value before so just do nothing
			} else {
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
		}
	}

	public int[][] Matrix2Array(){
		//		if(this.rowhead == null || this.colhead == null){
		//			return null;
		//		}
		int[][] array = new int[size][size];
		for(int i = 0; i < size; i++){
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
			for(int i = 0; i < size; i++){
				output += "[";
				for(int j = 0; j < size; j++){
					output += String.format("%5d", array[i][j]);
				}
				output += "  ]\n";
			}
		}
		return output;
	}

}
