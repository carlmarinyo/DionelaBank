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





public void Borrowitems(String Custname, int itemId, int itemsborrowed) { //

    BorrowedItems newbI = new BorrowedItems(Custname, itemId, itemsborrowed);
    borrowList.add(newbI);


    borrowListTOfile();
    fileTolistBorrow();//
   

}

public void borrowListTOfile() { //test
    try (FileWriter writer = new FileWriter("borrowers.txt", true)) { // append mode daw
        for (BorrowedItems borrowInfo : borrowList) {
            System.out.println("Customer Name: " + borrowInfo.username);
            System.out.println("Item ID: " + borrowInfo.itemId); 
            System.out.println("Quantity: " + borrowInfo.quantity);

            writer.write(borrowInfo.username + ", " + borrowInfo.itemId + ", " + borrowInfo.quantity + "\n");
        }
        writer.close();
        System.out.println("Successfully wrote borrowers to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred while saving items to the file.");
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
        System.out.println("Successfully read borrowers from the file.");
    } catch (IOException e) {
        System.out.println("An error occurred while reading borrowers from the file.");
        e.printStackTrace();
    }
}



public void displayBorrowers(String name){ //hindi ituu ataa, test tuu
    fileTolistBorrow();

    if(borrowList.size() == 0){
        System.out.println("No borrowers yet.");
    }
    else{
        for(BorrowedItems borrowInfo : borrowList){
            if(borrowInfo.username.equals(name)){
                System.out.println("Username: " + borrowInfo.username);
                System.out.println("Item ID: " + borrowInfo.itemId);
                System.out.println("Quantity: " + borrowInfo.quantity);
            }
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
  