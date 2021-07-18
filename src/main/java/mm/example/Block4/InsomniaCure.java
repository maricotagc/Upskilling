package mm.example.Block4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InsomniaCure {

    public static int damagedDragons(List<Integer> dragons) {
        int totalOfDamagedDragons = 0;
        int k = dragons.get(0);
        int l = dragons.get(1);
        int m = dragons.get(2);
        int n = dragons.get(3);
        int d = dragons.get(4);

        for (int i = 1; i <= d; i++) {
            if (i % k == 0) {
                totalOfDamagedDragons = totalOfDamagedDragons + 1;
            } else if (i % l == 0) {
                totalOfDamagedDragons = totalOfDamagedDragons + 1;
            } else if (i % m == 0) {
                totalOfDamagedDragons = totalOfDamagedDragons + 1;
            } else if (i % n == 0) {
                totalOfDamagedDragons = totalOfDamagedDragons + 1;
            }
        }
        return totalOfDamagedDragons;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> dragons = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            dragons.add(Integer.valueOf(br.readLine()));
        }

        System.out.println(InsomniaCure.damagedDragons(dragons));
    }
}
