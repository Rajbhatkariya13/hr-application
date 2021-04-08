import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public class getDesignationsTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
List<DesignationInterface> designations=designationManager.getDesignations();
for(DesignationInterface designation:designations)
{
System.out.println(designation.getCode()+", "+designation.getTitle());
}
}catch(BLException be)
{
if(be.hasGenericException()) System.out.println(be.getGenericException());
}
}
}