package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ListJsTemplate
{
  protected static String nl;
  public static synchronized ListJsTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListJsTemplate result = new ListJsTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " Ext.define('";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = "List', {" + NL + "extend : 'com.hh.global.SimpleGridPanelWindow'," + NL + "action : '";
  protected final String TEXT_5 = "-";
  protected final String TEXT_6 = "-'," + NL + "editPage : '";
  protected final String TEXT_7 = ".";
  protected final String TEXT_8 = ".";
  protected final String TEXT_9 = "Edit'," + NL + "constructor : function(config) {" + NL + "\tthis.config = config || {};" + NL + "\tthis.superclass.constructor.call(this, this.config);" + NL + "}," + NL + "getGridColumns : function() {" + NL + "return [" + NL;
  protected final String TEXT_10 = NL + "{ text : '";
  protected final String TEXT_11 = "', dataIndex : '";
  protected final String TEXT_12 = "',flex : 1},";
  protected final String TEXT_13 = NL + NL + "{dataIndex : 'id', flex : 1,hidden : true}" + NL + "];" + NL + "}," + NL + "getStoreFields : function() { return [\"id\"";
  protected final String TEXT_14 = NL + ",\"";
  protected final String TEXT_15 = "\"";
  protected final String TEXT_16 = NL + "];}" + NL + "});";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(jetModel.getBasePackName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(jetModel.getModelName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(jetModel.getBasePackName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_9);
     List<Column> columnList = jetModel.getColumnList(); 
    	for (Column column : columnList) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append( column.getText());
    stringBuffer.append(TEXT_11);
    stringBuffer.append( column.getName());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    	for (Column column : columnList) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append( column.getName());
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
