//C:\Users\maric\Upskilling\src\files\Problem - 785A - Codeforces.pdf

package mm.example.Block3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polyhedrons {

    private final static List<String> POLYHEDRONS = Arrays.asList("Tetrahedron", "Cube", "Octahedron", "Dodecahedron", "Icosahedron");

    public static int convertNumberOfPolyhedrons(String numberOfPolyhedrons) throws Exception {
        try {
            return Integer.parseInt(numberOfPolyhedrons);
        } catch (Exception e) {
            throw new Exception("The number of polyhedrons must be an integer.");
        }
    }

    public static boolean validateNamePolyhedron(String polyhedronName) throws Exception {

        if (POLYHEDRONS.contains(polyhedronName)) {
            return true;
        } else {
            throw new Exception("The polyhedron name should be in " + POLYHEDRONS.toString());
        }
    }

    public static int calculateTotalNumberOfPolyhedronsFaces(List<String> polyhedronsList) {

        Integer sum = 0;
        for (String polyhedron : polyhedronsList) {

            switch (polyhedron) {
                case "Tetrahedron":
                    sum = sum + 4;
                    break;
                case "Cube":
                    sum = sum + 6;
                    break;
                case "Octahedron":
                    sum = sum + 8;
                    break;
                case "Dodecahedron":
                    sum = sum + 12;
                    break;
                case "Icosahedron":
                    sum = sum + 20;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer numberOfPolyhedrons = Polyhedrons.convertNumberOfPolyhedrons(br.readLine());

        List<String> polyhedronsList = new ArrayList<>();

        for (int i = 0; i < numberOfPolyhedrons; i++) {
            String polyhedronName = br.readLine();
            if (validateNamePolyhedron(polyhedronName)) {
                polyhedronsList.add(polyhedronName);
            }
        }
        System.out.println(calculateTotalNumberOfPolyhedronsFaces(polyhedronsList));
    }

}
