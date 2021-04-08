package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class DesignationModel extends AbstractTableModel
{
private List<DesignationInterface> data;
private String title[];
private DesignationManager designationManager;
public DesignationModel()
{
designationManager=DesignationManager.getDesignationManager();
try
{
data=designationManager.getDesignations(DesignationManagerInterface.CODE);
}catch(BLException blException)
{
}
title=new String[2];
title[0]="S.no.";
title[1]="Designation";
}
public int getRowCount()
{
return data.size();
}
public int getColumnCount()
{
return title.length;
}
public String getColumnName(int column)
{
return title[column];
}
public Object getValueAt(int row,int column)
{
if(column==0) return row+1;
DesignationInterface designation=(DesignationInterface)data.get(row);
return designation.getTitle();
}
public boolean isCellEditable(int row,int col)
{
return false;
}
public Class getColumnClass(int col)
{
Class c=null;
if(col==0) return Integer.class;
if(col==1) return String.class;
return c;
}
public DesignationInterface getDesignationAt(int e) throws ModelException
{
if(e<0 || e>=data.size()) throw new ModelException("Invalid index");
return data.get(e);  
}
public void add(DesignationInterface designation) throws ModelException
{
try
{
designationManager.add(designation); 
data.add(designation);
fireTableDataChanged();
}catch(BLException blException)
{
throw new ModelException(blException.getException("TITLE"));
}
}

public DesignationInterface getDesignation(String text,boolean caseSensitive,boolean fullMatch) throws ModelException
{
// some code 
// doesnt match then throw model exception
DesignationInterface designation;
String title;
if(caseSensitive==true)
{
for(int i=0;i<data.size();i++)
{
designation=data.get(i);
title=designation.getTitle();
if(fullMatch==true)
{
if(title.equals(text)) return designation;
}
else
{
if(title.startsWith(text)) return designation;
}

} //for ends

} // if ends 
else
{
for(int i=0;i<data.size();i++)
{
designation=data.get(i);
title=designation.getTitle();
if(fullMatch==true)
{
if(title.equalsIgnoreCase(text)) return designation;
}
else
{
if(title.toUpperCase().startsWith(text.toUpperCase())) return designation;
}

} //for ends 

} //else ends
throw new ModelException("Not found");
} 

public int indexOf(DesignationInterface designation) throws ModelException
{
//some code
// if not found throw Modelexception
int index=-1;
for(int i=0;i<data.size();i++)
{
if(designation.getTitle().equals(data.get(i).getTitle()))
{
index=i;
break;
}
}
if(index==-1) throw new ModelException("Not found");
return index;
}


public void delete(DesignationInterface designation) throws ModelException
{
try
{
int code=designation.getCode();
designationManager.delete(code);
data.remove(designation);
fireTableDataChanged();
}catch(BLException blException)
{
if(blException.hasGenericException()) throw new ModelException(blException.getGenericException());
if(blException.hasException("CODE")) throw new ModelException(blException.getException("CODE"));
}
}

public void update(DesignationInterface designation) throws ModelException
{
int code=designation.getCode();
try
{
designationManager.update(designation);
int vCode;
for(int i=0;i<data.size();i++)
{
vCode=data.get(i).getCode();
if(vCode==code)
{
data.get(i).setTitle(designation.getTitle());
}
}
fireTableDataChanged();
}catch(BLException blException)
{
if(blException.hasGenericException()) throw new ModelException(blException.getGenericException());
if(blException.hasException("CODE")) throw new ModelException(blException.getException("CODE"));
if(blException.hasException("TITLE")) throw new ModelException(blException.getException("TITLE"));
}
}

public void exportToPdf(File file) throws ModelException
{
int sz=data.size();
int pageSize=28;
int currentPage=1;
int nop=0;
if(sz % pageSize==0) nop=sz/pageSize;
else nop=(sz/pageSize)+1;
try
{
Document document=new Document();
PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file));
Font titleFont=new Font(Font.FontFamily.TIMES_ROMAN,22,Font.BOLD,new BaseColor(0,0,0));
Font subTitleFont=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD,new BaseColor(0,0,0));
Font tableHeaderFont=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD,new BaseColor(0,0,0));
Font tableDataFont=new Font(Font.FontFamily.TIMES_ROMAN,16,Font.NORMAL,new BaseColor(0,0,0));
Font footerFont=new Font(Font.FontFamily.COURIER,16,Font.BOLD,new BaseColor(0,0,255));

PdfPTable table=new PdfPTable(2);
PdfPTable table2=null;
Paragraph header=new Paragraph();
 boolean newPage=true;
DesignationInterface designation;
int sno=0;
int i=0;
while(i<sz)
{
if(newPage)
{
Image image=Image.getInstance("Stark.png");
Chunk chunk=new Chunk(image,0,-70);
Paragraph img=new Paragraph();
img.add(chunk);
Paragraph p1=new Paragraph("STARK'S FINANCIAL SERVICES",titleFont);
p1.setAlignment(Element.ALIGN_CENTER);
p1.setSpacingAfter(15);
Paragraph p2=new Paragraph("List of Designations",subTitleFont);
p2.setAlignment(Element.ALIGN_CENTER);
Paragraph p3=new Paragraph("Page no: "+String.valueOf(currentPage)+"/"+String.valueOf(nop),subTitleFont);
p3.setAlignment(Element.ALIGN_RIGHT);
p3.setSpacingAfter(13);
PdfPCell cell11=new PdfPCell(new Paragraph("S.no",tableHeaderFont));
cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell12=new PdfPCell(new Paragraph("Designation",tableHeaderFont));
cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(cell11);
table.addCell(cell12);

header.add(img);
header.add(p1);
header.add(p2);
header.add(p3);
header.add(table);
table2=new PdfPTable(2);
newPage=false;
}
designation=data.get(i);
sno++;
PdfPCell cell1=new PdfPCell(new Paragraph(String.valueOf(sno),tableDataFont));
cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
PdfPCell cell2=new PdfPCell(new Paragraph(designation.getTitle(),tableDataFont));
cell2.setHorizontalAlignment(Element.ALIGN_LEFT);

table2.addCell(cell1);
table2.addCell(cell2);
i++;
if(sno%pageSize==0 || sno==sz)
{
Paragraph footer=new Paragraph("Software By : Sourabh Jaiswal",footerFont);
footer.setAlignment(Element.ALIGN_LEFT);
document.open();
document.add(header);
document.add(table2);
document.add(footer);
if(sno<sz)
{
document.newPage();
table=new PdfPTable(2);
header=new Paragraph();
currentPage++;
newPage=true;
}
}
}
document.close();
}catch(Exception e)
{
throw new ModelException(e.getMessage());
}
}
}