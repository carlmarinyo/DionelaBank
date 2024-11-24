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
    System.out.println("THIS IS THE SIZE OF BORROWLIST IN BORORWEITEMS METHOD " + borrowList.size()); // check if namamaintain yung size ng borrowlist kht reset

    borrowListTOfile();
    fileTolistBorrow();//
   

}

public void borrowListTOfile() { //test
    try (FileWriter writer = new FileWriter("borrowers.txt", true)) { // append mode daw
        for (BorrowedItems borrowInfo : borrowList) {
            System.out.println("IM WRITING YOU RN TO THE FILE: "); //THIS IS JUST A VISUAL NA GUMAGANA TO
            System.out.println("CUSTOMER USERNAME: " + borrowInfo.username);
            System.out.println("ITEM ID: " + borrowInfo.itemId); 
            System.out.println("QUANTITY: " + borrowInfo.quantity);

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
            System.out.println("I AM READING YOUR TEXT. PREPARING TO POPULATE ITEMS");
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
    // for (int k = i; k < count; k++) {
    // if (availablebooks[k].getItemId() == itemId) { //kunin si itemID from libraryItem array, tas compare dun sa ininput
    // itemFound = true;

    // int availableQuantity = availablebooks[k].getQuantity(); //kunin yung
    // quantity ng item sa library

    // if (availableQuantity >= itemsborrowed) {//kung may laman pa yung item sa
    // library
    // availablebooks[k].setQuantity(availableQuantity - itemsborrowed); //Miminuss
    // yung quantity ng item sa library
    // //studentArray[studnetCount++] = new accounts(UserID++, Custname,
    // itemsborrowed, availablebooks[i]); // add customer name, yung item na kinuha
    // sa library, at increment UserID nya sa arraylist ng Customer class. increment
    // din ng customerCount
    // System.out.println("Item is borrowed."); }

    // else if (availableQuantity > 0) { // if wala ng stock yung item sa library
    // System.out.println("Item " + availablebooks[k].getTitle() + " is out of
    // stock.");
    // System.out.println("Only " + availableQuantity + " item(s) available.");

    // } else { // if ubos item sa library
    // System.out.println("Item " + availablebooks[k].getTitle() + " is out of
    // stock.");

    // }
    // return;
    // }

    // }

    // break;
    // }
    // }

    // if (!itemFound) {
    // System.out.println("Item not found.");
    // }

    // }