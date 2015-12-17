import java.util.ArrayList;
import java.util.Scanner;
import Mastermind.MastermindGame;
import Mastermind.MastermindInterface;


public class MastermindMethods implements MastermindInterface{
	
	private Scanner scan = new Scanner(System.in);
	private static ArrayList<String[]> allCombinations;
	
	
	public MastermindMethods(String [] colors, int positions){
		allCombinations = new ArrayList<String []>();
		int totalCombinations = (int)Math.pow(colors.length, positions);
		String masterCombinations [] [] = new String [totalCombinations] [positions]; // 2d array to hold all combinations
		setCombinations(masterCombinations, colors, 0); // set all combinations in masterCombinations

		for(int i = 0; i < masterCombinations.length; i++){
			allCombinations.add(masterCombinations[i]); // add each row of combinations to list
		}
		
		initialGuess(); // gives a first guess to receive feedback from. Continue from there
		scan.close();
	}
		
	
	public void initialGuess(){
		System.out.println("\n\n\n\n\n\n\n");
		 
		System.out.println("Is this the correct combination? yes or no?");
		for(int i = 0; i < allCombinations.get(0).length; i++){
			System.out.print(allCombinations.get(0)[i] + " "); // print the first guess
		} 
		System.out.println();
		checkCorrect(scan.next()); // check if initial guess is correct

		System.out.println("Please enter how many are of the CORRECT color and position.");
		int colorAndPositionCorrect = scan.nextInt();
		
		System.out.println("Please enter how many of the others are of CORRECT color, but WRONG position.");
		int colorCorrect = scan.nextInt();
		
		response(colorAndPositionCorrect, colorCorrect);
	}
	
	
	public void response(int cpRight, int cRight){
		
		ArrayList<String []> removeThese = new ArrayList<String []>(); 
		// arraylist to hold elements to be removed
		
		String [] currentGuess = allCombinations.get(0);
		
		// compare correct color and position with remaining prospective combinations
		// and also compare correct color wrong position
		for(String [] temp : allCombinations){
			boolean [] temp2 = colorpositionCorrect(currentGuess, temp);
			
			// check if correct color and position are correct or if correct color wrong position is correct
			if(countTrues(temp2) != cpRight || countTrues(colorCorrect(currentGuess, temp, temp2)) != cRight){
				removeThese.add(temp); // add to the remove pile
			}
		}
		
		allCombinations.removeAll(removeThese); // remove method to remove equivalent elements in removeThese
		String [] nextGuess = allCombinations.get(0);
		
		System.out.println("\nWhat about this?");
		
		for(int i = 0; i < nextGuess.length; i++){
			System.out.print(nextGuess[i] + " ");
		}
		System.out.println("\nIs this the correct combination? yes or no?");
		checkCorrect(scan.next());
		
		System.out.println("\nPlease enter how many are of the correct color and position.");
		int colorAndPositionCorrect = scan.nextInt();
		
		System.out.println("\nPlease enter how many of the others are of correct color, but wrong position.");
		int colorCorrect = scan.nextInt();
		
		response(colorAndPositionCorrect, colorCorrect);
	}
	
	
	public static boolean [] colorCorrect(String [] guess, String [] prospect, boolean [] temp){
		boolean [] array = new boolean [guess.length];
		
		// check for correct color but wrong position
		for(int i = 0; i < guess.length; i++){
			if(temp[i] == false){
				for(int j = 0; j < prospect.length; j++){
					if(temp [j] == false){
						if(guess[i].equals(prospect[j])){
							array[j] = true;
						}
					}
				}
			}
		}
		
		return array;
	}
	
	
	public static boolean [] colorpositionCorrect(String [] guess, String [] prospect){
		boolean [] array = new boolean [prospect.length];
		
		// check each color to see if matches
		// if they do, all elements will be true
		for(int i = 0; i < prospect.length; i++){
			if(guess [i].equals(prospect[i])){
				array [i] = true;
			}
		}
		
		return array;
	}
	
	
	public static int countTrues(boolean [] array){
		int counter = 0;
		
		// count the number of trues in the given array
		// for the correct color and correct position
		for(int i = 0; i < array.length; i++){
			if(array[i] == true){
				counter++;
			}
		}
		return counter;
	}
	
	
	public String [] nextGuess(){
		// we can keep returning the first of the list because we keep removing unusable combinations
		return allCombinations.get(0); 
	}
	
	
    public static String [][] setCombinations(String temporaryArray [][], String [] colors, int columnNumber) {
        // Base case: if finished with the length of positions per combination
        if(columnNumber >= temporaryArray[0].length){
        	return temporaryArray;
        }
        
        // recursive method to get all possible combinations, shown by Pawlicki
        // method is based off of exponents of the number of colors
        int exp = (temporaryArray[0].length) - columnNumber - 1;
        int repetitions = (int) Math.pow((double) colors.length, (double) exp);
        int count = 0;
        
        while(count < temporaryArray.length){
        	for(int i = 0; i < colors.length; i++){
        		for(int j = 0; j < repetitions; j++){
        			temporaryArray[count++][columnNumber] = colors[i];
        		}
        	}
        }
        
        return setCombinations(temporaryArray, colors, columnNumber + 1);
    }
    
    
    public void checkCorrect(String check){
    	// simple check if the combination is correct or not
		if(check.equals("yes")){
			System.out.println("\nYAY I GOT THE CORRECT CODE!");
			System.out.println("\nWould you like to start a new game? yes/no");
			String check2 = scan.next();
			if(check2.equals("yes")){
				System.out.println("\n\n\n\n\n\n\n");
				newGame();
			} else{
				System.out.println("\nThank you for playing!!!");
				System.exit(0);
			}
		} else{
			System.out.println("Oh no!");
		}
	}
    
    
    public void newGame(){
		String[] args = { }; 
		MastermindGame.main(args); 
	} // Makes a new game by running the main method again
}
