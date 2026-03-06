import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictorTester {
    public static void main(String[] args) {
        MarkovPredictor markWeather = new MarkovPredictor("weather.csv");

        MarkovPredictor markActivites = new MarkovPredictor("activites.csv");

        System.out.println("Weather: Sunny");
        for (int i = 0; i < 10; i++) {
            System.out.println(markWeather.predictNextState("Sunny"));
        }

        System.out.println();

        System.out.println("Weather: Cloudy");
        for (int i = 0; i < 10; i++) {
            System.out.println(markWeather.predictNextState("Cloudy"));
        }

        System.out.println();

        System.out.println("Activites: Sleeping");
        for (int i = 0; i < 10; i++) {
            System.out.println(markActivites.predictNextState("Sleeping"));
        }

        System.out.println();

        System.out.println("Activites: Eating");
        for (int i = 0; i < 10; i++) {
            System.out.println(markActivites.predictNextState("Eating"));
        }
    }
}
