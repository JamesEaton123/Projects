//James Eaton
//Description: Zillow app, allows you to, using a node list, create a list of house objects that you can, add to, remove from, and serch for information on.
import java.util.Scanner; 
public class ZillowEaton{
}
//
class House implements Comparable<Object>{
   private int rooms;
   private int baths;
   private double area;
   private String address;
   private double price;
   private String zipcode;
   public House(int rooms, int baths, double area, String address, double price, String zipcode){
      this.rooms = rooms;
      this.baths = baths;
      this.area = area;
      this.address = address;
      this.price = price;
      this.zipcode = zipcode;      
   }
   public double getPrice() {
      return price;
   }
   public double getArea() {
      return area;
   }
   public int getRooms() {
      return rooms;
   }
   public double getBaths()
   {
      return baths;
   }
   public String getAddress()
   {
      return address;
   }
   public String getZipcode()
   {
      return zipcode; 
   }
   public void setRooms(int rooms) {
      this.rooms = rooms;
   }
   public void setBaths(int baths)
   {
      this.baths = baths;
   }
   public void setArea(double area)
   {
      this.area = area;
   }
   public void setPrice(double price)
   {
      this.price = price;
   }
   //compares two house objects and returns weather or not they are the same
   public boolean equals(Object o) {
      House h = (House) o;
      return (h.getAddress().equals(address)) && (h.getAddress().equals(address));
   }
   //compares two objects and returns a value, -1,1,or 0, based on which one has a higher price
   public int compareTo(Object o) {
         ListNode l = (ListNode) o;
         int high = 0;
         if (this.price < l.getHouse().getPrice()) {
             return -1;
         } 
         else if (this.price > l.getHouse().getPrice()) {
             return 1;
         } 
         else {
             return 0;
         }
   }
   public String toString() {
      return "\nRooms: " + rooms + "\nBaths: " + baths + "\nSquare Feet: " + area + "\nPrice: " + price + "\nZipCode: " + zipcode + "\nAddress: " + address + "\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n";
   }	
}
//ListNode class creates the node list for house so we can store them together as a list, while being able to manipulate the list and its contents
class ListNode{
   private House house;
   private ListNode next;
   public ListNode(House house) {
      this.house = house;
   }
   public ListNode(House h, ListNode next) {
      this.house = h;
      this.next = next;
   }
   public ListNode(){
   }
   public House getHouse() {
      return house;
   }
   public ListNode getNext() {
      return next;
   }
   public void setNext(ListNode next) {
      this.next = next;
   }
}
//interface list for Zillow class
interface List{
   public void add(int rooms, int baths, double price, double area, String address,String zipcode);
   public void add(int rooms, int baths,  double area, String address,double price, String zipcode, int index);
   public void remove(String address);
   public int size();
   public String toString();
   public String search(int room); 
   public String search(int room, int bath); 
   public String search(String zipcode);
}
//Zillow class allows you to manipulate the list of House objects with commands like remove and add, as well as get information by serching 
class Zillow implements List{
   private ListNode head;
   public static int size = 0;
   public Zillow() {
      head = null;
   }
   public Zillow(House house) {
      head = new ListNode(house);
      size++;
   }
   //adds a house object to the list
   public void add(int rooms, int baths, double price, double area, String address,String zipcode){
      House h = new House(rooms, baths, price, address, area, zipcode);
      ListNode curr = head;
      if(head == null)
      {
        head = new ListNode(h);
        size++;
        return;
      }
      ListNode n = new ListNode(h);
      while(curr.getNext()!= null)
      {
        curr = curr.getNext();
      }
      curr.setNext(n);

      size++; 
   
   }
   //adds a list object to a specific place in the list
   public void add(int rooms, int baths,  double area, String address,double price, String zipcode, int index){
      House h = new House(rooms, baths, price, address, area, zipcode);
      if(index > size)
        return;
      if(index == 0){
         ListNode n = new ListNode(h);
         n.setNext(head);
         head = n;
         size++;
         return;
      }
     ListNode curr = head;
     int i = 0; 
     while(curr.getNext() != null && i < index-1){
        curr = curr.getNext();
        i++;
     }
     ListNode n = new ListNode(h);
     n.setNext(curr.getNext());
     curr.setNext(n);
     size++;  
   }   	
   //removes a house
   public void remove(String address){
      if(head == null)
         return;
      if(head.getHouse().getAddress().equals(address))
         head = head.getNext(); 
      ListNode pre = head;
      ListNode curr = head;
      while(curr != null && !(curr.getHouse().getAddress().equals(address)))
      {
         pre = curr;
         curr = curr.getNext();
      } 
      if (curr!= null && curr.getNext() == null &&(curr.getHouse().getAddress().equals(address)))       {
        pre.setNext(null);
        size--;
      } 
      else if (curr == null)
        System.out.println("House not found");
      else
       {
         pre.setNext(curr.getNext());
         size--;
       }
   }	
   public int size(){
      return  size;
   }
   public String toString() {
      ListNode curr = head;
      String s = "";
      while(curr != null)
      {
         s += curr.getHouse().toString();
         curr = curr.getNext();
      }   
      return s;
   }
   //serches for, and creates a list of, all the houses that have a certian number of rooms
   public String search(int rooms) {
      String list = "";
      if(head == null )  { 
         return null; 
      }
      else {
         ListNode curr = head; 
         while(curr!= null){
            if(curr.getHouse().getRooms() == rooms){
               list = list + curr.getHouse().toString();
            }
            curr = curr.getNext();
               
         }
         return list;
      }
   }
   //serches for, and creates a list of, all the houses that have a certian number of rooms and bathrooms
   public String search(int rooms, int baths) {
      String list = "";
      if(head == null )  { 
         return null; 
      }
      else {
         ListNode curr = head; 
         while(curr!= null){
            if(curr.getHouse().getRooms() == rooms && curr.getHouse().getBaths() == baths){
               list = list + curr.getHouse().toString();
            }
            curr = curr.getNext();
               
         }
         return list;
      }
   }
   //serches for, and creates a list of, all the houses that have a certian number zipcode
   public String search(String zipcode){
      String list = "";
      if(head == null )  { 
         return null; 
      }
      else {
         ListNode curr = head; 
         while(curr!= null){
            if(curr.getHouse().getZipcode().equals(zipcode)){
               list += curr.getHouse().toString();
            }
            curr = curr.getNext();
               
         }
         return list;
      }
   }
   //returns the house that costs the most
   public House mostExpensiveHouse(){
      
      if(head == null){
         return null;
      }
      else{
         ListNode curr = head;
         House mostExpensive = null;
         double price = Integer.MIN_VALUE;
         while(curr.getNext() != null){ 
            if(curr.getHouse().getPrice() > price){
                mostExpensive = curr.getHouse();
                price = curr.getHouse().getPrice();
            }
            curr = curr.getNext();
         }
       
      return mostExpensive;
      } 
      
   }
   //returns the house that costs the least
   public House leastExpensiveHouse(){ 
      if(head == null){
         return null;
      }
      else{
         ListNode curr = head;
         House leastExpensive = null;
         double price = Integer.MAX_VALUE;
         while(curr.getNext() != null){
            if(curr.getHouse().getPrice() < price){
                leastExpensive = curr.getHouse();
                price = curr.getHouse().getPrice();
            }
            curr = curr.getNext();
         }
       
      return leastExpensive;
      }  
   }
 }  
