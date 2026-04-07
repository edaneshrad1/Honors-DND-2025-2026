import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MiniGPT {

	private int chainOrder;

	//book is what readData returns: the book converted into an ArrayList of every char
	private ArrayList<Character> book;

	//map is the HashMap we are using as the "brain" for this shit
	//map is initialized as what is returned by the makeHashMap method
	private HashMap<String, ArrayList<Character>> map;

	//this is an ArrayList of every unique character in book
	private ArrayList<Character> uniqueCharacters;

	public MiniGPT(String fileName, int chainOrder) {
		book = readData(fileName);
		this.chainOrder = chainOrder;
		map = makeHashMap();
		uniqueCharacters = getAllUniqueChars();
	}


	
	public void generateText(String outputFileName, int numChars) {

		//make the first chainOrder chars of the generated text the first chainOrder chars of book
		//instantiate that string as returnString
		StringBuilder returnString = new StringBuilder();
		for (int i = 0; i < chainOrder; i++) {
			returnString.append(book.get(i));
		}
		
		//loop from 0 to numChars
		for (int i = 0; i < numChars; i++) {

			//at each itteration of the loop instantiate the prediction String as the substring of returnString
			//from length - chainOrder to the end
			String predictionString = returnString.substring(returnString.length() - chainOrder);

			//append predictNextChar(predictionString) to returnString
			returnString.append(predictNextChar(predictionString));
		}
		
		System.out.println(returnString.toString());
	}

	//method that takes in a string of length chainOrder, predicts the next char, and returns it
	public char predictNextChar(String key) {

		//get the frequency list for the current String's next char
		int[] nextCharFrequencyList = nextCharsFrequency(key);

		//make a new array with the probability of every next char
		double[] nextCharProbability = nextCharProbability(nextCharFrequencyList);

		//remove the chars with a probability/cy of 0 from the array
		double[] noZeroProbabilites = removeZeroProbabilites(nextCharProbability);

		//make a new array of the ranges according to the probabilites
		double[] ranges = generateRangeValues(noZeroProbabilites);

		//make an arrayList of only the characters that actually occurred
		ArrayList<Character> noZeroCharList = removeZeroChars(nextCharFrequencyList);

		//generate a random number between 0 and 1
		double rand = Math.random();

		//make an int variable that represents the index of the list of chars
		int counter = 0;

		//loop through ranges
		for (int i = 0; i < ranges.length; i++) {

			//if rand is less than or equal to ranges[i] break out of the loop
			if (rand < ranges[i]) {
				break;
			
			//if not add to counter and continue
			} else {
				counter++;
				continue;
			}
		}
		
		return noZeroCharList.get(counter);
	}



	//returns whatever is in filePath as an ArrayList of chars
	public ArrayList<Character> readData(String filePath) {
		ArrayList<Character> chars = new ArrayList<Character>();
        // Example: Reading from a file. You could also use InputStreamReader 
        // for console input.
        try (BufferedReader reader = new BufferedReader(new FileReader("thegreatgatsby.txt"))) {
            int charAsInt;
            // Read until the end of the stream (-1 is returned)
            while ((charAsInt = reader.read()) != -1) {
                // Cast the integer value to a character
                char character = (char) charAsInt;
				chars.add(character);
            }
			return chars;
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
			return null;
        }
    }

	public ArrayList<Character> getBook() {
		return book;
	}

	public void setBook(ArrayList<Character> newBook) {
		book = newBook;
	}

	public int getChainOrder() {
		return chainOrder;
	}

	public void setChainOrder(int newChainOrder) {
		chainOrder = newChainOrder;
	}

	public HashMap<String, ArrayList<Character>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ArrayList<Character>> newMap) {
		map = newMap;
	}

	//method that makes the hashMap for MiniGPT with the key being a substring of length: chainOrder and the value being 
	// an ArrayList of chars with each index being every char that occurs in the book after that substring
	public HashMap<String, ArrayList<Character>> makeHashMap() {

		HashMap<String, ArrayList<Character>> returnMap = new HashMap<String, ArrayList<Character>>();

		//loop through book from 0 to length - chainOrder
		for (int i = 0; i + chainOrder < book.size(); i++) {

			//make the key a new StringBuilder
			StringBuilder keyBuilder = new StringBuilder();

			//loop from i to i + chainOrder in book
			for (int j = 0; j < chainOrder; j++) {

				//add each index of book from i to chainOrder to keyBuilder
				keyBuilder.append(book.get(i + j));
			}

			//make the key the toString of keyBuilder
			String key = keyBuilder.toString();

			//the next char is book.get(i + chainOrder)
			char nextChar = book.get(i + chainOrder);

			//if the key isn't already in the map add it with a new ArrayList
			if (!returnMap.containsKey(key)) {
				returnMap.put(key, new ArrayList<Character>());
			} else {
				continue;
			}

			///add the next char to the valueList for key
			returnMap.get(key).add(nextChar);
		}
		
		return returnMap;
	}


	//make a method that takes in a string of length chainOrder and returns a list of every char that comes 
	//after every instance of that string
	public ArrayList<Character> makeListOfNexts(String key) {
		ArrayList<Character> returnList = new ArrayList<Character>();

		//loop through book
		for (int i = 0; i < book.size(); i++) {

			//if you find key at the very end of book
			//i.e the last char of key is also the index in book break out of the loop
			if (i + key.length() == book.size()) {
				break;
			}

			//check if isSameString is true for key at each index
			if (isSameString(key, i)) {

				//if it is true add the next index in book to the return list
				returnList.add(book.get(i + key.length()));

			//if not continue itterating
			} else {
				continue;
			}
		}
		return returnList;
	}
	
	//make a method that takes in a string and checks if it is the same as a sequence of chars from book
	//starting from a specific index of book
	public boolean isSameString(String key, int start) {

		//loop through book from start to start + chainOrder
		for (int i = 0; i < chainOrder; i++) {

			//if i + start is greater than the size of the book - 1
			//(meaning the book from start to the end has a smaller length that key), return false
			if (i + start > book.size() - 1) {
				return false;
			}

			//if at any point the specific char in key is not equal to its corresponding index in book return false
			if (key.charAt(i) != book.get(i + start)) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	//make a method that returns a list of every unique char in book
	public ArrayList<Character> getAllUniqueChars() {
		ArrayList<Character> returnList = new ArrayList<Character>();

		//loop through book
		for (int i = 0; i < book.size(); i++) {

			//if return list doesn't contain the current index of book add it to returnList
			if (!returnList.contains(book.get(i))) {
				returnList.add(book.get(i));

				//if the list alread contains that character continue
			} else {
				continue;
			}
		}
		
		return returnList;
	}

	//make a method that counts the number of instance of each unique character for a specific key
	//returns an int array the where each index of the array corresponds to that same index of book
	//e.g. if your book is {'a', 'c', 'f', 'h'} and your array is [1, 4, 5, 0] 
	//then a shows up once, c shows up 4 times, f 5 times, and h 0 times
	public int[] nextCharsFrequency(String key) {

		//make an int array the same size as uniqueCharacters
		int[] returnArray = new int[uniqueCharacters.size()];

		//make a new variable that equals the value in map of key
		ArrayList<Character> valueList = map.get(key);

		//loop through the value list
		for (int i = 0; i < valueList.size(); i++) {

			//make a variable that stores the current index of valueList
			char currentValue = valueList.get(i);

			//loop through uniqueCharacters
			for (int j = 0; j < uniqueCharacters.size(); j++) {

				//once you find the index of current char in uniqueCharacters add 1 to that index in the array
				if (uniqueCharacters.get(j) == currentValue) {
					returnArray[j]++;
					break;
				} else {
					continue;
				}
			}
		}
	
		return returnArray;
	}

	//make a method that converts an array of frequencies into an array of probabilites
	public double[] nextCharProbability(int[] nextCharFrequencyList) {

		//make a new variable that represents the total amount of entries in the frequency list
		int counter = 0;

		//loop through the frequency list
		for (int i = 0; i < nextCharFrequencyList.length; i++) {
			
			//add the nextCharFrequencyList[i] to counter
			counter += nextCharFrequencyList[i];
		}

		//make a new array of doubles the same size as nextCharFrequencyList
		double[] probabilityArray = new double[nextCharFrequencyList.length];

		//loop through nextCharFrequencyList
		for (int i = 0; i < nextCharFrequencyList.length; i++) {

			//make probabilityArray[i] nextCharFrequencyList[i] / counter
			probabilityArray[i] = (double) (nextCharFrequencyList[i]) / (double) (counter);
		}

		return probabilityArray;
	}

	//method that removes all the zero probabilites from an array of probabilites
	public double[] removeZeroProbabilites(double[] nextCharProbabilities) {
		
		//make a new variable size
		int size = 0;

		//loop through nextCharProbabilites
		for (int i = 0; i < nextCharProbabilities.length; i++) {

			//add one to size every teim nextCharProbabilites[i] isn't 0
			if (nextCharProbabilities[i] > 0) {
				size++;
			} else {
				continue;
			}
		}
		
		//make a new double array of length size
		double[] noZeroProbabilites = new double[size];

		//make a variable that represents the index in the "zero-less" array
		int k = 0;

		//loop through nextCharProbabilites
		for (int i = 0; i < nextCharProbabilities.length; i++) {

			//if nextCharProbabilites[i] isn't 0:
			//make noZeroProbabilites[k] nextCharProbabilites[i] and add 1 to k
			if (nextCharProbabilities[i] > 0) {
				noZeroProbabilites[k] = nextCharProbabilities[i];
				k++;
			} else {
				continue;
			}
		}

		return noZeroProbabilites;
	}

	//method that gets the ranges for a list of probabilities
    //e.g[0.5, 0.62, 0.71, 0.91, 0.96, 0.99]; the index of this array corresponds to the index in noZeroProbability
	public double[] generateRangeValues(double[] noZeroProbabilites) {

		//make a new double array with the same size as noZeroProbabilites
		double[] ranges = new double[noZeroProbabilites.length];

		//make the first index of ranges the first index on noZeroProbabilites
		ranges[0] = noZeroProbabilites[0];

		//loop through ranges starting at 1
		for (int i = 1; i < ranges.length; i++) {

			//the range value is equal to the range value at the previous index + the probability at the current index
			ranges[i] = ranges[i - 1] + noZeroProbabilites[i];
		}
		
		return ranges;
	}

	//method that returns an ArrayList of only the next chars that actually occurred
	public ArrayList<Character> removeZeroChars(int[] nextCharFrequencyList) {

		ArrayList<Character> returnList = new ArrayList<Character>();

		//loop through nextCharFrequencyList
		for (int i = 0; i < nextCharFrequencyList.length; i++) {

			//if the current index of nextCharFrequencyList isn't 0 add that index in uniqueCharacters to the list
			if (nextCharFrequencyList[i] > 0) {
				returnList.add(uniqueCharacters.get(i));
			}
		}
		
		return returnList;
	}
}
