package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.common.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer,DesignationInterface> codeWiseMap;
private Map<String,DesignationInterface> titleWiseMap;
private List<DesignationInterface> codeWiseOrderedList;
private List<DesignationInterface> titleWiseOrderedList;
private final static DesignationManager designationManager;
private DesignationManager()
{
populateDataStructures();
}
static
{
designationManager=new DesignationManager();
}
public static DesignationManager getDesignationManager()
{
return designationManager;
}
void populateDataStructures()
{
this.codeWiseMap=new HashMap<>();
this.titleWiseMap=new HashMap<>();
this.codeWiseOrderedList=new LinkedList<>();
this.titleWiseOrderedList=new LinkedList<>();
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
List<DesignationDTOInterface> dlDesignations=designationDAO.getAll();
DesignationInterface designation;
int code;
String title;
for(DesignationDTOInterface dlDesignation:dlDesignations)
{
designation=new Designation();
POJOCopier.copy(designation,dlDesignation);
code=designation.getCode();
title=designation.getTitle();
codeWiseMap.put(new Integer(code),designation);
titleWiseMap.put(title,designation);
codeWiseOrderedList.add(designation);
titleWiseOrderedList.add(designation);
}
Collections.sort(codeWiseOrderedList);
Collections.sort(titleWiseOrderedList,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
String leftTitle=left.getTitle().toUpperCase();
String rightTitle=right.getTitle().toUpperCase();
return leftTitle.compareTo(rightTitle);
}
});
}catch(DAOException daoException)
{
return;
}
}
/*
void populateDataStructures()
{
this.codeWiseMap=new HashMap<>();
this.titleWiseMap=new HashMap<>();

List<DesignationDTOInterface> dlList=null;
try
{
dlList=new DesignationDAO().getAll();
}catch(DAOException daoException)
{
//some code
}
int i=0;
DesignationDTOInterface designationDTO;
DesignationInterface designation;
while(i<dlList.size())
{
designationDTO=dlList.get(i);
designation=new Designation();
designation.setCode(designationDTO.getCode());
designation.setTitle(designationDTO.getTitle());
this.codeWiseMap.put(designationDTO.getCode(),designation);
this.titleWiseMap.put(designationDTO.getTitle(),designation);
i++;
}
}

public void testDS()
{
Collection<DesignationInterface> values=codeWiseMap.values();
for(DesignationInterface value : values)
{
System.out.println(value.getTitle());
}
}
*/


