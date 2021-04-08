import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeUpdateTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManager employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee=new Employee();
employee.setEmployeeId(Keyboard.getString("Enter Employee Id of the employee you want to edit : "));
employee.setName(Keyboard.getString("Enter Employee Name : "));
DesignationInterface designation=new Designation();
designation.setCode(Keyboard.getInt("Enter  designation code : "));
designation.setTitle(Keyboard.getString("Enter designation title : "));
employee.setDesignation(designation);
employee.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(Keyboard.getString("Enter D.O.B (dd/MM/yyyy)  : ")));
employee.setBasicSalary(new BigDecimal(Keyboard.getString("Enter Salary : ")));
String isIndian=Keyboard.getString("Indian (Y/N) :");
if(isIndian.equals("Y")==false && isIndian.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(isIndian.equals("Y")) employee.isIndian(true);
else employee.isIndian(false);
String gender=Keyboard.getString("Enter gender (M/F) : ");
if(gender.equals("M")==false && gender.equals("F")==false)
{
System.out.println("Invalid input");
return;
}
if(gender.equals("M")) employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employee.setPANNumber(Keyboard.getString("Enter PAN number : "));
employee.setAadharCardNumber(Keyboard.getString("Enter Aadhar Card Number : "));
employeeManager.update(employee);
System.out.println("employee updated. ");
List<EmployeeInterface> employees=employeeManager.getEmployees(EmployeeManagerInterface.NAME);
for(EmployeeInterface employeeX:employees)
{
System.out.println(employeeX.getEmployeeId()+", "+employeeX.getName());
}
}catch(BLException blException)
{

if(blException.hasGenericException()) System.out.println(blException.getGenericException());
if(blException.hasException("ID")) System.out.println(blException.getException("ID"));
if(blException.hasException("NAME")) System.out.println(blException.getException("NAME"));
if(blException.hasException("TITLE")) System.out.println(blException.getException("TITLE"));
if(blException.hasException("CODE")) System.out.println(blException.getException("CODE"));
if(blException.hasException("SALARY")) System.out.println(blException.getException("SALARY"));
if(blException.hasException("DOB")) System.out.println(blException.getException("DOB"));
if(blException.hasException("GENDER")) System.out.println(blException.getException("GENDER"));
if(blException.hasException("PAN_NUMBER")) System.out.println(blException.getException("PAN_NUMBER"));
if(blException.hasException("AADHAR_CARD_NUMBER")) System.out.println(blException.getException("AADHAR_CARD_NUMBER"));
}
catch(ParseException pe)
{
}
}
}