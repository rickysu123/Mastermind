
	public interface MastermindInterface {
		 public void response(int colorsRightPositionWrong, int positionsAndColorRight);
		 public void newGame();  // Reset the game
		 public String [] nextGuess();  // return the next guess
	}
