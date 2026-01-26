class TicketCounter {
    int AvailableTicket = 5;
    synchronized void bookTicket(String name ,int noofseats){
        if ((AvailableTicket >= noofseats && AvailableTicket > 0)) {
            System.out.printf("\n %s %d seats booked successfully!",name,noofseats);
            AvailableTicket = AvailableTicket -noofseats;
        }
        else{
            System.out.printf("\n Sorry %s seats not available ",name);
        }
    }
}

class TicketBooking extends  Thread{
    TicketCounter t;
    String name;
    int noofseats;
    TicketBooking(TicketCounter t,String name,int noofseats){
        this.t=t;
        this.name= name;
        this.noofseats =noofseats;
    }

    @Override
    public  void  run(){
        t.bookTicket(name, noofseats);
    }
}
class BusReservationSystem{
    public static void main(String[] args) {
        TicketCounter t = new TicketCounter();
        TicketBooking t1 = new TicketBooking(t,"Raj",3);
        TicketBooking t2 = new TicketBooking(t,"Rohit",5);
        t1.start();
        t2.start();
    }
}

