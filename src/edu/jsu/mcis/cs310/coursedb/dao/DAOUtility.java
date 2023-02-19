package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.LinkedHashMap;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray result = new JsonArray();
        
        try {
            
            if (rs != null) {                
                ResultSetMetaData rsmd = rs.getMetaData();
                
                while(rs.next()){
                    JsonObject records = new JsonObject();

                    for(int i = 1; i <= rsmd.getColumnCount(); i++){
                        String key = rsmd.getColumnName(i);
                        String field = rs.getString(i);
                        
                        if (key.equals("section")){
                            int sectionNum = Integer.parseInt(field);
                            String section = String.format("%03d", sectionNum);
                            records.put(key, section);
                        }
                        else{
                            records.put(key, field);
                        }
                    }      
                    result.add(records);
                }
                for (Object e : result){
                    System.out.println(e);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(result);
        
    }
    
}
