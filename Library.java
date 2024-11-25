import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;



public class Library {
    private int itemId;
    private String title;   //mga general attributes
    private String creator;
    private int quantity;

    Scanner input = new Scanner(System.in);
    accounts account;
    
    private ArrayList<BorrowedItems> borrowInfoListCopy = new ArrayList<>(); //arraylist para sa borrowed items
    private ArrayList<accounts> accListCopy; //DECLARE AN ARRAYLIST WITHOUT POINTIN IT TO SOMETH YET
    private ArrayList<Library> availableItems = new ArrayList<>(); //arrayList para sa items


    private int accounts = 0; // pang loop
    private int adminCount = 0; // pang limit ng size ng array tsaka pang loop
    private int studentCount = 0; // pang limit ng size ng array tsaka pang loop
    public static int UserID = 100; // automatic magbibigay ng id pra sa User --tatanggalin to

    private int Custcount = 0; // pang loop

    public Library(int itemId, String title, String creator, int quantity) {
        this.itemId = itemId;
        this.title = title;
        this.creator = creator;
        this.quantity = quantity;
    }

    public Library(accounts e) { // mag iinitialize muna ng size ng library (kung ilang items sa laman ng
        this.account = e;     //the object passed is used here as reference, so the account object here now also points to the account in the main (MAY 2 ACCOUNTS CREATED KASE WHICH IS SA MAIN CLASS AND LIBRARY CLASS)       
        this.accListCopy = account.getAccDataList(); //for populating this arraylist in this class
      
        fileTOlist();
        //studentArray = new accounts[200]; // initialize ng arraylist, pra dto istore yunng nagborrow na customer
    }

    public Library(){
        System.out.println("Normal Constructor");
    }

    // METHODS

    public ArrayList<Library> getItemDataList() { //THIS JUST GIVES YOU A COPY BUT WILL NOT BE ABLE TO CHANGE THE ACTUAL DATA
        //KUMBAGA PASS BY VALUE LANG,,, NOT PASS BY REFERENCE
        // Return an unmodifiable view to protect the original list
        
        return new ArrayList<>(availableItems);
    }

    private int generateItemId() { // pang generate ng item id
        return ++itemId;
    }

   public void removeItem(){
        Scanner input = new Scanner(System.in);

        System.out.println("--------Remove Item--------");
        System.out.println("Items available: ");
        displayInventory();
        System.out.print("Enter the ID of the item to be removed: ");
        int removeId = input.nextInt();
        input.nextLine();

        
        boolean itemFound = false;
    
        // while(more){
            for(int i = 0; i < availableItems.size(); i++){
                if(availableItems.get(i).getItemId() == removeId){ //hahanapin kung match yung ID na ininput ng user sa arraylist
                    itemFound = true;
    
                    availableItems.remove(i);
                    saveItemsToFile();
                    System.out.println("Item " + removeId + " has been removed");
                    System.out.println("New list of items: ");
                    displayInventory();
                    break;
                } 
            }
    
            if(!itemFound){
                System.out.println("Item " + removeId + " not found");
            }
    
          
            displayInventory();
          //  saveItemsToFile();
    

    }


    // add items
    public void addItem() { // mag aadd ng either book,dvd,magazine sa array
        System.out.println("--------Add Items--------");
        System.out.print("What do you want to add?\n1. Book\n2. DVD\n3. Magazine\nYour choice: ");
        int choice = input.nextInt();
        input.nextLine();

        System.out.print("\nEnter the title: ");
        String title = input.nextLine();
        System.out.print("Enter the creator: ");
        String creator = input.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        Library newItem = null;
        int itemId = generateItemId();
        switch (choice) {

            case 1: // pang add ng book
                System.out.print("Enter number of pages in the book: ");
                int pages = input.nextInt();
                input.nextLine();
                newItem = new Book(itemId, title, creator, quantity, pages);// gagawa ng object na book

                break;

            case 2: // pag add ng dvd
                System.out.print("Enter the duration of the DVD (Hours): ");
                int duration = input.nextInt();
                input.nextLine();
                newItem = new DVD(itemId, title, creator, quantity, duration);
                break;

            case 3:
                // pag add ng magazine
                System.out.print("Enter the issue number of the magazine: ");
                int issueNumber = input.nextInt();
                input.nextLine();
                newItem = new Magazine(itemId, title, creator, quantity, issueNumber);
                break;
        }

        if (newItem != null) {
            boolean ExistAlready = false;
       
            for (Library item: availableItems) {                                      
                /*
                 checheck muna yung availableItems na arraylist kung may existing naba na name,author,  at class(book,dvd,magazine), before mag add
                 pag same name, author, same class: idadagdag nalang sa quantity ng nasa list yung ininput mo na quantity
                 pag wala: gagawa ng panibagong item

                 BALI PWEDE YUNG SAME NAME AT SAME CLASS as long as hindi sila same author
                 pwede rin same name at same author basta hindi sila same class
                 */

                 
                if (item.getTitle().equals(newItem.getTitle()) && item.getCreator().equals(newItem.getCreator()) && item.getClass().equals(newItem.getClass())) { 
                    item.increaseQuantity(quantity);
                    ExistAlready = true;
                    break;
                }
               
            }
            if (ExistAlready == false) {
                availableItems.add(newItem);
            }          
            saveItemsToFile(); //FOR SAVING THE CURRENT CHANGES
        }

    }
    
