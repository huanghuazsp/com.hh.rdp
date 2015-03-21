package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class EditJsTemplate
{
  protected static String nl;
  public static synchronized EditJsTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    EditJsTemplate result = new EditJsTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "Ext.define('";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = "Edit', {" + NL + "extend : 'com.hh.global.SimpleFormPanelWindow'," + NL + "action : '";
  protected final String TEXT_5 = "-";
  protected final String TEXT_6 = "-'," + NL + "title : '";
  protected final String TEXT_7 = "'," + NL + "width : 600," + NL + "height : 400," + NL + "constructor : function(config) {" + NL + "this.config = config || {};" + NL + "this.superclass.constructor.call(this, this.config);" + NL + "}," + NL + "getFormItems : function() {" + NL + "var formItesmJson = ";
  protected final String TEXT_8 = ";" + NL + "formItesmJson.push({" + NL + "\t\t\tname : 'id'," + NL + "\t\t\thidden : true" + NL + "\t\t});" + NL + "return  formItesmJson;" + NL + "}" + NL + "});";

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
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(jetModel.getFormJsonText());
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
