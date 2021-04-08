import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class deleteTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
int code=Keyboard.getInt("Enter code you want to delete: ");
designationManager.delete(code);
System.out.println("designation with code "+code+" deleted. ");
List<DesignationInterface> designations=designationManager.getDesignations();
for(DesignationInterface designationX:designations)
{
System.out.println(designationX.getCode()+", "+designationX.getTitle());
}
}catch(BLException be)
{
if(be.hasGenericException()) System.out.println(be.getGenericException());
if(be.hasException("CODE")) System.out.println(be.getException("CODE"));
if(be.hasException("TITLE")) System.out.println(be.getException("TITLE"));
}
}
}