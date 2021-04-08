import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class addTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation=new Designation();
designation.setCode(Keyboard.getInt("Enter code : "));
designation.setTitle(Keyboard.getString("Enter title : "));
designationManager.add(designation);
System.out.println("designation added. ");
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