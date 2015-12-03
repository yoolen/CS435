package project;

public class MatrixTest {
	public static void main(String[] args){
		Matrix a = new Matrix(4);
		Matrix b = new Matrix(4);		
		
		Node[] testrow = {	new Node(0,0,1), new Node(0,1,1), new Node(0,2,1), new Node(0,3,1),
							/*new Node(1,0,1),*/ new Node(1,1,1), /*new Node(1,2,1),*/ new Node(1,3,1),
							new Node(2,0,1), /*new Node(2,1,1),*/ new Node(2,2,1), new Node(2,3,1),
							new Node(3,0,1), new Node(3,1,1), new Node(3,2,1), new Node(3,3,1)};
		Node[] testcol = {new Node(0,0,1),  new Node(1,0,2), new Node(2,0,3), new Node(3,0,4)};
		
		for(Node node: testrow){
			a.insertNode(node);
		}
		
		for(Node node: testrow){
			b.insertNode(node);
		}
		
		System.out.println(a);
		System.out.println();
		//System.out.println(b);
		
		System.out.println(MatrixDriver.add(a,a));
	}
}
