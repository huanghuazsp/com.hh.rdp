package com.hh.rdp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.hh.rdp.browser.InitFunction;
import com.hh.rdp.browser.MyBrowser;

public class PreviewView extends ViewPart {
	public MyBrowser myBrowser;

	@Override
	public void createPartControl(Composite parent) {
		OpenPreviewView.viewPart = this;

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 10;
		composite.setLayout(gridLayout);
		myBrowser = new MyBrowser();
//		String pathString = "file://"+MyBrowser.basePath
//				+ "hhcommon/opensource/ckeditor/ckeditor.html";
		
		String pathString = "http://localhost:8080/hhcommon/opensource/ckeditor/ckeditor.html";
		myBrowser.createContents2(pathString, composite);
		new InitFunction(myBrowser.getBrowser(), "InitComplete");
	}

	public MyBrowser getMyBrowser() {
		return myBrowser;
	}

	public void setMyBrowser(MyBrowser myBrowser) {
		this.myBrowser = myBrowser;
	}

	@Override
	public void setFocus() {

	}
}
