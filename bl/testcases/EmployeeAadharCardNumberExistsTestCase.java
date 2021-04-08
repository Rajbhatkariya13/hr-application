import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
public class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
try
{
String aadharCardNumber=Keyboard.getString("Enter Aadhar Card Number : ");
boolean found=employeeManager.aadharCardNumberExists(aadharCardNumber);
if(found==true) System.out.println("Aadhar Card Number exists.");
else System.out.println("Aadhar Card Number does not exists.");
}catch(BLException be)
{
if(be.hasException("AADHAR_CARD_NUMBER")) System.out.println(be.getException("AADHAR_CARD_NUMBER"));
}
}
}