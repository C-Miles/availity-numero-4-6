package Numero4;

public class Main {

	public static void main(String[] args) {
		
		
		LispReader lispRdr = new LispReader();
		
		String valid = "(some lisp code)()()()((()))";
		String invalid = "((no bueno))((()";
		
		System.out.println(lispRdr.validator(valid));
		System.out.println(lispRdr.validator(invalid));

		
	}

}
