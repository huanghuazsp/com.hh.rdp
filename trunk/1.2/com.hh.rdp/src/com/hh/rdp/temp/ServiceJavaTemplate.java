package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ServiceJavaTemplate
{
  protected static String nl;
  public static synchronized ServiceJavaTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServiceJavaTemplate result = new ServiceJavaTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "import com.hh.system.service.impl.BaseService;" + NL + "import org.springframework.stereotype.Service;" + NL + "import ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ";" + NL + "" + NL + "@Service" + NL + "public class ";
  protected final String TEXT_5 = "Service extends BaseService<";
  protected final String TEXT_6 = "> {" + NL + "}";
  protected final String TEXT_7 = NL + " ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(jetModel.getServicePackName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(jetModel.getPackName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(jetModel.getClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
