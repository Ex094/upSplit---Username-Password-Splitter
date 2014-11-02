import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class upsplit {
    
    Vector user = new Vector(); //Stores Usernames
    Vector pass = new Vector(); //Stores Passwords
    
    public upsplit() {}
    
    public void split(String item) {
        
        for(int i = 0; i < item.length(); i++) {
            
            if(item.charAt(i) == ':') {
                
               user.add(item.substring(0, i));
               pass.add(item.substring(i + 1, item.length()));
            }
            
            if(i == item.length()) {
                
                System.out.println("Unsupported Format! Exiting!");
                break;
            }
        }
    }
    
    public void readFile(String file) throws FileNotFoundException {
        
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        
        try {
            
            while ((line = reader.readLine()) != null) {
                
                split(line); //Split the inputs
            }
            
        } catch (IOException ex) {
            Logger.getLogger(upsplit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeFile(String filename, String item) {
        
        BufferedWriter writer = null;
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename, true), "utf-8"));
            
            writer.write(item);
            writer.newLine();
            
        } catch (IOException ex) {
        
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }
    
    public void createFiles(String filename) {
        
        for(int i = 0; i < user.size(); i++) {
            
            writeFile(filename + "user.txt", (String)user.elementAt(i));
        }
        
        for(int i = 0; i < pass.size(); i++) {
            
            writeFile(filename + "pass.txt", (String)pass.elementAt(i));
        }
        
    }
}
