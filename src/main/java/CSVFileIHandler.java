
import java.text.*;
import java.util.*;
import java.io.*;
public class CSVFileIHandler implements IHandler {
    MyCollectionClass MyCollection = MyCollectionClass.getInstance();
    @Override
    public  void read()
    {
        String csvFile = "/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/employee.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] c = line.split(cvsSplitBy);
                double exp=new Double(c[3]);
                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(c[2]);
                Employee e=new Employee(c[0],c[1],date,exp);
                MyCollection.add(e);
            }
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public  void write() throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/new.csv");
        for(int i=0;i<100;i++) {
            Employee e = MyCollection.get();
            System.out.println(e);
            fileWriter.append(e.getFirstName());
            fileWriter.append(',');
            fileWriter.append(e.getLastName());
            fileWriter.append(',');
            DateFormat dateFormat = new SimpleDateFormat("ddH/mm/yyyy");
            String strDate = dateFormat.format(e.getDateOfBirth());
            fileWriter.append(strDate);
            fileWriter.append(',');
            fileWriter.append(String.valueOf(e.getExperience()));
            fileWriter.append('\n');
        }
        fileWriter.flush();
        fileWriter.close();
    }
}





