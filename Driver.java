import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.IDN;
import java.util.ArrayList;
import java.util.*;

public class Driver{
    public static void main(String[] args) {

       //Declare and Initialize variables
       String ID="";
       String command;
       boolean done = false;
       String idToDel;
       String fileName;

       //Create ARList that contains info from file
       ArrayList<Patron> pats = new ArrayList<>();

       //Scanner obj to allow inputs
       Scanner scnr = new Scanner(System.in);

       //Main HashMap which will hold data and primary key
       HashMap<String,Patron> patrons = new HashMap<>();


       //Main Menu for selecting commands
       System.out.println();
       System.out.println("Main Menu");
       System.out.println("add (Add Patron)");
       System.out.println("remove (Remove Patron)");
       System.out.println("display (Display Patrons)");
       System.out.println("load (import data from txt file)");
       System.out.println("exit (Exit Application)");

       //Welcome text
       System.out.println("Welcome to the Library Management System\n");

       //Loop to allow multiple commands
       while(done = true) {
          System.out.print("Input command:");

          command = scnr.nextLine();

          //ADD command, adds specified user
          if (command.equals("add")) {

              Patron patron = addP();
              patrons.put(patron.getID(), patron);
              System.out.println(patrons);

                    //REMOVE command, removes selected user
          } else if (command.equals("remove")) {
             //Displays list of patrons for reference
             System.out.println(patrons);
             System.out.println("Input ID to delete");

                  //Limit to input numbers(0-9) and 7 characters
                  idToDel = scnr.nextLine();
                  if(ID.matches("[0-9]{7}")){break;}
                  else{System.out.println("Please Input up to a 7 digit value");}
              patrons.remove(idToDel);

          } else if (command.equals("display")) {
              //Display list of patrons
              System.out.println(patrons);
          } else if (command.equals("exit")) {
             //Exit program and show thank you message
             System.out.println("Thank you for using the Library Management System");
             break;
          }
          //Load command
          else if (command.equals("load")) {
               System.out.println("Input text file location.");
               //Input location of file within computer
               fileName = scnr.nextLine();
               try {
                   pats = loadFile(fileName);
                   for (Patron patron : pats ) {
                       patrons.put(patron.getID(), patron);
                   }
                   System.out.println(patrons);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }


           }
       }
    }
    //ADD Patron method
    public static Patron addP(){
        //Initialize and Declare variables
        int i = 0;
        String ID = "";
        String name;
        String address;
        String overdue;

        //Scanner object
        Scanner scnr = new Scanner(System.in);

        //Vars for loop
        boolean mvOn = false;
        int idRep=0;
        double ovRep=0;
        i++;

        while(mvOn = true){
            //Input for id (limit to 7 digits)
            //Loop to try new id
            System.out.println("Type new 7 digit ID");
            try {
                while(mvOn = true){
                    ID = scnr.nextLine();
                    if(ID.matches("[0-9]{7}")){break;}
                    else{System.out.println("Please Input up to a 7 digit value");}}
                idRep = Integer.parseInt(ID);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            break;
        }
        

        //Name for new patron
        System.out.println("Type name for new patron");
        name = scnr.nextLine();

        //Address for new patron
        System.out.println("Type address of new patron");
        address = scnr.nextLine();
        
        //Overdue amount for new patron
        //limited to less than 250
        while(mvOn = true) {
            try {
                System.out.println("Type amount owed");
                overdue = scnr.nextLine();
                ovRep = Double.parseDouble(overdue);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            
            if(ovRep<=250){break;}else{System.out.println("Please Input a value between 0 and 250");}
        }
        System.out.println("Successfully added new patron: "+name);
        Patron returnN = new Patron(ID,name,address,ovRep);
        return returnN;
    }
    //LoadFile method
    public static ArrayList<Patron> loadFile(String filename) throws FileNotFoundException, IOException {
        //List to load to
        ArrayList<Patron> ret = new ArrayList<>();

        //Set up reading from file
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String in = "";

        //Process file
        while ((in = br.readLine()) != null) {
            String[] fields = in.split("-"); // id, name, address, overdue amount
            ret.add(new Patron(fields[0], fields[1], fields[2], Double.parseDouble(fields[3])));
        }

        //Close input reading
        br.close();

        return ret;
    }



}
