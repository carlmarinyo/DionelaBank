import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileHand{

    private ArrayList<String[]> bookList = new ArrayList<>();
    private ArrayList<String[]> accList = new ArrayList<>(); //para sa pag store ng lahat ng details, ma access gamit loop

    public void storeFileAcc(){ //this is for reading a file palang// storing of accounts in an arrayList
        try{
            //userCount = 0;
            BufferedReader reader = new BufferedReader(new FileReader("accJab.txt"));
            
            System.out.println("I AM READING YOUR TEXT.");

            String line;
            while ((line = reader.readLine()) != null){
                //userCount++; //for each acc
                String[] parts = line.split(",");
                accList.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()}); //creates a new string containing the part0 and part1 (trimmed version) like Array [] arr = {1, 2}
            }
            //pag i access acc need ng loop
            }
            catch(IOException e){
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
    }

    public void storeFileBook(){ //this is for reading a file palang// storing of accounts in an arrayList
        try{
            //userCount = 0;
            BufferedReader reader = new BufferedReader(new FileReader("bookJab.txt"));
            
            System.out.println("I AM READING YOUR TEXT.");

            String line;
            while ((line = reader.readLine()) != null){
                //userCount++; //for each acc
                String[] parts = line.split(",");
                bookList.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim()}); //creates a new string containing the part0 and part1 (trimmed version) like Array [] arr = {1, 2}
            }
            //pag i access acc need ng loop sa mga methods (depende na sayo to check accCheck for reference)
            }
            catch(IOException e){
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
    }

    public void showAllBooks(){
        for (String [] book : bookList){
            System.out.println("ITEM ID: "+ book[0]); 
            System.out.println("ITEM NAME: "+ book[1]);
            System.out.println("ITEM CREATOR: "+ book[2]);
            System.out.println("ITEM QUANTITY: "+ book[3]);
            System.out.println("ITEM PAGES: "+ book[4]);
        }
    }

// eto sample ng loop
    public boolean accCheck(String user, String pass){ //FOR LOGGING IN
        outer:
        for (String[] account : accList){ //ETO UNG LOOP
           //UNG account[number/index] eto ung parts ng bawat line like account[0] is ung username pag account[1] un ung password

            if (account[0].equals(user)){
                System.out.println("USER FOUND");
                if (account[1].equals(pass)){
                    System.out.println("OMG YOU LOGGED IN.");
                    if (account[2].equals("admin")) return true; //RETURN
                    else if (account[2].equals("student")) return false;
                    else System.out.println("THERE ARE SOME ERROR IN YOUR ACCOUNT");
                    break;
                }
                else System.out.println("WRONG PO YAN PASSWORD BOBO");
                break outer;
            }
            else {
                System.out.println("LOADING..");
            }
        }
        return false;
    }

    public void iterateAcc(){

    }

    public void borrowItemFile(String user, int itemid, int itemsborrowed){ //HINDI PA TO TAPOS
        Integer.toString(itemid);
        for (String [] account : accList){
            if (account[0].equals(user)){

                for (String [] book : bookList){
                    if (book[0].equals(itemid)){

                    }
                }
                

                // for (int k = i; k < count; k++) {
                //     if (availablebooks[k].getItemId() == itemId) { //kunin si itemID from libraryItem array, tas compare dun sa ininput
                //         itemFound = true;
                        
                //         int availableQuantity = availablebooks[k].getQuantity(); //kunin yung quantity ng item sa library
        
                //         if (availableQuantity >= itemsborrowed) {//kung may laman pa yung item sa library
                //             availablebooks[k].setQuantity(availableQuantity - itemsborrowed); //Miminuss yung quantity ng item sa library
                //             //studentArray[studnetCount++] = new accounts(UserID++, Custname, itemsborrowed, availablebooks[i]); // add customer name, yung item na kinuha sa library, at increment UserID nya sa arraylist ng Customer class. increment din ng customerCount
                //             System.out.println("Item is borrowed."); }
        
                //         else if (availableQuantity > 0) { // if wala ng stock yung item sa library
                //             System.out.println("Item " + availablebooks[k].getTitle() + " is out of stock.");
                //             System.out.println("Only " + availableQuantity + " item(s) available.");
        
                //         } else { // if ubos item sa library
                //             System.out.println("Item " + availablebooks[k].getTitle() + " is out of stock.");
                       
                //                 }
                //                 return;
                //         }
                        
                //     }//for loop
            }//if condition 
        }
    }
    
}