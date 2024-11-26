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

    
    public BorrowedItems(Library e){
        this.library1 = e;
        this.libraryItemsCopy = e.getItemDataList();
        this.username = "";
    }





    public void Borrowitems(String Custname, int itemId, int itemsborrowed) {
        // Load the current borrow list from the file
        fileTolistBorrow();
    
        boolean found = false;
    
        //Checks if the item was already borrowed before
        for (BorrowedItems borrowInfo : borrowList) {
            if (borrowInfo.username.equals(Custname) && borrowInfo.itemId == itemId) {
                borrowInfo.quantity += itemsborrowed; // if already borrwowed, it just adds to the quantity
                found = true;
                break;
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
         
        } catch (IOException e) {
            System.out.println("An error occurred while saving borrowers to the file.");
            e.printStackTrace();
        }
    }

// test
public void fileTolistBorrow() { //gaya ng sa Library sa additemss 

    borrowList.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader("borrowers.txt"))) {
            
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(", ");

            String username = data[0];
            int itemId = Integer.parseInt(data[1]);
            int quantity = Integer.parseInt(data[2]);

            BorrowedItems borrowInfo = new BorrowedItems(username, itemId, quantity);
            borrowList.add(borrowInfo);
           
        }
       
    } catch (IOException e) {
        System.out.println("An error occu1rred while reading borrowers from the file.");
        e.printStackTrace();
    }
}



public void displayBorrowers(String name){ //hindi ituu ataa, test tuu
    fileTolistBorrow();
    boolean ifFound = false;
    if(borrowList.size() == 0){
        System.out.println("No borrowers yet.");
    }
    else{
        
        for(BorrowedItems borrowInfo : borrowList){
           
            if(borrowInfo.username.equals(name)){
                ifFound = true;
                 System.out.println("\nUsername: " + borrowInfo.username);
                System.out.println("Item ID: " + borrowInfo.itemId);
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
  