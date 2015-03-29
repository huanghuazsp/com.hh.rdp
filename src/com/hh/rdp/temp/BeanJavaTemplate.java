package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class BeanJavaTemplate
{
  protected static String nl;
  public static synchronized BeanJavaTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BeanJavaTemplate result = new BeanJavaTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " package ";
  protected final String TEXT_2 = ";" + NL + "import java.util.Date;" + NL + "import javax.persistence.Entity;" + NL + "import javax.persistence.Table;" + NL + "import javax.persistence.Column;" + NL + "import javax.persistence.Temporal;" + NL + "import javax.persistence.TemporalType;" + NL + "import javax.persistence.Transient;" + NL + "import javax.persistence.Lob;" + NL + "import com.hh.hibernate.util.base.*;" + NL + "import com.hh.hibernate.dao.inf.Order;" + NL + "@Order" + NL + "@SuppressWarnings(\"serial\")" + NL + "@Entity" + NL + "@Table(name=\"";
  protected final String TEXT_3 = "-";
  protected final String TEXT_4 = "\")" + NL + "public class ";
  protected final String TEXT_5 = "  extends ";
  protected final String TEXT_6 = "{";
  protected final String TEXT_7 = NL + "\t//";
  protected final String TEXT_8 = NL + "\tprivate ";
  protected final String TEXT_9 = " ";
  protected final String TEXT_10 = ";" + NL + "\t";
  protected final String TEXT_11 = NL + "\t";
  protected final String TEXT_12 = NL + "\tpublic ";
  protected final String TEXT_13 = " get";
  protected final String TEXT_14 = "() {" + NL + "\t\treturn ";
  protected final String TEXT_15 = ";" + NL + "\t}" + NL + "\tpublic void set";
  protected final String TEXT_16 = "(";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = ") {" + NL + "\t\tthis.";
  protected final String TEXT_19 = " = ";
  protected final String TEXT_20 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_21 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String modelName = jetModel.getModelName();
 	String upModelName = modelName.toUpperCase();
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(jetModel.getPackName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(upModelName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jetModel.getTableName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(jetModel.getExtendClassName());
    stringBuffer.append(TEXT_6);
     List<Column> columnList = jetModel.getColumnList(); 
    	for (Column column : columnList) {
			String name = column.getName();
			String length = column.getLength();
			String nameUpper =name.substring(0, 1).toUpperCase()
			+name.substring(1);
			String databaseColumnName = AppUtil.classNameTodataBaseName(name);
			String type = column.getType();

    stringBuffer.append(TEXT_7);
    stringBuffer.append(column.getText());
    stringBuffer.append(TEXT_8);
    stringBuffer.append("boolean".equals(type)?"int" : type);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_10);
    
		String columnStr = "";
		if(column.isLob()){
			columnStr="@Lob\n\t";
		}
		if("Date".equals(type)){
			columnStr="@Temporal(TemporalType.TIMESTAMP)\n\t"
			+"@Column(name=\""+databaseColumnName+"\",length = 7";
		}else{
			columnStr+="@Column(name=\""+databaseColumnName+"\",";
			if(Check.isNoEmpty(length)){
				columnStr+=" length = "+length;
			}
		}
		if(columnStr.endsWith(",")){
			columnStr=columnStr.substring(0,columnStr.length()-1);
		}
		columnStr+=")";
	
    stringBuffer.append(TEXT_11);
    stringBuffer.append(columnStr);
    stringBuffer.append(TEXT_12);
    stringBuffer.append( type);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(nameUpper);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(nameUpper);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
