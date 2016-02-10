/** 
 * @author Jacob Rosales Chase
 * 
 * A script designed to remove duplicate addresses from a file of addresses
 */
import java.util.Date;
import java.lang.Comparable;

@SuppressWarnings({"deprecation"})
public class Address<T extends Address> implements Comparable<T>{
  
  private Date date;                  // the date the address was recieved
  private String address;            // the address itself
  
  /** 
   * Instatiates a new address based off a 
   * predetermined string format 
   * 
   * @param inString
   */
  public Address(String inString){
    StringBuilder inAddress = new StringBuilder(inString);
    
    String address = inAddress.substring(23);
    
    int year = Integer.valueOf(inAddress.substring(7, 11));
    int month = Integer.valueOf(inAddress.substring(1, 3)) - 1;
    int day = Integer.valueOf(inAddress.substring(4, 6));
    int minute = Integer.valueOf(inAddress.substring(15, 17));
    int second = Integer.valueOf(inAddress.substring(18, 20));
    
    int hour = (inAddress.substring(20, 22).equals("AM") ? 
                Integer.valueOf(inAddress.substring(12, 14)) : 
                Integer.valueOf(inAddress.substring(12, 14)) + 12);
    
    this.date = new Date(year, month, day, hour, minute, second);
    this.address = address;
    
  }
  
  public Address(Date date, String address){
  	this.date = date;
  	this.address = address;
  }
  
  /** 
   * gets the date
   * @return  the date
   */
  public Date getDate(){
    return date; 
  }
  
  public String getMonth(){
   StringBuilder month = new StringBuilder();
    if(String.valueOf(getDate().getMonth() + 1).length() < 2)
      month.append(0);
    
    month.append(getDate().getMonth() + 1);
    return month.toString();
  }
  
  public String getDay(){
    StringBuilder day = new StringBuilder();
    if(String.valueOf(getDate().getDate()).length() < 2)
       day.append(0);
    
    day.append(getDate().getDate());
    return day.toString();
  }
  
  public String getYear(){
   return String.valueOf(getDate().getYear());
  }
  
  public String getHour(){
    StringBuilder hour = new StringBuilder();
    int num = getDate().getHours();
    if(num > 12)
      num -= 12;
    
    if(String.valueOf(num).length() < 2)
      hour.append(0);
    
    hour.append(num);
    return hour.toString();
  }
  
  public String getMinute(){
    StringBuilder minute = new StringBuilder();
    if(String.valueOf(getDate().getMinutes()).length() < 2)
       minute.append(0);
    
    minute.append(getDate().getMinutes());
    return minute.toString();
  }
  
  public String getSecond(){
    StringBuilder second = new StringBuilder();
    if(String.valueOf(getDate().getSeconds()).length() < 2)
      second.append(0);
    
    second.append(getDate().getSeconds());
    return second.toString();
  }
  
  /** 
   * gets the address
   * @return address the address
   */
  public String getAddress(){
    return address; 
  }
  
  /** 
   * override the compareTo method for the address
   * 
   * @return  int > 1 if this address was recieved after a1 int == 0 if they were recieved at the same time, else int < 0
   */
  @Override
  public int compareTo(T a1){
   return this.getDate().compareTo(a1.getDate());
  }
  
  /** 
   * override toString() method
   * @return address           the address in String format
   */

  public String toString(){
   StringBuilder address = new StringBuilder();
   
   boolean am = this.getDate().getHours() < 12;
   
   address.append("["  + getMonth());
   address.append("/" + getDay());
   address.append("/" + getYear());
   address.append("-" + getHour());
   address.append(":" + getMinute());
   address.append(":" + getSecond());
   address.append((am ? "AM" : "PM") + "]");
   
   address.append(this.getAddress());
   
   return address.toString();
  }
  
  public boolean equals(Object o){
    if(o instanceof Address){
      Address m = (Address)o;
      if(m.getDate().equals(this.getDate()))
        return true;
    }
    return false;
  }
}
