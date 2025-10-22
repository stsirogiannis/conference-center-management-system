
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Connection conn;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        String url = "jdbc:mysql://localhost:3306/psifiakaDB";
        String userName = "root";
        String password = "";

        try {
            //Δημιουργία σύνδεσης με τη βάση
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
            return; //Αν αποτύχει τερματίζει το πρόγραμμα
        }

        do {
            //Εμφάνιση μενού
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                //Επιλογή λειτουργίας βάσει του μενού
                switch (choice) {
                    case 1: insertConferenceCenter(scanner); break;
                    case 2: insertConferenceRoom(scanner); break;
                    case 3: insertClientOnly(scanner); break;
                    case 4: insertReservation(scanner); break;
                    case 5: insertPaymentAndUpdateClient(scanner); break;
                    case 6: listRoomsByCenter(scanner); break;
                    case 7: listBookedRooms(); break;
                    case 8: listPendingRooms(); break;
                    case 9: listAvailableRooms(scanner); break;
                    case 10: updateReservationStatus(scanner); break;
                    case 11: updatePaymentStatus(scanner); break;
                    case 12: listAndDeleteCancelledReservations(); break;
                    case 13: updateConferenceCenter(scanner); break;
                    case 14: updateConferenceRoom(scanner); break;
                    case 15: updateClient(scanner); break;
                    case 16: updateReservation(scanner); break;
                    case 17: updatePayment(scanner); break;
                    case 0: System.out.println("Έξοδος."); break;
                    default: System.out.println("Μη έγκυρη επιλογή.");
                }
            } catch (SQLException e) {
                System.out.println("Σφάλμα βάσης δεδομένων: " + e.getMessage());
            }

        } while (choice != 0);

        //Κλείσιμο σύνδεσης
        try { conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Εμφάνιση του μενού επιλογών
    static void showMenu() {
        System.out.println("\n--- Μενού ---");
        System.out.println("1. Εισαγωγή Συνεδριακού Κέντρου");
        System.out.println("2. Εισαγωγή Αίθουσας");
        System.out.println("3. Εισαγωγή Πελάτη");
        System.out.println("4. Κράτηση Αίθουσας");
        System.out.println("5. Εισαγωγή Πληρωμής");
        System.out.println("6. Λίστα Αιθουσών Συνεδριακού Κέντρου");
        System.out.println("7. Κρατημένες αίθουσες");
        System.out.println("8. Κρατήσεις σε αναμονή με εκκρεμή πληρωμή");
        System.out.println("9. Διαθέσιμες αίθουσες");
        System.out.println("10. Ενημέρωση Κατάστασης Κράτησης");
        System.out.println("11. Ενημέρωση Κατάστασης Πληρωμής");
        System.out.println("12. Ακυρωμένες Κρατήσεις (Διαγραφή μετά την ημερομηνία)");
        System.out.println("13. Ενημέρωση Συνεδριακού Κέντρου");
        System.out.println("14. Ενημέρωση Αίθουσας");
        System.out.println("15. Ενημέρωση Πελάτη");
        System.out.println("16. Ενημέρωση Κράτησης");
        System.out.println("17. Ενημέρωση Πληρωμής");
        System.out.println("0. Έξοδος");
        System.out.print("\nΕπιλογή: ");
    }

    //Εισαγωγή νέου συνεδριακού κέντρου στη βάση
    static void insertConferenceCenter(Scanner sc) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ConfCentre VALUES (?, ?, ?, ?, ?, ?, ?)");
        System.out.print("Κωδικός συν. κέντρου: "); ps.setString(1, sc.nextLine());
        System.out.print("Όνομα: "); ps.setString(2, sc.nextLine());
        System.out.print("Διεύθυνση: "); ps.setString(3, sc.nextLine());
        System.out.print("Πόλη: "); ps.setString(4, sc.nextLine());
        System.out.print("Τηλέφωνο: "); ps.setString(5, sc.nextLine());
        System.out.print("Email: "); ps.setString(6, sc.nextLine());
        System.out.print("Παροχές: "); ps.setString(7, sc.nextLine());
        System.out.println("Προστέθηκε επιτυχώς: " + ps.executeUpdate());
    }

    //Εισαγωγή νέας αίθουσας συνεδριακού κέντρου
    static void insertConferenceRoom(Scanner sc) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ConfRoom VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.print("Κωδικός Αίθουσας: "); ps.setString(1, sc.nextLine());
        System.out.print("Όνομα: "); ps.setString(2, sc.nextLine());
        System.out.print("Χωρητικότητα: "); ps.setString(3, sc.nextLine());
        System.out.print("Τύπος Καθισμάτων: "); ps.setString(4, sc.nextLine());
        System.out.print("Εξοπλισμός: "); ps.setString(5, sc.nextLine());
        System.out.print("WiFi: "); ps.setString(6, sc.nextLine());
        System.out.print("Τιμή ανά ώρα: "); ps.setString(7, sc.nextLine());
        System.out.print("Διαθεσιμότητα: "); ps.setString(8, sc.nextLine());
        System.out.print("Κωδικός Συνεδριακού Κέντρου: "); ps.setString(9, sc.nextLine());
        System.out.println("Προστέθηκε επιτυχώς: " + ps.executeUpdate());
    }

    //Εισαγωγή νέου πελάτη
    static void insertClientOnly(Scanner sc) throws SQLException {

        System.out.print("Client ID: ");
        String Client_ID = sc.nextLine();

        System.out.print("Όνομα: ");
        String Name = sc.nextLine();

        System.out.print("Email: ");
        String Email = sc.nextLine();

        System.out.print("Τηλέφωνο: ");
        String Phone_Number = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Client (Client_ID, Name, Email, Phone_Number) VALUES (?, ?, ?, ?)"
        );
        ps.setString(1, Client_ID);
        ps.setString(2, Name);
        ps.setString(3, Email);
        ps.setString(4, Phone_Number);

        System.out.println("Προστέθηκε πελάτης επιτυχώς: " + ps.executeUpdate());
    }

    //Εισαγωγή πληρωμής και ενημέρωση πελάτη για αυτήν
    static void insertPaymentAndUpdateClient(Scanner sc) throws SQLException {

        System.out.print("Payment ID: ");
        String Payment_ID = sc.nextLine();

        System.out.print("Ποσό: ");
        String Amount = sc.nextLine();

        System.out.print("Μέθοδος: ");
        String Method = sc.nextLine();

        System.out.print("Ημερομηνία (YYYY-MM-DD): ");
        String Date = sc.nextLine();

        System.out.print("Κατάσταση (Confirmed/Pending/Cancelled): ");
        String Status = sc.nextLine();

        System.out.print("Client ID: ");
        String Client_Client_ID = sc.nextLine();

        //Εισαγωγή πληρωμής
        PreparedStatement ps1 = conn.prepareStatement(
                "INSERT INTO PaymDet (Payment_ID, Amount, Method, Date, Status, Client_Client_ID) VALUES (?, ?, ?, ?, ?, ?)"
        );
        ps1.setString(1, Payment_ID);
        ps1.setString(2, Amount);
        ps1.setString(3, Method);
        ps1.setString(4, Date);
        ps1.setString(5, Status);
        ps1.setString(6, Client_Client_ID);
        ps1.executeUpdate();

        //Ενημέρωση πελάτη με το ID της πληρωμής
        PreparedStatement ps2 = conn.prepareStatement(
                "UPDATE Client SET PaymDet_Payment_ID = ? WHERE Client_ID = ?"
        );
        ps2.setString(1, Payment_ID);
        ps2.setString(2, Client_Client_ID);
        ps2.executeUpdate();

        System.out.println("Η πληρωμή καταχωρήθηκε με επιτυχία και ο πελάτης ενημερώθηκε");
    }

    //Εισαγωγή νέας κράτησης αίθουσας
    static void insertReservation(Scanner sc) throws SQLException {

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO RoomRes (Reservation_ID, Start_Date, End_Date, Start_Time, End_Time, Preferred_City,Preferred_ConferenceCentre, Participants, Preferred_Equipment, Invoice_Required, Reservation_Status, ConfRoom_Room_Code, PaymDet_Payment_ID, Client_Client_ID) VALUES (?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?)"
        );

        System.out.print("Κωδικός Κράτησης: ");
        ps.setString(1, sc.nextLine());

        System.out.print("Ημερομηνία Έναρξης (YYYY-MM-DD): ");
        ps.setString(2, sc.nextLine());

        System.out.print("Ημερομηνία Λήξης (YYYY-MM-DD): ");
        ps.setString(3, sc.nextLine());

        System.out.print("Ώρα Έναρξης: ");
        ps.setString(4, sc.nextLine());

        System.out.print("Ώρα Λήξης: ");
        ps.setString(5, sc.nextLine());

        System.out.print("Προτεινόμενη πόλη: ");
        ps.setString(6, sc.nextLine());

        System.out.print("Προτεινόμενο Συν. Κέντρο (κωδικός): ");
        ps.setString(7, sc.nextLine());

        System.out.print("Συμμετέχοντες: ");
        ps.setString(8, sc.nextLine());

        System.out.print("Προτεινόμενος Εξοπλισμός: ");
        ps.setString(9, sc.nextLine());

        System.out.print("Έκδοση Τιμολογίου (Yes/No): ");
        ps.setString(10, sc.nextLine());

        System.out.print("Κατάσταση κράτησης (Confirmed/Pending/Cancelled): ");
        ps.setString(11, sc.nextLine());

        System.out.print("Προτεινόμενη Αίθουσα (κωδικός): ");
        ps.setString(12, sc.nextLine());

        System.out.print("Κωδικός Πληρωμής: ");
        ps.setString(13, sc.nextLine());

        System.out.print("Κωδικός Πελάτη: ");
        ps.setString(14, sc.nextLine());

        int rowsInserted = ps.executeUpdate();
        System.out.println("Προστέθηκαν με επιτυχία: " + rowsInserted + " κρατήσεις");
    }

    //----------ΑΝΑΚΤΗΣΗ ΛΙΣΤΩΝ------------

    //Λίστα αιθουσών για ένα συγκεκριμένο συνεδριακό κέντρο
    static void listRoomsByCenter(Scanner sc) throws SQLException {
        System.out.print("Δώσε Κωδικό Κέντρου: ");
        String code = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM ConfRoom WHERE ConfCentre_Unique_Code = ?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) System.out.println(rs.getString("Room_Code") + " - " + rs.getString("Name"));
    }

    //Λίστα κρατημένων αιθουσών (status = Confirmed)
    static void listBookedRooms() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ConfRoom_Room_Code FROM RoomRes WHERE Reservation_Status IN ('Pending', 'Confirmed')");
        while (rs.next()) System.out.println("Room: " + rs.getString(1));
    }

    //Κρατήσεις με κατάσταση Pending και πληρωμή σε κατάσταση Pending
    static void listPendingRooms() throws SQLException {
        Statement stmt = conn.createStatement();
        String query = """
        SELECT rr.Reservation_ID
        FROM RoomRes rr
        JOIN PaymDet p ON rr.PaymDet_Payment_ID = p.Payment_ID
        WHERE rr.Reservation_Status = 'Pending'
          AND p.Status != 'Confirmed'
        """;

        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Κρατήσεις σε αναμονή με εκκρεμή πληρωμή:");
        while (rs.next()) {
            System.out.println("Reservation Code: " + rs.getString("Reservation_ID"));
        }
    }

    //Διαθέσιμες αίθουσες
    static void listAvailableRooms(Scanner sc) throws SQLException {
        System.out.print("Πόλη: "); String city = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM ConfRoom WHERE Availability = 'Yes' AND ConfCentre_Unique_Code IN (SELECT Unique_Code FROM ConfCentre WHERE City = ?)");
        ps.setString(1, city);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) System.out.println("Διαθέσιμη αίθουσα: " + rs.getString("Room_Code"));
    }

    //----------ΕΝΗΜΕΡΩΣΗ ΛΙΣΤΩΝ---------------------

    //Ενημέρωση κατάστασης κράτησης
    static void updateReservationStatus(Scanner sc) throws SQLException {
        System.out.print("Reservation ID: "); String id = sc.nextLine();
        System.out.print("Νέα Κατάσταση (Confirmed/Pending/Cancelled): "); String status = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE RoomRes SET Reservation_Status = ? WHERE Reservation_ID = ?");
        ps.setString(1, status); ps.setString(2, id);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Ενημέρωση κατάστασης πληρωμής
    static void updatePaymentStatus(Scanner sc) throws SQLException {
        System.out.print("Payment ID: "); String id = sc.nextLine();
        System.out.print("Νέα Κατάσταση (Confirmed/Pending): "); String status = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE PaymDet SET Status = ? WHERE Payment_ID = ?");
        ps.setString(1, status); ps.setString(2, id);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Λίστα ακυρωμένων κρατήσεων που έχουν περάσει την ημερομηνία έναρξης και διαγραφή αυτών
    static void listAndDeleteCancelledReservations() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM RoomRes WHERE Reservation_Status = 'Cancelled'");
        while (rs.next()) {
            String id = rs.getString("Reservation_ID");
            System.out.println("Ακυρωμένη Κράτηση: " + id);
            String endDate = rs.getString("Start_Date");
            if (LocalDate.parse(endDate).isBefore(LocalDate.now())) {
                PreparedStatement del = conn.prepareStatement("DELETE FROM RoomRes WHERE Reservation_ID = ?");
                del.setString(1, id);
                del.executeUpdate();
                System.out.println("Διαγράφηκε επιτυχώς: " + id);
            }
        }
    }

    //Ενημέρωση στοιχείων συνεδριακού κέντρου
    static void updateConferenceCenter(Scanner sc) throws SQLException {
        System.out.print("Κωδικός Κέντρου: ");
        String code = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE ConfCentre SET Name = ?, Address = ?, City = ?, Phone_Number = ?, Email = ?, Services = ? WHERE Unique_Code = ?");
        System.out.print("Όνομα: "); ps.setString(1, sc.nextLine());
        System.out.print("Διεύθυνση: "); ps.setString(2, sc.nextLine());
        System.out.print("Πόλη: "); ps.setString(3, sc.nextLine());
        System.out.print("Τηλέφωνο: "); ps.setString(4, sc.nextLine());
        System.out.print("Email: "); ps.setString(5, sc.nextLine());
        System.out.print("Παροχές: "); ps.setString(6, sc.nextLine());
        ps.setString(7, code);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Ενημέρωση στοιχείων αίθουσας
    static void updateConferenceRoom(Scanner sc) throws SQLException {
        System.out.print("Κωδικός Αίθουσας: ");
        String code = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE ConfRoom SET Name = ?, Max_Capacity = ?, Seating_Type = ?, Equipment = ?, Wifi_Availability = ?, Hourly_Rent = ?, Availability = ?, ConfCentre_Unique_Code = ? WHERE Room_Code = ?");
        System.out.print("Όνομα: "); ps.setString(1, sc.nextLine());
        System.out.print("Χωρητικότητα: "); ps.setString(2, sc.nextLine());
        System.out.print("Τύπος Καθισμάτων: "); ps.setString(3, sc.nextLine());
        System.out.print("Εξοπλισμός: "); ps.setString(4, sc.nextLine());
        System.out.print("WiFi: "); ps.setString(5, sc.nextLine());
        System.out.print("Τιμή ανά ώρα: "); ps.setString(6, sc.nextLine());
        System.out.print("Διαθεσιμότητα: "); ps.setString(7, sc.nextLine());
        System.out.print("Κωδικός Κέντρου: "); ps.setString(8, sc.nextLine());
        ps.setString(9, code);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Ενημέρωση στοιχείων πελάτη
    static void updateClient(Scanner sc) throws SQLException {
        System.out.print("Client ID: ");
        String id = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE Client SET Name = ?, Email = ?, Phone_Number = ?, PaymDet_Payment_ID = ? WHERE Client_ID = ?");
        System.out.print("Όνομα: "); ps.setString(1, sc.nextLine());
        System.out.print("Email: "); ps.setString(2, sc.nextLine());
        System.out.print("Τηλέφωνο: "); ps.setString(3, sc.nextLine());
        System.out.print("Payment ID: "); ps.setString(4, sc.nextLine());
        ps.setString(5, id);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Ενημέρωση κράτησης
    static void updateReservation(Scanner sc) throws SQLException {
        System.out.print("Reservation ID: ");
        String id = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE RoomRes SET ConfRoom_Room_Code = ?, Reservation_Status = ?, Start_Date = ?, End_Date = ? WHERE Reservation_ID = ?");
        System.out.print("Room Code: "); ps.setString(1, sc.nextLine());
        System.out.print("Κατάσταση: "); ps.setString(2, sc.nextLine());
        System.out.print("Ημερομηνία Έναρξης: "); ps.setString(3, sc.nextLine());
        System.out.print("Ημερομηνία Λήξης: "); ps.setString(4, sc.nextLine());
        ps.setString(5, id);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }

    //Ενημέρωση πληρωμής
    static void updatePayment(Scanner sc) throws SQLException {
        System.out.print("Payment ID: ");
        String id = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("UPDATE PaymDet SET Amount = ?, Method = ?, Date = ?, Status = ?, Client_Client_ID = ? WHERE Payment_ID = ?");
        System.out.print("Ποσό: "); ps.setString(1, sc.nextLine());
        System.out.print("Μέθοδος: "); ps.setString(2, sc.nextLine());
        System.out.print("Ημερομηνία: "); ps.setString(3, sc.nextLine());
        System.out.print("Κατάσταση: "); ps.setString(4, sc.nextLine());
        System.out.print("Client ID: "); ps.setString(5, sc.nextLine());
        ps.setString(6, id);
        System.out.println("Ενημερώθηκαν: " + ps.executeUpdate());
    }
}

