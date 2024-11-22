import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//GOALS
//LOGIN ACC
//REGISTER ACC
//FILE TO LIST = POPULATE / LOAD
//LIST TO FILE = WRITE / SAVE

public class accounts{ //THIS IS OVERALL FOR ACCOUNTS
    private String name;
    private String password;
    private String username;
    private String accType;
 // private LibraryItem borrowedItem; wait d ko pa gets pano implement
//  private int ItemsBorrowed; wait d ko pa gets pano implement
    private ArrayList <accounts> accountsList = new ArrayList<>(); //THE ONLY USE FOR THIS IS FOR ITERATING/LOOPING THE TEXT FILE TO FIND THE CURRENT USER DATA AND SUCH

    Scanner scan = new Scanner(System.in);

    public accounts(String username, String password, String accType, String name) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.accType = accType;
    //  this.borrowedItem = borrowedItem;
        
    }
    public accounts(){
        this.username = "";
        //or acc count static
    }

    public void fileTolistAcc(){ //FOR POPULATING THE ARRAYLIST AT THE START OR WHENEVER NECESSARY
        try{
            BufferedReader reader = new BufferedReader(new FileReader("accJab.txt"));
            System.out.println("I AM READING YOUR TEXT. PREPARING TO POPULATE");

            String line;
            while ((line = reader.readLine()) != null){ //READS EACH TO STORE IT IN VARIABLE LINE THEN DO THE BLOCK OF CODE THEN NEXT LINE TO STORE IN VARIABLE LINE
                String[] parts = line.split(","); //SPLIT IS SEPARATE THE LINE INTO PIECES AND THESE PIECES ARE STORED IN PARTS ARRAY (FOR EACH INDEX IS EACH PIECE)
                this.username = parts[0].trim();
                this.password = parts[1].trim();
                this.accType = parts[2].trim();
                this.name = parts[3]; //NO TRIM SINCE WE WANT THE FULL NAME TO NOT GET TRIMMED // KASE TRIMMED IS REMOVING SPACES ORRR IDK? EXPERIMENT PA
                
                accounts newAcc = new accounts(parts[0].trim(), parts[1].trim(), parts[2].trim(),parts[3].trim());
                accountsList.add(newAcc);
            }
            }
            catch(IOException e){
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
    }//end bracket ng fileTOlistAcc

    public void listTofileAcc(){ //THIS IS FOR WRITING THE ARRAYLIST TO TEXT FILE //ONLY CALL WHEN NECESSARY, MIGHT REWRITE SOME SHTS
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("accJab.txt"));

            for (accounts accounts : accountsList){ //ETO UNG LOOP
                System.out.println("IM WRITING YOU RN TO THE FILE: "); //THIS IS JUST A VISUAL NA GUMAGANA TO
                System.out.println("USER: " + accounts.username); //THIS IS JUST A VISUAL NA GUMAGANA TO
                //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password
                writer.write(accounts.username + ", " + accounts.password + ", " + accounts.accType + ", " + accounts.name + "\n");
                //THE FORMAT WOULD LOOK LIKE
                //luthredean, luthpass, admin, Luther Dean
             }
             writer.close();
        }
        catch(IOException e){
            System.err.println("An error occurred while writing the file: " + e.getMessage());
        }
    } //end bracket ng listTOfileAcc


    public void createAcc(){ //JUST A MENU AND INPUT PURPOSE FOR THE OBJECT
        System.out.println("ENTER USERNAME: ");
        //a method to check if that user already exists
        this.username = scan.nextLine(); //PWEDENG setUsername(scan.nextLine());
        System.out.println("ENTER YOUR FULL NAME: ");
        this.name = scan.nextLine();
        System.out.println("ENTER PASSWORD: ");
        this.password = scan.nextLine();
        System.out.println("WHAT ACCESS [2]student or [1]admin: "); //put some sht kase pag 3 input student padin.
        this.accType = (scan.nextInt() == 1) ? "admin" : "student";
    } //end bracket ng createAcc

    public boolean loginAcc(String username, String password){ //
        outer:                      //or Object nasa parameter?
        for (accounts accounts : accountsList){ //ETO UNG LOOP
           //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password

            if (accounts.username.equals(username)){ //checks if the current accounts.username is equal sa ininput ng user for login
                System.out.println("USER FOUND");
                if (accounts.password.equals(password)){ //checks if password is correct
                    System.out.println("OMG YOU LOGGED IN.");
                    if (accounts.accType.equals("admin")) return true; //RETURNS BOOLEAN FOR MENU PURPOSES
                    else if (accounts.accType.equals("student")) return false;
                    else System.out.println("THERE ARE SOME ERROR IN YOUR ACCOUNT");
                    break;
                }
                else System.out.println("WRONG PO YAN PASSWORD BOBO");
                break outer;
            }
            else {
                System.out.println("LOADING..");
            }
        }
        return false;
    } //end bracket ng loginAcc

    

    public void addAccToList(accounts e){ //CALL THIS FOR ADDING THE ACCOUNT IN THE LIST
        accountsList.add(e);
        
        //ETONG LOOP NA TO IS FOR TESTING LANG IF NASA ARRAYLIST NABA TALAGA UNG INADD NA OBJECT
        for (accounts accounts : accountsList) {
            System.out.println("==========FOR TESTTTINGGGGG=========UP LOOB NG ADD ACC TO LIST METHOD");
            System.out.println("USERNAME: " + accounts.username);
            System.out.println("USERPASS: " + accounts.password);
            System.out.println("FULL NAME: " + accounts.name);
            System.out.println("ACCESS TYPE: " + accounts.accType);
            System.out.println("==========FOR TESTTTINGGGGG=========DOWN LOOB NG ADD ACC TO LIST METHOD");
        }
    } //END BRACKET NG addAccTOlist

    public void viewAccArrayList(){ //VIEWING ALL OBJECTS SA ARRAYLIST
        for (accounts accounts : accountsList) {
            System.out.println("==========FOR TESTTTINGGGGG=========UP LOOB NG VIEW ACC ARRAYLIST");
            System.out.println("USERNAME: " + accounts.username);
            System.out.println("USERPASS: " + accounts.password);
            System.out.println("FULL NAME: " + accounts.name);
            System.out.println("ACCESS TYPE: " + accounts.accType);
            System.out.println("==========FOR TESTTTINGGGGG=========DOWN LOOB NG VIEW ACC ARRAYLIST");
        }
    }


    public String getAccounttype() {
        return accType;
    }

    public String getpassword(){
        return password;
    }

    public String getUserID() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() { //FOR SOLO DISPLAY
        System.out.println("SOLO DISPLAY (current object only//the logged in)");
        System.out.println("Name: " + getName());
        System.out.println("Password: " + getpassword());
        System.out.println("Account Type: " + getAccounttype());
        System.out.println();
    }//end bracket ng displayInfo

    //more methods here IDK PA
    //
}//END BRACKET NG CLASS
