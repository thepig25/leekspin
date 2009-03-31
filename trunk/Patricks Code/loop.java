
public class loop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		
		int[] Array = new int[7];
		int[] temp = new int[7];
		
		for(int m = 1; m < Array.length; m++ ){
			Array[m] = m;
			
		}
		
		for(int print =0; print<Array.length; print++){
			System.out.print(Array[print]);
		}
		System.out.println("");
		
		for(int i = 0; i < Array.length ; i++){
			Array[i]= temp[0];
			for(int j = (i+1); i < (Array.length -1); i++){
				
				Array[j]= temp[j];
				for(int print =0; print<Array.length; print++){
					System.out.print(temp[print]);
				}
				System.out.print(" ");
			}
			for(int print =0; print<Array.length; print++){
				System.out.print(temp[print]);
				
			}
			System.out.println("");
		}
		for(int print =0; print<Array.length; print++){
			System.out.print(temp[print]);
		}
	}

}
