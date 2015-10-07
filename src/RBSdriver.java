
public class RBSdriver {
	
	public static void rbs(int[] array, int n){
		int temp;
		if(n==0){
			return;
		}
		for(int i = 0; i < n-1; i++){
			if(array[i] > array[i+1]){
				temp = array[i+1];
				array[i+1] = array[i];
				array[i] = temp;
			}
		}
		rbs(array, n-1);
	}
	
	public static void main(String[] args){
		int[] arr1 = {5,3,8,0,9,2,1,4,7};
		rbs(arr1, arr1.length);
		for(int i: arr1){
			System.out.print(i+" ");
		}
	}
	
}
