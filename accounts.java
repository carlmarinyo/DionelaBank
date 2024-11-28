import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//GOALS
//LOGIN ACC
//REGISTER ACC
//FILE TO LIST = POPULATE / LOAD
//LIST TO FILE = WRITE / SAVE
//METHOD FOR DUPLICATE USERNAMES// BAWAL DUPLICATE

public class accounts{ //THIS IS OVERALL FOR ACCOUNTS
    private String name;
    private String password;
    private String username;
    private String accType;
 // private LibraryItem borrowedItem; wait d ko pa gets pano implement
//  private int ItemsBorrowed; wait d ko pa gets pano implement
    private ArrayList <accounts> accountsList = new ArrayList<>(); //THE ONLY USE FOR THIS IS FOR ITERATING/LOOPING THE TEXT FILE TO FIND THE CURRENT USER DATA AND SUCH

    private static String currentUser;
    Scanner scan = new Scanner(System.in);

    public accounts(String username, String password, String accType, String name) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.accType = accType;
    //  this.borrowedItem = borrowedItem;
        
    }
    public accounts(boolean e){
        this.username = "";
        fileTolistAcc();
    }

    public accounts(){
        this.username = "";
        fileTolistAcc(); 
    }

    public void setCurrentUser(String username){
        currentUser = username;
    }

     

    public void fileTolistAcc(){ //FOR POPULATING THE ARRAYLIST AT THE START OR WHENEVER NECESSARY
        try{
            BufferedReader reader = new BufferedReader(new FileReader("accJab.txt"));
        

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


    public boolean checkUser(String username){
        for (accounts accounts : accountsList){ //ETO UNG LOOP
            //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password
        
             if (accounts.username.equals(username)){ //checks if the current accounts.username is equal sa ininput ng user for login
                 return true;
             }
           
         }
         return false;
    }


    public void createAcc(String username, String pass, String accType, String name){ //JUST A MENU AND INPUT PURPOSE FOR THE OBJECT
        this.username = username; //for initializing for the current USER purpose this is their object kase
        this.password = pass;
        this.accType = accType;
        this.name = name;
 
        accounts newAcc = new accounts(username, pass, accType, name);
        addAccToList(newAcc);
     }




    public int loginAcc(String username, String password){ //
        outer:                      //or Object nasa parameter?
        for (accounts accounts : accountsList){ //ETO UNG LOOP
           //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password

            if (accounts.username.equals(username)){ //checks if the current accounts.username is equal sa ininput ng user for login
               
                if (accounts.password.equals(password)){ //checks if password is correct

                    currentUser = username;
                    
                    if (accounts.accType.equals("admin")) return 1; //RETURNS BOOLEAN FOR MENU PURPOSES
                    else if (accounts.accType.equals("student")) return 2;
                    
                    break;
                }
               
         
            }
         
        }
        return 3;
    } //end bracket ng loginAcc

    

    public void addAccToList(accounts e){ //CALL THIS FOR ADDING THE ACCOUNT IN THE LIST
        accountsList.add(e);
        
        //ETONG LOOP NA TO IS FOR TESTING LANG IF NASA ARRAYLIST NABA TALAGA UNG INADD NA OBJECT
       
    } //END BRACKET NG addAccTOlist




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
        System.out.println("Username: " + getUserID());
        System.out.println("Name: " + getName());
        System.out.println("Password: " + getpassword());
        System.out.println("Account Type: " + getAccounttype());
        System.out.println();
    }//end bracket ng displayInfo

    //more methods here IDK PA
    //checkArraySize(){}
}//END BRACKET NG CLASS
