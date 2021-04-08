import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.common.*;
import java.util.*;
public class titleExistsTestCase
{
public static void main(String gg[])
{
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
String title=Keyboard.getString("Enter title : ");
boolean found=designationManager.titleExists(title);
if(found==true) System.out.println("designation : "+title+" exists.");
else System.out.println("designation : "+title+" does not exists.");
}catch(BLException be)
{
if(be.hasGenericException()) System.out.println(be.getGenericException());
if(be.hasException("CODE")) System.out.println(be.getException("CODE"));
if(be.hasException("TITLE")) System.out.println(be.getException("TITLE"));
}
}
}