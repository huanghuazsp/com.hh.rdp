package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ListJspTemplate
{
  protected static String nl;
  public static synchronized ListJspTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListJspTemplate result = new ListJspTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<html>" + NL + "<head>" + NL + "<title>数据列表</title>";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tfunction doDelete() {" + NL + "\t\t$.hh.pagelist.deleteData({" + NL + "\t\t\tpageid : 'pagelist'," + NL + "\t\t\taction : 'test-";
  protected final String TEXT_5 = "-deleteByIds'" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doAdd() {" + NL + "\t\tDialog.open({" + NL + "\t\t\turl : 'jsp-";
  protected final String TEXT_6 = "-";
  protected final String TEXT_7 = "-";
  protected final String TEXT_8 = "Edit'," + NL + "\t\t\tparams : {" + NL + "\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t$(\"#pagelist\").loadData();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doEdit() {" + NL + "\t\t$.hh.pagelist.callRow(\"pagelist\", function(row) {" + NL + "\t\t\tDialog.open({" + NL + "\t\t\t\turl : 'jsp-";
  protected final String TEXT_9 = "-";
  protected final String TEXT_10 = "-";
  protected final String TEXT_11 = "Edit'," + NL + "\t\t\t\turlParams : {" + NL + "\t\t\t\t\tid : row.id" + NL + "\t\t\t\t}," + NL + "\t\t\t\tparams : {" + NL + "\t\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t\t$(\"#pagelist\").loadData();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doQuery() {" + NL + "\t\t$('#pagelist').loadData({" + NL + "\t\t\tparams : $('#queryForm').getValue()" + NL + "\t\t});" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"toolbar\" config=\"type:'head'\">" + NL + "\t\t<span xtype=\"button\" config=\"onClick:doAdd,text:'添加' , itype :'add' \"></span>" + NL + "\t\t<span xtype=\"button\"" + NL + "\t\t\tconfig=\"onClick:doEdit,text:'修改' , itype :'edit' \"></span> <span" + NL + "\t\t\txtype=\"button\" config=\"onClick:doDelete,text:'删除' , itype :'delete' \"></span>" + NL + "\t\t<!--  <span" + NL + "\t\t\txtype=\"button\" config=\"onClick: doQuery ,text:'查询' , itype :'query' \"></span> <span" + NL + "\t\t\txtype=\"button\"" + NL + "\t\t\tconfig=\"onClick: $.hh.pagelist.doUp , params:{ pageid :'pagelist',action:'test-Test-order'}  ,  icon : 'hh_up' \"></span>" + NL + "\t\t<span xtype=\"button\"" + NL + "\t\t\tconfig=\"onClick: $.hh.pagelist.doDown , params:{ pageid :'pagelist',action:'test-Test-order'} , icon : 'hh_down' \"></span> -->" + NL + "\t</div>" + NL + "\t<!-- <table xtype=\"form\" id=\"queryForm\" style=\"width:600px;\">" + NL + "\t\t<tr>" + NL + "\t\t\t<td xtype=\"label\">test：</td>" + NL + "\t\t\t<td><span xtype=\"text\" config=\" name : 'test'\"></span></td>" + NL + "\t\t</tr>" + NL + "\t</table> -->" + NL + "\t<div id=\"pagelist\" xtype=\"pagelist\"" + NL + "\t\tconfig=\" url: '";
  protected final String TEXT_12 = "-";
  protected final String TEXT_13 = "-queryPagingData' ,column : [" + NL + "\t\t{" + NL + "\t\t\tname : 'test' ," + NL + "\t\t\ttext : 'test'" + NL + "\t\t}" + NL + "\t]\">" + NL + "\t</div>" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String className = jetModel.getClassName();
 	String lowClassName = jetModel.getClassName().toLowerCase();
 	String modelName = jetModel.getModelName();
 
    
 	String topStr="&lt;%@page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.BaseSystemUtil\"%&gt;\n";
  	topStr+="&lt;%=BaseSystemUtil.getBaseDoctype()%&gt;\n";
  	
  	String jsStr = "&lt;%=BaseSystemUtil.getBaseJs()%&gt;\n";
  	
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(topStr);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jsStr);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(lowClassName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(lowClassName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
