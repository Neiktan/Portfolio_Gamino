import java.util.PriorityQueue; //for having a ordered collection
import java.util.Collections; //for adding multiple elements at once
//used for no duplicate elements
import java.util.HashSet;
import java.util.Set; 

public class Gamino_SLO1 {
    public static void main(String[] args) {

        //priority queues
        //queues received from the instructor
        PriorityQueue<String> queue1 = new PriorityQueue<>();  //first priority queue
        Collections.addAll(queue1, "George", "Jim", "John", "Blake", "Kevin", "Michael", "Walter", "Angel"); //adding elements to first queue

        PriorityQueue<String> queue2 = new PriorityQueue<>(); //second priority queue
        Collections.addAll(queue2, "George", "Katie", "Kevin", "Michelle", "Ryan", "Angel"); //adding elements to second queue

        //Applying methods
        //MO = "Main to Output"
        PriorityQueue<String> unionMO = union(queue1, queue2); //calling union method, named it unionMO
        PriorityQueue<String> differenceMO = difference(queue1, queue2); //calling difference method, named it differenceMO
        PriorityQueue<String> intersectionMO = intersection(queue1, queue2); //calling intersection method, named it intersectionMO

        //Outputs
        System.out.println("\n\t\t\tQUEUES RECEIVED:"); //title
        System.out.println("First queue: " + queue1 + "."); //showing first queue without modifying it
        System.out.println("Second queue: " + queue2 + "."); //showing second queue without modifying it
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("\nUNION OF QUEUES WITHOUT DUPLICATES: " + orderList(unionMO) + "."); //showing union of both queues without duplicates and ordered
        System.out.println("DIFFERENCE BETWEEN FIRST QUEUE AND SECOND QUEUE: " + orderList(differenceMO) + "."); //showing difference between both queues and in order
        System.out.println("INTERSECTIONS OF DUPLICATES: " + orderList(intersectionMO) + "."); //showing intersection of both queues and in order
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
    }


    //-------------------------------------------------------------------------------------------------------
    //Methods!!!
    
    //UNION METHOD
    //the Union of the two queues without duplicates
    private static PriorityQueue<String> union(
            Iterable<String> queue1, Iterable<String> queue2) {

        Set<String> set = new HashSet<>();
        //adding elements from both queues to the set
        for (String s : queue1) {
            set.add(s); //add elements from queue1
        }
        for (String s : queue2) { 
            set.add(s); //add elements from queue2, duplicates ignored
        }

        //returning a only one queue without duplicates
        return new PriorityQueue<>(set);
    }



    //DIFFERENCE METHOD
    //showing the elements that are different in the first queue compared to the second queue
    private static PriorityQueue<String> difference(
            Iterable<String> queue1, Iterable<String> queue2) {

        Set<String> set1 = new HashSet<>(); //first set
        for (String s : queue1) {
            set1.add(s);
        }

        Set<String> set2 = new HashSet<>(); //second set
        for (String s : queue2) {
            set2.add(s);
        }

        //delete from set1 all elements that are also in set2
        set1.removeAll(set2);

        return new PriorityQueue<>(set1);
    }



    //INTERSECTION METHOD
    //showing elements that are the same in both queues
    private static PriorityQueue<String> intersection(
            Iterable<String> queue1, Iterable<String> queue2) {

        Set<String> set1 = new HashSet<>(); //first set
        for (String s : queue1) {
            set1.add(s);
        }

        Set<String> set2 = new HashSet<>(); //second set
        for (String s : queue2) {
            set2.add(s);
        }

        //keep only elements that are in both sets
        //3 of them (George, Kevin, Angel)
        set1.retainAll(set2);

        return new PriorityQueue<>(set1);
    }



    //ORDER LIST METHOD
    //gives an ordered string of the elements in the queue (A-Z)
    private static String orderList(Iterable<String> items) { 
        PriorityQueue<String> temp = new PriorityQueue<>(); //temporary priority queue
        for (String s : items) {
            temp.add(s); //adding elements to the temporary queue
        }

        StringBuilder sb = new StringBuilder(); //string builder to create the output string
        while (!temp.isEmpty()) { //while temp is not empty
            sb.append(temp.poll()); //removes and returns the head of the queue
            if (!temp.isEmpty()) {
                sb.append(", "); //adding comma and space if there are more elements
            }
        }
        return sb.toString(); //returning the ordered string
    }
}
