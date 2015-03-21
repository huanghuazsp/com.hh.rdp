package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;

public class SpringXmlTemplate
{
  protected static String nl;
  public static synchronized SpringXmlTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    SpringXmlTemplate result = new SpringXmlTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<beans xmlns=\"http://www.springframework.org/schema/beans\"" + NL + "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:jee=\"http://www.springframework.org/schema/jee\"" + NL + "\txmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:aop=\"http://www.springframework.org/schema/aop\"" + NL + "\txmlns:p=\"http://www.springframework.org/schema/p\" xmlns:util=\"http://www.springframework.org/schema/util\"" + NL + "\txmlns:tool=\"http://www.springframework.org/schema/tool\" xmlns:context=\"http://www.springframework.org/schema/context\"" + NL + "\txsi:schemaLocation=\"http://www.springframework.org/schema/beans" + NL + "      http://www.springframework.org/schema/beans/spring-beans.xsd" + NL + "      http://www.springframework.org/schema/tx" + NL + "      http://www.springframework.org/schema/tx/spring-tx.xsd" + NL + "      http://www.springframework.org/schema/aop" + NL + "      http://www.springframework.org/schema/aop/spring-aop.xsd" + NL + "      http://www.springframework.org/schema/jee" + NL + "      http://www.springframework.org/schema/jee/spring-jee.xsd" + NL + "      http://www.springframework.org/schema/context" + NL + "      http://www.springframework.org/schema/context/spring-context.xsd" + NL + "      http://www.springframework.org/schema/util" + NL + "      http://www.springframework.org/schema/util/spring-util.xsd" + NL + "      http://www.springframework.org/schema/tool" + NL + "      http://www.springframework.org/schema/tool/spring-tool.xsd\">" + NL + "\t<!-- default-lazy-init=\"true\" -->" + NL + "" + NL + "\t<context:component-scan base-package=\"";
  protected final String TEXT_2 = ".service\"></context:component-scan>" + NL + "" + NL + "</beans>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	Map<String,Object> paramMap =( Map) argument;
 	String packName = Convert.toString(paramMap.get("packName"));
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(packName);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
