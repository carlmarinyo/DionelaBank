import java.util.Scanner;

public class Library {
    private LibraryItem availablebooks[];
    private int count; //pang limit ng size ng array tsaka pang loop 
    private int customerCount; //pang limit ng size ng array tsaka pang loop
    public static int id = 1; //automatic magbibigay ng id pra sa Items

    public static int UserID = 100; //automatic magbibigay ng id pra sa User
    
    private Customer CustomerborrowedItems[]; //arraylist para sa borrowed items ng customer, based on Customer class too




    public Library(int count) {   //mag iinitialize muna ng size ng library (kung ilang items sa laman ng library, iba pa siya sa quantity ng each item)
        availablebooks = new LibraryItem[count];
        this.count = 0;
        CustomerborrowedItems = new Customer[count]; //initialize ng arraylist, pra dto istore yunng nagborrow na customer
    }

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
    Scanner input = new Scanner(System.in);
    boolean more = true; //habang true -- loop

    while(more) { 
        boolean found = false;

        System.out.println("Enter User ID: ");
        int userId = input.nextInt();
        input.nextLine(); 

        
        for (int i = 0; i < customerCount; i++) { // check muna if asa sa arraylist ng Customer class yung User ID
            if (CustomerborrowedItems[i].getUserID() == userId) { // kunin si UserID sa customer class, tas compare sa input
               
                found = true;

                System.out.println("UserID found");
                System.out.println("Enter Item ID to be returned: ");
                int itemId = input.nextInt();
                input.nextLine();

                boolean foundid = false;
                
                for (int j = 0; j < count; j++) {
                    if (availablebooks[j].getItemId() == itemId) {
                        foundid = true;
                        availablebooks[j].increaseQuantity();
                        System.out.println("\"" + availablebooks[j].getTitle() + "\" has been returned");

                        // equal sa si k sa i ksi dun nahanap yung item na irereturn, tas yun yung iremove, so need imove yung mga customer to the left, ksi mageerror sya if null
                        for (int k = i; k < customerCount - 1; k++) { //need imove mga customers to the left, ksi if nagnull, nagerror sya cannot invoke displayInfo
                            CustomerborrowedItems[k] = CustomerborrowedItems[k + 1]; //itu yung pagmove sa left, itu yung binorrow na items na magmomove sa last index pra yun yung null at asa huli
                        }
                        CustomerborrowedItems[--customerCount] = null; // Decrease yung count ng customer and set yung last index equal sa null
                        break;
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

    // Other Operations na pwede gawennn
    // 1. Check if same name ang humiram sa item, if ganun ilalagay sa same name na customer
    // 2. pwede rin maglagay ng quantity sa customer class para sa multiple items na kinuha
    //    -- Array cguroo gamit pra dtu

    public void Borrowitems(String Custname, int itemId) { // FOR BORROWING TUU
        for (int i = 0; i < count; i++) {
            if (availablebooks[i].getItemId() == itemId) { //kunin si itemID from libraryItem array, tas compare dun sa ininput
                if (availablebooks[i].getQuantity() > 0) {//kung may laman pa yung item sa library
                    availablebooks[i].setQuantity(availablebooks[i].getQuantity() - 1); //Miminuss yung quantity ng item sa library
                    CustomerborrowedItems[customerCount++] = new Customer(UserID++, Custname, availablebooks[i]); // add customer name, yung item na kinuha sa library, at increment UserID nya sa arraylist ng Customer class. increment din ng customerCount
                    System.out.println("Item is borrowed."); 

                } else { // if ubos item sa library
                    System.out.println("Item is out of stock.");
               
                        }
                
                }
                // Di ko alam bkt nagpapakita too kht true nmn yung Condtion na EQAUL YUNG TITLE AT INPUT
                else { //kung wala yung item
                        System.out.println("Item not found.");
                    } 
            }

        }
    

        public void DisplayBorrowedItems() {
            System.out.println("Borrowed Items: ");
            if (customerCount == 0) {
                System.out.println("No items have been borrowed yet.");
            } else {
                for (int i = 0; i < customerCount; i++) {
                    if (CustomerborrowedItems[i] != null) //kung may laman yung arraylist
                    CustomerborrowedItems[i].CustDisplayBorroweditems();
                }
            }
        }

}

