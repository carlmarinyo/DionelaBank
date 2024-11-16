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










}

