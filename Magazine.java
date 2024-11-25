public class Magazine extends Library {
    private int issueNumber; //unique attribute ng magazine


public int getIssueNumber() {
    return issueNumber;
}


    public  Magazine(int itemId, String title, String creator,int quantity, int issueNumber) {
        super(itemId,title, creator, quantity); //para mainherit parameter ng libraryitem(id,title,creator,quantity)
        this.issueNumber = issueNumber;
    }




    @Override
    public void displayInfo() {
        System.out.println("Type: Magazine");
        super.displayInfo();   //tinatawag yung displayInfo ng LibraryItem(para maprint yung id, title, creator, quantity)
        System.out.println("Issue Number: "+issueNumber);
    }
}
