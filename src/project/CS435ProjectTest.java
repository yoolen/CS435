package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class CS435ProjectTest {

	@Test
	public void testNode() {
		Node n = new Node(1, 2, 3);
	}
	
	@Test
	public void testMatrix(){
	// Matrix should be NxN
		Matrix m;
		try {
			m = new Matrix(5);
			assertEquals(m.getSize(), 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testMatrixPrint(){
		Matrix m;
		try{
			m = new Matrix(-1);
		} catch (Exception e) {
			assertEquals(e.getMessage(),"Size cannot be less than 1!");
		}
	}

	public static void testRow(Node row){
		Node node = row;
		do{						
			System.out.println("node: " + node.getValue() + ":" + node + ":" + row + ":" + node.getNextr());
			node = node.getNextr();
		} while (node != row);
	}
}
