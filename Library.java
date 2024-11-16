import java.util.Scanner;

public class Library {
    private LibraryItem availablebooks[];
    private int count; //pang limit ng size ng array tsaka pang loop 
    public static int id = 1; //automatic magbibigay ng id


public Library(int count) {   //mag iinitialize muna ng size ng library (kung ilang items sa laman ng library, iba pa siya sa quantity ng each item)
availablebooks = new LibraryItem[count];
this.count = 0;
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

    public void returnBook(){
        Scanner input = new Scanner(System.in);
        boolean more = true;

        while(more){ //habang true -- loop
            System.out.println("Enter User ID: ");
            String userId = input.nextLine();

            System.out.println("Enter Item ID to be returned: ");
            int itemId = input.nextInt();
            input.nextLine();


            boolean found = false;
            for (int i = 0; i < count; i++){
                if (availablebooks[i].getItemId == itemId){
                    found = true;
                    availablebooks[i].increaseQuantity();
                    System.out.println("\"" + availablebooks[i].getTitle() + "\" has been returned");
                    break;
                }
            }

            if (!found){
                System.out.println("Item with ID " + itemId + "not found");
            }

            System.out.println("Return more? (y/n): ");
            char response = input.nextLine().charAt(0);

            if (response == 'y' || response == 'Y'){
                more = true;
            }
            else{
                more = false;
            }
        }
    }










}

