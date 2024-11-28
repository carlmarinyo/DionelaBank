import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BorrowedItems{
    private String username;
    private int itemId;
    private int quantity;

    Library library1;
    private ArrayList <Library> libraryItemsCopy;
    private ArrayList <BorrowedItems> borrowList = new ArrayList<>();

    public BorrowedItems(String username, int itemId, int quantity){
    this.username = username;
    this.itemId = itemId;
    this.quantity = quantity;
    }


    public BorrowedItems(){
        //this.libraryItemsCopy = e.getItemDataList();
        this.username = "";
        fileTolistBorrow();
    }

    public int checkList(String user, int itemid){
        for (BorrowedItems borrowInfo : borrowList){
            if (borrowInfo.getUsername().equals(user)){
                if (borrowInfo.getItemId() == itemid){
                    return 1;
                }
            }
        }
        return 2;
    }


    public int checkQuanti(String user, int itemId, int quantity){
        for (BorrowedItems borrowInfo : borrowList) {
            if (borrowInfo.getUsername().equals(user)){
                if (borrowInfo.getItemId() == itemId){
                    if (quantity > borrowInfo.getQuantity()) {
                        System.out.println("Your return is greater than borrowed quantity!");
                        System.out.println("You only borrowed: " + borrowInfo.getQuantity());
                        return 2;
                        //return some specific int para di matawag ung return item ng borrowinfo
                    } else return 1;
                }            
                else {
                    
                    return 1;
                }
            }
            else {
                System.out.println("User haven't borrowed anything");
                break;
            }
        }
        return 2;
    }

    public int Returnitems(String userId, int itemid, int quantity){
        boolean itemFound = false;

        for (BorrowedItems borrowInfo : borrowList) { //checking the array first 
         if (borrowInfo.getUsername().equals(userId)){
          
            if (borrowInfo.getItemId() == itemid) {
                itemFound = true;
            
                borrowInfo.setQuantity(borrowInfo.getQuantity() - quantity); //changing the quantity based on returned
                
                if (borrowInfo.getQuantity() == 0) {
                System.out.println("All items returned");
                borrowList.remove(borrowInfo); // mareremove sa borrowers file yung nag borrow pag
                                                                   // nag reach ng 0 yung quantity
                borrowListTOfile(); // pang save sa file
                return 1;
                        }
            else {
                System.out.println("You are still borrowing " + borrowInfo.quantity);
                borrowListTOfile();
            }
                }
         }
         else {
            System.out.println("No user found on borrowers");
            return 2;
        }
               }
            if (!itemFound) {
             
                return 2;
            }
            return 1;
    }


    public void Borrowitems(String Custname, int itemId, int itemsborrowed) {
        // Load the current borrow list from the file
        //fileTolistBorrow();
    
        boolean found = false;
    
        //Checks if the item was already borrowed before
        for (BorrowedItems borrowInfo : borrowList) {
            if (borrowInfo.username.equals(Custname) && borrowInfo.itemId == itemId) {
                borrowInfo.quantity += itemsborrowed; // if already borrwowed, it just adds to the quantity
                found = true;
            }
        }
    
        
        if (!found) { //if its not found, it will create a new entry
            BorrowedItems newbI = new BorrowedItems(Custname, itemId, itemsborrowed);
            borrowList.add(newbI);
        }
    
        // Save the updated list back to the file
        borrowListTOfile();
    }

    public void borrowListTOfile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("borrowers.txt"))) {
            for (BorrowedItems borrowInfo : borrowList) {
                writer.write(borrowInfo.username + ", " + borrowInfo.itemId + ", " + borrowInfo.quantity + "\n");
            }
         System.out.println("Successfully added to the text file!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving borrowers to the file.");
            e.printStackTrace();
        }
    }

// test
public void fileTolistBorrow() { //gaya ng sa Library sa additemss 
    try {
        BufferedReader reader = new BufferedReader(new FileReader("borrowers.txt")); 
            
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            
            this.username = parts[0].trim();
            this.itemId = Integer.parseInt(parts[1].trim());
            this.quantity = Integer.parseInt(parts[2].trim());

            BorrowedItems borrowInfo = new BorrowedItems(username, itemId, quantity);
            borrowList.add(borrowInfo);
           
        }
       
    } catch (IOException e) {
        System.out.println("An error occu1rred while reading borrowers from the file.");
        e.printStackTrace();
    }
}



public void displayBorrowers(String name){ //hindi ituu ataa, test tuu
    boolean ifFound = false;
    if(borrowList.size() == 0){
        System.out.println("No borrowers yet.");
    }
    else{
        System.out.println("-------------------");
        System.out.println("\nList of borrowed by " + name);
        for(BorrowedItems borrowInfo : borrowList){
           
            if(borrowInfo.username.equals(name)){
                ifFound = true;

               
                System.out.println("\nItem ID: " + borrowInfo.itemId);
                System.out.println("Quantity: " + borrowInfo.quantity);
            }
        }
        if (!ifFound) {
            System.out.println("No borrwed items yet.");
            
        }
    }

}


//getters

public String getUsername() {
    return username;
}

public int getItemId() {
    return itemId;

}

public int getQuantity() {
    return quantity;


}

//setters
public void setUsername(String username) {
    this.username = username;

}

public void setItemId(int itemId) {
    this.itemId = itemId;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}

}
