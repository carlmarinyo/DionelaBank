import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


//PARA MA ACCESS UNG DETAILS, ACCESS IT THROUGH PUBLIC METHODS USING THE INHERITED CLASSES

public class Person{
    private String username; //for each account
    private String password; //for each account
    private String name; //for each account
    private int userId; //for each account
    private String accType; //for each account
    private ArrayList<String[]> accList = new ArrayList<>(); //this is for each account line sa textfile
    public static int userCount; //public to anyone
    int choice;
    String user;
    String pass;
    Scanner scan = new Scanner(System.in);

    public Person(){
        userCount++; //everytime a user is made
    }

    public void login(){
        System.out.println("DIONELA LIBRARY SYSTEM");
        System.out.println("1. Login");
        System.out.println("2. Create an Account");
        System.out.println("3. Exit");
        System.out.println("CHOOSE YOUR ANSWER: ");
        choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Username/Userid: ");
                user = scan.nextLine();
                System.out.print("Password: ");
                pass = scan.nextLine();
                this.storeFileAcc(); // for getting the user, pass, type, in textfile
                this.accCheck(user, pass);
                break;
            case 2:

                break;

            case 3:

                break;
            default:
                break;
        }
    }

    public void displayMenuAd(){
        System.out.println("MENU NG ADMIN");
        System.out.println(userCount);

        // if (a.adminAcc == true) {
           
        // }
    }

    public void displayMenuSt(){
        System.out.println("MENU NG STUDENT");
        System.out.println(userCount);
    }


    public void storeFileAcc(){ //this is for reading a file palang// storing of accounts in an arrayList
        try{
            userCount = 0;
            BufferedReader reader = new BufferedReader(new FileReader("accJab.txt"));
            
            System.out.println("I AM READING YOUR TEXT.");

            String line;
            while ((line = reader.readLine()) != null){
                userCount++; //for each acc
                String[] parts = line.split(",");
                accList.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()}); //creates a new string containing the part0 and part1 (trimmed version) like Array [] arr = {1, 2}
            }
            
            }
            catch(IOException e){
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
    }

    public void accCheck(String user, String pass){ //
        outer:
        for (String[] account : accList){
           // System.out.println("User: " + account[0] + "\nPassword: " + account[1]); // for showing each account in the file

            if (account[0].equals(user)){
                System.out.println("USER FOUND");
                if (account[1].equals(pass)){
                    System.out.println("OMG YOU LOGGED IN.");
                    if (account[2].equals("admin")) displayMenuAd(); //RETURN
                    else if (account[2].equals("student")) displayMenuSt();
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
    }
}