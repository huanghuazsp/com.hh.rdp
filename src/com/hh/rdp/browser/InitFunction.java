package com.hh.rdp.browser;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.hh.rdp.util.AppUtil;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.Json;
import com.hh.rdp.view.OpenPreviewView;

public class InitFunction extends BrowserFunction {

	private Browser browser;

	public InitFunction(Browser browser, String name) {
		// name 为该函数的名字，JavaScript 根据这个名字调用该函数
		super(browser, name);
		this.browser = browser;
	}

	public Object function(Object[] arguments) {
		IProject project = AppUtil.getProject(null);
		if (project==null) {
			FrameMessage.info("请选中一个项目！");
			return null;
		}
		// arguments 为 JavaScript 传递来的参数，包含 Java 端需要的数据
		String content = ((String) arguments[0]);
		List<Map<String, Object>> mapList = Json.toMapList(content);
		OpenPreviewView.setMapList(mapList);
		OpenPreviewView.setFormJsonText(content);
//		new GenerateCodeDialog(browser.getShell(), project).open();
		return content;
	}

}
