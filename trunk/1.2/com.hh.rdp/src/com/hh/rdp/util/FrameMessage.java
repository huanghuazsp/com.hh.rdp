package com.hh.rdp.util;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class FrameMessage {
	static public void warning(String msg) {
		MessageBox m = new MessageBox(new Shell(Display.getDefault(),
				SWT.SHELL_TRIM | SWT.APPLICATION_MODAL), SWT.ICON_WARNING);
		m.setText("警告");
		m.setMessage(msg);
		m.open();
	}
	
	static public void error(String msg) {
		MessageBox m = new MessageBox(new Shell(Display.getDefault(),
				SWT.SHELL_TRIM | SWT.APPLICATION_MODAL), SWT.ICON_ERROR);
		m.setText("错误");
		m.setMessage(msg);
		m.open();
	}
	
	static public void error(Exception e) {
		MessageBox m = new MessageBox(new Shell(Display.getDefault(),
				SWT.SHELL_TRIM | SWT.APPLICATION_MODAL), SWT.ICON_ERROR);
		m.setText("错误");
		m.setMessage("异常：" + e.getClass().getName() + "："
				+ e.getMessage());
		m.open();
	}


	static public void info(String msg) {
		MessageBox m = new MessageBox(new Shell(Display.getDefault(),
				SWT.SHELL_TRIM | SWT.APPLICATION_MODAL), SWT.ICON_INFORMATION);
		m.setText("消息");
		m.setMessage(msg);
		m.open();
	}

	static public boolean question(String msg) {
		MessageBox m = new MessageBox(new Shell(Display.getDefault(),
				SWT.SHELL_TRIM | SWT.APPLICATION_MODAL), SWT.ICON_QUESTION
				| SWT.OK | SWT.CANCEL);
		m.setText("选择");
		m.setMessage(msg);
		if (m.open() == SWT.OK)
			return true;
		return false;
	}
}
