class TicketCounter 
{
	int AvailableSeats = 5;
	
	synchronized void bookticket(String name, int noofseats)
	{
       		 if((AvailableSeats >= noofseats) && noofseats > 0)
		{
			System.out.printf("\n %s %d seats booked successfully",name,noofseats);
            			AvailableSeats = AvailableSeats - noofseats;
        		}
		else
		{
           	 		System.out.printf("\n Sorry %s seats not available", name);
        		}
    	}
}

class TicketBookingThread extends Thread
{
		TicketCounter t;
    		String name;
    		int noofseats;
		TicketBookingThread(TicketCounter t, String name, int noofseats)
		{
       		 	this.t = t;
        			this.name = name;
        			this.noofseats = noofseats;
   		 }

    		@Override
    		public void run() 
		{
        			t.bookticket(name,noofseats);
    		}
}

public class BusReservationSystem 
{
		public static void main(String[] args) 
		{
        			TicketCounter t = new TicketCounter();
        			TicketBookingThread t1 = new TicketBookingThread(t,"Raj",3);
        			TicketBookingThread t2 = new TicketBookingThread(t,"Rohit",2);
			t1.start();
        			//t2.setPriority(7);
        			t2.start();
    		}
}
