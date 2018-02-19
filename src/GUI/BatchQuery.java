
package Resource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usama
 */
public class BatchQuery
{

    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet rs = null;

    public static void Queryinit()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Furniture_House;integratedSecurity=true");
            state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            state.clearBatch();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }       
    }
    
    public static void AddQuery(String query)
    {
        try
        {
            state.addBatch(query);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(BatchQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void ExecuteBatch() throws SQLException
    {
            state.executeBatch();
            
    }
    
    
    
}
    