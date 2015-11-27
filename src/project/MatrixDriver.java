package project;

import java.util.LinkedList;
import java.util.Scanner;

public class MatrixDriver {
	private static Matrix a, b, c, d;

	public static Matrix identityMatrix(int size){
		Matrix identity = new Matrix(size);
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(i == j){
					identity.insertNode(new Node(i, j, 1));
				}
			}
		}
		return identity;
	}

	public static void initializeByDefault(){
		// Initializes matrix A to default values as specified, it does this by creating 
		// an array of the nodes with default values and then inserting them into the matrix
		Node[] defaultValues = {new Node(0,0,8), new Node(0,2,6), new Node(1,1,7), new Node(1,2,5), new Node(2,0,3), new Node(3,3,9)};
		for(Node node: defaultValues){
			if(node.getR() < a.getSize() && node.getC() < a.getSize()){
				a.insertNode(node);
			}
		}
	}

	public static void initializeByInput(){
		// Uses a scanner object to read in values from the user if the user chooses to
		// insert values manually; values must be input as 3 tuples (row, column, value).
		// A node is then made with this value and inserted.
		Scanner scanner = new Scanner(System.in);
		String input, inputarray[] = null;
		Node node = null;
		a = new Matrix(a.getSize());
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
		initializeByDefault();
		// generate Matrices using formulas
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
		// Subtraction is just adding a negative matrix
		return add(a, scalarMultiply(-1, b));
	}

	public static Matrix add(Matrix a, Matrix b){
		// Addition is done by creating a new matrix the calculating the sums of respective nodes
		// in the two matrices and then creating a new node with that sum and inserting into the
		// sum matrix which is returned once fully populated.
		Matrix sum = new Matrix(a.getSize());
		Node node = null, node2 = null;

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
			} else {	// Neither is null so we have to do some math
				node = a.getRowhead()[i];
				node2 = b.getRowhead()[i];
				do{
					if(node.getC() == node2.getC()){ // if the two nodes are aligned add and insert
						int addnodes = node.getValue() + node2.getValue();
						sum.insertNode(new Node(node.getR(),node.getC(),addnodes));
						node = node.getNextr();
						if(node == a.getRowhead()[i]){ // insert the rest of b if we've run out of a nodes
							if(node2.getNextr() == b.getRowhead()[i]){
								break;
							}
							while(node2.getNextr() != b.getRowhead()[i]){
								sum.insertNode(new Node(node2.getR(),node2.getC(),node2.getValue()));
								node2 = node2.getNextr();
							}
							sum.insertNode(new Node(node2.getR(),node2.getC(),node2.getValue()));
							break;
						}
						node2 = node2.getNextr();
						if(node2 == b.getRowhead()[i]){ // insert the rest of b if we've run out of a nodes
							if(node.getNextr() == a.getRowhead()[i]){
								break;
							}
							while(node.getNextr() != a.getRowhead()[i]){
								sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
								node = node.getNextr();
							}
							sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
							break;
						}
					} else if(node.getC() < node2.getC()){ // if not aligned, it means that the respective node in the other matrix is 0 
						sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
						node = node.getNextr(); // traverse further on the row of the first matrix
						if(node == a.getRowhead()[i]){ // insert the rest of b if we've run out of a nodes
							while(node2.getNextr() != b.getRowhead()[i]){
								sum.insertNode(new Node(node2.getR(),node2.getC(),node2.getValue()));
								node2 = node2.getNextr();
							}
							sum.insertNode(new Node(node2.getR(),node2.getC(),node2.getValue()));
							break;
						}
					} else if(node2.getC() < node.getC()){
						sum.insertNode(new Node(node2.getR(),node2.getC(),node2.getValue()));
						node2 = node2.getNextr(); // traverse further on the row of the second matrix
						if(node2 == b.getRowhead()[i]){ // insert the rest of b if we've run out of a nodes
							while(node.getNextr() != a.getRowhead()[i]){
								sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
								node = node.getNextr();
							}
							sum.insertNode(new Node(node.getR(),node.getC(),node.getValue()));
							break;
						}
					} 
				} while(node != a.getRowhead()[i] || node2 != b.getRowhead()[i]);
			}	
		}
		return sum; // return sum matrix
	}

	public static Matrix scalarMultiply(int c, Matrix a){
		// Scalar multiplication takes each node in the matrix, makes a copy of it
		// then multiplies the value by some constant c that is passed in.
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

	public static Matrix transpose(Matrix a){
		// Transpose works by reading each node from a matrix and inserting it into a new
		// matrix with the row and column reversed. Once all nodes are inserted we return
		// the transposed matrix.
		Matrix atrans = new Matrix(a.getSize());
		Node traverse = null;
		for(int i = 0; i < a.getSize(); i++){
			Node parser = a.getRowhead()[i];
			if(parser == null){ continue; }
			while(parser.getNextr() != a.getRowhead()[i]){
				atrans.insertNode(new Node(parser.getC(), parser.getR(), parser.getValue()));
				parser = parser.getNextr();
			}	
			atrans.insertNode(new Node(parser.getC(), parser.getR(), parser.getValue()));
		}
		return atrans;
	}

	public static int vectorMultiply(Node rowa, Node colb){
		// Because the column and row vectors are linked lists, we can use some specific behaviours to simplify
		// the process of calculating the sum of the products in a vector multiplication. A vector product requires
		// that both vectors be the same size; we then multiply the respective elements and add these products
		// together to get the vector product. 
		//  To do this we traverse the two vectors (which are implemented as lists) using 2 traversal nodes. Initial 
		// two traversal nodes to the heads of each vector, and then compare the column value of the node of row a 
		// to the row value of the node of column b. If either value is smaller than the other, that means that the
		// corresponding vector with the larger value has values of 0 for all values before that node (and multiplying
		// anything by 0 results in 0), so we simply go to the next node in the smaller of the two vectors. If the
		// column and row values are equal then we have found a non-zero product; multiply these two and add it to the
		// sum and then increment both traversal nodes and repeat.
		int sum = 0;
		if(rowa == null || colb == null){ // if either row or column is null the product will be 0
			return 0;
		}
		Node trava = rowa, travb = colb;
		//System.out.printf("Row:%d Col:%d!\n", rowa.getR(), colb.getC());
		loop: {
			do{
				if(trava.getNextr() == rowa && travb.getNextc() == colb){	// this is done to handle 1x1 matrices; do one
					//System.out.println("Found the end!");
					break;													// addition after the vector is processed for
				}															// the final 2 nodes.
				if(trava.getC() == travb.getR()){	// Column from row vector and row from column vector match up so we can multiply
					sum += trava.getValue() * travb.getValue();
					//System.out.println("Equal! Sum:" + sum);
					if(trava.getNextr() != rowa && travb.getNextc() != colb){
						trava = trava.getNextr();		// increment both by 1 node
						travb = travb.getNextc();
						//System.out.println("Moved both up!");
					} else {
						//System.out.println("Internal Break!");
						break loop;
					}
				} else if (trava.getC() < travb.getR() && trava.getNextr() != rowa){	// column value of the row vector is less, so corresponding element in
					trava = trava.getNextr();									// column vector must have been a 0 (not inserted into the vector per 
					//System.out.println("Moved a up!");															// our implementation) so increment by 1 node and check again
				} else if (travb.getR() < trava.getC() && travb.getNextc() != colb){	// Only other possibility is that value of the row in the column vector is less
					travb = travb.getNextc();									// than the column value of the row vector so we increment that by 1 node
					//System.out.println("Moved b up!");
				} else {
					//System.out.println("Break!");
					break;
				} 
			} while(trava.getNextr() != rowa || travb.getNextc() != colb);
			if(trava.getC() == travb.getR()){	// Column from row vector and row from column vector match up so we can multiply
				sum += trava.getValue() * travb.getValue();
				//System.out.println("Equal at the end! Sum:" + sum);
			}	
		}	
		//System.out.println("At the end!\n");
		return sum;
	}

	public static Matrix multiply(Matrix a, Matrix b){
		// Matrix multiplication works by breaking matrix a into row vectors and matrix b into column vectors.
		// Then each location in the resultant matrix is equal to the sum of the products of the respective
		// entries in the vectors (also each location can be identified by the row and column number being
		// multiplied). 
		// 	In this implementation, a null row or column indicates that all the values in that
		// row or column are 0, so the products will all be 0 and the resulting sum will be 0 as well (so if
		// a null row or column is detected we automatically know the value of the matrix at (row,col) will be 0). 
		//  A helper function vectorMultiply is used to calculate the value of the node to be inserted. 
		Matrix product = new Matrix(a.getSize());
		for(int i = 0; i < a.getSize(); i++){
			for(int j = 0; j < b.getSize(); j++){
				product.insertNode(new Node(i, j, vectorMultiply(a.getRowhead()[i], b.getColhead()[j])));
			}
		}
		return product;
	}

	public static Matrix power(Matrix a, int exp){
		if(exp == 0){
			return identityMatrix(a.getSize());
		}
		if(exp == 1){	// base case is exponent of 1, just return original matrix
			return a;
		}
		return multiply(power(a, (int)(exp/2)), power(a, exp - (int)(exp/2))); // otherwise break the exponent into two halves 
	}

	public static void main(String[] args) {
		int size = 0;
		Scanner scan = new Scanner(System.in);
		String input;
		boolean choice = false;

		/*		do{
			System.out.print("Would you like to manually initialize the matrix: y/n (n for default values)? ");
			input = scan.nextLine();
			if(input.length() > 1 || !input.equals("y") && !input.equals("n")){
				System.out.println(input + " Please enter a valid choice.");
			} else {
				choice = input.equals("y") ? true : false;
				if(!choice){
					size = 4;
				}
				break;
			}
		} while (true);*/

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

		// Part I deliverables
		// Initialize the Matrices
		initializeByFormula();


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

		// Part III deliverables
		Matrix q, r, s, t, u, v, w, x, y, z, aa, ab, ac, ad, ae, af, ag, ah, ai, aj;
		System.out.println("Matrix Q = A * B:");
		q = multiply(a, b);
		System.out.println(q);

		System.out.println("Matrix R = B * D:");
		r = multiply(b, d);
		System.out.println(r);

		System.out.println("Matrix S = E * G:");
		s = multiply(e, g);
		System.out.println(s);

		System.out.println("Matrix T = G * E:");
		t = multiply(g, e);
		System.out.println(t);

		System.out.println("Matrix U = Q * H:");
		u = multiply(q, h);
		System.out.println(u);

		System.out.println("Matrix V = S * T:");
		v = multiply(s, t);
		System.out.println(v);

		System.out.println("Matrix W = R * S:");
		w = multiply(r, s);
		System.out.println(w);

		System.out.println("Matrix X = D ^ 5:");
		x = power(d, 5);
		System.out.println(x);

		System.out.println("Matrix Y = C ^ 8:");
		y = power(c, 8);
		System.out.println(y);

		System.out.println("Matrix Z = B ^ 10:");
		z = power(b, 10);
		System.out.println(z);

		System.out.println("Matrix AA = F ^ 2:");
		aa = power(f, 2);
		System.out.println(aa);
		System.out.println(aa.toStringByCol());

		System.out.println("Matrix AB = C ^ 3:");
		ab = power(c, 3);
		System.out.println(ab);

		System.out.println("Matrix AC = A ^ 4:");
		ac = power(a, 4);
		System.out.println(ac);

		System.out.println("Matrix AD = E ^ 3:");
		ad = power(e, 3);
		System.out.println(ad);

		System.out.println("Matrix AE = F ^ T:");
		ae = transpose(f);
		System.out.println(ae);

		System.out.println("Matrix AF = E ^ T:");
		af = transpose(e);
		System.out.println(af);

		System.out.println("Matrix AG = V ^ T:");
		ag = transpose(v);
		System.out.println(ag);

		System.out.println("Matrix AH = L ^ T:");
		ah = transpose(l);
		System.out.println(ah);

		System.out.println("Matrix AI = ((A + B) ^ T) - (A ^ T) - (B ^ T):");
		ai = subtract(subtract(transpose(add(a, b)), transpose(a)), transpose(b));
		System.out.println(ai);

		System.out.println("Matrix AJ = ((A * B) ^ T) - ((B ^ T) * (A ^ T)):");
		aj = subtract(transpose(multiply(a,b)), multiply(transpose(b), transpose(a)));
		System.out.println(aj);

		initializeByInput();
		System.out.println(a);
	}

	public static void printList(Matrix a){
		System.out.println("rows");
		for(int bullshit = 0; bullshit < a.getSize(); bullshit++){
			Node test = a.getRowhead()[bullshit];
			if(test != null){
				while(test.getNextr() != a.getRowhead()[bullshit]){
					System.out.print(test.getValue() + " ");
					test = test.getNextr();
				}
				System.out.println(test.getValue());
			} else {
				System.out.println("nothing here");
			}
		}
		System.out.println("columns");
		for(int bullshit = 0; bullshit < a.getSize(); bullshit++){
			Node test = a.getColhead()[bullshit];
			if(test != null){
				while(test.getNextc() != a.getColhead()[bullshit]){
					System.out.print(test.getValue() + " ");
					test = test.getNextc();
				}
				System.out.println(test.getValue());
			} else {
				System.out.println("nothing here");
			}
		}
	}
}
