import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationAddTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAO designationDAO=new DesignationDAO();
designationDTO.setTitle(title);
designationDAO.add(designationDTO);
System.out.println("Designation: "+title+" added with code: "+designationDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}