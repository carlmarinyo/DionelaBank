import java.io.BufferedReader;
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
    BorrowedItems borrowItemInfo;
    private ArrayList<BorrowedItems> borrowInfoListCopy;
    private ArrayList<accounts> accListCopy; //DECLARE AN ARRAYLIST WITHOUT POINTIN IT TO SOMETH YET
    private ArrayList<Library> availableItems = new ArrayList<>(); //arrayList para sa items

    private int accounts = 0; // pang loop
    private int adminCount = 0; // pang limit ng size ng array tsaka pang loop
    private int studentCount = 0; // pang limit ng size ng array tsaka pang loop
    public static int UserID = 100; // automatic magbibigay ng id pra sa User --tatanggalin to

    public Library(int itemId, String title, String creator, int quantity) {
        this.itemId = itemId;
        this.title = title;
        this.creator = creator;
        this.quantity = quantity;
    }

    public Library(accounts e) { // mag iinitialize muna ng size ng library (kung ilang items sa laman ng
        this.account = e;     //the object passed is used here as reference, so the account object here now also points to the account in the main (MAY 2 ACCOUNTS CREATED KASE WHICH IS SA MAIN CLASS AND LIBRARY CLASS)       
        this.accListCopy = account.getAccDataList(); //for populating this arraylist in this class
        System.out.println("THIS IS THE LIBRARY SPEAKING");
        fileTOlist();
        //studentArray = new accounts[200]; // initialize ng arraylist, pra dto istore yunng nagborrow na customer
    }

    public Library(){
        System.out.println("NORMAL CONSTRUCTOPR");
    }

    // METHODS

    public ArrayList<Library> getItemDataList() { //THIS JUST GIVES YOU A COPY BUT WILL NOT BE ABLE TO CHANGE THE ACTUAL DATA
        //KUMBAGA PASS BY VALUE LANG,,, NOT PASS BY REFERENCE
        // Return an unmodifiable view to protect the original list
        System.out.println("PASSING A COPY TO YOUR CLASS NOW!");
        return new ArrayList<>(availableItems);
    }

    private int generateItemId() { // pang generate ng item id
        return ++itemId;
    }

    public void removeItem(){
        Scanner input = new Scanner(System.in);

        System.out.println("--------Remove Item--------");
        System.out.print("Enter the ID of the item to be removed: ");
        int removeId = input.nextInt();
        input.nextLine();

        
        boolean itemFound = false;
    
        // while(more){
            for(int i = 0; i < availableItems.size(); i++){
                if(availableItems.get(i).getItemId() == removeId){ //hahanapin kung match yung ID na ininput ng user sa arraylist
                    itemFound = true;
    
                    availableItems.remove(i);
                    System.out.println("Item " + removeId + " has been removed");
                    break;
                } 
            }
    
            if(!itemFound){
                System.out.println("Item " + removeId + " not found");
            }
    
            System.out.println("\n\n-------Updated List--------");
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
        try (FileWriter writer = new FileWriter("libraryItems.txt")) {
            for (Library item : availableItems) {
                System.out.println("IM WRITING YOU RN TO THE FILE: "); //THIS IS JUST A VISUAL NA GUMAGANA TO
                System.out.println("ITEM NAME: " + item.title); 
                writer.write(item.itemId + ", " + item.title + ", " + item.creator + ", " + item.quantity + "\n");
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
            System.out.println("I AM READING YOUR TEXT. PREPARING TO POPULATE ITEMS"); 

            String line;
            while((line = reader.readLine()) != null){
                String [] parts = line.split(",");
                this.itemId = Integer.parseInt(parts[0].trim());
                this.title = parts[1].trim();
                this.creator = parts[2].trim();
                this.quantity = Integer.parseInt(parts[3].trim());

                Library newItem = new Library(this.itemId, parts[1].trim(), parts[2].trim(),this.quantity);
                availableItems.add(newItem);
            }
            System.out.println("Successfully wrote items to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving items to the file.");
           // e.printStackTrace();
        }
    }






    public void displayInventory() { // display inventory
        System.out.println("--------Available Items--------");
        if (availableItems.size() != 0) { // checks if the availableitems is not empty {}
            for (Library item : availableItems) {
                item.getTitle();
                System.out.println();
            }
        }
        else {
            System.out.println("No items in inventory");
        }
    }



    public int getItemId() {
        return itemId;
      }
      
      public String getTitle() {
          return title;
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


public void borrowItem(){
    Scanner scan = new Scanner(System.in);
    do {
        System.out.println("--------Borrow Item--------");                                
                        
        if (availableItems.size() <= 0) {
           System.out.println("Inventory is currently empty...");
         break;//checheck niya kung may laman naba, pag wala mag iistop di na tutuloy sa baba
                             }
        else { 
             System.out.println("LIST OF BOOKS: ");
            for (Library items : availableItems) {
                 items.displayInfo(); //display muna mga items sa library
             }
                            
         }  
           System.out.print("Enter Customer Name: ");
           String customerN = scan.nextLine(); //kunin si Customer name
                        
           System.out.print("Enter the ItemID of the item you want to borrow: ");
           int itemId = scan.nextInt(); //kunin si item na gusto i borrow ni Customerrr
           scan.nextLine();
    
         System.out.print("Enter the quantity of the item you want to borrow: ");
           int quantity = scan.nextInt(); //kunin si quantity ng item na gusto iborrow ni Costomer
          scan.nextLine();
    
                        // SAVING THE DATA TO THE ARRAYLIST OF BORROWED ITEMS
          borrowItemInfo.Borrowitems(customerN, itemId, quantity); // tawag si Library class Borrowitem method pra mailagay si Customer name at hiniram
         
         System.out.println("Do you want to borrow again? [1] - Yes / [2] - No");
          loop = scan.nextInt();
          scan.nextLine();
        scan.nextLine();
    
                } 
       while(loop != 2);
}






    // // Creating Accounts  --zai
    // public Boolean createAdmins(String name, String password) {

    //     if (adminCount == 5) {
    //         System.out.println("Limit has been reached, cannot add anymore Admins.");
    //         return false;
    //     }

    //     else {
    //         AdminArray[adminCount++] = new accounts(name, password, "Admin");
    //         accounts++;
    //         System.out.println("Admin Account has been created.");
    //         System.out.println("Username: " + name);
    //         System.out.println("Password: " + password);
    //         System.out.println("-------------------------------");
    //         return true;

    //     }

    // }

    // public Boolean createStudents(String name, String password) {
    //     if (studentCount == 200) {
    //         System.out.println("Limit has been reached, cannot add anymore Students.");
    //         return false;
    //     } else {
    //         studentArray[studentCount++] = new accounts(UserID++, name, password, null, "Student");
    //         accounts++;
    //         System.out.println("Student Account has been created.");
    //         System.out.println("Username: " + name);
    //         System.out.println("Password: " + password);
    //         System.out.println("-------------------------------");
    //         return true;
    //     }

    // }

    public void DisplayingAccs() {
        System.out.println("--------List of Admins--------\n");
        System.out.println(accListCopy.size());
        // if (accListCopy.size() == 0){
        //         System.out.println("No Accounts have been created yet.");
        //     }
        //     else {
                for (accounts accounts : accListCopy) {
                    System.out.println("TEST1");
                    accounts.displayInfo(); //or without loop u can do account.viewAccArrayList()
                    //account.viewAccArrayList();
                    System.out.println("TEST2");
                }
        //}
    }

    // public void DisplayingStudents() {
    //     System.out.println("--------List of Students--------\n");
    //     if (studentCount == 0) {
    //         System.out.println("No Students have been created yet.");
    //     } else {
    //         for (int i = 0; i < studentCount; i++) {
    //             studentArray[i].displayInfo();
    //         }
    //     }
    // }

    // RETURNING METHODSSS
    // public void returnBook(){
    // System.out.println("--------Return Items--------");
    // boolean more = true; //habang true -- loop

    // while(more) {
    // boolean found = false;

    // System.out.println("Enter User ID: ");
    // int userId = input.nextInt();
    // input.nextLine();

    // for (int i = 0; i < studentCount; i++) { // check muna if asa sa arraylist ng
    // Customer class yung User ID
    // if (studentArray[i].getUserID() == userId) { // kunin si UserID sa customer
    // class, tas compare sa input

    // found = true;

    // System.out.println("UserID found");
    // System.out.println("Enter Item ID to be returned: ");
    // int itemId = input.nextInt();
    // input.nextLine();

    // boolean foundid = false;

    // // pang check to kung asa arraylist ng available books yung itemid na ininput
    // ng user
    // if(availablebooks[i].getItemId() == itemId){
    // System.out.println("Item with ID " + itemId + " found");
    // for (int j = 0; j < count; j++) {
    // if (availablebooks[j].getItemId() == itemId) {
    // foundid = true;

    // int itemsBorrowed = studentArray[i].getBorrowedItems(); //ireturn yung
    // quantity ng item na binorrow ni costomerr pra makuha si quantity and gamitin
    // sa increasequantity

    // availablebooks[j].increaseQuantity(itemsBorrowed); //increase yung quantity
    // ng item sa base sa getborroweditems sa customer class
    // System.out.println("\"" + availablebooks[j].getTitle() + "\" has been
    // returned");

    // // equal sa si k sa i ksi dun nahanap yung item na irereturn, tas yun yung
    // iremove, so need imove yung mga customer to the left, ksi mageerror sya if
    // null
    // for (int k = i; k < studentCount - 1; k++) { //need imove mga customers to
    // the left, ksi if nagnull, nagerror sya cannot invoke displayInfo
    // studentArray[k] = studentArray[k + 1]; //itu yung pagmove sa left, itu yung
    // binorrow na items na magmomove sa last index pra yun yung null at asa huli
    // }
    // studentArray[--studentCount] = null; // Decrease yung count ng customer and
    // set yung last index equal sa null
    // break;
    // }
    // }
    // }

    // if (!foundid) {
    // System.out.println("Item with ID " + itemId + " not found");
    // }

    // break;
    // }
    // }

    // if (!found) {
    // System.out.println("UserID not found.");
    // }

    // System.out.println("Return more? (y/n): ");
    // char response = input.nextLine().charAt(0);

    // if (response == 'y' || response == 'Y'){
    // more = true;
    // } else {
    // more = false;
    // }
    // }
    // }

    

    // public void DisplayBorrowedItems() {
    // System.out.println("--------List of Borrowed Items--------\n");
    // if (studnetCount == 0) {
    // System.out.println("No items have been borrowed yet.");
    // } else {
    // for (int i = 0; i < studnetCount; i++) {
    // if (studentArray[i] != null) //kung may laman yung arraylist
    // studentArray[i].CustDisplayBorroweditems();
    // }
    // }
    // }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}