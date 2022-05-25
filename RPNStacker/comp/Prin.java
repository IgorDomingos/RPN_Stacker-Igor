package comp;

import exceptions.UnexpectedCharacterException;
import exceptions.UnexpectedOperatorException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Prin {
	public static void main(String[] args) throws Exception {
		
	        RPNStacker rpn = new RPNStacker();
	        rpn.scan(new FileReader("src/input.in"));
	        int result = rpn.calc();
	        System.out.println(result);
	    }
}
