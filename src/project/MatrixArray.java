package project;

import java.util.Scanner;

class Nodule {
	public int row, col, val;

	public Nodule(int row, int col, int val){
		this.row = row;
		this.col = col;
		this.val = val;
	}
}

class MADriver {
	public static MatrixArray MAadd(MatrixArray a, MatrixArray b){
		MatrixArray ma = new MatrixArray(a.getSize());
		for(int i = 0; i < a.getSize(); i++){
			for(int j = 0; j < a.getSize(); j++){
				ma.array[i][j] = a.array[i][j] + b.array[i][j];
			}
		}
		return ma;
	}

	public static MatrixArray MAscalarMult(MatrixArray a, int c){
		MatrixArray ma = new MatrixArray(a.getSize());
		for(int i = 0; i < a.getSize(); i++){
			for(int j = 0; j < a.getSize(); j++){
				ma.array[i][j] = a.array[i][j] * c;
			}
		}
		return ma;
	}

	public static MatrixArray MAsubtract(MatrixArray a, MatrixArray b){
		return MAadd(a, MAscalarMult(b, -1));
	}

	public static MatrixArray MAtranspose(MatrixArray a){
		MatrixArray ma = new MatrixArray(a.getSize());
		for(int i = 0; i < a.getSize(); i++){
			for(int j = 0; j < a.getSize(); j++){
				ma.array[i][j] = a.array[j][i];
			}
		}
		return ma;
	}

	public static int vectorSum(MatrixArray a, int i, MatrixArray b, int j){
		int sum = 0;
		for(int k = 0; k < a.getSize(); k++){
			sum += a.array[i][k]*b.array[k][j];
		}
		return sum;
	}

	public static MatrixArray MAmatrixMult(MatrixArray a, MatrixArray b){
		MatrixArray ma = new MatrixArray(a.getSize());
		for(int i = 0; i < a.getSize(); i ++){
			for(int j = 0; j < a.getSize(); j++){
				ma.array[i][j] = vectorSum(a, i, b, j);
			}
		}
		return ma;
	}

	public static MatrixArray MApower(MatrixArray a, int x){
		if(x < 1){
			return null;
		}
		if(x == 1){
			return a;
		}
		return MAmatrixMult(a, MApower(a, x-1));
	}
}

public class MatrixArray{
	int[][] array;
	private int size = 0;

	public MatrixArray(int n){
		array = new int[n][n];
		size = n;
	}

	public int getSize(){
		return this.size;
	}

	public void insertNodule(Nodule nodule){
		if (nodule.val == 0) {
			// do nothing
		} else {
			array[nodule.row][nodule.col] = nodule.val;
		}
	}


	@Override
	public String toString(){ // Print method
		String output = "";
		if(array == null){ output = "Empty array"; }
		else{
			for(int i = 0; i < size; i++){
				output += "[";
				for(int j = 0; j < size; j++){
					output += String.format("%8d", array[i][j]);
				}
				output += "  ]\n";
			}
		}
		return output;
	}

