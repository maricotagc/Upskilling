// C:\Users\maric\Upskilling\src\files\Problem - 4C - Codeforces.pdf

package mm.example.Block6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RegistrationSystem {

    HashMap<String, Integer> users = new HashMap<>();

    public String createNewUser(String inputtedUser) {
        StringBuilder newUser = new StringBuilder();

        if (!users.containsKey(inputtedUser)) {
            users.put(inputtedUser, 0);
            newUser = newUser.append("OK");
        } else {
            users.put(inputtedUser, users.get(inputtedUser) + 1);
            newUser = newUser.append(inputtedUser).append(users.get(inputtedUser));
        }
        return newUser.toString();
    }

    public static void main(String[] args) throws IOException {

        RegistrationSystem test = new RegistrationSystem();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int usersNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < usersNumber; i++) {
            String inputtedUser = br.readLine();
            System.out.println(test.createNewUser(inputtedUser));
        }
    }
}
