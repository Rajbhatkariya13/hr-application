import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeGetCountTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
int count=employeeManager.getCount();
System.out.println("Total number of employees : "+count);
}catch(BLException be)
{
//if(be.hasException("PAN_NUMBER")) System.out.println(be.getException("PAN_NUMBER"));
}
}
}