/*POSSIBLE ADDITIONS
1. Pag meron na nung same name ng book, iaadd nalang sa quantity hindi na gagawa ng bagong index sa array
Example: 
Sa array meron nang book na may title "Dexter"
pag nag add ka ng book na "Dexter" din yung title, iadd nalang siya sa quantity nung nasa loob na ng array

Possible Implementation: 
- Kukunin muna user input para sa mga title, author, etc, bago i add sa array, mag loloop muna sa buong array tas checheck kung existing na yung
name, pag meron kukunin yung userinput na quantity tapos i-add nalang sa quantity nung array. Pag wala, gagawa ng bagong index para dun


1. Add books [DONE] MARLO
2. View Item [] MARLO
3. Borrow Item [DONE] Zai
4. Return Item [DONE] Kirk
5. View Borrowed items [DONE] Zai
6. Exit

*/

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Library Library1 = new Library(10);  //initialize kung gano karaming item meron sa library (iba pa to sa quantity ng bawat item)


        Scanner scan = new Scanner(System.in);
        int loop; //para sa for loop
        int choice; //para sa switch case

        do {
            System.out.println("Welcome to Library malupit!");
            System.out.println("MENU");
            System.out.println("1. Add Item");
            System.out.println("2. View Item");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. View Borrowed Items");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            System.out.println();


            switch(choice) {
                case 1:
                do {
                    System.out.println("\n--------Library malupit-------\n");
                    System.out.print("What do you want to add?\n1. Book\n2. DVD\n3. Magazine\nYour choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();

                    System.out.println();
        
                    switch (choice) {
                        //pang add ng book
                        case 1: 
                        System.out.println("\n--------Add Book--------");
                        System.out.print("Enter title of the book: ");
                        String btitle = scan.nextLine();
                        System.out.print("Enter author of the book: ");
                        String bcreator = scan.nextLine();
                        System.out.print("Enter number of pages in the book: ");
                        int pages = scan.nextInt();
                        System.out.print("Enter quantity of books: ");
                        int bquantity = scan.nextInt();
            
                        LibraryItem book1 = new Book(Library.id , btitle, bcreator, bquantity, pages); //gagawa ng object na book
                        Library1.addItem(book1); //iaadd yung object sa array 
                        System.out.println("\n\n-------Updated List--------");
                        Library1.displayInventory();
                        break;
        
        
                        case 2: 
                        //pag add ng dvd
                        System.out.println("\n--------Add DVD--------");
                        System.out.print("Enter the title of the DVD: ");
                        String dtitle = scan.nextLine();
                        System.out.print("Enter the director of the DVD: ");
                        String dcreator = scan.nextLine();
                        System.out.print("Enter the duration of the DVD (Hours): ");
                        int duration = scan.nextInt();
                        System.out.print("Enter the quantity of DVDs: ");
                        int dquantity = scan.nextInt();
            
                        LibraryItem dvd1 = new DVD(Library.id , dtitle, dcreator, dquantity, duration); //gagawa ng object na dvd
                        Library1.addItem(dvd1); // iaadd sa array yung ginawang dvd
                        System.out.println("\n\n-------Updated List--------");
                        Library1.displayInventory();
                        break;
        
                        case 3:
                        //pag add ng magazine
                        System.out.println("\n--------Add Magazine--------");
                        System.out.print("Enter the title of the magazine: ");
                        String mtitle = scan.nextLine();
                        System.out.print("Enter the publisher of the magazine: ");
                        String mcreator = scan.nextLine();
                        System.out.print("Enter the issue number of the magazine: ");
                        int issueNumber = scan.nextInt();
                        System.out.print("Enter quantity of the magazines: ");
                        int mquantity = scan.nextInt();
                        LibraryItem mag1 = new Magazine(Library.id , mtitle, mcreator, mquantity, issueNumber); //gagawa ng object na magazine
                        Library1.addItem(mag1); //iaadd sa array yung ginawang magazine
                        System.out.println("\n\n-------Updated List--------");
                        Library1.displayInventory();
                        
                        break;
                    }
                    System.out.println("Do you want to add again? [1] - Yes / [2] - No");
                    loop = scan.nextInt();
        
        
                    }
                    while(loop != 2);

                break;

                case 2:
                //view item

                

                break;

                case 3:
                //borrow item
                do {
                    Library1.displayInventory(); //display muna mga items sa library

                    System.out.print("Enter Customer Name: ");
                    String customerN = scan.nextLine(); //kunin si Customer name
                    
                    System.out.print("Enter the Name of the item you want to borrow: ");
                    String borrow = scan.nextLine(); //kunin si item na gusto i borrow ni Customerrr
                    Library1.Borrowitems(customerN, borrow); // tawag si Library class Borrowitem method pra mailagay si Customer name at hiniram

                    System.out.println("Do you want to borrow again? [1] - Yes / [2] - No");
                    loop = scan.nextInt();

                } while(loop != 2);


                break;

                case 4:
                //return item
                Library1.returnBook();

                break;

                case 5:
                //view borrowed items
                Library1.DisplayBorrowedItems();

                break;

                case 6:
                //exit
                break;

            } 
        
    } while(choice != 6);

    }

}



   
