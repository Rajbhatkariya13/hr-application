import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String aadharCardNumber=Keyboard.getString("Enter Aadhar Card Number : ");
EmployeeInterface employee=employeeManager.getByAadharCardNumber(aadharCardNumber);
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Gender : "+employee.getGender());
System.out.println("Designation : "+employee.getDesignation().getTitle());
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
}catch(BLException be)
{
if(be.hasException("AADHAR_CARD_NUMBER")) System.out.println(be.getException("AADHAR_CARD_NUMBER"));
}
}
}