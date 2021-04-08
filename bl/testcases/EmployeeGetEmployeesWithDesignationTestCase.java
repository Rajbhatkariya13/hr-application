import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class EmployeeGetEmployeesWithDesignationTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
int code=Keyboard.getInt("Enter designation code : ");
List<EmployeeInterface> list=employeeManager.getEmployeesWithDesignation(code);
EmployeeInterface employee;
for(int i=0;i<list.size();i++)
{
employee=list.get(i);
System.out.println("Employee Id : "+employee.getEmployeeId()+", Name : "+employee.getName()+", Gender : "+employee.getGender()+", "+employee.getDesignation().getTitle());
}
}catch(BLException be)
{
if(be.hasException("CODE")) System.out.println(be.getException("CODE"));
}
}
}