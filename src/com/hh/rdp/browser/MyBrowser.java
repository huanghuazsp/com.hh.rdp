package com.hh.rdp.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class MyBrowser {
	private Label throbber;

	private Button button;

	private Combo url;

	private Button button_4;

	private Button button_3;

	private Browser browser;

	// private Browser browser2;

	private Label status;

	private Button button_2;

	private Button button_1;

	private static final String AT_REST = "准备";

	private String[] urlList = new String[256];

	int urlListItemCount = 0;

	public void run(String location) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("浏览器");
		createContents(shell, location);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public void createContents(Shell shell, String location) {
		shell.setLayout(new FillLayout());
		Composite controls = new Composite(shell, SWT.NONE);
		createContents2(location, controls);
	}

	public void createContents2(String location, Composite controls) {
		GridLayout gridLayout = new GridLayout();// 创建网格布局对象
		gridLayout.numColumns = 8; // 设置网格布局列数为8
		controls.setLayout(gridLayout);

		button_1 = new Button(controls, SWT.PUSH);
		button_1.setText("后退");
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.back();
			}
		});
		button_2 = new Button(controls, SWT.PUSH);
		button_2.setText("前进");
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.forward();
			}
		});

		button_3 = new Button(controls, SWT.PUSH);
		button_3.setText("刷新");
		button_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.refresh();
			}
		});

		button_4 = new Button(controls, SWT.PUSH);
		button_4.setText("停止");
		button_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.stop();
			}
		});

		url = new Combo(controls, SWT.ARROW_DOWN);
		url.setSize(600, url.getSize().y);
		url.setFocus();
		
		GridData urlGridData = new GridData();
		urlGridData.horizontalAlignment = GridData.FILL;
		urlGridData.grabExcessHorizontalSpace = true;
		url.setLayoutData(urlGridData);

		button = new Button(controls, SWT.PUSH);
		button.setText("跳转");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String urlStr = url.getText();
				int flag = 1;
				browser.setUrl(urlStr);
				for (int i = 0; i < urlListItemCount; i++) {
					if (urlList[i].equals(urlStr)) {
						flag = 0;
					}
				}
				if (flag == 1) {
					urlList[urlListItemCount] = urlStr;
					url.add(urlStr);
					urlListItemCount++;
				}
			}
		});

		throbber = new Label(controls, SWT.NONE);
		throbber.setText(AT_REST);

		GridData gridData = new GridData();
		gridData.horizontalSpan = 8; // 跨8列
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL;// 水平方向充满
		gridData.grabExcessHorizontalSpace = true;// 抢占水平方向额外空间
	     System.setProperty("org.eclipse.swt.browser.XULRunnerPath", "D:\\kaifa\\xulrunner"); 
		browser = new Browser(controls,  SWT.MOZILLA);
		browser.setLayoutData(gridData);
		browser.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent arg0) {
				String urlStr = arg0.browser.getUrl();
				int flag = 1;
				browser.setVisible(false);
				// browser2.setVisible(true);
				// arg0.browser = browser2;
				for (int i = 0; i < urlListItemCount; i++) {
					if (urlList[i].equals(urlStr)) {
						flag = 0;
					}
				}
				if (flag == 1) {
					urlList[urlListItemCount] = urlStr;
					url.add(urlStr);
					url.setText(urlStr);
					urlListItemCount++;
				}
			}
		});

		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 8; // 跨8列
		gridData2.horizontalAlignment = GridData.FILL;// 水平方向充满
		gridData2.grabExcessHorizontalSpace = true;// 抢占水平方向额外空间
		status = new Label(controls, SWT.NONE);
		status.setLayoutData(gridData2);
		// browser2 = new Browser(controls, SWT.BORDER);
		// browser2.setLayoutData(gridData);
		// browser2.addOpenWindowListener(new OpenWindowListener() {
		// public void open(WindowEvent arg0) {
		// String urlStr = arg0.browser.getUrl();
		// int flag = 1;
		// browser2.setVisible(false);
		// browser.setVisible(true);
		// arg0.browser = browser;
		// for (int i = 0; i < urlListItemCount; i++) {
		// if (urlList[i].equals(urlStr)) {
		// flag = 0;
		// }
		// }
		// if (flag == 1) {
		// urlList[urlListItemCount] = urlStr;
		// url.add(urlStr);
		// url.setText(urlStr);
		// urlListItemCount++;
		// }
		// }
		// });

		browser.addCloseWindowListener(new AdvancedCloseWindowListener());
		browser.addLocationListener(new AdvancedLocationListener(url));
		browser.addProgressListener(new AdvancedProgressListener(throbber));
		browser.addStatusTextListener(new AdvancedStatusTextListener(status));

		if (location != null) {
			browser.setUrl(location);
		}
	}

	class AdvancedCloseWindowListener implements CloseWindowListener {
		public void close(WindowEvent event) {
			((Browser) event.widget).getShell().close();
		}
	}

	class AdvancedLocationListener implements LocationListener {

		private Combo location;

		public AdvancedLocationListener(Combo text) {

			location = text;
		}

		public void changing(LocationEvent event) {
			// Show the location that's loading

			location.setText("Loading " + event.location + "...");
		}

		public void changed(LocationEvent event) {
			location.setText(event.location);
		}
	}

	class AdvancedProgressListener implements ProgressListener {

		private Label progress;

		public AdvancedProgressListener(Label label) {
			progress = label;
		}

		public void changed(ProgressEvent event) {
			if (event.total != 0) {
				int percent = (int) (event.current / event.total);
				progress.setText(percent + "%");
			} else {
				progress.setText("完成");
			}
		}

		public void completed(ProgressEvent event) {
			progress.setText(AT_REST);
		}
	}

	class AdvancedStatusTextListener implements StatusTextListener {

		private Label status;

		public AdvancedStatusTextListener(Label label) {
			status = label;
		}

		public void changed(StatusTextEvent event) {

			status.setText(event.text);
		}
	}

	public static void main(String[] args) {
		new MyBrowser().run(args.length == 0 ? null : args[0]);
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

}