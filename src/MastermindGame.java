
import java.util.Scanner;

public class MastermindGame {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*****************************************");
		System.out.println("      HELLO! WELCOME TO MASTERMIND!      ");
		System.out.println("*****************************************");

		
		// prompt user
		System.out.println("Please enter a number of positions.");
		int positions = scan.nextInt();
		while(positions > 6){
			System.out.println("Please enter a number of positions less than 7.");
			positions = scan.nextInt();
		}
		while(positions < 1){
			System.out.println("Please enter a number of positions greater than 0.");
			positions = scan.nextInt();
		}
		
		System.out.println("Please enter a number of colors.");
		int numberOfColors = scan.nextInt();
		while(numberOfColors> 6){
			System.out.println("Please enter a number of colors less than 7.");
			numberOfColors = scan.nextInt();
		}
		while(numberOfColors < 1){
			System.out.println("Please enter a number of colors greater than 0.");
			numberOfColors = scan.nextInt();
		}
		
		String [] colors = new String [numberOfColors]; // an array of length number of colors given by user
		
		System.out.println("Please enter each color.");
		for(int i = 0; i < colors.length; i++){
			colors [i] = scan.next();
		}	
		
		MastermindMethods game = new MastermindMethods(colors, positions); // begin game
	}

}
