import java.util.Scanner;

public class Library {
    private LibraryItem availablebooks[];
    private int count; //pang limit ng size ng array tsaka pang loop 
    
    private int accounts = 0; //pang loop
    private int adminCount = 0; //pang limit ng size ng array tsaka pang loop
    private int studentCount = 0; //pang limit ng size ng array tsaka pang loop
    public static int id = 1; //automatic magbibigay ng id pra sa Items
    public static int UserID = 100; //automatic magbibigay ng id pra sa User
        
    //pra sa students
    private accounts[] studentArray = new accounts[200]; //array para sa students, based on accounts class too
    

    //pra sa admnis
    private accounts[]AdminArray = new accounts[5]; //array para sa admin, based on accounts class too, 5 admins, tas 2 ang details nilaa



    public Library(int count) {   //mag iinitialize muna ng size ng library (kung ilang items sa laman ng library, iba pa siya sa quantity ng each item)
        availablebooks = new LibraryItem[count];
        this.count = 0;
        studentArray = new accounts[200]; //initialize ng arraylist, pra dto istore yunng nagborrow na customer
        AdminArray = new accounts[5]; //initialize ng arraylist, pra dto istore yunng nagborrow na customer
    }



//METHODS



//Creating Accounts
public Boolean createAdmins(String name, String password){
    
    
    if(adminCount == 5){
        System.out.println("Limit has been reached, cannot add anymore Admins.");
        return false;
    }
    
    else {
        AdminArray[adminCount++] = new accounts(name, password, "Admin");
        accounts++;
        System.out.println("Admin Account has been created.");
        System.out.println("Username: " + name);
        System.out.println("Password: " + password);
        System.out.println("-------------------------------"); 
        return true;
    
    }

}


public Boolean createStudents(String name, String password){
if(studentCount == 200){
    System.out.println("Limit has been reached, cannot add anymore Students.");
    return false;
}
    else {
    studentArray[studentCount++] = new accounts(UserID++, name, password, null, "Student");
    accounts++;
    System.out.println("Student Account has been created.");
    System.out.println("Username: " + name);
    System.out.println("Password: " + password);
    System.out.println("-------------------------------");
    return true;
    }

}

public void DisplayingAdmins(){
    System.out.println("--------List of Admins--------\n");
    if (adminCount == 0) {
        System.out.println("No Admins have been created yet.");
    } else {
        for (int i = 0; i < adminCount; i++) {
            AdminArray[i].displayInfo();
        }
    }
}
                
public void DisplayingStudents(){
    System.out.println("--------List of Students--------\n");
    if (studentCount == 0) {
        System.out.println("No Students have been created yet.");
    } else {
        for (int i = 0; i < studentCount; i++) {
            studentArray[i].displayInfo();
        }
    }
}  





//add items
    public void addItem(LibraryItem item) { //mag aadd ng either book,dvd,magazine sa array
        if (count < availablebooks.length) {
            availablebooks[count] = item;
            id++;  //iincrement yung current id para sa sunod na item
            count++; 
        }
        else {
            System.out.println("Limit has been reached, cannot add anymore items.");
        }
        }

