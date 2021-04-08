import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String employeeId=Keyboard.getString("Enter Employee ID : ");
boolean found=employeeManager.employeeIdExists(employeeId);
if(found==true) System.out.println("Employee ID exists.");
else System.out.println("Employee ID does not exists.");
}catch(BLException be)
{
if(be.hasException("ID")) System.out.println(be.getException("ID"));
}
}
}