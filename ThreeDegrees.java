
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * main program, ThreeDegrees.java
 * will read the file and process search queries
 * @author Amrit Gautam
 */
public class ThreeDegrees {

    /* Main program Threedegree, uses provided movies and actors as graph connection
    * throws file not found exception if file is not available*/

    public static void main (String[] args) throws FileNotFoundException {

        System.out.println("Enter movie data filename(ex: src/mymovies.txt):"); //use src/mymovies.txt for path
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine(); //user input
        NewGraph g = new NewGraph(s);
        System.out.println(g.toString()); //prints graph


      while (true) {
         //check to make sure actors exist in the graph
          System.out.println("Enter first actor name (two case-sensitive words):");
          String s1 = scanner.nextLine();
          if (null == s1) {
              System.out.println("enter name again:");
              break;
          }

          if (!(g.hasNode(s1))) { //if actor not in the mymovies.txt file
              System.out.println(s1 + "\nis not known in the mymovies.txt dataset");
              continue;
          }

          System.out.println("Enter second actor name (two case-sensitive words):");
          String s2 = scanner.nextLine();
          if (null == s2) {
              System.out.println("enter name again:");
              break;
          }

          if (!(g.hasNode(s2))) {
              System.out.println(s2 + "is not known in the mymovies.txt dataset");
          }

          //tells if two actors have common node (movies)
          if (g.hasNode(s1) && g.hasNode(s2)) {
              List<Node> search = g.getShortestPath(s1,s2);
              int size = (search.size()-1)/2 ;  // sets chain of maximum length 3



               if (s1.equals(s2)) { // same actors....

                      System.out.println("Silly! They are in their own movie!\n");


                  }

              else if (search.isEmpty()){ // no connection

                  System.out.println("No connection detected");
              }
                //performs the search and either prints the chain linking the  names in a
               // chain of maximum length three,
               // or prints a message that reports no chain was found
               else if (size ==1) {

                   System.out.println ((search.get(0).getName() + "\nwas in "+search.get(1).getName() +
                           "\nwith" + search.get(2).getName()));
               }

               else if (size ==2) {

                   System.out.println ((search.get(0).getName() + "\nwas in "+search.get(1).getName() +
                           "\nwith" + search.get(2).getName()+ "\nwho was in " + search.get(3).getName() +
                           "\nwith" + search.get(4).getName()));
               }

               else if (size ==3) {

                   System.out.println ((search.get(0).getName() + "\nwas in "+search.get(1).getName() +
                           "\nwith" + search.get(2).getName()+ "\nwho was in " + search.get(3).getName() +
                           "\nwith" + search.get(4).getName()
                           + "\nwho was in " + search.get(5).getName() + "\nwith" + search.get(6).getName()));
               }

              if (size > 3) {

                  System.out.println("No path less than or equal to a 3-hop distance exists between" +s1  +s2);

              }
          }


      }

    }


}
