package project;

import java.util.Scanner;

public class MatrixDriver {
	private static Matrix a, b, c, d;
	
	public static void initializeByDefault(){
		Node[] defaultValues = {new Node(0,0,8), new Node(0,2,6), new Node(1,1,7), new Node(1,2,5), new Node(2,0,3), new Node(3,3,9)};
		for(Node node: defaultValues){
			a.insertNode(node);
		}
	}
	
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
		return add(a, scalarMultiply(-1, b));
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
	
	public static Matrix scalarMultiply(int c, Matrix a){
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
		boolean input = false; // toggle this to enable user input
		
		if(input){
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
		} else {
			size = 4;
		}
		
		a = new Matrix(size);
		b = new Matrix(size);
		c = new Matrix(size);
		d = new Matrix(size);
		
		// Part I deliverables
		// Initialize the Matrices
		initializeByFormula();
		if(input){
			initializeByInput();
		} else {
			initializeByDefault();
		}
		
		// Print out the tables;
		System.out.println("Matrix A:");
		System.out.println(a);
		System.out.println("Matrix B:");
		System.out.println(b);
		System.out.println("Matrix C:");
		System.out.println(c);
		System.out.println("Matrix D:");
		System.out.println(d);
		
		
		// Part II deliverables
		Matrix e, f, g, h, i, j, k, l, m, n, o, p;
		System.out.println("Matrix E = B + D:");
		e = add(b,d);
		System.out.println(e);
		
		System.out.println("Matrix F = D - C:");
		f = subtract(d, c);
		System.out.println(f);
		
		System.out.println("Matrix G = A + B:");
		g = add(a, b);
		System.out.println(g);
		
		System.out.println("Matrix H = A - B:");
		h = subtract(a, b);
		System.out.println(h);
				
		System.out.println("Matrix I = E - F:");
		i = subtract(e, f);
		System.out.println(i);
		
		System.out.println("Matrix J = G + H:");
		j = add(g, h);
		System.out.println(j);
		
		System.out.println("Matrix K = 5 * B:");
		k = scalarMultiply(5, b);
		System.out.println(k);
		
		System.out.println("Matrix L = 8 * C:");
		l = scalarMultiply(8, c);
		System.out.println(l);
		
		System.out.println("Matrix M = 3 * G:");
		m = scalarMultiply(3, g);
		System.out.println(m);
		
		System.out.println("Matrix N = 2 * H:");
		n = scalarMultiply(2, h);
		System.out.println(n);
		
		System.out.println("Matrix O = 2 * M:");
		o = scalarMultiply(2, m);
		System.out.println(o);
		
		System.out.println("Matrix P = 3 * F:");
		p = scalarMultiply(3, f);
		System.out.println(p);
	}
}