	public static void main(String[] args){
		MatrixArray a = new MatrixArray(4);
		a.array= new int[][]{{8, 0, 6, 0},{0, 7, 5, 0},{3, 0, 0, 0},{0, 0, 0, 9}};
		MatrixArray b = new MatrixArray(4);
		b.array= new int[][]{{1, 0, 0, 0},{0, 2, 0, 0},{0, 0, 3, 0},{0, 0, 0, 4}};
		MatrixArray c = new MatrixArray(4);
		c.array= new int[][]{{0, 0, 0, -9},{-3, 0, 0, 0},{0, -5, 0, 0},{0, 0, 0, 0}};
		MatrixArray d = new MatrixArray(4);
		d.array= new int[][]{{0, 0, -1, 0},{0, 4, -2, 6},{0, 0, -3, 0},{0, 6, -4, 8}};

		System.out.println("Matrix A:");
		System.out.println(a);
		System.out.println("Matrix B:");
		System.out.println(b);
		System.out.println("Matrix C:");
		System.out.println(c);
		System.out.println("Matrix D:");
		System.out.println(d);

		// Part II deliverables
		MatrixArray e, f, g, h, i, j, k, l, m, n, o, p;
		System.out.println("Matrix E = B + D:");
		e = MADriver.MAadd(b,d);
		System.out.println(e);

		System.out.println("Matrix F = D - C:");
		f = MADriver.MAsubtract(d, c);
		System.out.println(f);

		System.out.println("Matrix G = A + B:");
		g = MADriver.MAadd(a, b);
		System.out.println(g);

		System.out.println("Matrix H = A - B:");
		h = MADriver.MAsubtract(a, b);
		System.out.println(h);

		System.out.println("Matrix I = E - F:");
		i = MADriver.MAsubtract(e, f);
		System.out.println(i);

		System.out.println("Matrix J = G + H:");
		j = MADriver.MAadd(g, h);
		System.out.println(j);

		System.out.println("Matrix K = 5 * B:");
		k = MADriver.MAscalarMult(b, 5);
		System.out.println(k);

		System.out.println("Matrix L = 8 * C:");
		l = MADriver.MAscalarMult(c, 8);
		System.out.println(l);

		System.out.println("Matrix M = 3 * G:");
		m = MADriver.MAscalarMult(g, 3);
		System.out.println(m);

		System.out.println("Matrix N = 2 * H:");
		n = MADriver.MAscalarMult(h, 2);
		System.out.println(n);

		System.out.println("Matrix O = 2 * M:");
		o = MADriver.MAscalarMult(m, 2);
		System.out.println(o);

		System.out.println("Matrix P = 3 * F:");
		p = MADriver.MAscalarMult(f, 3);
		System.out.println(p);

		// Part III deliverables
		MatrixArray q, r, s, t, u, v, w, x, y, z, aa, ab, ac, ad, ae, af, ag, ah, ai, aj;
		System.out.println("Matrix Q = A * B:");
		q = MADriver.MAmatrixMult(a, b);
		System.out.println(q);

		System.out.println("Matrix R = B * D:");
		r = MADriver.MAmatrixMult(b, d);
		System.out.println(r);

		System.out.println("Matrix S = E * G:");
		s = MADriver.MAmatrixMult(e, g);
		System.out.println(s);

		System.out.println("Matrix T = G * E:");
		t = MADriver.MAmatrixMult(g, e);
		System.out.println(t);

		System.out.println("Matrix U = Q * H:");
		u = MADriver.MAmatrixMult(q, h);
		System.out.println(u);

		System.out.println("Matrix V = S * T:");
		v = MADriver.MAmatrixMult(s, t);
		System.out.println(v);

		System.out.println("Matrix W = R * S:");
		w = MADriver.MAmatrixMult(r, s);
		System.out.println(w);

		System.out.println("Matrix X = D ^ 5:");
		x = MADriver.MApower(d, 5);
		System.out.println(x);

		System.out.println("Matrix Y = C ^ 8:");
		y = MADriver.MApower(c, 8);
		System.out.println(y);

		System.out.println("Matrix Z = B ^ 10:");
		z = MADriver.MApower(b, 10);
		System.out.println(z);

		System.out.println("Matrix AA = F ^ 2:");
		aa = MADriver.MApower(f, 2);
		System.out.println(aa);

		System.out.println("Matrix AB = C ^ 3:");
		ab = MADriver.MApower(c, 3);
		System.out.println(ab);

		System.out.println("Matrix AC = A ^ 4:");
		ac = MADriver.MApower(a, 4);
		System.out.println(ac);

		System.out.println("Matrix AD = E ^ 3:");
		ad = MADriver.MApower(e, 3);
		System.out.println(ad);

		System.out.println("Matrix AE = F ^ T:");
		ae = MADriver.MAtranspose(f);
		System.out.println(ae);

		System.out.println("Matrix AF = E ^ T:");
		af = MADriver.MAtranspose(e);
		System.out.println(af);

		System.out.println("Matrix AG = V ^ T:");
		ag = MADriver.MAtranspose(v);
		System.out.println(ag);

		System.out.println("Matrix AH = L ^ T:");
		ah = MADriver.MAtranspose(l);
		System.out.println(ah);

		System.out.println("Matrix AI = ((A + B) ^ T) - (A ^ T) - (B ^ T):");
		ai = MADriver.MAsubtract(MADriver.MAsubtract(MADriver.MAtranspose(MADriver.MAadd(a, b)), MADriver.MAtranspose(a)), MADriver.MAtranspose(b));
		System.out.println(ai);

		System.out.println("Matrix AJ = ((A * B) ^ T) - ((B ^ T) * (A ^ T)):");
		aj = MADriver.MAsubtract(MADriver.MAtranspose(MADriver.MAmatrixMult(a,b)), MADriver.MAmatrixMult(MADriver.MAtranspose(b), MADriver.MAtranspose(a)));
		System.out.println(aj);
	}
}
