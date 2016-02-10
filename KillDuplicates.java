import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;


public class KillDuplicates{
  
  public static LinkedList<Address> extractAddresses(String filePath) throws IOException, FileNotFoundException{
    
    FileReader file = new FileReader(filePath);
    BufferedReader buffer = new BufferedReader(file);
    String address = buffer.readLine();
    LinkedList<Address> addressList = new LinkedList<Address>();
    
    while(address != null){
    addressList.addFirst(new Address(address));
    address = buffer.readLine();
    }
    
    buffer.close();
    file.close();
    
    return addressList;
  }
  
  static LinkedList<Address> removeDuplicates(LinkedList<Address> list) {
    
    Collections.sort(list);
    // Store unique items in result.
    LinkedList<Address> result = new LinkedList<Address>();
    int i = 0;
    int z = 0;
    int y = 0;
    // Loop over argument list.
    for (Address address : list) {
      y++;
      // If String is not in set, add it to the list and the set.
      if (!result.contains(address)) {
        result.addLast(address);
        i++;
      }
      else{
       z ++;
      }
      if (y % 1000 == 0)
        System.out.println("Parsed " + y + " addresses...");
    }
    System.out.println(i + " unique addresses");
    System.out.println(z + " repeats");
    return result;
  }
  
  public static void writeAddresses(String filePath, List<Address> addresses) throws IOException, FileNotFoundException{
    File file = new File(filePath);
    
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    
    for(Address a : addresses){
      writer.write(a.toString());
      writer.newLine();
    }
    
    writer.close();
  }
  
  public static void main(String args[]){
    if(args.length != 1){
      System.out.println("Too " + (args.length < 1 ? "few " : "many ") + "arguments");
      return;
    }
    String pathFile = args[0];
    if(pathFile.equals("spamMail"))
       pathFile = "/Users/CobyRosales/Library/Containers/com.apple.mail/Data/Documents/dateparsed.txt";
    System.out.println("Starting from " + pathFile);
    try{
      System.out.println("Extracting addresses... ");
    LinkedList<Address> addresses = extractAddresses(pathFile);
    
    System.out.println("Removing duplicates... ");
    addresses = removeDuplicates(addresses);
    
    File file = new File(pathFile);
    file.delete();
    
    System.out.println("Writing addresses... ");
    writeAddresses(pathFile, addresses);
    }
    catch(IOException e){
     System.out.println("Something went wrong"); 
    }
    
    System.out.println("Process Finalized");
  }
}
