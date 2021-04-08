import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationGetAllTestCase
{
public static void main(String gg[])
{
try
{
List<DesignationDTOInterface> list;
DesignationDAO designationDAO=new DesignationDAO();
list=designationDAO.getAll();
int i=0;
while(i<list.size())
{
System.out.println("Designation: "+list.get(i).getTitle()+" with code: "+list.get(i).getCode());
i++;
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}