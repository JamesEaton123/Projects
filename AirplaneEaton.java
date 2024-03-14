//Name: James Eaton
//Description: Stores People as passengers in a plane. Person is and object that stores the names and phonenumbers of people, passengers extends people and stores information in relation to them being passengers such as their seat number, and airplane stores the people together on a plane/array.
import java.util.*;
public class AirplaneEaton{}
//person class, stores information on a person's first and last name as well as their phone number.
class Person{
    private String first;
    private String last;
    private String phone;
    public Person(String first, String last, String phone){
      this.first = first;
      this.last = last;
      this.phone = phone;
    }
    public String getFirst(){
      return first;
    }
    public String getLast(){
      return last;
    } 
    public String getPhone(){
      return phone;
    }
    public void setFirst(String first){
      this.first = first;
    }
    public void setLast(String last){
      this.last = last;
    }
    public void setPhone(String phone){
      this.phone = phone;
    }
    public String toString(){
      return "First Name: " + first + "\nLast Name: " + last + "\nPhone Number: " + phone;
    }
    public boolean equals(Object o){
      Person p = (Person) o;
      return (p.getFirst().equals(first)) && (p.getLast().equals(last));
    }
}
//Passenger class stores information on a person as it pertains to them being on a plane ie. their seat number and ticket number
class Passenger extends Person{
   private int seatNumber;
   private String classType;
   private String ticketId;
   public Passenger(String first, String last, String ticketId, int seatNumber, String classType, String phone){
      super(first, last, phone);
      this.seatNumber = seatNumber;
      this.classType = classType;
      this.ticketId = ticketId;
   }
   public int getSeatNUmber(){
      return seatNumber;
   }
   public String getClassType(){
      return classType;
    }
    public String getTicketId(){
      return ticketId;
    }
    public void changeSeatNumber(int num){
      seatNumber = num;
    }
    public void setClassType(String classType){
      this.classType = classType;
    }
    public void setTicketId(String ticketId){
      this.ticketId = ticketId;
    }
    public String toString(){
      return super.toString() + "\nSeat Number: " + seatNumber + "\nClass: " + classType + "\nTicket ID: " + ticketId;
    }
}
//interface for the airplane class
interface list {   
   public boolean add(Object o);
   public Object search(Object o);
   public boolean delete(Object o);
   public void printLast();
   public void takeOff();
}
//Airplane class, stores information on the plane itself (weather or not the plane took off, plane number, etc), and store the passengers in an array
class Airplane implements list{
   public static int count = 0;
   
   private Passenger[] plane = new Passenger[10];
   private boolean takenOff = false;
   private int planeNum;
   public Airplane(int planeNum){
      this.planeNum = planeNum;
   }
   public int getPlaneNum(){
      return planeNum;
   }
   public static int getCount(){
      return count;
   }
   public void setPlaneNum(int num){
      planeNum = num;
   }
   //adds a person to the passenger list
   public boolean add(Object o) {
      if(o instanceof Passenger){
         Passenger p = (Passenger)o;
         if(!takenOff){
               plane[count] = p;
            count++;
            return true;
         }    
      }
      return false;
   }
   public String toString(){
      String s = "";
      for(int i = 0; i < plane.length; i++){
         if(plane[i] != null){ 
            s += "\n" + plane[i].toString();
            s += "\n+++++++++++++++++++++++++++";
         }
      }
      return s;
   }
   //serches for and returns a name on the passenger list
   public Object search(Object o) {
      boolean b = o instanceof String;
      if(!b)
         return null;
      String name = (String)o;
      for(int i = 0; i < plane.length; i++){
         if(plane[i]!= null && plane[i].getLast().equalsIgnoreCase(name)){ 
            return plane[i]; 
         }
      }
      return null;
   }
   //deletes a person from the list of passengers
   public boolean delete(Object o) {
      boolean b = o instanceof String;
      if(!b)
         return false;
      String name = (String)o;
      for(int i = 0; i < plane.length; i++){
         if(plane[i]!= null && plane[i].getLast().equalsIgnoreCase(name)){ 
            plane[i] = null;
            count--;
            return true;
         }
      }
      return false;
   }
   //Prints out the last names of the passengers
   public void printLast() {
      for(int i = 0; i < plane.length; i++){
         if(plane[i] != null){ 
            System.out.println(plane[i].getLast());
         }
      }
   }
   //sets takeOff to true meaning that the plane has taken off
   public void takeOff() {
      takenOff = true;
   }
}
class Driver{
   public static void main(String[]args) {
      Scanner sc = new Scanner(System.in);
      Airplane plane = new Airplane(123354);
      Passenger one = new Passenger("John", "Adams", "1111111111", 10, "First class", "111-111-1111");
      Passenger two = new Passenger("George", "Washington", "2222222222", 1, "Economy class", "222-222-2222");
      Passenger three = new Passenger("Thomas", "Edison", "3333333333", 30, "Economy class","333-333-3333");
      Passenger four = new Passenger("Barack", "Obama", "4444444444", 44, "Economy class","444-444-4444");
      plane.add(one);
      plane.add(two);
      plane.add(three);
      plane.add(four);
      System.out.println("Plane is off");
      plane.takeOff();
      Passenger five = new Passenger("James", "Eaton", "5555555555", 111, "Tied to the roof","546-2135-2346");
      plane.add(five);
      String repeat = "";
      while(plane.getCount() > 0) 
      {
         System.out.println("Number of Passengers: " + plane.getCount()); 
         System.out.println(plane.toString());
         System.out.println();
         System.out.println("Last Names:");
         plane.printLast();  
         System.out.println();
         System.out.println("Testing the static method getCount");
         System.out.println("Number of Passengers: " + plane.getCount());
         System.out.println();
         System.out.print("Enter the name of the passenger to veiw their info: ");
         String name = sc.nextLine();
         System.out.println(plane.search(name));
         System.out.println();
         System.out.print("Enter name of the passengeryou wish to delete: ");
         String last = sc.nextLine();
         plane.delete(last);
         System.out.println("Removed from list");
         System.out.println();
         System.out.println("Updated list");
         System.out.println(plane);
         System.out.print("Press\"Enter\" to continue");
         repeat = sc.nextLine();
      }
      System.out.println("No remaining passengers");
   	
   }

}
 		    
