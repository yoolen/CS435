package project;

import java.util.Scanner;

public class MatrixDriver {
	private static Matrix a, b, c, d;
	
	public static void initializeByInput(){
		Scanner scanner = new Scanner(System.in);
		String input, inputarray[] = null;
		Node node = null;
		System.out.println("Initializing your matrix by input...");
		while(true){
			System.out.print("Please enter an element as a 3-tuple consisting of row, column, value (ie. 1 1 3) or -1 to exit:");
			input = scanner.nextLine();
			inputarray = input.split(" ");
			if(inputarray.length != 3){ 	// if the user input anything other than the correct elements do some checking
				if(inputarray.length == 1){	// possibly input -1 
					if(inputarray[0].matches("-1")){
						System.out.println("Completed entering Matrix values.");
						break;
					} else {
						System.out.println("Incorrect values!");
					}
				} else {
					System.out.println("Incorrect number of values!");
				}				
			} else {	// correct number of values, check for correctness of value types
				checking: {
					for(int i=0; i<3; i++){
						if(!inputarray[i].matches("-?[0-9]+")){	// catch bad inputs
							System.out.println("Non-integer values are not permitted!");
							break checking;
						}
					}
					if(Integer.valueOf(inputarray[0]) >= a.getSize() || Integer.valueOf(inputarray[0]) < 0){
						System.out.println("Row out of bounds!");
						break checking;
					} else if (Integer.valueOf(inputarray[1]) >= a.getSize() || Integer.valueOf(inputarray[0]) < 0){
						System.out.println("Column out of bounds!");
						break checking;
					} 
					// Finished checking for some errors so add the node
					a.insertNode(new Node(Integer.valueOf(inputarray[0]),Integer.valueOf(inputarray[1]),Integer.valueOf(inputarray[2])));
				}
			
			}			
		}
	}
	
	public static void initializeByFormula(){
		// generate Matrices
		for(int i = 0; i < b.getSize(); i++){
			for(int j = 0; j < b.getSize(); j++){
				if(i==j){
					b.insertNode(new Node(i,j,i+1));
				}
				if(i+1 == (j+2) % c.getSize()){
					c.insertNode(new Node(i,j,-2*j-3));
				}
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					d.insertNode(new Node(i,j,i+j+2));
				}
				if(j==2){
					d.insertNode(new Node(i,j,-i-1));
				}
			}
		}
	}
	
	public static Matrix subtract(Matrix a, Matrix b){
		return add(a, scalarMultiply(b, -1));
	}
	
	public static Matrix add(Matrix a, Matrix b){
		Matrix sum = new Matrix(a.getSize());
		Node node = null;
		
		for(int i = 0; i < sum.getSize(); i++){
			if(a.getRowhead()[i] == null && b.getRowhead()[i] == null){	// If both null skip to next row
				continue;
			} else if(a.getRowhead()[i] == null){	// If the row in A is null just insert all from B
				node = b.getRowhead()[i];
				do{					
					sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
					node = node.getNextr();
				} while (node != b.getRowhead()[i]);
			} else if(b.getRowhead()[i] == null){		// If the row in B is null just insert all from A
				node = a.getRowhead()[i];
				do{						
					sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
					node = node.getNextr();
				} while (node != a.getRowhead()[i]);
			} else {								// Neither is null
				node = a.getRowhead()[i];
				do{						
					sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
					node = node.getNextr();
				} while (node != a.getRowhead()[i]);  // is node.getNextr() or just node?	
				// add nodes from second matrix into sum matrix
				node = b.getRowhead()[i];
				do{						
					sum.addNode(new Node(node.getR(),node.getC(),node.getValue()));
					node = node.getNextr();				
				} while (node != b.getRowhead()[i]);

			}
		}
		return sum; // return sum matrix
	}
	
	public static Matrix scalarMultiply(Matrix a, int c){
		Matrix sproduct = new Matrix(a.getSize());
		for(int i = 0; i < a.getSize(); i++){
			Node parser = a.getRowhead()[i];
			if(parser == null){ continue; }
			while(parser.getNextr() != a.getRowhead()[i]){
				sproduct.insertNode(new Node(parser.getR(), parser.getC(), parser.getValue() * c));
				parser = parser.getNextr();
			}	
			sproduct.insertNode(new Node(parser.getR(), parser.getC(), parser.getValue() * c));
		}
		return sproduct;
	}
	
	public static void main(String[] args) {
		int size = 0;
		Scanner scan = new Scanner(System.in);
		do{
			System.out.print("Please enter the size of the matrix (must be an integer greater than 0):");
			if(!scan.hasNextInt()){
				System.out.println("Integers only please.");
				scan.next();
			} else {
				size = scan.nextInt();
				if(size < 1){
					System.out.println("Matrix must be at least 1x1.");
				} else {
					break;
				}
			}
		} while(true);
		a = new Matrix(size);
		b = new Matrix(size);
		c = new Matrix(size);
		d = new Matrix(size);
		
		// Initialize the Matrices
		initializeByFormula();
		initializeByInput();
		
		// Print out the tables;
		System.out.println("Matrix A:");
		System.out.println(a);
		System.out.println("Matrix B:");
		System.out.println(b);
		System.out.println("Matrix C:");
		System.out.println(c);
		System.out.println("Matrix D:");
		System.out.println(d);
		
		System.out.println("Matrix Add C + D:");
		System.out.println(add(c,d));
		
		System.out.println("Matrix Scalar Multiply D * 5:");
		System.out.println(scalarMultiply(d, 5));
		
		System.out.println("Matrix Subtract C - D:");
		System.out.println(subtract(c,d));
	}
}
