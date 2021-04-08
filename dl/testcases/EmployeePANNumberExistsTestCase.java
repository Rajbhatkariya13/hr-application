import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.common.*;
public class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String panNumber=Keyboard.getString("Enter the PAN number : ");
boolean found=new EmployeeDAO().panNumberExists(panNumber);
if(found==true) System.out.println("PAN number : "+panNumber+" exists.");
else System.out.println("PAN number : "+panNumber+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}