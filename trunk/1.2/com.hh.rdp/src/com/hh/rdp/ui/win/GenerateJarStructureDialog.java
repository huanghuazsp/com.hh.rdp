package com.hh.rdp.ui.win;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.temp.SpringXmlTemplate;
import com.hh.rdp.util.AppUtil;
import com.hh.rdp.util.FileUtil;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.WidgetUtil;
import com.hh.rdp.util.XmlFormat;

public class GenerateJarStructureDialog extends Dialog {
	private Text projectNameText;
	private Text basePackText;
	private IProject project;
	File file = null;
	String pomXmlString = "";

	public GenerateJarStructureDialog(Shell shell, IProject project) {
		super(shell);
		this.project = project;
		file = new File(project.getLocation() + "/pom.xml");
		try {
			pomXmlString = FileUtil.readFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		String projectName = project.getName();

		getShell().setText("JAR基础结构生成");

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		composite.setLayout(gridLayout);

		projectNameText = WidgetUtil.createText(composite, "项目名称：");
		projectNameText.setText(projectName);
		basePackText = WidgetUtil.createText(composite, "包路径：");
		basePackText.setText(getBasePack());
		return composite;
	}

	private String getBasePack() {
		return pomXmlString.substring(
				pomXmlString.indexOf("<groupId>") + ("<groupId>".length()),
				pomXmlString.indexOf("</groupId>")).trim();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 200);
	}

	@Override
	protected void okPressed() {
		try {
			AppUtil.createSrcFolder(project, "src/main/java");
//			AppUtil.createSrcFolder(project, "src/main/js");
			AppUtil.createSrcFolder(project, "src/main/resources");
			AppUtil.createSrcFolder(project, "src/main/jsp");

			AppUtil.createPackageFragment(project, "src/main/java",
					basePackText.getText() + ".action");
			AppUtil.createPackageFragment(project, "src/main/java",
					basePackText.getText() + ".bean");
			AppUtil.createPackageFragment(project, "src/main/java",
					basePackText.getText() + ".service.impl");
			AppUtil.createPackageFragment(project, "src/main/java",
					basePackText.getText() + ".util");

//			AppUtil.createPackageFragment(project, "src/main/js", "page."
//					+ basePackText.getText());

			AppUtil.createPackageFragment(project, "src/main/resources",
					"spring");
			SpringXmlTemplate springXmlTemplate = new SpringXmlTemplate();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("packName", basePackText.getText());
			String code = springXmlTemplate.generate(map);
			AppUtil.createCode(project, "src/main/resources", "spring", code,
					projectNameText.getText() + "-spring.xml");

			AppUtil.createPackageFragment(project, "src/main/jsp", "jsp."
					+ basePackText.getText());

			String pomXmlString2 = pomXmlString;

			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			if (pomXmlString2.indexOf("<build>") < 0) {
				StringBuffer stringBuffer = new StringBuffer(pomXmlString2);
				InputStream inputStream = classLoader
						.getResourceAsStream("config/pombuild.txt");
				String str = FileUtil.readInputStream(inputStream);
				stringBuffer.insert(pomXmlString2.indexOf("</properties>")
						+ ("</properties>".length()), str);
				pomXmlString2 = stringBuffer.toString();
			}
			if (pomXmlString2.indexOf("com.hh.system") < 0) {
				StringBuffer stringBuffer = new StringBuffer(pomXmlString2);
				InputStream inputStream = classLoader
						.getResourceAsStream("config/systemdependency.txt");
				String str = FileUtil.readInputStream(inputStream);
				stringBuffer.insert(pomXmlString2.indexOf("<dependencies>")
						+ ("<dependencies>".length()), str);
				pomXmlString2 = stringBuffer.toString();
			}

			String pomxmlnewString = XmlFormat.encodeingXmlStr(pomXmlString2);
			FileUtil.writeToResultFile(file, pomxmlnewString);

			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			FrameMessage.error("异常：" + e.getClass().getName() + "："
					+ e.getMessage());
			e.printStackTrace();
			return;
		}
		this.close();
	}
}