public void add(DesignationInterface designation) throws BLException
{
BLException blException;
blException=new BLException();
if(designation==null)
{
blException.setGenericException("designation should not be null");
throw blException;
}
String vTitle=designation.getTitle().trim();
int vCode=designation.getCode();
if(vCode!=0) blException.addException("CODE","designation code should be zero.");
if(vTitle==null) blException.addException("TITLE","Title should not be null.");
if(vTitle.length()==0) blException.addException("TITLE","Title length should not be zero.");
if(vTitle.length()>35) blException.addException("TITLE","Title should not exceed 35 characters.");
if(titleWiseMap.containsKey(vTitle)) blException.addException("TITLE","desgination : "+vTitle+" already exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("TITLE")) throw blException;
if(blException.hasException("CODE")) throw blException;
try
{
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designationDTO=new DesignationDTO();
POJOCopier.copy(designationDTO,designation);
designationDAO.add(designationDTO);
int generatedCode=designationDTO.getCode();
codeWiseMap.put(generatedCode,designation);
titleWiseMap.put(vTitle,designation);
codeWiseOrderedList.add(designation);
titleWiseOrderedList.add(designation);
designation.setCode(generatedCode);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void update(DesignationInterface designation) throws BLException
{
BLException blException;
blException=new BLException();
if(designation==null)
{
blException.setGenericException("designation should not be null");
throw blException;
}
String vTitle=designation.getTitle().trim();
int vCode=designation.getCode();
if(vCode<=0) blException.addException("CODE","invalid designation code.");
if(vTitle==null) blException.addException("TITLE","Title should not be null.");
if(vTitle.length()==0) blException.addException("TITLE","Title length should not be zero.");
if(vTitle.length()>35) blException.addException("TITLE","Title should not exceed 35 characters.");
if(codeWiseMap.containsKey(vCode)==false) blException.addException("CODE","designation code "+vCode+" does not exist.");
if(titleWiseMap.containsKey(vTitle)) blException.addException("TITLE","desgination : "+vTitle+" already exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("TITLE")) throw blException;
if(blException.hasException("CODE")) throw blException;
try
{
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designationDTO=new DesignationDTO();
POJOCopier.copy(designationDTO,designation);
designationDAO.update(designationDTO);
int generatedCode=designationDTO.getCode();
DesignationInterface dd=codeWiseMap.get(vCode);
String ddTitle=dd.getTitle();
codeWiseMap.remove(vCode);
titleWiseMap.remove(ddTitle);
codeWiseOrderedList.remove(dd);
titleWiseOrderedList.remove(dd);

codeWiseMap.put(vCode,designation);
titleWiseMap.put(vTitle,designation);
codeWiseOrderedList.add(designation);
titleWiseOrderedList.add(designation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void delete(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0) blException.addException("CODE","Invalid desigantion code.");
if(codeWiseMap.containsKey(code)==false) blException.addException("CODE","designation code : "+code+" doesn't exists.");
if(blException.hasException("CODE")) throw blException;
boolean found=isDesignationAttachedToAnEmployee(code);
if(found==true)
{
blException.addException("CODE","Employee exists with this code, Unable to delete designation");
throw blException;
}
try
{
DesignationInterface dd=codeWiseMap.get(code);
String ddTitle=dd.getTitle();
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.delete(code);
codeWiseMap.remove(code);
titleWiseMap.remove(ddTitle);
codeWiseOrderedList.remove(dd);
titleWiseOrderedList.remove(dd);
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}
public List<DesignationInterface> getDesignations(DesignationManagerInterface.ATTRIBUTE ...orderBy) throws BLException
{
List<DesignationInterface> list;
if(orderBy.length==0 || orderBy[0]==DesignationManagerInterface.TITLE)
{
list=titleWiseOrderedList;
}
else
{
list=codeWiseOrderedList;
}
List<DesignationInterface> designations=new LinkedList<>();
DesignationInterface designation;
for(DesignationInterface d : list)
{
designation=new Designation();
POJOCopier.copy(designation,d);
designations.add(designation);
}
return designations;
}
public DesignationInterface getByCode(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0) blException.addException("CODE","invalid designation code.");
if(codeWiseMap.containsKey(code)==false) blException.addException("CODE","designation code : "+code+" doesn't exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("CODE")) throw blException;
DesignationInterface designation=codeWiseMap.get(code);
DesignationInterface dd=new Designation();
POJOCopier.copy(dd,designation);
return dd;
}
public DesignationInterface getByTitle(String title) throws BLException
{
BLException blException;
blException=new BLException();
if(title==null)
{
 blException.setGenericException("title should not be null.");
throw blException;
}
String vTitle=title.trim();
if(vTitle.length()==0) blException.addException("TITLE","Title length should not be zero.");
if(vTitle.length()>35) blException.addException("TITLE","Title length should not exceed 35 characters.");
if(titleWiseMap.containsKey(vTitle)==false) blException.addException("TITLE","designation : "+vTitle+" doesn't exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("TITLE")) throw blException;
DesignationInterface designation=titleWiseMap.get(vTitle);
DesignationInterface dd=new Designation();
POJOCopier.copy(dd,designation);
return dd;
}
public boolean codeExists(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0){
 blException.addException("CODE","invalid designation code.");
throw blException;
}
if(codeWiseMap.containsKey(code)) return true;
return false;
}
public boolean titleExists(String title) throws BLException
{
BLException blException;
blException=new BLException();
if(title==null)
{
 blException.setGenericException("title should not be null.");
throw blException;
}
String vTitle=title.trim();
if(vTitle.length()==0) blException.addException("TITLE","Title length should not be zero.");
if(vTitle.length()>35) blException.addException("TITLE","Title length should not exceed 35 characters.");
if(blException.hasException("TITLE")) throw blException;
if(titleWiseMap.containsKey(vTitle)) return true;
return false;
}
public int getCount() throws BLException
{
return this.codeWiseMap.size();
}
public boolean isDesignationAttachedToAnEmployee(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0)
{
blException.addException("CODE","Invalid designation code.");
throw blException;
}
int count=0;
EmployeeManager employeeManager=EmployeeManager.getEmployeeManager();
count=employeeManager.getEmployeesCountWithDesignation(code);
if(count==0)
{
return false;
}
return true;
}
}