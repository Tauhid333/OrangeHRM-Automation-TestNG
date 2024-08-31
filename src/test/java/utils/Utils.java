package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(String empId,String firstName, String lastName, String username, String password) throws IOException, ParseException {
        String fileLoc = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray empArray = (JSONArray) parser.parse(new FileReader(fileLoc));

        JSONObject empObj = new JSONObject();
        empObj.put("employeeId",empId);
        empObj.put("firstName",firstName);
        empObj.put("lastName",lastName);
        empObj.put("username",username);
        empObj.put("password",password);

        empArray.add(empObj);

        FileWriter writer = new FileWriter(fileLoc);
        writer.write(empArray.toJSONString());
        writer.flush();
        writer.close();
    }
}
