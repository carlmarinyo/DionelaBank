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
//FILE TO LIST
//LIST TO FILE



public class accounts{ //THIS IS OVERALL FOR ACCOUNTS
    private String name;
    private String password;
    private String username;
    private String accType;
 //   private LibraryItem borrowedItem; wait d ko pa gets pano implement
// private int ItemsBorrowed; wait d ko pa gets pano implement
    private ArrayList <accounts> accountsList = new ArrayList<>(); //THE ONLY USE FOR THIS IS FOR ITERATING/LOOPING THE TEXT FILE TO FIND THE CURRENT USER DATA AND SUCH

    Scanner scan = new Scanner(System.in);

    //accountsList.

    //utilization ng constructor overloading
    //pra sa students
    public accounts(String username, String password, String name, String accType) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.accType = accType;
    //    this.borrowedItem = borrowedItem;
        
    }
    public accounts(){
        this.username = "";
        //or acc count static
    }

    public void createAcc(){
        System.out.println("ENTER USERNAME: ");
        //a method to check if that user already exists
        this.username = scan.nextLine(); //PWEDENG setUsername(scan.nextLine());
        System.out.println("ENTER YOUR FULL NAME: ");
        this.name = scan.nextLine();
        System.out.println("ENTER PASSWORD: ");
        this.password = scan.nextLine();
        System.out.println("WHAT ACCESS [2]student or [1]admin: ");
        this.accType = (scan.nextInt() == 1) ? "admin" : "student";
    }

    public boolean loginAcc(String username, String password){ //or Object nasa parameter
        outer:
        for (accounts accounts : accountsList){ //ETO UNG LOOP
           //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password

            if (accounts.username.equals(username)){
                System.out.println("USER FOUND");
                if (accounts.password.equals(password)){
                    System.out.println("OMG YOU LOGGED IN.");
                    if (accounts.accType.equals("admin")) return true; //RETURN
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
    }

    public void fileTOlistAcc(){
        try{
            //userCount = 0;
            BufferedReader reader = new BufferedReader(new FileReader("accJab.txt"));
            
            System.out.println("I AM READING YOUR TEXT.");

            String line;
            while ((line = reader.readLine()) != null){
                //userCount++; //for each acc
                String[] parts = line.split(",");
                this.username = parts[0].trim();
                this.password = parts[1].trim();
                this.accType = parts[2].trim();
                this.name = parts[3]; //creates a new string containing the part0 and part1 (trimmed version) like Array [] arr = {1, 2}
                
                accounts newAcc = new accounts(parts[0].trim(), parts[1].trim(), parts[2].trim(),parts[3]);
                accountsList.add(newAcc);
            }
            //pag i access acc need ng loop
            }
            catch(IOException e){
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
    }

    public void listTOfileAcc(String username, String pass, String accType, String name){ //ONLY CALL WHEN NECESSARY, MIGHT REWRITE SOME SHTS
       
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("accJab.txt"));
            fileTOlistAcc(); //para d marewrite // nag lagay ng data sa arraylist if empty tong arraylist
            //need dapat ung data sa parameter is malagay din sa array list 
            accounts newAcc = new accounts(username, pass, accType, name);
            accountsList.add(newAcc);

            for (accounts accounts : accountsList){ //ETO UNG LOOP
                //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password
               // writer.write(accounts.username + ", " + accounts.password + ", " + accounts.accType + ", " + accounts.name + "\n");
                writer.write(accounts.username + ", " + accounts.password + ", " + accounts.accType + ", " + accounts.name + "\n");
             }
             writer.close();
        }
        catch(IOException e){
            System.err.println("An error occurred while writing the file: " + e.getMessage());
        }

    }

    public void addAccToList(accounts e){ //CALL THIS FOR ADDING THE ACCOUNT IN THE LIST
        accountsList.add(e);

        for (accounts accounts : accountsList) {
            System.out.println("==========FOR TESTTTINGGGGG=========UP LOOB NG ADD ACC TO LIST METHOD");
            System.out.println("USERNAME: " + accounts.username);
            System.out.println("USERPASS: " + accounts.password);
            System.out.println("FULL NAME: " + accounts.name);
            System.out.println("ACCESS TYPE: " + accounts.accType);
            System.out.println("==========FOR TESTTTINGGGGG=========DOWN LOOB NG ADD ACC TO LIST METHOD");
        }
    }

    public void viewAccArrayList(){
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

 
    // public void DisplayBorroweditems() { //para sa Libray class, kunin si Customer name at borrowed item
    //     System.out.println("Customer Name: " + name);
    //     borrowedItem.displayInfo();
    //     System.out.println();
    //     System.out.println("----Quantity of Borrowed items: " + getBorrowedItems() + "----For Customer: " + name);

    
    //     System.out.println();
    // }

    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Password: " + getpassword());
        System.out.println("Account Type: " + getAccounttype());
        System.out.println();
    }

}
