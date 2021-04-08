import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public class EmployeeGetEmployeesTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManagerInterface employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
List<EmployeeInterface> employees=employeeManager.getEmployees(EmployeeManagerInterface.NAME,EmployeeManagerInterface.DESIGNATION);
for(EmployeeInterface employee:employees)
{
System.out.println(employee.getEmployeeId()+",	"+employee.getName()+",	 "+employee.getDesignation().getTitle()+" , Salary : "+employee.getBasicSalary().toPlainString());
}
}catch(BLException be)
{
if(be.hasGenericException()) System.out.println(be.getGenericException());
}
}
}