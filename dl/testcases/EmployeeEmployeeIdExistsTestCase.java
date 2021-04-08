import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.common.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=Keyboard.getString("Enter the Employee id : ");
boolean found=new EmployeeDAO().employeeIdExists(employeeId);
if(found==true) System.out.println("Employee id: "+employeeId+" exists.");
else System.out.println("Employee id: "+employeeId+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}