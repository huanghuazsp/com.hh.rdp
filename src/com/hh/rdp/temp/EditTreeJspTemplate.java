package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class EditTreeJspTemplate
{
  protected static String nl;
  public static synchronized EditTreeJspTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    EditTreeJspTemplate result = new EditTreeJspTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<html>" + NL + "<head>" + NL + "<title>数据编辑</title>";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tvar params = BaseUtil.getIframeParams();" + NL + "\tvar width = 600;" + NL + "\tvar height = 450;" + NL + "\tvar objectid = '";
  protected final String TEXT_5 = "';" + NL + "" + NL + "\tfunction callback() {" + NL + "\t}" + NL + "\tfunction save() {" + NL + "\t\t$.hh.validation.check('form', function(formData) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_6 = "-";
  protected final String TEXT_7 = "-saveTree', {" + NL + "\t\t\t\tdata : formData," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\tif (result.success) {" + NL + "\t\t\t\t\t\tcallback(formData);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t});" + NL + "\t}" + NL + "" + NL + "\tfunction findData(objid) {" + NL + "\t\tif (objid) {" + NL + "\t\t\tRequest.request('";
  protected final String TEXT_8 = "-";
  protected final String TEXT_9 = "-findObjectById', {" + NL + "\t\t\t\tdata : {" + NL + "\t\t\t\t\tid : objid" + NL + "\t\t\t\t}," + NL + "\t\t\t\tcallback : function(result) {" + NL + "\t\t\t\t\t$('#form').setValue(result);" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tfunction newData(params) {" + NL + "\t\tparams.expanded=0;" + NL + "\t\tparams.leaf=0;" + NL + "\t\t$('#form').setValue(params);" + NL + "\t}" + NL + "" + NL + "\tfunction init() {" + NL + "" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"hh_content\">" + NL + "\t\t<form id=\"form\" xtype=\"form\">" + NL + "\t\t\t<span xtype=\"text\" config=\" hidden:true,name : 'id'\"></span>" + NL + "\t\t\t<table xtype=\"form\" width=80% align=center>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td xtype=\"label\">名称：</td>" + NL + "\t\t\t\t\t<td><span xtype=\"text\" config=\" name : 'text',required :true\"></span></td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td xtype=\"label\">父节点：</td>" + NL + "\t\t\t\t\t<td><span id=\"node_span\" xtype=\"selectTree\"" + NL + "\t\t\t\t\t\tconfig=\"name: 'node' , findTextAction : '";
  protected final String TEXT_10 = "-";
  protected final String TEXT_11 = "-findObjectById' , url : '";
  protected final String TEXT_12 = "-";
  protected final String TEXT_13 = "-queryTreeList' \"></span>" + NL + "\t\t\t\t\t</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td xtype=\"label\">类型：</td>" + NL + "\t\t\t\t\t<td><span xtype=\"radio\"" + NL + "\t\t\t\t\t\tconfig=\"name: 'leaf' ,defaultValue : 0,  data :[{id:1,text:'子节点'},{id:0,text:'父节点'}]\"></span></td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td xtype=\"label\">是否展开：</td>" + NL + "\t\t\t\t\t<td><span xtype=\"radio\"" + NL + "\t\t\t\t\t\tconfig=\"name: 'expanded' ,defaultValue : 0,  data :[{id:1,text:'是'},{id:0,text:'否'}]\"></span></td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t<td xtype=\"label\">";
  protected final String TEXT_16 = "：</td>" + NL + "\t\t\t\t\t\t<td><span xtype=\"text\" config=\" name : '";
  protected final String TEXT_17 = "' \"></span></td>" + NL + "\t\t\t\t\t</tr>" + NL + "\t\t\t\t";
  protected final String TEXT_18 = NL + "\t\t\t</table>" + NL + "\t\t</form>" + NL + "\t</div>" + NL + "\t<div xtype=\"toolbar\">" + NL + "\t\t<span xtype=\"button\" config=\"text:'保存' , onClick : save \"></span>" + NL + "\t</div>" + NL + "</body>" + NL + "</html>";

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
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_13);
     List<Column> columnList = jetModel.getColumnList(); 
    stringBuffer.append(TEXT_14);
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
				
    stringBuffer.append(TEXT_15);
    stringBuffer.append(text);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
