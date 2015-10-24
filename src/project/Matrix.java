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
	
	public Node[] getRowhead(){
		return this.rowhead;
	}
	
	public Node[] getColhead(){
		return this.colhead;
	}
	
	/*	Add node takes a node and adds it to the matrix in the correct row and column
	 *  Preconditions: addNode is only executed on a non-empty row (null rows are handled elsewhere)
	 *  Postconditions: if a node does not exist it will be added to the matrix; if it already
	 *  exists the value of the inserted node will be added to the current node.
	 */ 
	public void addNode(Node node){
		Node curr = rowhead[node.getR()];			// need to traverse the row until at correct location
		while(curr.getNextr() != rowhead[node.getR()] && node.getC() < curr.getC()){
			curr = curr.getNextr();				// if we reach the end, append to the end
		}
		if(curr.getC()==node.getC()){ 			// if duplicate location is found then we add the
			curr.setValue(curr.getValue() + node.getValue()); 	// value of the current node to that of the inserted
			node = null;						// node and clear the node
		} else {
			node.setNextr(curr.getNextr());		// otherwise simply insert the node
			curr.setNextr(node);
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
				while(curr.getNextr() != rowhead[node.getR()] && curr.getC() < node.getC()){
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
					while(curr.getNextc() != colhead[node.getC()] && curr.getR() < node.getR()){
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
	
	public int[][] Matrix2Arraybycol(){ // testing to see if linking is done properly for columns
		//		if(this.rowhead == null || this.colhead == null){
		//			return null;
		//		}
		int[][] array = new int[size][size];
		for(int i = 0; i < size; i++){
			Node parser = this.colhead[i];
			if(parser == null){ continue; }
			while(parser.getNextc() != this.colhead[i]){
				array[parser.getR()][i] = parser.getValue();
				parser = parser.getNextc();
			}	
			array[parser.getR()][i] = parser.getValue();
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
	
	public String printbycol(){ // Print method
		int[][] array = this.Matrix2Arraybycol();
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
