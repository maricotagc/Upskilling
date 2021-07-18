package mm.example.Block0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMaxMinArray {

    public int findMaxAndMinInArray(int[] inputArray) {

        List<Integer> filteredArray = new ArrayList<>();

        if (inputArray == null || inputArray.length == 0) {
            throw new IllegalArgumentException("The array is either empty or null");
        }

        for (int a : inputArray) {
            if (a >= 0 && (a <= 1000 || a >= 1500) || a == 1030) {
                filteredArray.add(a);
            }
        }

        if (filteredArray.isEmpty()) {
            throw new IllegalArgumentException("The values in this array are invalid");
        }

        int min = filteredArray.get(0);
        int max = filteredArray.get(0);

        for (int y : filteredArray) {
            if (y > max) {
                max = y;
            }
            if (y < min) {
                min = y;
            }
        }

        int sumMinMax = (min + max);

        System.out.println("Input : " + Arrays.toString(inputArray));
        System.out.println("Step 1: " + filteredArray);
        System.out.println("Step 2: " + filteredArray);
        System.out.println("Step 3: " + max);
        System.out.println("Step 4: " + min);
        System.out.println("Step 5: " + sumMinMax);
        return sumMinMax;
    }
}