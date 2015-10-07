package project;

public class Matrix extends Exception{
	private Node[] rowheader;
	private Node[] colheader;
	
	public Matrix(int n) throws Exception{
		if(n <= 0){
			throw new Exception("Size cannot be less than 1!");
		}
		rowheader = new Node[n];
		colheader = new Node[n];
	}
	
	public Node[] getRowheader(){
		return this.rowheader;
	}
	
	public Node[] getColheader(){
		return this.colheader;
	}
	
	public int[][] Matrix2Array(){
		if(this.rowheader == null || this.colheader == null){
			return null;
		}
		int[][] array = new int[rowheader.length][colheader.length];
		for(int i = 0; i < rowheader.length; i++){
			Node parser = this.rowheader[i];
			while(parser.nextc != this.rowheader[i]){
				array[i][parser.col] = parser.value;
			}
		}
		return array;
	}
	
	@Override
	public String toString(){
		int[][] array = this.Matrix2Array();
		String output = "";
		if(array == null){ output = "Empty array"; }
		else{
			for(int i = 0; i < rowheader.length; i++){
				output += "[";
				for(int j = 0; j < colheader.length; j++){
					output += String.format("%3d", array[i][j]);
				}
				output += "  ]\n";
			}
		}
		return output;
	}
	
}
