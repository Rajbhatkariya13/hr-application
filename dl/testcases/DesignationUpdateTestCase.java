import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationUpdateTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
String title=gg[1];
try
{
DesignationDTOInterface designationDTO=new DesignationDTO();
DesignationDAO designationDAO=new DesignationDAO();
designationDTO.setTitle(title);
designationDTO.setCode(code);
designationDAO.update(designationDTO);
System.out.println("Designation: "+title+" updated with code: "+code);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}