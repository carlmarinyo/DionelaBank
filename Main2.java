import java.util.Scanner;

public class Main{
    public static void main(String [] args){
        String username;
        String pass;
        String accType;
        String name;
        int choice;
        Scanner scan = new Scanner(System.in);
        accounts acc = new accounts(true);
        //BorrowedItems userBi = new BorrowedItems();
        
        Library Library1 = new Library(acc); //you passed the current object reference to the library class

        //acc.fileTolistAcc(); //FOR POPULATING ARRAYLIST BEFORE STARTING PROGRAM
        System.out.println("\nWelcome to Dionela Library!");
        System.out.println("--------MENU---------");
        System.out.print("1. Login\n2. Create an account\n3. Exit\nYour choice: ");
        choice = scan.nextInt();
        scan.nextLine(); //CIN.IGNORE

        switch (choice) {
            case 1: //LOGIN
                //check if there's an account already method //NOT URGENT

                System.out.print("ENTER USERNAME: ");
                username = scan.nextLine();
                System.out.print("ENTER PASS: ");
                pass = scan.nextLine();
                if (acc.loginAcc(username, pass)==true){ //the string is passsed as argument to the parameter of method then returns true or false
                    //method menu for Admin?
                    System.out.println("\n--------Welcome Admin !--------");
                        do {
                            System.out.print("1. Add Item\n2. Remove Item\n3. Exit.\nYour choice: ");
                            choice = scan.nextInt();
                            scan.nextLine();

                            switch (choice) { // para sa add item
                              case 1:
                              Library1.addItem();
                              //Library1.DisplayingAccs();
                              
                              //Library1.displayInfo();
                              //Library1.addItem();
                              break;
                
                
                                case 2: 
                               // para sa remove item
                               Library1.removeItem();
                                break;
                              

                            }//END BRACKET NG SWITCH CASE FOR ADMIN
                        } //end bracket ng DO-WHILE
                        while (choice != 3);
                } //END BRACKET NG IF
                else {
                    //method menu for student
                    do {
                        System.out.println("\n--------Welcome Student !--------");
                    System.out.print("1. Borrow Item\n2. Return Item\n3. View Available Items\n4. View Borrowed Items\n5. Exit\nYour input: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    switch (choice) {
                        case 1: //borrow item
                        Library1.borrowItem();
        
                        break;

                        // case 2: //Return Item
                        // Library1.returnBook();   //kirk babaguhin to, pag nag login yung user rereturn niya lang yung mga binorrow niya 
                        //                           //bali di na mag tatanong ng userid
                        // break;

                        case 3: //View Available Items
                        Library1.displayInventory();
                        break;

                        case 4: //View borrowed
                        Library1.DisplayBorrowedItems(username); //zai babaguhin dito is naka display lang yung binorrow nung user mismo
                                                          //di pwede yung kita pati binorrow nugn iba
                        break;

                        case 5:
                        System.out.println("Student is now exiting.....");
                        break;
                     
                        default:
                        System.out.println("Choose from 1-5 only.");

                    } // end ng student menu switch
                     }
                     while (choice != 5); 
                }//END BRACKET NG ELSE

                break;
            case 2: //REGISTER
                acc.createAcc(); //menu for creating acc.. THIS IS ONLY INPUTS
                acc.addAccToList(acc); //the object is now passed here (THE OBJECT NOW CONTAINS INFO THANKS TO createAcc METHOD)
                acc.listTofileAcc(); //this now updates the textfile using the current arrayList
            break;

            case 3: //SOMEWAY TO EXIT //test area muna
                acc.viewAccArrayList(); //test
                break;
            default:
                break;
        } //END BRACKET OF SWITCH
        
       //THIS IS OUTSIDE OF SWITCH CASE JUST TO THE CURRENT OBJECT EXPERIMENT HERE
        // System.out.println("======================MAIN=======");
        // acc.viewAccArrayList();
        // System.out.println("=============================MAIN==========");
        // acc.displayInfo();
        // System.out.println("=============================MAIN==========");
    }
}