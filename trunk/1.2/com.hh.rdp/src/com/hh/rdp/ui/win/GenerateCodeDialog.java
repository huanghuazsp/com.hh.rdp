package com.hh.rdp.ui.win;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.model.Column;
import com.hh.rdp.model.JetModel;
import com.hh.rdp.temp.ActionJavaTemplate;
import com.hh.rdp.temp.BeanJavaTemplate;
import com.hh.rdp.temp.EditJsTemplate;
import com.hh.rdp.temp.ListJsTemplate;
import com.hh.rdp.temp.ServiceJavaTemplate;
import com.hh.rdp.util.AppUtil;
import com.hh.rdp.util.Convert;
import com.hh.rdp.util.FileUtil;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.WidgetUtil;
import com.hh.rdp.view.OpenPreviewView;

public class GenerateCodeDialog extends Dialog {
	private Text projectNameText;
	private Text basePackText;
	private Text beanNameText;
	private Text beanPackText;
	private Text servicePackText;
	private Text actionPackText;

	// private Text editPackText;
	// private Text listPackText;
	private IProject project;
	File file = null;
	String pomXmlString = "";

	public GenerateCodeDialog(Shell shell, IProject project) {
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

		getShell().setText("代码生成");

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

		beanNameText = WidgetUtil.createText(composite, "类名：");

		beanPackText = WidgetUtil.createText(composite, "beanPack：");
		beanPackText.setText(basePackText.getText() + ".bean");

		servicePackText = WidgetUtil.createText(composite, "servicePack：");
		servicePackText.setText(basePackText.getText() + ".service.impl");

		actionPackText = WidgetUtil.createText(composite, "actionPack：");
		actionPackText.setText(basePackText.getText() + ".action");

		// listPackText = WidgetUtil.createText(composite, "listPage：");
		// listPackText.setText(basePackText.getText() +"."+)
		// editPackText = WidgetUtil.createText(composite, "editPage：");
		// editPackText.setText(string)

		basePackText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				String text = ((Text) arg0.getSource()).getText();
				beanPackText.setText(text + ".bean");
				servicePackText.setText(text + ".service.impl");
				actionPackText.setText(text + ".action");
			}
		});

		return composite;
	}

	private String getBasePack() {
		return pomXmlString.substring(
				pomXmlString.indexOf("<groupId>") + ("<groupId>".length()),
				pomXmlString.indexOf("</groupId>")).trim();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}

	@Override
	protected void okPressed() {
		try {
			List<Map<String, Object>> mapList = OpenPreviewView.getMapList();

			List<Column> columnList = toColumn(mapList);

			String className = beanNameText.getText().substring(0, 1)
					.toUpperCase()
					+ beanNameText.getText().substring(1);

			JetModel jetModel = new JetModel();
			jetModel.setClassName(className);
			jetModel.setMapList(mapList);
			jetModel.setColumnList(columnList);
			jetModel.setPackName(beanPackText.getText());
			jetModel.setTableName(AppUtil.classNameTodataBaseName(jetModel
					.getClassName()));
			jetModel.setExtendClassName("BaseTwoEntity");

			jetModel.setServicePackName(servicePackText.getText());
			jetModel.setActionPackName(actionPackText.getText());
			jetModel.setBasePackName(basePackText.getText());

			jetModel.setModelName(jetModel.getBasePackName().substring(
					jetModel.getBasePackName().lastIndexOf(".") + 1));
			jetModel.setFormJsonText(OpenPreviewView.getFormJsonText());

			BeanJavaTemplate beanJavaTemplate = new BeanJavaTemplate();
			String domainCode = beanJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, "src/main/java",
					beanPackText.getText(), domainCode, className + ".java");

			ActionJavaTemplate actionJavaTemplate = new ActionJavaTemplate();
			String actionCode = actionJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, "src/main/java",
					actionPackText.getText(), actionCode, "Action" + className
							+ ".java");

			ServiceJavaTemplate serviceJavaTemplate = new ServiceJavaTemplate();
			String serviceCode = serviceJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, "src/main/java",
					servicePackText.getText(), serviceCode, className
							+ "Service.java");

			ListJsTemplate listJsTemplate = new ListJsTemplate();
			String listCode = listJsTemplate.generate(jetModel);
			AppUtil.createCode(project, "src/main/js",
					"page." + basePackText.getText() + "." + className,
					listCode, className + "List.class.js");

			EditJsTemplate editJsTemplate = new EditJsTemplate();
			String editCode = editJsTemplate.generate(jetModel);
			AppUtil.createCode(project, "src/main/js",
					"page." + basePackText.getText() + "." + className,
					editCode, className + "Edit.class.js");

			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			FrameMessage.error("异常：" + e.getClass().getName() + "："
					+ e.getMessage());
			e.printStackTrace();
			return;
		}
		this.close();
	}

	private List<Column> toColumn(List<Map<String, Object>> mapList) {
		List<Column> columnList = new ArrayList<Column>();
		for (Map<String, Object> map : mapList) {
			Column column = new Column();
			column.setDefaultValue(Convert.toString(map.get("value")));
			column.setLength((Convert.toLong(map.get("maxLength")) * 2) + "");
			column.setName(Convert.toString(map.get("name")));
			column.setText(Convert.toString(map.get("fieldLabel")));

			String xtype = Convert.toString(map.get("xtype"));

			if ("widgetDateTimer".equals(xtype)
					|| "widgetDateField".equals(xtype)
					|| "datefield".equals(xtype)) {
				column.setType("Date");
			} else if ("numberfield".equals(xtype)) {
				column.setType("int");
			} else if ("htmleditor".equals(xtype)) {
				column.setType("String");
				column.setLob(true);
			} else {
				column.setType("String");
			}
			
			if (Convert.toLong(column.getLength())>4000) {
				column.setLob(true);
			}

			columnList.add(column);
		}
		return columnList;
	}
}
