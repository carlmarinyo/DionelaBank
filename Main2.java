import java.util.Scanner;

public class Main2{
    public static void main(String [] args){
        String username;
        String pass;
        String accType;
        String name;
        int choice;
        Scanner scan = new Scanner(System.in);

        accounts acc = new accounts();
        BorrowedItems userBI = new BorrowedItems();
        
        Library Library1 = new Library(); //you passed the current object reference to the library class
        int choice1 = 0; //loop whole program
        int loop;
        do {

       
     
        System.out.println("\n\n--------Dionela Library---------");
        System.out.print("1. Login\n2. Create an account\n3. Exit\nYour choice: ");
        choice = scan.nextInt();
  
        scan.nextLine(); //CIN.IGNORE

        switch (choice) {
            case 1: //LOGIN
                //check if there's an account already method //NOT URGENT
                
                System.out.println("\n--------Login---------");
                System.out.print("Enter username: ");
                username = scan.nextLine();
                System.out.print("Enter password: ");
                pass = scan.nextLine();
                if (acc.loginAcc(username, pass)==1){ //the string is passsed as argument to the parameter of method then returns true or false
                    //method menu for Admin?
                    System.out.println("Successfully logged in....");
                 
                        do {
                            System.out.println("\n--------Admin---------");
                            System.out.print("1. Add Item\n2. Remove Item\n3. Exit.\nYour choice: ");
                            choice1 = scan.nextInt();
                            scan.nextLine();

                            switch (choice1) { // para sa add item
                              case 1:
                              Library1.addItem();
                           
                              break;
                
                
                                case 2: 
                               // para sa remove item
                               Library1.removeItem();
                                break;

                                case 3:
                              System.out.println("Returning to main menu....");
                              break;
                                default:
                                System.out.println("Choose from 1-3 only.");
                                break;
                        

                            }//END BRACKET NG SWITCH CASE FOR ADMIN
                        } //end bracket ng DO-WHILE
                        while (choice1 != 3);
                       
                } //END BRACKET NG IF
                else if (acc.loginAcc(username, pass)==2){
                    System.out.println("Successfully logged in....\n");
                    //method menu for student
                    do {
                      
                        System.out.println("\n--------Student---------");
                    System.out.print("1. Borrow Item\n2. Return Item\n3. View Available Items\n4. View Borrowed Items\n5. Exit\nYour input: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    switch (choice) {
                        case 1: //borrow item
                        do {
                            System.out.println("\n\t\t--------Borrow Item---------");
                            Library1.displayInventory();
                            System.out.print("\nEnter the ItemID of the item you want to borrow: ");
                            int itemId = scan.nextInt(); // kunin si item na gusto i borrow ni Customerrr

                            System.out.print("Enter the quantity of the item you want to borrow: ");
                            int quantity = scan.nextInt(); // kunin si quantity ng item na gusto iborrow ni Costomer
                            scan.nextLine();
                            
                            if (Library1.borrowItem(quantity, itemId) == 1){//the item gets minus
                                userBI.Borrowitems(acc.getUserID(), itemId, quantity); //saves the one who borrows and what item borrowed and how many
                            }
                            else {System.out.println("You tried accessing non-exising item ID or borrowed more quantity than available.");}
            

                            System.out.print("Do you want to borrow again? [1] - Yes / [2] - No: ");
                            loop = scan.nextInt();
                            scan.nextLine();
                        } while (loop != 2);
                        
                        
        
                        break;

                        case 2: //Return Item  cinomment out muna namin kirk
                        int more;
                        do {
                        System.out.println("\n--------Return Items--------");
                        more = 0;
                            System.out.print("\nEnter the Item ID to be returned: ");
                            int itemId = scan.nextInt();
                            System.out.print("How many do you want to return?: ");
                            int quantity = scan.nextInt();
                            scan.nextLine();
                            if (userBI.checkQuanti(username, itemId, quantity) == 1){
                                if (userBI.Returnitems(username, itemId, quantity) == 1){
                                    Library1.returnItem(itemId, quantity);
                                }
                                else {System.out.println("No such item borrowed yet.");
                                return;
                            }
                            }
                            else {
                                System.out.println("Items were not returned successfully.");
                            }
                            
                         System.out.print("Return again? [1 - Yes | 2 - No]: ");
                         more = scan.nextInt();
                        } while (more != 2);
                        //Library1.returnItem();  
                        break;

                        case 3: //View Available Items
                        Library1.displayInventory();
                        break;

                        case 4: //View borrowed
                        userBI.displayBorrowers(username); 
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

                else {
                    System.out.println("Wrong username or password.");
                }

                break;
                case 2: //REGISTER
                System.out.println("\n--------Register---------");
                System.out.print("Enter username: ");
                //a method to check if that user already exists
                username = scan.nextLine(); //PWEDENG setUsername(scan.nextLine());
                if (acc.checkUser(username) == true){
                 System.out.println("Username already exists");
               
                 break;
                }
                else {
                System.out.print("Enter your full name: ");
                name = scan.nextLine();
                System.out.print("Enter password: ");
                String password = scan.nextLine();
                System.out.print("Access Type [1] - Admin | [2] - Student: "); //put some sht kase pag 3 input student padin.
                int choice2 = scan.nextInt();
                //this.accType = (scan.nextInt() == 1) ? "admin" : "student";
                if (choice2 == 1) accType = "admin";
                else if (choice2 == 2) accType = "student";
                else {
                    System.out.println("Choose from 1-2 only.");
                    break;
                }
                acc.createAcc(username, password, accType, name); //menu for creating acc.. THIS IS ONLY INPUTS
                //acc.addAccToList(acc); //the object is now passed here (THE OBJECT NOW CONTAINS INFO THANKS TO createAcc METHOD)
                acc.listTofileAcc(); //this now updates the textfile using the current arrayList
                System.out.println("Account successfuly created for user "+ name);
            }
            break;

            case 3: //SOMEWAY TO EXIT //test area muna
            System.out.println("Thank you for using Dionela Library!");
                break;
            default:
            System.out.println("Choose from 1-3 only.");
                break;
        } //END BRACKET OF SWITCH
        
    }
    while (choice != 3);

    }
}   
