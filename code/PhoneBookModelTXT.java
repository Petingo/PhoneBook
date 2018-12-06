import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

public class PhoneBookModelTXT extends PhoneBookModel {
    
    private String FILENAME = "../database.csv";
    private String SPLITER = ",";
    
    public PhoneBookModelTXT(PhoneBookView view) {
        super(view);
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(SPLITER);
                phoneBook.put(data[0], data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeDatabase(){
        try{
            FileWriter fw = new FileWriter(FILENAME);
            Set<String> keys = phoneBook.keySet();
            for(String key: keys){
                fw.write(key + SPLITER + phoneBook.get(key) + "\n");
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
