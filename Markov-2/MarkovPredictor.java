import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {

    // Please define your own variables and data structures

    //data is what readData returns
    private ArrayList<String[]> data;

    //map is what makeHashMap returns
    private HashMap<String, ArrayList<String>> map;

    //weatherTypes is all of the different weather types
    private ArrayList<String> weatherTypes;

    public MarkovPredictor(String filePath) {
        data = readData(filePath);
        map = makeHashMap();
        weatherTypes = getWeathers();
    }

    public ArrayList<String[]> readData(String filePath) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String[]> list = new ArrayList<String[]>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(",");
                for (int i = 0; i < strs.length; i++) {
                    strs[i].trim();
                }
                list.add(strs);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public void setData(ArrayList<String[]> newData) {
        this.data = newData;
    }

    public HashMap<String, ArrayList<String>> getHashMap() {
        return map;
    }

    public void setHashMap(HashMap<String, ArrayList<String>> newMap) {
        this.map = newMap;
    }

    public ArrayList<String> getWeatherTypes() {
        return weatherTypes;
    }

    public void setWeatherTypes(ArrayList<String> newWeathers) {
        this.weatherTypes = newWeathers;
    }


    //get every single weather type and its frequency in the data set as a whole
    //make a random double between 0 and 1
    //map the frequencies to specific values between 0 and 1
        //e.g if cloudy is 67%, if random number is between 0 and 67 return cloudy
        //if sunny if 12%, if random number is between 67 and 79 return sunny and so forth

    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {

        //get the frequency list for currentState's next weather types
        int[] weatherFreqList = weatherTypeCounter(currentState);

        //make a new array of doubles that represents the probabilites for each weather type
        double[] probabilityList = weatherTypeProbability(weatherFreqList);

        //make a new array of probabilities without any zeroes
        double[] noZeroProbability = removeZeroProbabilities(probabilityList);

        //make a new array of the ranges according to the probabilites
        double[] ranges = generateRangeValues(noZeroProbability);

        //make a new ArrayList of only the weather types that actually occured
        ArrayList<String> noZeroWeatherList = removeZeroWeathers(weatherFreqList);

        //generate a random number between 0 and 1
        double rand = Math.random();


        //instantiate counter variable int counter
        int counter = 0;

        //loop through ranges
        for (int i = 0; i < ranges.length; i++) {

            //if rand is less than the current index of ranges break out of loop
            if (rand <= ranges[i]) {
                break;

            //if not add to counter and continue
            } else {
                counter++;
                continue;
            }
        }

        //return the value of noZeroWeatherList at counter
        return noZeroWeatherList.get(counter);
    }

    //method that returns a new ArrayList of weather types that doesn't include weather types that didn't occur
    public ArrayList<String> removeZeroWeathers(int[] weatherFreqList) {

        //instantiate the return list as weatherTypes
        ArrayList<String> returnList = new ArrayList<String>();

        //loop through weatherFreqList
        for (int i = 0; i < weatherFreqList.length; i++) {

            //if the current index of weatherFreqList isn't 0 add it to returnList
            if (weatherFreqList[i] > 0) {
                returnList.add(weatherTypes.get(i));
            } else {
                continue;
            }
        }

        return returnList;
    }

    //method that gets the ranges for the probabilities
    //e.g[0.5, 0.62, 0.71, 0.91, 0.96, 0.99]; the index of this array corresponds to the index in noZeroProbability
    public double[] generateRangeValues(double[] noZeroProbability) {

        //make a new array with the same length as the probabilities array
        double[] ranges = new double[noZeroProbability.length];

        //make the first term the same
        ranges[0] = noZeroProbability[0];

        //loop through the new array starting at 1
        for (int i = 1; i< ranges.length; i++) {

            //the range value is equal to the range value at the previous index + the probability at the current index
            ranges[i] = ranges[i - 1] + noZeroProbability[i];
        }
        
        return ranges;
    }

    //method that gets rid of any 0 values in the probability array
    public double[] removeZeroProbabilities(double[] weatherTypeProbability) {
        int size = 0;
        for (int i = 0; i < weatherTypeProbability.length; i++) {
            if (weatherTypeProbability[i] > 0) {
                size++;
            } else {
                continue;
            }
        }

        double[] noZeroProbability = new double[size];

        int k = 0;
        for (int i = 0; i < weatherTypeProbability.length; i++) {
            if (weatherTypeProbability[i] > 0) {
                noZeroProbability[k] = weatherTypeProbability[i];
                k++;
            } else {
                continue;
            }
        }

        return noZeroProbability;
    }

    //converts the frequencies into probabilites
    public double[] weatherTypeProbability(int[] weatherFreqList) {
        
        //instantiate new variable that represents the total amount entries in weatherFreqList
        int count = 0;

        //loop through weatherFreqList
        for (int i = 0; i < weatherFreqList.length; i++) {

            //add weatherFreqList[i] to count
            count += weatherFreqList[i];
        }
        
        //make a new array of doubles
        double[] probabilityArray = new double[weatherFreqList.length];

        //loop through weatherFreqList
        for (int i = 0; i < weatherFreqList.length; i++) {

            //make each index of the double array weatherFreqList[i] / count
            probabilityArray[i] = (double) (weatherFreqList[i]) / (double) (count);
        }
        
        return probabilityArray;
    }


    //method that gets all the different weather types and return them in an array list
    public ArrayList<String> getWeathers() {
        ArrayList<String> returnList = new ArrayList<String>();

        //loop through data
        for (int i = 0; i < data.size(); i++) {

            //loop through the arrays within data
            for (int j = 0; j < data.get(i).length; j++) {
                //data.get(i) is the weather pattern/array e.g Sunny -> cloudy; ["Sunny", "Cloudy"]
                //data.get(i)[j] is the weather type e.g "sunny"

                String weatherType = data.get(i)[j];

                //if returnList doesn't already contain the current weather thing add it
                if (!returnList.contains(weatherType)) {
                    returnList.add(weatherType);
                } else {
                    continue;
                }
            }
        }
        return returnList;
    }


    //create the frequency tables for each weather type
    //make a new hash map
    //add each weather pattern to the map
        //the key is the weather type of the first day or strs[0]
        //the value is the list of "second-day" weather types


    //method that takes in a list of all the weather patterns, puts them into a hash map with the shit above and returns the map
    public HashMap<String, ArrayList<String>> makeHashMap() {

        HashMap<String, ArrayList<String>> returnMap = new HashMap<String, ArrayList<String>>();

        //loop through list
        for (int i = 0; i < data.size(); i++) {

            //put each entry into the map with the key being index 0 of the array and the value being the "next-day" weathers
            String key = data.get(i)[0];

            ArrayList<String> value = getNextDayWeathers(key);

            returnMap.put(key, value);
        }

        return returnMap;
    }

    //make a list of all the "day-after" weather types for one specific weather type
    public ArrayList<String> getNextDayWeathers(String key) {

        ArrayList<String> returnList = new ArrayList<String>();

        //loop through data
        for (int i = 0; i < data.size(); i++) {

            String firstDay = data.get(i)[0];

            String secondDay = data.get(i)[1];

            //if firstDay is equal to the key add secondDay to the returnList
            if (firstDay.equals(key)) {
                returnList.add(secondDay);
            } else {
                continue;
            }
        }
        
        return returnList;
    }




    //go through a bucket in a hash map and consolodate the data
    //turn: 
        //Sunny -> {"Partly Cloudy", "Sunny", "Sunny", "Partly Cloudy", "Snowy", "Sunny"}
    //into:
        //Sunny -> {Partly Cloudy: 2, Sunny: 3, Snowy: 1}

    //i know all the weather types
    //make a variable for each weather type that tracks how many times it shows up with that key in the map
    //make an array of ints the same size as the ArrayList of weather types
    //have each index in the array correspond to each index in the ArrayList
        //e.g array[0] -> ArrayList.get(0), array[1] -> ArrayList.get(1) etc
    //the value at each index in the array represents the number of times the weather type in the ArrayList shows up with that key in the map
        //e.g if array[0] is 5 and ArrayList.get(0) is "Sunny", that means "Sunny" shows up 5 times with the key: "Sunny"



    //make a method that counts the number of instances of each weather type for a specific key (returns an int arry)
    //takes in a list of all the different weather types, a hash map, and a key from that map
    //weatherTypes is a list of all the possible weather types
    //map is the hash map
    //key is the "first-day" weather type / key for the hash map
    //this method more or less just returns a frequency list for all the weatherTypes at key
    public int[] weatherTypeCounter(String key) {

        //make an array of ints the same size as the ArrayList of weather types
        int[] returnArray = new int[weatherTypes.size()];

        //make an ArrayList variable that represents the value in map of key
        ArrayList<String> valueList = map.get(key);

        //itterate through valueList
        for (int i = 0; i < valueList.size(); i++) {

            //make a variable that stores the value at the current index of valueList
            String currentValue = valueList.get(i);

            //loop through weatherTypes
            for (int j = 0; j < weatherTypes.size(); j++) {

                //when weatherTypes.get(j) is equal to currentValue add 1 to returnArray[j]
                if (currentValue.equals(weatherTypes.get(j))) {
                    returnArray[j]++;
                    break;
                } else {
                    continue;
                }
            }
        }

        //once finished with itterating through all that shit we're done!
        return returnArray;
    }

    //toString method that returns the "day-one" weather type (aka the key!) and the amount of times each weather type shows up as a "day-two"
    //e.g Sunny -> {Partly Cloudy: 2, Sunny: 3, Snowy: 1}
    public String toStringForKey(String key) {

        //get the frequency list for key's weather types
        int[] weatherFreqList = weatherTypeCounter(key);

        //make a new stringBuilder
        StringBuilder returnString = new StringBuilder();

        //append key to returnString
        returnString.append(key + " -> {");

        //loop through weatherFreqList
        for (int i = 0; i < weatherFreqList.length; i++) {

            //if the current index isn't 0 append weatherTypes.get(i) + some bullshit + weatherFreqList[i]
            if (weatherFreqList[i] > 0) {
                returnString.append(weatherTypes.get(i) + ": " + weatherFreqList[i] + ", ");
            } else {
                continue;
            }
        }

        //get rid of the ", " at the end of returnString and append the close bracket
        returnString.delete(returnString.length() - 2, returnString.length() - 1);
        returnString.append("}");

        return returnString.toString();
    }


    //returns the toStringForKey method for each weather type
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < weatherTypes.size(); i++) {
            returnString.append(toStringForKey(weatherTypes.get(i)));
            returnString.append("\n");
        }

        return returnString.toString();
    }

}