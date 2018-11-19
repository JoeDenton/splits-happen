import java.util.Scanner; 

/*
 * Asks user to input a string representing a sequence of rolls in a game of bowling and returns the total of the game
 * @author Joseph Denton 
 * @version 1.0 
 */
public class SplitsHappen {

	/*
	 * Calculates the final score in a game of bowling given a string representation of a sequence of rolls
	 * @param in: This is a string representation of a sequence of rolls 
	 * @return total: This is the total for the game 
	 * @throws IllegalArgumentException if the character is not a valid character used to represent a roll 
	 */
	public static int totalCalculator(String in) {
		
		int total = 0;
		double frame  = 0;
		for(int j = 0; j < in.length(); j++) {
			
			switch(in.charAt(j)) {
			
			// Case where a strike is rolled
			case 'X':
				frame++; 
				// Case when strike is rolled in the last frame, or as an extra roll
				if(frame >= 10) {
					total += 10; 	
					break; 
					
				}
				// Case when the next two rolls after strike are also strikes 
				else if(in.charAt(j+1) == 'X' && in.charAt(j+2) == 'X') {
					total+= 30;
					break; 
				}
				// Case when the next roll after the strike is also a strike
				else if(in.charAt(j+1) == 'X') {
					total += 20 + Character.getNumericValue(in.charAt(j+2));	
					break; 
				}
				// Case when the roll after the next is also a strike 
				else if(in.charAt(j+2) == 'X') {
					total+= 20 + Character.getNumericValue(in.charAt(j+1));	
					break; 
				}
				// Case when the next two rolls are both misses
				else if(in.charAt(j+1) == '-' && in.charAt(j+2) == '-') {
					total += 10; 
					break; 
				}
				// Case when next roll after a strike is a miss 
				else if(in.charAt(j+1) == '-') {
					total += 10 + Character.getNumericValue(in.charAt(j+2));
					break; 
				}
				// Case when the roll after the next is a miss 
				else if(in.charAt(j+2) == '-') {
					total += 10 + Character.getNumericValue(in.charAt(j+1)); 
					break; 
				}
				// Case when the next roll is a spare
				else if(in.charAt(j+2) == '/') {
					total+= 20;
					break; 
				}
				// Case when next two rolls are a numeric value (is not a strike, spare, or a miss) 
				else {
					total+= (10 + Character.getNumericValue(in.charAt(j+1)) + Character.getNumericValue(in.charAt(j+2))); 
					break; 
				}
			
				
			// Case when a miss is rolled 
			case '-': frame+= 0.5; break;
			
			// Case when a spare is rolled 
			case '/': 
				
				frame+= 0.5; 
				
				// Case when the spare is rolled in the last frame, or as an extra roll 
				if(frame >= 10) {
					total += (10 - Character.getNumericValue(in.charAt(j-1)));
					break; 
				}
				// Case when the next roll after spare is a strike
				else if(in.charAt(j+1) == 'X') {
					total += 10 + (10 - Character.getNumericValue(in.charAt(j-1)));
					break; 
				}
				// Case when the next roll after spare is a miss 
				else if(in.charAt(j+1) == '-') {
					total += (10 - Character.getNumericValue(in.charAt(j-1))); 
					break; 
				}
				// Case when next roll after spare is a numeric value 
				else {
					total += (10 - Character.getNumericValue(in.charAt(j-1))) + Character.getNumericValue(in.charAt(j+1)); 
					break;
				}
				
			// Cases for when a numeric value of pins is knocked down (not a strike, spare, or miss)
			case '1': total += 1; frame += 0.5; break; 
			case '2': total += 2; frame += 0.5; break; 
			case '3': total += 3; frame += 0.5; break;
			case '4': total += 4; frame += 0.5; break;
			case '5': total += 5; frame += 0.5; break; 
			case '6': total += 6; frame += 0.5; break; 
			case '7': total += 7; frame += 0.5; break;
			case '8': total += 8; frame += 0.5; break;
			case '9': total += 9; frame += 0.5; break;
			
			// Case when it is not a valid roll (not a numeric value, X, / or -)
			default:
				throw new IllegalArgumentException("Illegal Argument"); 
			}
		}
		return total; 
	}
	
	public static void main(String[] args) {
		
		String input = "";
		Scanner in = new Scanner(System.in); 
		System.out.println("Please enter your sequence of rolls: ");
		input = in.nextLine(); 
		in.close();
		System.out.println("Your total score is: " + totalCalculator(input));
		
	}
}