    public void saveItemsToFile() { //test
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libraryItems.txt"))) {
            for (Library item : availableItems) {
                String itemType = item.getClass().getSimpleName().toLowerCase();
                if (item instanceof Book) {
                    Book book = (Book) item;
                    writer.write(book.getItemId() + ", " + book.getTitle() + ", " + book.getCreator() + ", " + book.getQuantity() + ", " + itemType + ", " + book.getPages() + "\n");
                       
                }

                else if (item instanceof Magazine) {
                    Magazine magazine = (Magazine) item;
                    writer.write(magazine.getItemId() + ", " + magazine.getTitle() + ", " + magazine.getCreator() + ", " + magazine.getQuantity() + ", " + itemType + ", " + magazine.getIssueNumber() + "\n");
                       
                }

               else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                    
                    writer.write(dvd.getItemId() + ", " + dvd.getTitle() + ", " + dvd.getCreator() + ", " + dvd.getQuantity() + ", " + itemType + ", " + dvd.getHours() + "\n");
                       
                }
              
            }
         
            writer.close();
            System.out.println("Successfully wrote items to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving items to the file.");
            e.printStackTrace();
        }
    }

    public void fileTOlist() { //test
        try {
            BufferedReader reader = new BufferedReader(new FileReader("libraryItems.txt"));
          

            String line;
            while((line = reader.readLine()) != null){
                String [] parts = line.split(",");
                this.itemId = Integer.parseInt(parts[0].trim());
                this.title = parts[1].trim();
                this.creator = parts[2].trim();
                this.quantity = Integer.parseInt(parts[3].trim());

              String itemType = parts[4].trim();
              Library newItem = null;

              switch (itemType.toLowerCase()) {
                case "book":
                    int pages = Integer.parseInt(parts[5].trim());
                    newItem = new Book(itemId, title, creator, quantity, pages);
                    break;

                    case "dvd":
                    int hours = Integer.parseInt(parts[5].trim());
                    newItem = new DVD(itemId, title, creator, quantity, hours);
                    break;

                    case "magazine":
                    int issueNumber = Integer.parseInt(parts[5].trim());
                    newItem = new Magazine(itemId, title, creator, quantity, issueNumber);
                    break;
              
                default:
                    break;
                   
              }
              if (newItem != null) {
                availableItems.add(newItem);
            }
            }
            System.out.println("Successfully loaded the text file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading items to the file." + e.getMessage());
           // e.printStackTrace();
        }
    }






    public void displayInventory() { // display inventory
        System.out.println("\t---------------Available Items----------------");
        
        // Adjust the format to add "Creator" column
        System.out.printf("%-10s %-30s %-20s %-10s%n", "Item ID", "Title", "Creator", "Quantity"); // Added Creator column
        System.out.println("----------------------------------------------------");
    
        if (availableItems.size() != 0) { // checks if the availableItems list is not empty
            for (Library item : availableItems) {
                // Display each item's details, including the creator
                System.out.printf("%-10d %-30s %-20s %-10d%n", item.getItemId(), item.getTitle(), item.getCreator(), item.getQuantity());
            }
        } else {
            System.out.println("No items in inventory");
        }
    }



    public int getItemId() {
        return itemId;
      }
      
      public String getTitle() {
          return this.title;
      }
      
      public String getCreator() {
        return creator;
      }
      
      public int getQuantity() {
        return quantity;
      }
      
      public void increaseQuantity(int returned) {
      this.quantity += returned; // pagupdate directaa sa quantity
      }
      
      public void setQuantity(int quantity) { // pra sa Quantityyy 
        this.quantity = quantity;
      
      }
      
      
    //   public String toFileFormat() {
    //     return this.getClass().getSimpleName() + "," + this.itemId + "," + this.title + "," + this.creator + "," + this.quantity;
    //   }
      
      public void displayInfo() {  //iooverride ng mga children
        System.out.println("Item ID: " + getItemId());
        System.out.println("Title: " + getTitle());
        System.out.println("Creator: " + getCreator());
        System.out.println("Quantity: " + getQuantity());
        
      }

      BorrowedItems borrowItemInfo = null; //pra di magnull pointer exception

