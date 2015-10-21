package project;

import java.util.Scanner;

public class MatrixDriver {
	
	public static void main(String[] args) {
		int size = 0;
		Scanner scan = new Scanner(System.in);
		do{
			System.out.print("Please enter the size of the matrix (must be an integer greater than 0):");
			if(!scan.hasNextInt()){
				System.err.println("Integers only please.");
				scan.next();
			} else {
				size = scan.nextInt();
				if(size < 1){
					System.err.println("Matrix must be at least 1x1.");
				} else {
					break;
				}
			}
		} while(true);
		Matrix a = new Matrix(size);
		Node node = new Node(1,1,1);
		a.insertNode(node);
		node = new Node(1,2,3);
		a.insertNode(node);
		System.out.print(a);
	}
}
