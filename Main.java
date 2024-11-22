/*POSSIBLE ADDITIONS
1. Pag meron na nung same name ng book, iaadd nalang sa quantity hindi na gagawa ng bagong index sa array
Example: 
Sa array meron nang book na may title "Dexter"
pag nag add ka ng book na "Dexter" din yung title, iadd nalang siya sa quantity nung nasa loob na ng array

Possible Implementation: 
- Kukunin muna user input para sa mga title, author, etc, bago i add sa array, mag loloop muna sa buong array tas checheck kung existing na yung
name, pag meron kukunin yung userinput na quantity tapos i-add nalang sa quantity nung array. Pag wala, gagawa ng bagong index para dun


*/



/*
 


-KIRK, ZAI CINOMMENT OUT KO MUNA YUNG MGA GINAWA NINYO KASI SA INADJUST KO, HINDI NA GUMAGANA YUNG MGA FUNCTION NIYO

- AADJUST NI LUTHER YUNG LAHAT NG MAY KINALAMAN SA ACCOUNT 





 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Library Library1 = new Library();  //initialize kung gano karaming item meron sa library (iba pa to sa quantity ng bawat item)
        int choice=0; //para sa switch case
        int choice2 = 0;//para sa looping ng student borrow item 
        int choice3 = 0; //para sa looping ng menu ng student
        Scanner scan = new Scanner(System.in);
        int loop; //para sa for loop
        
        int cchoice;
        String username[] = new String[200];                                
        String password[] = new String[200]; //5 users lang                           -- matatangal pag naayos
        String acctype[] = new String[200]; // either Admin or Student


        int increment = 0; //para sa indexing ng mga username,password,acctype --matatangal din to pag inayos na
        do { 
            //first loop either login or registration
        System.out.println("\nWelcome to Dionela Library!");
        System.out.print("1. Login\n2. Create an account\n3. Exit\nYour choice: ");
        cchoice = scan.nextInt();
        scan.nextLine();
        switch(cchoice) {
            case 1: //login
        
            if (increment == 0) { //checheck kung may laman naba na username/pass
                System.out.println("There is no account yet.");
                break;
            }
           boolean accfound = false;
            System.out.println("Login:");
            System.out.print("Username: ");
            String tempuser = scan.nextLine();
            System.out.print("Password: ");
            String temppassword = scan.nextLine();
            
        
                for (int i = 0; i < increment; i++) { //loloop depende kung ilan na yung account created
         

                    if (tempuser.equals(username[i]) && temppassword.equals(password[i])) { //checheck kung yung tinype mo na user/pass ay meron sa array
                        accfound=true; 
                       if (acctype[i] == "Admin") { //if admin ang nag login  
                         
                        
                        System.out.println("\n--------Welcome Admin "+ username[i]+"!--------");
                        do {
                            System.out.print("1. Add Item\n2. Remove Item\n3. Exit.\nYour choice: ");
                            choice = scan.nextInt();
                            scan.nextLine();

                            switch (choice) { // para sa add item
                                case 1:
                              Library1.addItem();;

                
                
                                case 2: 
                               // para sa remove item
                                break;
                              

                            }
                         
                        }
                       while (choice != 3);
                        
                       }


                       else if (acctype[i] == "Student") { //if student ang nag login
                         
                       
                        do {
                            System.out.println("\n--------Welcome Student "+ username[i]+"!--------");
                        System.out.print("1. Borrow Item\n2. Return Item\n3. View Available Items\n4. View Borrowed Items\n5. Exit");
                        choice2 = scan.nextInt();
                        scan.nextLine();
                        switch (choice2) {
                            // case 1: //borrow item
                            // do {
                            //     System.out.println("--------Borrow Item--------");                                
                                                
                            //                     if (Library1.id - 1 <= 0) {
                            //                         System.out.println("Inventory is currently empty...");
                            //                         break;//checheck niya kung may laman naba, pag wala mag iistop di na tutuloy sa baba
                            //                          }
                            //                     else { 
                            //                         Library1.displayInventory(); //display muna mga items sa library
                            //                     }  
                            //                     System.out.print("Enter Customer Name: ");
                            //                     String customerN = scan.nextLine(); //kunin si Customer name
                                                
                            //                     System.out.print("Enter the ItemID of the item you want to borrow: ");
                            //                     int borrow = scan.nextInt(); //kunin si item na gusto i borrow ni Customerrr
                            //                     scan.nextLine();
                            
                            //                     System.out.print("Enter the quantity of the item you want to borrow: ");
                            //                     int quantity = scan.nextInt(); //kunin si quantity ng item na gusto iborrow ni Costomer
                            //                     scan.nextLine();
                            
                            //                     Library1.Borrowitems(customerN, borrow, quantity); // tawag si Library class Borrowitem method pra mailagay si Customer name at hiniram
                            
                            //                     System.out.println("Do you want to borrow again? [1] - Yes / [2] - No");
                            //                     loop = scan.nextInt();
                            //                     scan.nextLine();
                            //                     scan.nextLine();
                            
                            //                 } 
                            //                 while(loop != 2);
                            // break;

                            // case 2: //Return Item
                            // Library1.returnBook();   //kirk babaguhin to, pag nag login yung user rereturn niya lang yung mga binorrow niya 
                            //                           //bali di na mag tatanong ng userid
                            // break;

                            case 3: //View Available Items
                            Library1.displayInventory();
                            break;

                            case 4: //View borrowed
                            //Library1.DisplayBorrowedItems(); //zai babaguhin dito is naka display lang yung binorrow nung user mismo
                                                              //di pwede yung kita pati binorrow nugn iba
                            break;

                            case 5:
                            System.out.println("Student is now exiting.....");

                         
                            default:
                            System.out.println("Choose from 1-5 only.");

                        } // end ng student menu switch
                         }
                         while (choice2 != 5);
                       } // end ng student else if

                    }
                   
                }
                if (accfound == false) {
                    System.out.println("Account not found.");
                   }
            
            
            break;

            case 2: 
           /*
            Possible implementation sa file handling:
            Pag write(lalagay sa text file): yung username, password, account type na may same increment or index ay magkakasama sa isang row
            Example: username123, password123, Admin
            tapos loloop hanggang maubos yung array ng username[], password[], acctype[]

            Pag read(kukunin mula sa text file):
            Kada row, ififillup lang din ulit yung array ng username[], password[], acctype[]
            Example
            From text file: username123, password123, Admin

            Pag nasa array na ulit:
            username[0] = "username123"
            password[0] = "password123"
            acctype[0] = "Admin"

            */
            System.out.println("\n--------Account creation--------");
            System.out.print("Enter your username: ");
            String registrationName = scan.nextLine();

            for (int i = 0; i <= increment; i++) {
            
                if (registrationName.equals(username[i])) { //checheck kung may same ba na username
                    System.out.println("Username is taken already.");
                    break;
                 
                }
                else {
                    username[increment] = registrationName;
                    System.out.print("Enter your password: ");
            password[increment] = scan.nextLine();
            System.out.print("Account Type? [1- Admin] [2- Student]: ");
            int tchoice = scan.nextInt();
            scan.nextLine();


            if (tchoice == 1) {
                acctype[increment] = "Admin";
                if (Library1.createAdmins(username[increment], password[increment])) {
                Library1.DisplayingAdmins();
                System.out.println("Account for admin "+username[increment]+" is added.");
                increment++;                
            
            }

                else {
                    break;
                }

            }

            else if (tchoice == 2) {
                acctype[increment] = "Student";
                if(Library1.createStudents(username[increment], password[increment])) {
                Library1.DisplayingStudents();
                System.out.println("Account for student "+username[increment]+" is added.");
                increment++;
                }
                else {
                    break;
                }
            }

            else {
                System.out.println("Choose only from [1- Admin] [2- Student]");
            }
            break;
                }
            }

           
            
            
            

        }
    }
    while (cchoice != 3);
    System.out.println("\nThank you for using the program!, exiting now....");
    

    }

}

/*
 20/11/2024
 -Added registration feature (Takes username, password, acctype)
 -Also checks if username already exists which ensures that there is no same usernames

-Added login feature (Asks for username and password, then checks if the acc type is admin/student)

- Menu displayed if admin is logged in (Add Item, Remove Item)

- Menu displayed if student is logged in (Borrow Item, Rent Item, View all available, View all borrowed)

HINIWALAY KO NA YUNG MENU
 */

   