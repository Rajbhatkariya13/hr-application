import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeePanNumberExistsTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String panNumber=Keyboard.getString("Enter PAN Number : ");
boolean found=employeeManager.panNumberExists(panNumber);
if(found==true) System.out.println("PAN Number exists.");
else System.out.println("PAN Number does not exists.");
}catch(BLException be)
{
if(be.hasException("PAN_NUMBER")) System.out.println(be.getException("PAN_NUMBER"));
}
}
}