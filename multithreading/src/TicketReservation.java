import java.util.Scanner;

class ReservationSystem {
    int totalSeats;

    ReservationSystem(int seats) {
        totalSeats = seats;
    }

    synchronized void bookTicket(String name, int seats) {
        System.out.println(name + " is trying to book " + seats + " seat(s)");

        if (seats <= totalSeats) {
            System.out.println("Booking confirmed for " + name);
            totalSeats = totalSeats - seats;
            System.out.println("Seats left: " + totalSeats);
        } else {
            System.out.println("Booking failed for " + name +
                    ". Requested: " + seats +
                    ", Available: " + totalSeats);
        }
    }
}

class BookingThread extends Thread {
    ReservationSystem rs;
    String name;
    int seats;

    BookingThread(ReservationSystem rs, String name, int seats) {
        this.rs = rs;
        this.name = name;
        this.seats = seats;
    }

    public void run() {
        rs.bookTicket(name, seats);
    }
}

public class TicketReservation {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total seats: ");
        int total = sc.nextInt();
        sc.nextLine();

        ReservationSystem rs = new ReservationSystem(total);

        System.out.print("Enter number of booking requests: ");
        int n = sc.nextInt();
        sc.nextLine();

        BookingThread threads[] = new BookingThread[n];

        for(int i = 0; i < n; i++) {
            System.out.println("\nBooking Request #" + (i + 1));
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Seats Required: ");
            int seats = sc.nextInt();
            sc.nextLine();

            threads[i] = new BookingThread(rs, name, seats);
        }

        for(int i = 0; i < n; i++) threads[i].start();
        for(int i = 0; i < n; i++) threads[i].join();

    }
}
