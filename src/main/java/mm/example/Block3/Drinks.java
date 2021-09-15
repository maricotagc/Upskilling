//C:\Users\maric\Upskilling\src\files\Problem - 200B - Codeforces.pdf

package mm.example.Block3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Drinks {

    public static Integer validateNumberOfDrinks(String drinks) throws Exception {
        try {
           return Integer.parseInt(drinks);
        } catch (Exception e) {
            throw new Exception("The input must be a valid integer. User has inputted: [" + drinks + "]");
        }
    }

    public static List<Double> validateOJPercentage(String[] drink) throws Exception {

        List<Double> drinks = new ArrayList<>();

        for (int i = 0; i < drink.length; i++) {
            try {
                drinks.add(Double.parseDouble(drink[i]));
            } catch (Exception e) {
                throw new Exception("The orange juice percentage must be a valid integer");
            }
        }
        return drinks;
    }

    public static Double calculateOrangePercentual(Integer numberOfDrinks, List<Double> ojs) {
        double sum = 0D;
        double orangePercent = 0D;

        for (int i = 0; i < numberOfDrinks; i++) {
            sum = sum + ojs.get(i);
        }
        return orangePercent = sum / numberOfDrinks;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer numberOfDrinks = Drinks.validateNumberOfDrinks(br.readLine());
        List<Double> ojPercentage = Drinks.validateOJPercentage(br.readLine().split(" "));
        System.out.println(Drinks.calculateOrangePercentual(numberOfDrinks, ojPercentage));
    }
}
