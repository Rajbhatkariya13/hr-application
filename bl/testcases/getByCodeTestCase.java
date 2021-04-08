import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class getByCodeTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
int code=Keyboard.getInt("Enter code : ");
designation=designationManager.getByCode(code);
System.out.println("Designation Code : "+designation.getCode());
System.out.println("Designation : "+designation.getTitle());
}catch(BLException be)
{
if(be.hasGenericException()) System.out.println(be.getGenericException());
if(be.hasException("CODE")) System.out.println(be.getException("CODE"));
if(be.hasException("TITLE")) System.out.println(be.getException("TITLE"));
}
}
}