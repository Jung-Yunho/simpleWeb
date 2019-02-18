package controller;

import java.sql.*;

public class JdbcTest
{
    static{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ResultSet rs = null;
        Connection conn = null;
        Statement stmt = null;

        try{
            String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            conn = DriverManager.getConnection(dbUrl, "imdemo30", "nets1234");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT name FROM tuser");

            while (rs.next())
                System.out.println(rs.getString(1));
        }
        catch (SQLException e){

        }
        finally
        {
            try{
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
