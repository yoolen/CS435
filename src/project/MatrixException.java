package project;

public class MatrixException extends Exception{
	public MatrixException(String msg){
		super(msg);
	}
	
	public MatrixException(String msg, Throwable throwable){
		super(msg, throwable);
	}
}
