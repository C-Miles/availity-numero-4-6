package Numero4;

public class LispReader {
	
	boolean validator(String str) {
		
//		Turn input into an array for looping
		char[] arr = str.toCharArray();
		
//		Copy first array into a reference type array to utilize .equals
		Character[] arr2 = new Character[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			arr2[i] = arr[i];
		}
		
		int count = 0; 
		
//		Increment/decrement counter to keep track of parentheses, return false if ever negative or anything other than 0
		for(int i = 0; i < arr2.length; i++) {
			if(arr2[i].equals('(')) {
				count += 1;
			}
			if(arr2[i].equals(')')) {
				count -= 1;
			}
			if(count < 0) {
				return false;
			}
			
		}
		
		if(count == 0) {
			return true;
		}
		
		return false;
	}

}
