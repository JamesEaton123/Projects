import java.util.*;
public class InstagramEaton{
}
class User implements Comparable {
   private String first;
   private String last;
   private String username;
   private boolean followBack;
   private int age;   
   public User(boolean followBack, String first, String last, String username, int age){
       this.followBack = followBack;
       this.first = first;
       this.last = last;
       this.username = username;
       this.age = age;
   }
   public boolean getFollow()
   {
      return followBack;
   }
   public void unfollow()
   {
      followBack = false;
   }
   public void  follow()
   {
      followBack = true;
   }
   public String getFirst() {
      return first;
   }
   public String getLast() {
      return last;
   }
             
   public void setFirst(String first) {
      this.first = first;
   }
   public void setLast(String last) {
      this.first = first;
   }
   public String getUsername()
   {
      return username;
   }
   //Compares two user's usernames, returns -1 if they are the same, returns 0 is they are different
   public int compareTo(Object o) {
      User u = (User) o;
      if(username.equals(u.getUsername()))
         return -1;
      else
         return 0;
   }
   //Returns true if usernames are the same
   public boolean equals(User other) {
      return this.username.equalsIgnoreCase(other.username);  
   }
   //toString method
   public String toString() {
      String s = "";
      if(followBack == false)
         s = "not ";
      return "Username: " + username + "\nFirst Name: " +first+ "\nLast Name: " + last + "\n\nYou are " + s + "following this person";    
   }
   //added getters and setters
   public int getAge(){
      return age;
   }
   public void setAge(int age){
   this.age = age;
   }
}

class Instagram{
   private ArrayList<User> app; 
   Instagram(){
      app = new ArrayList<User>();
   }
   //Input a persons first and last name, and then you will follow them and return true if you havent already, or return false
   public void followBack(String first, String last){
       String s = first + " " + last;
       for(int i = 0; i < app.size(); i++){ 
         String s1 = app.get(i).getFirst() + " " + app.get(i).getLast();
         if(s1.equalsIgnoreCase(s1)){
            app.get(i).follow(); 
         } 
       }
   }
   //Creates and adds an object to the "app" array list that represents your following list, also keeps list in alphabetical order
   public boolean follow(boolean followBack, String first, String last, String username, int age) { 
    User u = new User(followBack, first, last, username, age);
    int i;
    for(i = 0; i < app.size(); i++){
        if(app.get(i).getUsername().compareTo(username) > 0) {
            break;
        } else if (app.get(i).getUsername().equals(username)) {
            return false;
        }
    }
    app.add(i, u);
    return true;
   }
   //deletes a person from list that you input
   public boolean delete(String first, String last ) {
      for(int i = 0; i<app.size(); i++){
         if(app.get(i).getFirst().equals(first) && app.get(i).getLast().equals(last)){
            app.remove(i);
            return true;
         }
      }
      return false;
   }
   //returns true or false depending if a name is found or not
   public boolean find(String first, String last) {
      for(int i = 0; i<app.size(); i++){
         if(app.get(i).getFirst().equals(first) && app.get(i).getLast().equals(last))
               return true;
      }
      return false;
   } 
   public ArrayList<User>getList(){
      return app;
   }
   //returns the size of the arraylist
   public int followersNum(){
      return app.size();
   }
   //counts and returns how many people you follow
   public int followingsNum(){
      int count = 0;
      for(int i = 0; i<app.size(); i++){
         if (app.get(i).getFollow() == true)
            count++;
      }
      return count;    
   } 
   public String toString(){ 
     String s = "";
     for(int i = 0; i<app.size(); i++){
         s += app.get(i).toString() +  "\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n";
     }
     return s;   
   }
}
//driver for the Instagram class, tests creating objects and all their methods.
class Driver
{
   public static void main(String[] args){
      Instagram insta = new Instagram();
      insta.follow(true, "James", "Eaton", "J.Eaton123", 19);
      insta.follow(false, "Caleb", "Gardiner",  "Gmoney", 5);
      insta.follow(true, "John", "Underwood",  "Junderwood", 22);
      insta.follow(true, "Frank", "Smith", "Smif", 97);
      insta.follow(false, "Trever", "Payne", "Tpayne", 12);
      System.out.println("These people follow you: \n");
      System.out.println(insta);
      System.out.println("Removing John Underwood");
      insta.delete("John", "Underwood");
      System.out.println("These people follow you: ");
      System.out.println(insta);
      System.out.println("Adding Lorry Johnson");
      insta.follow(true, "Lorry", "Johnson", "LJ", 55);
      System.out.println("These people follow you: ");
      System.out.println(insta);
      System.out.println("Search Tanner Duran");
      if(insta.find("Tanner", "Duran") == false) {
         System.out.println("Tanner Duran does not follow you");
         System.out.println("\n***************************");   
         System.out.println("You follow " + insta.followersNum() + " people");
         System.out.println("You have " + insta.followingsNum() + " followers");  
         System.out.println(insta);
         Scanner kb = new Scanner(System.in);
         System.out.println("Enter the full name of a person to follow: ");
         String first = kb.next();
         String last = kb.next();  
         insta.followBack(first, last);
         System.out.println(insta);
      }
   }
}

   	
   }
}
*/
