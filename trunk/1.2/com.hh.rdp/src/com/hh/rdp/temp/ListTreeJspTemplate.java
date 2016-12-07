package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ListTreeJspTemplate
{
  protected static String nl;
  public static synchronized ListTreeJspTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListTreeJspTemplate result = new ListTreeJspTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<html>" + NL + "<head>" + NL + "<title>数据列表</title>";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tvar iframeId = '";
  protected final String TEXT_5 = "';" + NL + "\tfunction doAdd() {" + NL + "\t\t$('#centerdiv').undisabled();" + NL + "\t\tvar selectNode = $.hh.tree.getSelectNode('tree');" + NL + "\t\tvar iframe = window.frames[iframeId];" + NL + "\t\tiframe.callback = function() {" + NL + "\t\t\t$.hh.tree.refresh('tree');" + NL + "\t\t\t$('#centerdiv').disabled('请选择要编辑的树节点或添加新的数据！');" + NL + "\t\t}" + NL + "\t\tif (selectNode) {" + NL + "\t\t\tiframe.newData({" + NL + "\t\t\t\tnode : selectNode.id" + NL + "\t\t\t});" + NL + "\t\t} else {" + NL + "\t\t\tiframe.newData({});" + NL + "\t\t}" + NL + "\t\treturn;" + NL + "\t\tDialog.open({" + NL + "\t\t\turl : 'jsp-";
  protected final String TEXT_6 = "-";
  protected final String TEXT_7 = "-";
  protected final String TEXT_8 = "Edit'," + NL + "\t\t\tparams : {" + NL + "\t\t\t\tselectNode : selectNode," + NL + "\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t$.hh.tree.refresh('tree');" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doEdit(treeNode) {" + NL + "\t\tDialog.open({" + NL + "\t\t\turl : 'jsp-";
  protected final String TEXT_9 = "-";
  protected final String TEXT_10 = "-";
  protected final String TEXT_11 = "Edit'," + NL + "\t\t\tparams : {" + NL + "\t\t\t\tobject : treeNode," + NL + "\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t$.hh.tree.refresh('tree');" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doDelete(treeNode) {" + NL + "\t\t$.hh.tree.deleteData({" + NL + "\t\t\tpageid : 'tree'," + NL + "\t\t\taction : '";
  protected final String TEXT_12 = "-";
  protected final String TEXT_13 = "-deleteTreeByIds'," + NL + "\t\t\tid : treeNode.id," + NL + "\t\t\tcallback : function(result) {" + NL + "\t\t\t\tif (result.success!=false) {" + NL + "\t\t\t\t\t$('#centerdiv').disabled('请选择要编辑的树节点或添加新的数据！');" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction treeClick(treeNode) {" + NL + "\t\t$('#centerdiv').undisabled();" + NL + "\t\tvar iframe = window.frames[iframeId];" + NL + "\t\tiframe.callback = function(object) {" + NL + "\t\t\ttreeNode.name = object.text;" + NL + "\t\t\t$.hh.tree.updateNode('tree', treeNode);" + NL + "\t\t\t$.hh.tree.getTree('tree').refresh();" + NL + "\t\t}" + NL + "\t\tiframe.findData(treeNode.id);" + NL + "\t}" + NL + "\tfunction init(){" + NL + "\t\t$('#centerdiv').disabled('请选择要编辑的树节点或添加新的数据！');" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"border_layout\">" + NL + "\t\t<div config=\"render : 'west'\">" + NL + "\t\t\t<div xtype=\"toolbar\" config=\"type:'head'\">" + NL + "\t\t\t\t<span xtype=\"button\" config=\"onClick: doAdd ,text:'添加'\"></span> <span" + NL + "\t\t\t\t\txtype=\"button\"" + NL + "\t\t\t\t\tconfig=\"onClick: $.hh.tree.doUp , params:{treeid:'tree',action:'";
  protected final String TEXT_14 = "-";
  protected final String TEXT_15 = "-order'}  , textHidden : true,text:'上移' ,icon : 'hh_up' \"></span>" + NL + "\t\t\t\t<span xtype=\"button\"" + NL + "\t\t\t\t\tconfig=\"onClick: $.hh.tree.doDown , params:{treeid:'tree',action:'";
  protected final String TEXT_16 = "-";
  protected final String TEXT_17 = "-order'} , textHidden : true,text:'下移' ,icon : 'hh_down' \"></span>" + NL + "\t\t\t\t<span xtype=\"button\"" + NL + "\t\t\t\t\tconfig=\"onClick : $.hh.tree.refresh,text : '刷新' ,params: 'tree'  \"></span>" + NL + "\t\t\t</div>" + NL + "\t\t\t<span xtype=\"tree\"" + NL + "\t\t\t\tconfig=\" id:'tree', url:'";
  protected final String TEXT_18 = "-";
  protected final String TEXT_19 = "-queryTreeList' ,remove: doDelete , onClick : treeClick  \"></span>" + NL + "\t\t</div>" + NL + "\t\t<div style=\"overflow: visible;\" id=centerdiv>" + NL + "\t\t\t<iframe id=\"";
  protected final String TEXT_20 = "\" name=\"";
  protected final String TEXT_21 = "\" width=100%" + NL + "\t\t\t\theight=100% frameborder=0 src=\"jsp-";
  protected final String TEXT_22 = "-";
  protected final String TEXT_23 = "-";
  protected final String TEXT_24 = "Edit\"></iframe>" + NL + "\t\t</div>" + NL + "\t</div>" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String className = jetModel.getClassName2();
 	String lowClassName = jetModel.getClassName2().toLowerCase();
 	String modelName = jetModel.getModelName();
 
    
 	String topStr="&lt;%@page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.BaseSystemUtil\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.pk.PrimaryKey\"%&gt;\n";
  	topStr+="&lt;%=BaseSystemUtil.getBaseDoctype()%&gt;\n";
  	
  	String jsStr = "&lt;%=BaseSystemUtil.getBaseJs(\"layout\",\"ztree\", \"ztree_edit\")%&gt;\n";
  	jsStr+="&lt;%String iframeId = PrimaryKey.getUUID();%&gt;\n";
  	
  	String iframeStr = "&lt;%=iframeId%&gt;";
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(topStr);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jsStr);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(iframeStr);
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
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(iframeStr);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(iframeStr);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(lowClassName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
