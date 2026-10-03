//System.out.println("2. Log Daily Workout (Day " + (currentTrackingDay + 1) + ")");
//System.out.println("3. View Performance & Fitness Report");
//System.out.println("8. Log Workout Details (Type & Calories)");

import java.util.Scanner;

public class FitZonePro 
{

    // --- Global Storage using Simple 1D Arrays ---
    // Maximum capacity of the gym
    static final int MAX_MEMBERS = 50;
    
    // Parallel arrays to hold information for each member
    // Index 0 holds data for Member 1, Index 1 for Member 2, etc.
    static String[] memberNames = new String[MAX_MEMBERS];
    static int[] memberAges = new int[MAX_MEMBERS];
    static String[] membershipTypes = new String[MAX_MEMBERS];
    static double[] monthlyFees = new double[MAX_MEMBERS];
    static double[] totalDue = new double[MAX_MEMBERS];
    static double[] totalPaid = new double[MAX_MEMBERS];
    static boolean[] isActive = new boolean[MAX_MEMBERS];

    // Keeps track of how many members are currently registered
    static int memberCount = 0;

    // Scanner object to handle user keyboard input
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) 
    {
        int choice;

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║        FITZONE PRO - INTEGRATED SYSTEM       ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // Main application loop: keeps displaying the menu until choice is 6 (Exit)
        do {
            printMenu();
            System.out.print("Enter your choice (1-6): ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear scanner buffer (consumes the remaining newline)

            // Switch statement to direct user to the selected option
            switch (choice) 
            {
                case 1: 
                    addMember(); 
                    break;
                case 2: 
                    managePayments(); 
                    break;
                case 3: 
                    viewAllMembers(); 
                    break;
                case 4: 
                    editMember(); 
                    break;
                case 5: 
                    searchMember(); 
                    break;
                case 6: 
                    System.out.println("Exiting application... Stay Healthy!"); 
                    break;
                default: 
                    System.out.println("❌ Invalid Choice! Please enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    // Displays the main program options to the user
    static void printMenu() 
    {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register New Member");
        //System.out.println("#. Log Daily Workout (Day " + (currentTrackingDay + 1) + ")");
        //System.out.println("#. View Performance & Fitness Report");
        System.out.println("2. Billing & Fee Payments");
        System.out.println("3. View Member Directory");
        System.out.println("4. Edit Member Details");
        System.out.println("5. Search Member");
        //System.out.println("#. Log Workout Details (Type & Calories)");
        System.out.println("6. Exit");
    }

    // --- FUNCTION 1: Register New Member ---
    static void addMember() 
    {
        // Step 1: Check if the gym has reached full capacity
        if (memberCount >= MAX_MEMBERS) 
        {
            System.out.println("❌ Gym is full! Cannot add more members.");
            return; // Stop the function here
        }

        System.out.println("\n--- Register New Member ---");

        // Step 2: Get member's name
        System.out.print("Enter Name: ");
        memberNames[memberCount] = sc.nextLine();

        // Step 3: Get member's age
        System.out.print("Enter Age: ");
        memberAges[memberCount] = sc.nextInt();

        // Step 4: Choose membership plan
        System.out.println("\nSelect Membership Plan:");
        System.out.println("1. Basic (Rp. 500/month)");
        System.out.println("2. Premium (Rp. 1000/month)");
        System.out.println("3. VIP (Rp. 2000/month)");
        System.out.print("Enter choice (1-3): ");
        int planChoice = sc.nextInt();

        // Set membership name and fee using a switch statement
        switch (planChoice) 
        {
            case 1:
                membershipTypes[memberCount] = "Basic";
                monthlyFees[memberCount] = 500;
                break;
            case 2:
                membershipTypes[memberCount] = "Premium";
                monthlyFees[memberCount] = 1000;
                break;
            case 3:
                membershipTypes[memberCount] = "VIP";
                monthlyFees[memberCount] = 2000;
                break;
            default:
                System.out.println(" Invalid choice entered. Defaulting to Basic plan.");
                membershipTypes[memberCount] = "Basic";
                monthlyFees[memberCount] = 500;
                break;
        }

        // Step 5: Get total months enrolled to calculate initial bill
        System.out.print("Enter Months Enrolled: ");
        int months = sc.nextInt();

        // Step 6: Calculate total money due and set default values
        totalDue[memberCount] = monthlyFees[memberCount] * months;
        totalPaid[memberCount] = 0.0; // Starts with zero payment
        isActive[memberCount] = true;  // Set status as Active

        // Step 7: Increment count so next member goes into the next array slot
        memberCount++;

        System.out.println(" Member M" + memberCount + " added successfully!");
    }

    // --- FUNCTION 2: Billing & Fee Payments ---
    static void managePayments() 
    {
        System.out.println("\n--- Billing & Fee Payments ---");
        System.out.print("Enter Member ID number (e.g., enter 1 for Member M1): ");
        int memberId = sc.nextInt();
        
        // Convert 1-based ID (M1, M2) to 0-based array index (0, 1)
        int index = memberId - 1;

        // Check if the ID entered actually exists in our system
        if (index < 0 || index >= memberCount) {
            System.out.println("Invalid Member ID!");
            return;
        }

        // Calculate current pending balance
        double pendingBalance = totalDue[index] - totalPaid[index];
        System.out.println("Member Name: " + memberNames[index]);
        System.out.println("Pending Balance: ₹" + pendingBalance);

        // Process payment if balance is remaining
        if (pendingBalance > 0) 
        {
            System.out.print("Enter payment amount to collect: ");
            double paymentAmount = sc.nextDouble();

            // Add payment amount to total paid so far
            totalPaid[index] = totalPaid[index] + paymentAmount;
            
            // Recalculate remaining balance
            double remainingBalance = totalDue[index] - totalPaid[index];
            System.out.println("Payment recorded!");
            System.out.println("New Pending Balance: ₹" + remainingBalance);
        } 
        else 
        {
            System.out.println("No pending balance for this member.");
        }
    }

    // --- FUNCTION 3: View Member Directory ---
    static void viewAllMembers() 
{
        System.out.println("\n--- Member Directory ---");

        // Check if there are any members registered
        if (memberCount == 0) 
        {
            System.out.println("No members registered yet.");
            return;
        }

        // Print header columns for the table output
        System.out.printf("%-5s %-15s %-10s %-10s %s\n", "ID", "Name", "Plan", "Due (₹)", "Status");
        System.out.println("--------------------------------------------------");

        // Loop through all registered members and print their details
        for (int i = 0; i < memberCount; i++) 
        {
            // Determine active/inactive status string
            String statusText;
            if (isActive[i] == true) 
            {
                statusText = "Active";
            }
            else
            {
                statusText = "Inactive";
            }

            // Calculate pending due amount for member i
            double amountDue = totalDue[i] - totalPaid[i];

            // Print member row with clean spacing alignment
            System.out.printf("M%-4d %-15s %-10s %-10.2f %s\n", (i + 1), memberNames[i], membershipTypes[i], amountDue, statusText);
        }
    }

    // --- FUNCTION 4: Edit Member Details ---
    static void editMember() 
    {
        System.out.println("\n--- Edit Member Details ---");
        System.out.print("Enter Member ID to edit (e.g., 1 for M1): ");
        int memberId = sc.nextInt();
        
        // Convert to array index
        int index = memberId - 1;

        // Check if member ID is valid
        if (index < 0 || index >= memberCount) 
        {
            System.out.println("Invalid Member ID!");
            return;
        }

        // Check if member is active
        if (isActive[index] == false) 
        {
            System.out.println("Member is inactive and cannot be edited.");
            return;
        }

        // Show edit options
        System.out.println("\nWhat field would you like to edit?");
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Age");
        System.out.println("3. Edit Membership Plan");
        System.out.print("Enter choice (1-3): ");
        int editChoice = sc.nextInt();
        sc.nextLine(); // Clear scanner buffer

        // Process user choice
        if (editChoice == 1)
        {
            // Edit Name
            System.out.println("Current Name: " + memberNames[index]);
            System.out.print("Enter New Name: ");
            memberNames[index] = sc.nextLine();
            System.out.println("Name updated successfully!");

        } 
        else if (editChoice == 2) 
        {
            // Edit Age
            System.out.println("Current Age: " + memberAges[index]);
            System.out.print("Enter New Age: ");
            memberAges[index] = sc.nextInt();
            System.out.println("Age updated successfully!");

        }
        else if (editChoice == 3)
        {
            // Edit Membership Plan
            System.out.println("Current Plan: " + membershipTypes[index]);
            System.out.println("Choose New Plan:");
            System.out.println("1. Basic (Rp. 500)");
            System.out.println("2. Premium (Rp. 1000)");
            System.out.println("3. VIP (Rp. 2000)");
            System.out.print("Enter choice (1-3): ");
            int newPlanChoice = sc.nextInt();

            if (newPlanChoice == 1) 
            {
                membershipTypes[index] = "Basic";
                monthlyFees[index] = 500;
            } 
            else if (newPlanChoice == 2) 
            {
                membershipTypes[index] = "Premium";
                monthlyFees[index] = 1000;
            } 
            else if (newPlanChoice == 3) 
            {
                membershipTypes[index] = "VIP";
                monthlyFees[index] = 2000;
            } 
            else 
            {
                System.out.println("Invalid selection. Plan unchanged.");
                return;
            }
            System.out.println("Membership plan updated successfully!");

        }
        else
        {
            System.out.println("Invalid option selected.");
        }
    }

    // --- FUNCTION 5: Search Member ---
    static void searchMember() 
    {
        System.out.println("\n--- Search Member ---");
        System.out.print("Enter name (or part of name) to search: ");
        String searchQuery = sc.nextLine();
        
        boolean foundMatch = false; // Flag to track if any match is found

        System.out.println("\n--- Search Results ---");
        System.out.printf("%-5s %-20s %-10s %s\n", "ID", "Name", "Plan", "Status");
        System.out.println("--------------------------------------------------");

        // Loop through all members to look for matches
        for (int i = 0; i < memberCount; i++) 
        {
            // Convert both strings to lowercase so search is case-insensitive
            String currentName = memberNames[i].toLowerCase();
            String searchLower = searchQuery.toLowerCase();

            // Check if currentName contains the search text
            if (currentName.contains(searchLower)) 
            {
                String statusText;
                if (isActive[i] == true) 
                {
                    statusText = "Active";
                } 
                else
                {
                    statusText = "Inactive";
                }

                // Print member matching details
                System.out.printf("M%-4d %-20s %-10s %s\n", 
                        (i + 1), memberNames[i], membershipTypes[i], statusText);
                
                foundMatch = true; // Mark that we found at least one result
            }
        }

        // Display message if no match was found after loop completes
        if (foundMatch == false) 
        {
            System.out.println("❌ No members found matching '" + searchQuery + "'");
        }
    }
}
