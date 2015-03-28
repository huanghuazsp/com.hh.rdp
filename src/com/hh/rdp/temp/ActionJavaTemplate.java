package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ActionJavaTemplate
{
  protected static String nl;
  public static synchronized ActionJavaTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ActionJavaTemplate result = new ActionJavaTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import org.springframework.beans.factory.annotation.Autowired;" + NL + "" + NL + "import com.hh.system.util.base.BaseServiceAction;" + NL + "import ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ";" + NL + "import com.hh.system.service.impl.BaseService;" + NL + "import ";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = "Service;" + NL + "" + NL + "@SuppressWarnings(\"serial\")" + NL + "public class Action";
  protected final String TEXT_7 = " extends BaseServiceAction< ";
  protected final String TEXT_8 = " > {" + NL + "\t@Autowired" + NL + "\tprivate ";
  protected final String TEXT_9 = "Service ";
  protected final String TEXT_10 = "Service;" + NL + "\tpublic BaseService<";
  protected final String TEXT_11 = "> getService() {" + NL + "\t\treturn ";
  protected final String TEXT_12 = "Service;" + NL + "\t}" + NL + "}";
  protected final String TEXT_13 = NL + " ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(jetModel.getActionPackName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(jetModel.getPackName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(jetModel.getServicePackName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(jetModel.getClassName().toLowerCase());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(jetModel.getClassName().toLowerCase());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
