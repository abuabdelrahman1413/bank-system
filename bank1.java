import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class bank1 {
  static class BankAccount {

    // attributes
    String name;
    String accountNumber;
    String balance;

    // methods
    // constructor
    public BankAccount(String name, String accountNumber, String balance) {
      this.name = name;
      this.accountNumber = accountNumber;
      this.balance = balance;
    }

    @Override
    public String toString() {
      return "Name: " + name + ", Account number: " + accountNumber + ", balance: " + balance + "$";
    }
  }

  static ArrayList<BankAccount> accounts = new ArrayList<>();

  static final String fileName = "clients.txt";

  public static void main(String[] args) {
    loadClientsFromFile();

    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.println("\n--- Bank Account Manager ---");
      System.out.println("1. Display all clients");
      System.out.println("2. Add a client");
      System.out.println("3. Delete a client");
      System.out.println("4. Search for a client");
      System.out.println("5. Exit");
      System.out.println("==============================================");
      System.out.print("Choose an option: ");
      String choice = input.nextLine();

      switch (choice) {
        case "1":
          showClients();
          break;
        case "2":
          addClients(input);
          break;
        // case "3":
        //   deleteClients(input);
        //   break;
        case "5":
          Exit();
          return;
          // default:
          //   System.out.println("Invalid option try again");
      }
    }
  }

  private static void showClients() {
    if (accounts.isEmpty()) {
      System.out.println("No clients found");
    } else {
      System.out.println("\n--- Clients list ---");
      for (int i = 0; i < accounts.size(); i++) {
        System.out.println((i + 1) + "." + accounts.get(i));
      }
    }
  }

  private static void addClients(Scanner input) {
    System.out.println("Enter client name: ");
    String name = input.nextLine();
    System.out.println("Enter client account number: ");
    String accountNumber = input.nextLine();
    System.out.println("Enter client balance: ");
    String balance = input.nextLine();

    accounts.add(new BankAccount(name, accountNumber, balance));
    System.out.println("Client added successfully!");

    saveClientsToFile();
  }

  private static void loadClientsFromFile() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] partsOfString = line.split(",", 3);
        if (partsOfString.length == 3) {
          String name = partsOfString[0];
          String accountNumber = partsOfString[1];
          String balance = partsOfString[2];

          accounts.add(new BankAccount(name, accountNumber, balance));
        }
      }
      System.out.println("Clients loaded from file successfully.");
      reader.close();

    } catch (Exception e) {
      System.err.println("Error ... ");
    }
  }

  private static void saveClientsToFile() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

      for (BankAccount account : accounts) {
        writer.write(account.name + "," + account.accountNumber + "," + account.balance);
        writer.newLine();
      }
      System.out.println("Clients saved to file");
      writer.close();
    } catch (Exception e) {
      System.err.println("Error ...");
    }
  }

  private static void Exit() {
    System.out.println("Exiting ... ");
  }
}
