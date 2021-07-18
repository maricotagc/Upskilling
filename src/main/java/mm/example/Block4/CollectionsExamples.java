//To check execute all examples and check result from https://www.geeksforgeeks.org/collections-in-java-2/

package mm.example.Block4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsExamples {

    public static class AddAll {
        public static void main(String[] args) {
            try {
                // creating object of List<String>
                List<String> arrlist1 = new ArrayList<String>();
                // Adding element to arrlist1
                arrlist1.add("A");
                arrlist1.add("B");
                arrlist1.add("C");
                arrlist1.add("Tajmahal");

                // printing the arrlist1 before operation
                System.out.println("arrlist1 before operation : " + arrlist1);

                // add the specified element to specified Collections
                // using addAll() method
                boolean b = Collections.addAll(arrlist1, "1", "2", "3");

                // printing the arrlist1 after operation
                System.out.println("result : " + b);

                // printing the arrlist1 after operation
                System.out.println("arrlist1 after operation : " + arrlist1);

                // Verify if collection contains a specific element
                System.out.println("Does arrlist1 contain Tajmahal? " + arrlist1.contains("Tajmahal"));

                // Verify if collection contains all elements from another collection
                List<String> arrList2 = new ArrayList<String>();

                arrList2.add("1");
                arrList2.add("2");
                arrList2.add("3");
                System.out.println("Does arrlist1 contain arrList2? " + arrlist1.containsAll(arrList2));

                // Verify if the collection is equals to the object
                // creating object of List<String>
                List<String> arrList3 = new ArrayList<String>();
                arrList3.add("A");
                arrList3.add("B");
                arrList3.add("C");
                arrList3.add("Tajmahal");
                arrList3.add("1");
                arrList3.add("2");
                arrList3.add("3");

                System.out.println("Is arrlist1 equals to arrList3? " + arrlist1.equals(arrList3));

                // Verify the hashcode for each list
                List<String> arrList4 = new ArrayList<String>();
                arrList4.add("A");
                arrList4.add("B");
                System.out.println("The hashcode for arrlist1 is " + arrlist1.hashCode());
                System.out.println("The hashcode for arrList4 is " + arrList4.hashCode());

                //Clear all elements and print final result
                arrlist1.clear();
                System.out.println("arrlist1 after clear operation: " + arrlist1);

                // Verify if list contains no elements
                System.out.println("Is arrList1 empty? " + arrlist1.isEmpty());

                // Verify max value in collection
                System.out.println("The max value in ArrList4 is " + Collections.max(arrList4));

                // Remove on item
                arrList4.remove("A");
                System.out.println("arrList4 after removal of object A: " + arrList4);

                // Remove all items
                List<String> arrList5 = new ArrayList<String>();
                arrList5.add("A");
                arrList5.add("B");
                arrList5.add("C");

                List<String> arrList6 = new ArrayList<String>();
                arrList6.add("A");
                arrList6.add("B");
                System.out.println("arrList5 before remove all" + arrList5);
                arrList5.removeAll(arrList6);

                System.out.println("arrList5 after removal of arrList6: " + arrList5);

            } catch (NullPointerException e) {
                System.out.println("Exception thrown : " + e);
            } catch (IllegalArgumentException e) {
                System.out.println("Exception thrown : " + e);
            }
        }
    }
}