//
class Driver{
   public static void main(String[] args){
      Zillow  list = new Zillow();
      //int rooms, int baths, double price, double area, String address,String zipcode
      list.add(1,8,100000,1000,"This Way", "12345");
      list.add(2,7,200000,2000,"Down The Street", "12121");
      list.add(3,6,300000,3000,"Up There", "12345");
      list.add(4,5,400000,4000,"That Way", "23232");
      list.add(5,4,500000,5000,"Bumpy Road", "12345");
      list.add(6,3,600000,6000,"Rough Drive", "34343");
      list.add(7,2,700000,7000,"Up the Street", "12345");
      list.add(8,1,800000,8000,"Left Court", "45454");
      Scanner kb = new Scanner(System.in);
      while(true){
         choice();
         System.out.print("Please Choose an Option -  ");
         int input = kb.nextInt();
         
         if(input == 1){
            System.out.print("Zipcode - ");
            String zip = kb.next();
            String s = list.search(zip);
            if (s.length() != 0)
               System.out.println(s);
            else
               System.out.println("Not found");   
         }
         if(input == 2){
            System.out.print("Rooms - ");
            int rooms = kb.nextInt();
            String s = list.search(rooms);
            if (s.length() != 0)
               System.out.println(s);
            else
               System.out.println("Not found");
         }
         if(input == 3){
            System.out.print("Rooms - ");
            int rooms = kb.nextInt();
            System.out.print("Baths - ");
            int baths = kb.nextInt();
            String s = list.search(rooms, baths);
            if (s.length() != 0)
               System.out.println(s);
            else
               System.out.println("Not found");
         }
         if(input == 4){
            System.out.print("Address - ");
            kb.nextLine();
            String address  = kb.nextLine();
            list.remove(address);
         }
         if(input == 5){
            System.out.print("Rooms - ");
            int rooms = kb.nextInt();
            System.out.print("Baths - ");
            int baths = kb.nextInt();
            System.out.print("Price - ");
            double price = kb.nextDouble();
            System.out.print("Square Footage - ");
            double area = kb.nextDouble();
            System.out.print("Zipcode - ");
            String zip = kb.next();
            kb.nextLine();
            System.out.print("Address - ");
            String address = kb.nextLine();
            list.add(rooms, baths,price,area,address,zip);
         }
         if(input == 6){
            System.out.println(list);
         }
         if(input == 7){
            System.out.println(list.mostExpensiveHouse()); 
         }
         if(input == 8){
            System.out.println(list.leastExpensiveHouse()); 
         }                        
      } 
   }
   public static void choice(){
      System.out.println("\n*********************************************************\n");
      System.out.println("Enter 1 to list the houses based on the zipcode");
      System.out.println("Enter 2 to list the houses based on the number of the rooms");
      System.out.println("Enter 3 to list the houses with the number of rooms and baths");
      System.out.println("Enter 4 to remove a house from the list");
      System.out.println("Enter 5 to add a house to the list");
      System.out.println("Enter 6 to list all the houses");
      System.out.println("Enter 7 to list the most expensive house");
      System.out.println("Enter 8 to list the least expensive house");
      System.out.println("***************************************************\n");
   }
}

   
}*/
	
 
