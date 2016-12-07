package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class EditJspTemplate
{
  protected static String nl;
  public static synchronized EditJspTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    EditJspTemplate result = new EditJspTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<html>" + NL + "<head>" + NL + "<title>数据编辑</title>";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tvar params = $.hh.getIframeParams();" + NL + "\tvar width = 600;" + NL + "\tvar height = 450;" + NL + "" + NL + "\tvar objectid = '";
  protected final String TEXT_5 = "';" + NL + "" + NL + "\tfunction save() {" + NL + "\t\t$.hh.validation.check('form', function(formData) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_6 = "-";
  protected final String TEXT_7 = "-save', {" + NL + "\t\t\t\tdata : formData," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\tif (result.success!=false) {" + NL + "\t\t\t\t\t\tparams.callback(formData);" + NL + "\t\t\t\t\t\tDialog.close();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t});" + NL + "\t}" + NL + "" + NL + "\tfunction findData() {" + NL + "\t\tif (objectid) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_8 = "-";
  protected final String TEXT_9 = "-findObjectById', {" + NL + "\t\t\t\tdata : {" + NL + "\t\t\t\t\tid : objectid" + NL + "\t\t\t\t}," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\t$('#form').setValue(result);" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tfunction init() {" + NL + "\t\tfindData();" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"hh_content\">" + NL + "\t\t<form id=\"form\" xtype=\"form\" class=\"form\">" + NL + "\t\t\t<span xtype=\"text\" config=\" hidden:true,name : 'id'\"></span>" + NL + "\t\t\t<table xtype=\"form\">" + NL + "\t\t\t\t";
  protected final String TEXT_10 = NL + "\t\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t<td xtype=\"label\">";
  protected final String TEXT_12 = "：</td>" + NL + "\t\t\t\t\t\t<td><span xtype=\"text\" config=\" name : '";
  protected final String TEXT_13 = "' \"></span></td>" + NL + "\t\t\t\t\t</tr>" + NL + "\t\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t</table>" + NL + "\t\t</form>" + NL + "\t</div>" + NL + "\t<div xtype=\"toolbar\">" + NL + "\t\t<span xtype=\"button\" config=\"text:'保存' , onClick : save \"></span> <span" + NL + "\t\t\txtype=\"button\" config=\"text:'取消' , onClick : Dialog.close \"></span>" + NL + "\t</div>" + NL + "</body>" + NL + "</html>" + NL + "" + NL + " ";
  protected final String TEXT_15 = NL + " ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String className = jetModel.getClassName2();
 	String lowClassName = jetModel.getClassName2().toLowerCase();
 	String modelName = jetModel.getModelName();
 
    
 	String topStr="&lt;%@page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.BaseSystemUtil\"%&gt;\n";
  	topStr+="&lt;%@page import=\"com.hh.system.util.Convert\"%&gt;\n";
  	topStr+="&lt;%=BaseSystemUtil.getBaseDoctype()%&gt;\n";
  	
  	String jsStr = "&lt;%=BaseSystemUtil.getBaseJs(\"checkform\")%&gt;\n";
  	
  	String objectIdStr = "&lt;%=Convert.toString(request.getParameter(\"id\"))%&gt;";
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(topStr);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jsStr);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(objectIdStr);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
     List<Column> columnList = jetModel.getColumnList(); 
    stringBuffer.append(TEXT_10);
    	for (int i =0;i<columnList.size();i++) {
						    String douhao = ","; 
							if(columnList.size()-1==i){
								douhao="";
							}
							Column column = columnList.get(i);
							String name = column.getName();
							String text = column.getText();
							String length = column.getLength();
							String nameUpper =name.substring(0, 1).toUpperCase()+name.substring(1);
							String databaseColumnName = AppUtil.classNameTodataBaseName(name);
							String type = column.getType();
				
    stringBuffer.append(TEXT_11);
    stringBuffer.append(text);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
