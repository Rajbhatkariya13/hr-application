import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeGetByPanNumberTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String panNumber=Keyboard.getString("Enter PAN Number : ");
EmployeeInterface employee=employeeManager.getByPANNumber(panNumber);
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Gender : "+employee.getGender());
System.out.println("Designation : "+employee.getDesignation().getTitle());
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("Aadhar Card Number : "+employee.getAadharCardNumber());
}catch(BLException be)
{
if(be.hasException("PAN_NUMBER")) System.out.println(be.getException("PAN_NUMBER"));
}
}
}