public void borrowItem(){
    int loop;
    Scanner scan = new Scanner(System.in);
    BorrowedItems borrowItemInfo = null; //pra di magnull pointer exception

    do {
        System.out.println("--------Borrow Item--------");                                
        System.out.println("NUMBER OF TITLE: " + availableItems.size());
        if (availableItems.size() == 0) {
           System.out.println("Inventory is currently empty...");
         break;//checheck niya kung may laman naba, pag wala mag iistop di na tutuloy sa baba
                             }
        else { 
             System.out.println("LIST OF BOOKS: ");
            for (Library items : availableItems) {
                 items.displayInfo(); //display muna mga items sa library
             }                 
         }  
           System.out.print("Enter Customer Name: "); //HAVE TO CHECK FIRST IF CUSTOMER EXISTS WALA PA
           String customerN = scan.nextLine(); //kunin si Customer name
                        
           System.out.print("Enter the ItemID of the item you want to borrow: ");
           int itemId = scan.nextInt(); //kunin si item na gusto i borrow ni Customerrr
           
         System.out.print("Enter the quantity of the item you want to borrow: ");
           int quantity = scan.nextInt(); //kunin si quantity ng item na gusto iborrow ni Costomer
              scan.nextLine();


                        // SAVING THE DATA TO THE ARRAYLIST OF BORROWED ITEMS
                    borrowItemInfo = new BorrowedItems(customerN, itemId, quantity); // tawag si borroweditem class Borrowitem, pra mailagay si Customer name at hiniram, nag popointer null exception pagnawala tu
                    borrowItemInfo.Borrowitems(customerN, itemId, quantity);  //method sa borroweditems, di ko mafigure out sa file handling ksi eh
                    borrowInfoListCopy.add(borrowItemInfo); //add si borrowItemInfo sa arraylist ng borrowed items na borrowinfolistcopy

          for (Library items : availableItems) {
            if (items.getItemId() == itemId){ //PUT A CONDITION NA KAPAG MERON PANG QUANTITY OR WALA
                System.out.println("CURRENT ITEM QUANTITY: " + items.getQuantity());
                items.quantity = (items.quantity - quantity);

                borrowItemInfo = new BorrowedItems(customerN, itemId, quantity);
                borrowItemInfo.Borrowitems(customerN, itemId, quantity);
                System.out.println("ITEM MINUS QUANTITY SUCCESS LOOK: " + items.getQuantity());
                //after minus in quantity we want to save changed to this. so how
                saveItemsToFile();
                System.out.println("=====SUCCESS UPDATED QUANTITY ITEM======");
                break; 
            }
        }  

          System.out.print("Do you want to borrow again? [1] - Yes / [2] - No: ");
          loop = scan.nextInt();
          scan.nextLine();
    
                } 
       while(loop != 2);
}
      
    
    //   // test
      public void DisplayBorrowedItems(String name) {    
      
          if(borrowInfoListCopy.size() == 0){
              System.out.println("No borrowers yet.");
          }
          else{
              for(BorrowedItems borrowInfo : borrowInfoListCopy){
                  if(borrowInfo.getUsername().equals(name)){
                      System.out.println("Username: " + borrowInfo.getUsername());
                      System.out.println("Item ID: " + borrowInfo.getItemId());
                      System.out.println("Quantity: " + borrowInfo.getQuantity());
                  }
              }
          }
      
      }





 

 //RETURNING METHODSSS
   public void returnItem(){
        System.out.println("--------Return Items--------");
        boolean more = true;

        while(more){
            System.out.print("Enter the Item ID to be returned: ");
            int itemId = input.nextInt();
            System.out.print("How many do you want to return?: ");
            int quantity = input.nextInt();
            input.nextLine();

            boolean itemFound = false;

            for(BorrowedItems borrowInfo : borrowInfoListCopy){
                if(borrowInfo.getItemId() == itemId){
                    itemFound = true;

                    if(quantity > borrowInfo.getQuantity()){
                        System.out.println("Quantity is greater than borrowed quantity!");
                        System.out.println("You only borrowed: " + borrowInfo.getQuantity());
                    }

                    else{ 
                        borrowInfo.setQuantity(borrowInfo.getQuantity() - quantity);
                        for (Library item : availableItems){
                            if(item.getItemId() == itemId){
                                item.quantity = (item.quantity + quantity);
                            System.out.println("Item with ID " + itemId + " has been returned");
                            System.out.println("New quantity: " + item.getQuantity()); //pang check lang

                            saveItemsToFile();
                            System.out.println("BOOM FUCKING SUCCESS");
                            break;
                            }
                        }
                    }
                }
            }

            if(!itemFound){
                System.out.println("Item with ID " + itemId + " not found!");
            }

            System.out.print("Do you still want to return more? (y/n): ");
            char response = input.nextLine().charAt(0);

            if(response == 'y' || response == 'Y'){
                more = true;
            }

            else{
                more = false;
            }
        }
    }

    
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}





