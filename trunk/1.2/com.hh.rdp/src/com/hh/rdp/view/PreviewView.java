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
		OpenPreviewView.viewPart=this;
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 10;
		composite.setLayout(gridLayout);
		myBrowser = new MyBrowser();
		myBrowser
				.createContents2(
						"http://localhost:8080/HHSSHE/webapp-desktop-page?params={vsj:%22com.hh.form.MainFormDesigner%22}",
						composite);
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
