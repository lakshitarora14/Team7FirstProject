import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONIHandler implements IHandler {
    MyCollectionClass collectionClass = MyCollectionClass.getInstance();

    @Override
    public String toString() {
        return "JSONIHandler{" +
                "collectionClass=" + collectionClass +
                '}';
    }

    public void write() {

        JSONArray employeeList = new JSONArray();
        for(int i=0;i<100;i++){
            Employee emp = collectionClass.get();
            JSONObject employeeDetails= new JSONObject();
            employeeDetails.put("firstName", emp.getFirstName());
            employeeDetails.put("lastName", emp.getLastName());
            employeeDetails.put("experience",emp.getExperience() + "" );
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(emp.getDateOfBirth());
            employeeDetails.put("dateOfBirth", strDate);

            employeeList.add(employeeDetails);
        }

        FileWriter file=null;
        file = null ;
        try {
            file = new FileWriter("/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/demo.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            file.write(employeeList.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void read() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        Object obj = null;
        try {
             reader = new FileReader("/Users/kevalmehta/IdeaProjects/coviamproject/src/main/java/employee.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            obj = jsonParser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) obj;
        Employee emp =null;
        int counter =0;
        for(Object ob : jsonArray){
             emp = new Employee();
            JSONObject object = (JSONObject) ob;
            emp.setFirstName((String) object.get("firstName"));
            emp.setLastName((String)object.get("lastName"));
            emp.setExperience(Long.valueOf((Long) object.get("experience")).doubleValue());
            Date date = new Date((String)object.get("dateOfBirth"));
            emp.setDateOfBirth(date);
            collectionClass.add(emp);
            System.out.println(counter +" Counter");
            counter++;
            }

        System.out.println(emp.getExperience());
    }
}
