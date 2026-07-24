package ExcelGenerator;

import java.util.Arrays;
import java.util.OptionalDouble;

public class Demo {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
////		Input : Abhishek321
////		O/P : 123Abhishek
//		
//		String str = "Abhishek 321";
//		String[] s = str.split(" ");
//		
//	StringBuilder sb = new StringBuilder(s[1]);
//	sb.reverse().append(" " + s[0]).toString();
//	System.out.println("Original String :" + str);
//	System.out.println("Reverse String :" +  sb);
//	
//	
//		
//	}
	
	public static void main(String[] args) {
        String input = "Abhishek321";
        String output = rearrange(input);
        System.out.println(output); // Output: 123Abhishek
        
        int[] arr = {1,2,4,6,7,32};
        OptionalDouble ans = Arrays.stream(arr).average();
    }

    public static String rearrange(String str) {
        StringBuilder digits = new StringBuilder();
        StringBuilder letters = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            } else {
                letters.append(c);
            }
        }
        return digits.reverse().toString() + letters.toString();
    }

}
