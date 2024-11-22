import java.util.Scanner;

public class Main{
    public static void main(String [] args){
        String username;
        String pass;
        String accType;
        String name;
        int choice;
        Scanner scan = new Scanner(System.in);
        FileHand file = new FileHand();
        accounts acc = new accounts();
        Library libra = new Library(5);
        // Person p1 = new Person();
        // p1.login();


        // System.out.print("ENTER USER ");
        // user = scan.nextLine();
        // System.out.print("ENTER PASS ");
        // pass = scan.nextLine();
        // file.storeFileAcc();
        // System.out.println("MY ACCESS IS: " + file.accCheck(user, pass));

        System.out.println("MENU");
        System.out.println("1. LOGIN");
        System.out.println("2. CREATE ACCOUNT");
        System.out.println("ENTER YOUR CHOICE: ");
        choice = scan.nextInt();
        scan.nextLine(); //CIN.IGNORE

        switch (choice) {
            case 1:
                System.out.print("ENTER USERNAME: ");
                username = scan.nextLine();
                System.out.print("ENTER PASS: ");
                pass = scan.nextLine();
                acc.fileTOlistAcc();
                acc.loginAcc(username, pass);

                //acc.
                //libra.Borrowitems("luther", 123, 1);
                // file.storeFileBook();
                // file.showAllBooks();
                break;
            case 2:
                System.out.print("ENTER USERNAME: ");
                username = scan.nextLine();
                System.out.print("ENTER PASS: ");
                pass = scan.nextLine();
                System.out.print("Enter Account Type: [1]Admin [2]Student ");
                accType = (scan.nextInt() == 1) ? "admin" : "student"; //gawan daw ng error handling kase baka ibang input 
                scan.nextLine();
                System.out.print("Enter Full NAME: ");
                name = scan.nextLine();
                acc.listTOfileAcc(username, pass, accType, name);


                //acc.fileTOlistAcc(); //tanggalin nyo comment neto to see ano effect pag nadoble tawag ng fileTOlist
                //acc.listTOfileAcc("carlmarinop", "carlpass", "student", "Carl Marino"); //may fileTolist din kase dito sa method nato
                //acc.listTOfileAcc("luthredean", "luthpass", "admin", "Luther Dean");
            break;

            case 3: //CREATION OF ACCOUNT
            acc.createAcc();
            acc.addAccToList(acc);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            acc.viewAccArrayList();
            default:
                break;
        }
        
       
        System.out.println("======================MAIN=======");
        acc.viewAccArrayList();
        System.out.println("=============================MAIN==========");
       
    }
}