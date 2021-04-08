import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeGetEmployeesCountWithDesignationTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
int code=Keyboard.getInt("Enter designation code : ");
int count=employeeManager.getEmployeesCountWithDesignation(code);
System.out.println("Total number of employees : "+count);
}catch(BLException be)
{
if(be.hasException("CODE")) System.out.println(be.getException("CODE"));
}
}
}