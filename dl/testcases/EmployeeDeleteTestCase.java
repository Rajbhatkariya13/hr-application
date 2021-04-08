import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.common.*;
public class EmployeeDeleteTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=Keyboard.getString("Enter the Employee id : ");
new EmployeeDAO().delete(employeeId);
System.out.println("Employee with employee id: "+employeeId+" deleted.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}