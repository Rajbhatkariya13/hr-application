import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class EmployeeDeleteTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String employeeId=Keyboard.getString("Enter Employee ID : ");
employeeManager.delete(employeeId);
System.out.println("Employee with id "+employeeId+" deleted.");

List<EmployeeInterface> employees=employeeManager.getEmployees(EmployeeManagerInterface.EMPLOYEE_ID);
for(EmployeeInterface employeeX:employees)
{
System.out.println(employeeX.getEmployeeId()+", "+employeeX.getName());
}
}catch(BLException be)
{
if(be.hasException("ID")) System.out.println(be.getException("ID"));
}
}
}