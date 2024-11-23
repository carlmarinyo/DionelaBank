import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class BorrowedItems{
    private String username;
    private String itemId;
    private String quantity;
    Library library1;
    private ArrayList <Library> libraryItemsCopy;
    private ArrayList <BorrowedItems> borrowList = new ArrayList<>();

    public BorrowedItems(String username, String itemId, String quantity){
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

    BorrowedItems newbI = new BorrowedItems(Custname, Integer.toString(itemId), Integer.toString(itemsborrowed));
    borrowList.add(newbI);
    System.out.println("THIS IS THE SIZE OF BORROWLIST IN BORORWEITEMS METHOD" + borrowList.size());
   
    borrowListTOfile();

}

public void borrowListTOfile() { //test
    try (FileWriter writer = new FileWriter("borrowers.txt")) {
        for (BorrowedItems borrowInfo : borrowList) {
            System.out.println("IM WRITING YOU RN TO THE FILE: "); //THIS IS JUST A VISUAL NA GUMAGANA TO
            System.out.println("CUSTOMER USERNAME: " + borrowInfo.username);
            System.out.println("ITEM ID: " + borrowInfo.itemId); 
            writer.write(borrowInfo.username + ", " + borrowInfo.itemId + ", " + borrowInfo.quantity + "\n");
        }
        writer.close();
        System.out.println("Successfully wrote borrowers to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred while saving items to the file.");
        e.printStackTrace();
    }
}
}

public void displayBorrowers(){

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