    public void displayInventory() {
        System.out.println("--------Available Items--------");
        if (count == 0) { //checheck kung may laman ba o wala
            System.out.println("The library is empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            availablebooks[i].displayInfo();
            System.out.print("\n\n");
        }
    }

    //RETURNING METHODSSS
    public void returnBook(){
        System.out.println("--------Return Items--------");
    Scanner input = new Scanner(System.in);
    boolean more = true; //habang true -- loop

    while(more) { 
        boolean found = false;

        System.out.println("Enter User ID: ");
        int userId = input.nextInt();
        input.nextLine(); 

        
        for (int i = 0; i < studentCount; i++) { // check muna if asa sa arraylist ng Customer class yung User ID
            if (studentArray[i].getUserID() == userId) { // kunin si UserID sa customer class, tas compare sa input
               
                found = true;

                System.out.println("UserID found");
                System.out.println("Enter Item ID to be returned: ");
                int itemId = input.nextInt();
                input.nextLine();

                boolean foundid = false;
               
                // pang check to kung asa arraylist ng available books yung itemid na ininput ng user
                if(availablebooks[i].getItemId() ==  itemId){
                    System.out.println("Item with ID " + itemId + " found");
                    for (int j = 0; j < count; j++) {
                        if (availablebooks[j].getItemId() == itemId) {
                            foundid = true;
    
                            int itemsBorrowed = studentArray[i].getBorrowedItems(); //ireturn yung quantity ng item na binorrow ni costomerr pra makuha si quantity and gamitin sa increasequantity
    
                            availablebooks[j].increaseQuantity(itemsBorrowed);  //increase yung quantity ng item sa base sa getborroweditems sa customer class
                            System.out.println("\"" + availablebooks[j].getTitle() + "\" has been returned");
    
                            // equal sa si k sa i ksi dun nahanap yung item na irereturn, tas yun yung iremove, so need imove yung mga customer to the left, ksi mageerror sya if null
                            for (int k = i; k < studentCount - 1; k++) { //need imove mga customers to the left, ksi if nagnull, nagerror sya cannot invoke displayInfo
                                studentArray[k] = studentArray[k + 1]; //itu yung pagmove sa left, itu yung binorrow na items na magmomove sa last index pra yun yung null at asa huli
                            }
                            studentArray[--studentCount] = null; // Decrease yung count ng customer and set yung last index equal sa null
                            break;
                        }
                    }
                }

                if (!foundid) {
                    System.out.println("Item with ID " + itemId + " not found");
                }

                break; 
            }
        }

        
        if (!found) {
            System.out.println("UserID not found.");
        }

        
        System.out.println("Return more? (y/n): ");
        char response = input.nextLine().charAt(0);

        if (response == 'y' || response == 'Y'){
            more = true;
        } else {
            more = false;
        }
    }
}

    //BORROWING METHODSSS
    public void Borrowitems(String Custname, int itemId, int itemsborrowed) { // FOR BORROWING TUU
        boolean itemFound = false;
        
        for(int i = 0; i < studentCount; i++){
            if(studentArray[i].getName().equals(Custname)){
                itemFound = true;
                for (int k = i; k < count; k++) {
                    if (availablebooks[k].getItemId() == itemId) { //kunin si itemID from libraryItem array, tas compare dun sa ininput
                        itemFound = true;
                        
                        int availableQuantity = availablebooks[k].getQuantity(); //kunin yung quantity ng item sa library
        
                        if (availableQuantity >= itemsborrowed) {//kung may laman pa yung item sa library
                            availablebooks[k].setQuantity(availableQuantity - itemsborrowed); //Miminuss yung quantity ng item sa library
                            //studentArray[studnetCount++] = new accounts(UserID++, Custname, itemsborrowed, availablebooks[i]); // add customer name, yung item na kinuha sa library, at increment UserID nya sa arraylist ng Customer class. increment din ng customerCount
                            System.out.println("Item is borrowed."); }
        
                        else if (availableQuantity > 0) { // if wala ng stock yung item sa library
                            System.out.println("Item " + availablebooks[k].getTitle() + " is out of stock.");
                            System.out.println("Only " + availableQuantity + " item(s) available.");
        
                        } else { // if ubos item sa library
                            System.out.println("Item " + availablebooks[k].getTitle() + " is out of stock.");
                       
                                }
                                return;
                        }
                        
                    }

                break;
            }
        }

            if (!itemFound) {
                System.out.println("Item not found.");
            }

        }
    

        // public void DisplayBorrowedItems() {
        //     System.out.println("--------List of Borrowed Items--------\n");
        //     if (studnetCount == 0) {
        //         System.out.println("No items have been borrowed yet.");
        //     } else {
        //         for (int i = 0; i < studnetCount; i++) {
        //             if (studentArray[i] != null) //kung may laman yung arraylist
        //             studentArray[i].CustDisplayBorroweditems();
        //         }
        //     }
        // }



public void clearScreen() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}




        
}
