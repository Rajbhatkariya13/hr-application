import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.common.*;
public class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String aadharCardNumber=Keyboard.getString("Enter the Aadhar Card number : ");
boolean found=new EmployeeDAO().aadharCardNumberExists(aadharCardNumber);
if(found==true) System.out.println("Aadhar Card number : "+aadharCardNumber+" exists.");
else System.out.println("Aadhar Card number : "+aadharCardNumber+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}