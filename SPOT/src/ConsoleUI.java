package ui;

import customer.Customer;
import customer.SeasonPass;
import entry.EntryGate;
import entry.Ticket;
import exit.ExitGate;
import reports.OccupancyReport;
import reports.RevenueTracker;
import slot.SlotManager;
import vehicle.Bike;
import vehicle.Car;
import vehicle.Truck;
import vehicle.Vehicle;

import java.util.Scanner;

public class ConsoleUI {

    private SlotManager    slotManager;
    private EntryGate      entryGate;
    private ExitGate       exitGate;
    private RevenueTracker revenueTracker;
    private Scanner        sc;

    private Ticket[]   activeTickets;
    private int        ticketCount;
    private Customer[] customers;
    private int        customerCount;

    public ConsoleUI() {
        slotManager    = new SlotManager();
        revenueTracker = new RevenueTracker();
        entryGate      = new EntryGate(slotManager);
        exitGate       = new ExitGate(slotManager, revenueTracker);
        sc             = new Scanner(System.in);

        activeTickets = new Ticket[500];
        ticketCount   = 0;
        customers     = new Customer[200];
        customerCount = 0;
    }

    public void start() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println(  "║   Welcome to ParkEase v1.0       ║");
        System.out.println(  "║   Smart Parking Lot System        ║");
        System.out.println(  "╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt();
            switch (choice) {
                case 1: handleEntry();           break;
                case 2: handleExit();            break;
                case 3: handleCustomerRegister();break;
                case 4: handleSeasonPass();      break;
                case 5: slotManager.printDashboard();      break;
                case 6: new OccupancyReport(slotManager).generate(); break;
                case 7: revenueTracker.printReport();      break;
                case 8: listCustomers();         break;
                case 0: running = false;         break;
                default: System.out.println("  Invalid choice.");
            }
        }
        System.out.println("\nThank you for using ParkEase. Goodbye!");
    }

    // ─── Entry ───────────────────────────────────────────────────────────────

    private void handleEntry() {
        System.out.println("\n── VEHICLE ENTRY ──");
        System.out.print("  Vehicle number : ");
        String vNum = sc.nextLine().trim().toUpperCase();

        System.out.print("  Owner name     : ");
        String owner = sc.nextLine().trim();

        System.out.println("  Type → [1] Bike   [2] Car   [3] Truck");
        System.out.print("  Choice : ");
        int type = readInt();

        Vehicle vehicle;
        if      (type == 1) vehicle = new Bike(vNum, owner);
        else if (type == 2) vehicle = new Car(vNum, owner);
        else if (type == 3) vehicle = new Truck(vNum, owner);
        else { System.out.println("  Invalid type."); return; }

        Ticket ticket = entryGate.checkIn(vehicle);
        if (ticket != null)
            activeTickets[ticketCount++] = ticket;
    }

    // ─── Exit ────────────────────────────────────────────────────────────────

    private void handleExit() {
        System.out.println("\n── VEHICLE EXIT ──");
        System.out.print("  Enter ticket ID (e.g. TKT-0001) : ");
        String ticketId = sc.nextLine().trim().toUpperCase();

        Ticket found = removeTicket(ticketId);
        if (found == null) {
            System.out.println("  [EXIT] Ticket not found.");
            return;
        }

        // Check if vehicle owner has a season pass
        boolean hasPass = false;
        Customer c = findCustomerByVehicle(found.getVehicle().getVehicleNumber());
        if (c != null && c.hasValidPass()) {
            System.out.println("  [PASS] Season pass detected for " + c.getName() + " — No charge.");
            hasPass = true;
        }

        exitGate.checkOut(found, hasPass);
    }

    // ─── Customer ────────────────────────────────────────────────────────────

    private void handleCustomerRegister() {
        System.out.println("\n── REGISTER CUSTOMER ──");
        System.out.print("  Name           : ");
        String name = sc.nextLine().trim();
        System.out.print("  Contact        : ");
        String contact = sc.nextLine().trim();
        System.out.print("  Vehicle number : ");
        String vNum = sc.nextLine().trim().toUpperCase();

        Customer customer = new Customer(name, contact, vNum);
        customers[customerCount++] = customer;
        System.out.println("\n  Customer registered!");
        customer.print();
    }

    private void handleSeasonPass() {
        System.out.println("\n── ASSIGN SEASON PASS ──");
        System.out.print("  Vehicle number : ");
        String vNum = sc.nextLine().trim().toUpperCase();

        Customer c = findCustomerByVehicle(vNum);
        if (c == null) {
            System.out.println("  Customer not found. Register first.");
            return;
        }

        System.out.println("  Pass type → [1] Weekly (Rs.500)   [2] Monthly (Rs.1500)");
        System.out.print("  Choice : ");
        int choice = readInt();

        String passType = (choice == 1) ? SeasonPass.WEEKLY : SeasonPass.MONTHLY;
        c.assignPass(new SeasonPass(passType));
    }

    private void listCustomers() {
        System.out.println("\n── REGISTERED CUSTOMERS ──");
        if (customerCount == 0) {
            System.out.println("  No customers registered yet.");
            return;
        }
        for (int i = 0; i < customerCount; i++) {
            System.out.println("  ─────────────────────────");
            customers[i].print();
        }
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private void printMainMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println(  "║        PARKEASE — MENU           ║");
        System.out.println(  "╠══════════════════════════════════╣");
        System.out.println(  "║  [1] Vehicle Entry               ║");
        System.out.println(  "║  [2] Vehicle Exit & Bill         ║");
        System.out.println(  "║  [3] Register Customer           ║");
        System.out.println(  "║  [4] Assign Season Pass          ║");
        System.out.println(  "║  [5] Live Slot Dashboard         ║");
        System.out.println(  "║  [6] Occupancy Report            ║");
        System.out.println(  "║  [7] Revenue Report              ║");
        System.out.println(  "║  [8] List Customers              ║");
        System.out.println(  "║  [0] Exit                        ║");
        System.out.println(  "╚══════════════════════════════════╝");
        System.out.print(    "  Choice : ");
    }

    private Ticket removeTicket(String ticketId) {
        for (int i = 0; i < ticketCount; i++) {
            if (activeTickets[i].getTicketId().equals(ticketId)) {
                Ticket found = activeTickets[i];
                activeTickets[i] = activeTickets[ticketCount - 1];
                activeTickets[--ticketCount] = null;
                return found;
            }
        }
        return null;
    }

    private Customer findCustomerByVehicle(String vehicleNumber) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getVehicleNumber().equals(vehicleNumber))
                return customers[i];
        }
        return null;
    }

    private int readInt() {
        try   { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
