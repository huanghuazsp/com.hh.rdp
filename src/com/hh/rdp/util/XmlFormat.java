package com.hh.rdp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFormat {
	public static String encodeingXmlStr(String xmlMsg) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document _document = reader.read(new StringReader(xmlMsg));
		String paramXML = "";
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(_document);
			writer.flush();
			writer.close();
			paramXML = out.toString(format.getEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paramXML;
	}
}
