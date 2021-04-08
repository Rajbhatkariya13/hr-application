package com.thinking.machines.hr.bl.exceptions;
import java.util.*;
public class BLException extends Exception
{
private String genericException;
private Map<String,String>exceptions;
public BLException()
{
this("");
}
public BLException(String genericException)
{
this.setGenericException(genericException);
exceptions=new HashMap<String,String>();
}
public boolean hasGenericException()
{
return this.genericException.length()!=0;
}
public String getGenericException()
{
return this.genericException;
}
public void setGenericException(String genericException)
{
if(genericException==null)
{
this.genericException="";
}
else
{
this.genericException=genericException.trim();
}
}
public void addException(String property,String exception)
{
if(property==null)
{
this.setGenericException(exception);
return;
}
if(exception==null) this.exceptions.remove(property);
else this.exceptions.put(property.trim(),exception.trim());
}
public boolean hasException(String property)
{
if(property==null) hasGenericException();
return this.exceptions.containsKey(property.trim());
}
public String getException(String property)
{
if(property==null) getGenericException();
String exception=this.exceptions.get(property.trim());
if(exception==null) exception="";
return exception;
}
public Set<String> getProperties()
{
return this.exceptions.keySet();
}
public List<String> getExceptions()
{
List<String> exceptions=new ArrayList<String>(this.exceptions.values());
return exceptions;
}
}