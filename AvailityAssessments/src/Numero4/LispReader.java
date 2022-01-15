package Numero4;

public class LispReader {
	
	boolean validator(String str) {
		
		char[] arr = str.toCharArray();
		Character[] arr2 = new Character[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			arr2[i] = arr[i];
		}
		
		int count = 0;
		
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
