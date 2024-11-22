import java.util.Scanner;

public class Main{
    public static void main(String [] args){
        String username;
        String pass;
        String accType;
        String name;
        int choice;
        Scanner scan = new Scanner(System.in);
        accounts acc = new accounts();
        
        acc.fileTOlistAcc(); //FOR POPULATING ARRAYLIST BEFORE STARTING PROGRAM
        System.out.println("MENU");
        System.out.println("1. LOGIN");
        System.out.println("2. CREATE ACCOUNT");
        System.out.println("3. EXIT");
        System.out.println("ENTER YOUR CHOICE: ");
        choice = scan.nextInt();
        scan.nextLine(); //CIN.IGNORE

        switch (choice) {
            case 1:
                System.out.print("ENTER USERNAME: ");
                username = scan.nextLine();
                System.out.print("ENTER PASS: ");
                pass = scan.nextLine();
                acc.loginAcc(username, pass); //the string is passsed as argument to the parameter of method

                break;
            case 2:
                acc.createAcc(); //menu for creating acc.. THIS IS ONLY INPUTS
                acc.addAccToList(acc); //the object is now passed here (THE OBJECT NOW CONTAINS INFO THANKS TO createAcc METHOD)
                acc.listTofileAcc(); //this now updates the textfile using the current arrayList
            break;

            case 3: //SOMEWAY TO EXIT
         
                break;
            default:
                break;
        } //END BRACKET OF SWITCH
        
       //THIS IS OUTSIDE OF SWITCH CASE JUST TO SEE THE INFOS OF THE ARRAYLIST
        System.out.println("======================MAIN=======");
        acc.viewAccArrayList();
        System.out.println("=============================MAIN==========");
       
    }
}
