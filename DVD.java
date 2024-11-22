public class DVD extends LibraryItem{
    private int hours; //unique attribute ng dvd
    
    public DVD(int itemId, String title, String author,int quantity, int hours) {
        super(itemId,title, author, quantity); //para mainherit parameter ng libraryitem(id,title,creator,quantity)
        this.hours = hours;
    }
    
public int getHours() {
    return hours;
}

@Override
public String toFileFormat() {
    return super.toFileFormat() + "," + this.hours;
}


    @Override
    public void displayInfo() {
        System.out.println("Type: DVD");
        super.displayInfo();  //tinatawag yung displayInfo ng LibraryItem(para maprint yung id, title, creator, quantity)
        System.out.println("Hours: "+hours);
    }
    
    
    }
    