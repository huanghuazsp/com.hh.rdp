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
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tvar params = BaseUtil.getIframeParams();" + NL + "\tvar width = 600;" + NL + "\tvar height = 450;" + NL + "" + NL + "\tvar objectid = '";
  protected final String TEXT_5 = "';" + NL + "" + NL + "\tfunction save() {" + NL + "\t\t$.hh.validation.check('form', function(formData) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_6 = "-";
  protected final String TEXT_7 = "-save', {" + NL + "\t\t\t\tdata : formData," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\tif (result.success) {" + NL + "\t\t\t\t\t\tparams.callback(formData);" + NL + "\t\t\t\t\t\tDialog.close();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t});" + NL + "\t}" + NL + "" + NL + "\tfunction findData() {" + NL + "\t\tif (objectid) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_8 = "-";
  protected final String TEXT_9 = "-findObjectById', {" + NL + "\t\t\t\tdata : {" + NL + "\t\t\t\t\tid : objectid" + NL + "\t\t\t\t}," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\t$('#form').setValue(result);" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tfunction init() {" + NL + "\t\tfindData();" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"hh_content\">" + NL + "\t\t<form id=\"form\" xtype=\"form\">" + NL + "\t\t\t<span xtype=\"text\" config=\" hidden:true,name : 'id'\"></span>" + NL + "\t\t\t<table xtype=\"form\">" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td xtype=\"label\">test：</td>" + NL + "\t\t\t\t\t<td><span xtype=\"text\" config=\" name : 'test' \"></span></td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t</table>" + NL + "\t\t</form>" + NL + "\t</div>" + NL + "\t<div xtype=\"toolbar\">" + NL + "\t\t<span xtype=\"button\" config=\"text:'保存' , onClick : save \"></span> <span" + NL + "\t\t\txtype=\"button\" config=\"text:'取消' , onClick : Dialog.close \"></span>" + NL + "\t</div>" + NL + "</body>" + NL + "</html>" + NL + "" + NL + " ";
  protected final String TEXT_10 = NL + " ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String className = jetModel.getClassName();
 	String lowClassName = jetModel.getClassName().toLowerCase();
 	String modelName = jetModel.getModelName();
 
    
 	String topStr="&lt;%@page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.BaseSystemUtil\"%&gt;\n";
  	topStr+="&lt;%@page import=\"com.hh.system.util.Convert\"%&gt;\n";
  	topStr+="&lt;%=BaseSystemUtil.getBaseDoctype()%&gt;\n";
  	
  	String jsStr = "&lt;%=BaseSystemUtil.getBaseJs(\"checkform\",\"date\")%&gt;\n";
  	
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
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
