import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.sql.*;
public class ConnectionTestCase
{
public static void main(String gg[])
{
try
{
Connection con=DAOConnection.getConnection();
System.out.println("Connection established.");
con.close();
System.out.println("Connection closed.");
}catch(DAOException de)
{
System.out.println(de);
}
catch(SQLException s)
{
System.out.println(s);
}
}
}