import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class testSort
{
public static void main(String gg[])
{
try
{
List<EmployeeInterface> employees=EmployeeManager.getEmployeeManager().testSorting();
for(EmployeeInterface employee:employees)
{
System.out.println(employee.getEmployeeId()+", "+employee.getName()+" , "+employee.getDesignation().getCode()+" , "+employee.getDesignation().getTitle());
}
}catch(Exception be)
{
//if(be.hasGenericException()) System.out.println(be.getGenericException());
}
}
}