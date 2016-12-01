package com.hh.rdp.dm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
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
import org.eclipse.ui.part.FileEditorInput;

import com.hh.rdp.dm.model.Table;
import com.hh.rdp.model.Column;
import com.hh.rdp.model.JetModel;
import com.hh.rdp.temp.ActionJavaTemplate;
import com.hh.rdp.temp.BeanJavaTemplate;
import com.hh.rdp.temp.EditJspTemplate;
import com.hh.rdp.temp.EditTreeJspTemplate;
import com.hh.rdp.temp.ListJspTemplate;
import com.hh.rdp.temp.ListTreeJspTemplate;
import com.hh.rdp.temp.ServiceJavaTemplate;
import com.hh.rdp.util.AppUtil;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.StaticVar;
import com.hh.rdp.util.WidgetUtil;
import com.hh.rdp.util.image.ImageCache;
import com.hh.rdp.util.image.ImageKeys;
import com.hh.rdp.util.widget.Combox;

public class CreateSourceDialog extends Dialog {
	private PageGrid page;
	private Text basePackageText;
	private Text beanPackageText;
	private Text servicePackageText;
	private Text actionPackageText;
	private Text listPagePackageText;
	private Text editPagePackageText;
	private Text classNameText;
	private Combox templateCombox;

	private Table table;

	public CreateSourceDialog(PageGrid page, Shell parentShell, Table table) {
		super(parentShell);
		this.page = page;
		this.table = table;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("一键生成代码");
		getShell().setImage(ImageCache.getImage(ImageKeys.database_lightning));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		GridData baseGridData = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		WidgetUtil.createLabel(composite, "BasePackage：");
		basePackageText = new Text(composite, SWT.BORDER);
		basePackageText.setLayoutData(baseGridData);

		basePackageText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				String text = ((Text) arg0.getSource()).getText();
				beanPackageText.setText(text + ".bean");
				servicePackageText.setText(text + ".service.impl");
				actionPackageText.setText(text + ".action");
				listPagePackageText.setText("jsp." + text + "."
						+ classNameText.getText().toLowerCase());
				editPagePackageText.setText("jsp." + text + "."
						+ classNameText.getText().toLowerCase());
			}
		});

		WidgetUtil.createLabel(composite, "beanPackage：");
		beanPackageText = new Text(composite, SWT.BORDER);
		beanPackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel(composite, "servicePackage：");
		servicePackageText = new Text(composite, SWT.BORDER);
		servicePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel(composite, "ServiceRestPackage：");
		actionPackageText = new Text(composite, SWT.BORDER);
		actionPackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel(composite, "ListPagePackage：");
		listPagePackageText = new Text(composite, SWT.BORDER);
		listPagePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel(composite, "EditPagePackage：");
		editPagePackageText = new Text(composite, SWT.BORDER);
		editPagePackageText.setLayoutData(baseGridData);

		WidgetUtil.createLabel(composite, "BEAN类名：");
		classNameText = new Text(composite, SWT.BORDER);
		classNameText.setLayoutData(baseGridData);
		classNameText.setText(table.getName().substring(0, 1).toUpperCase()+table.getName().substring(1));
		
		
		Map<String, String> mbMap = new HashMap<String, String>();
		mbMap.put("list", "列表");
		mbMap.put("tree", "树");
		templateCombox = WidgetUtil.createCombox(composite, "模板：", mbMap);
		

		FileEditorInput fileEditorInput = (FileEditorInput) page
				.getEditorPartMain().getEditorInput();
		IFile file = fileEditorInput.getFile();
		String fileStr = file.getFullPath().toString();
		String packageName = fileStr.substring(
				fileStr.indexOf(StaticVar.JAVA_SOURCE_FOLDER)
						+ StaticVar.JAVA_SOURCE_FOLDER.length() + 1).replace(
				page.getEditorPartMain().getSourceXmlEditorPart().getTitle(),
				"");
		packageName = packageName.substring(0, packageName.length() - 1);
		int index = findCharIndex(packageName, "/", 3);
		if (index != -1) {
			packageName = packageName.substring(0, index);
		}

		basePackageText.setText(packageName.replaceAll("/", "."));

		return composite;
	}

	private int findCharIndex(String name, String cr, int index) {
		int a = 0;
		for (int i = 0; i < name.length(); i++) {
			String charStr = name.substring(i, i + 1);
			if (charStr.equals(cr)) {
				a++;
			}
			if (a == index) {
				return i;
			}
		}
		return -1;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(540, 330);
	}

	@Override
	protected void okPressed() {
		String messageStr = "";
		List<Column> columnList = table.getChildren();
		for (Column column : columnList) {
			if (Check.isEmpty(column.getName())) {
				messageStr += "（" + column.getText() + "）名称不能为空!!\n";
			}
		}
		if (Check.isNoEmpty(messageStr)) {
			FrameMessage.info(messageStr);
			return;
		}
		IProject project = AppUtil.getProject(page.getEditorPartMain());
		IJavaProject javaPoject = JavaCore.create(project);
		IPackageFragmentRoot packageFragmentRoot;
		try {

			String className = classNameText.getText();
			String beanPackage = beanPackageText.getText();
			String servicePackage = servicePackageText.getText();
			String actionPackage = actionPackageText.getText();
			String basePackage = basePackageText.getText();
			String template = templateCombox.getValue();

			JetModel jetModel = new JetModel();
			jetModel.setTableText(table.getText());
			jetModel.setServicePackName(servicePackageText.getText());
			jetModel.setActionPackName(actionPackageText.getText());
			jetModel.setBasePackName(basePackageText.getText());
			jetModel.setModelName(jetModel.getBasePackName().substring(
					jetModel.getBasePackName().lastIndexOf(".") + 1));
			jetModel.setClassName2(className);
			jetModel.setClassName(jetModel.getModelName().substring(0, 1).toUpperCase()+
					jetModel.getModelName().substring(1).toLowerCase()+className);
			className=jetModel.getClassName();
			jetModel.setColumnList(columnList);
			jetModel.setPackName(beanPackageText.getText());
			jetModel.setTableName(AppUtil.classNameTodataBaseName(jetModel
					.getClassName()));
			if ("tree".equals(template)) {
				jetModel.setExtendClassName("BaseTreeNodeEntity<"+jetModel.getClassName()+">");
			}else {
				jetModel.setExtendClassName("BaseTwoEntity");
			}
			


			BeanJavaTemplate beanJavaTemplate = new BeanJavaTemplate();
			String domainCode = beanJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, StaticVar.JAVA_SOURCE_FOLDER,
					beanPackage, domainCode, className + ".java");

			ActionJavaTemplate actionJavaTemplate = new ActionJavaTemplate();
			String actionCode = actionJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, StaticVar.JAVA_SOURCE_FOLDER,
					actionPackage, actionCode, "Action" + jetModel.getClassName2() + ".java");

			ServiceJavaTemplate serviceJavaTemplate = new ServiceJavaTemplate();
			String serviceCode = serviceJavaTemplate.generate(jetModel);
			AppUtil.createCode(project, StaticVar.JAVA_SOURCE_FOLDER,
					servicePackage, serviceCode, className + "Service.java");

			
			if ("tree".equals(template)) {
				ListTreeJspTemplate listJsTemplate = new ListTreeJspTemplate();
				String listCode = listJsTemplate.generate(jetModel).replace("&lt;", "<").replace("&gt;", ">");
				AppUtil.createCode(project, StaticVar.JSP_PAGE_SOURCE_FOLDER, "jsp."
						+ basePackage + "." + jetModel.getClassName2().toLowerCase(), listCode, jetModel.getClassName2()
						+ "List.jsp");
				EditTreeJspTemplate editJsTemplate = new EditTreeJspTemplate();
				String editCode = editJsTemplate.generate(jetModel).replace("&lt;", "<").replace("&gt;", ">");
				AppUtil.createCode(project, StaticVar.JSP_PAGE_SOURCE_FOLDER, "jsp."
						+ basePackage + "." + jetModel.getClassName2().toLowerCase(), editCode, jetModel.getClassName2()
						+ "Edit.jsp");
			}else {
				ListJspTemplate listJsTemplate = new ListJspTemplate();
				String listCode = listJsTemplate.generate(jetModel).replace("&lt;", "<").replace("&gt;", ">");
				AppUtil.createCode(project, StaticVar.JSP_PAGE_SOURCE_FOLDER, "jsp."
						+ basePackage + "." + jetModel.getClassName2().toLowerCase(), listCode, jetModel.getClassName2()
						+ "List.jsp");
				EditJspTemplate editJsTemplate = new EditJspTemplate();
				String editCode = editJsTemplate.generate(jetModel).replace("&lt;", "<").replace("&gt;", ">");
				AppUtil.createCode(project, StaticVar.JSP_PAGE_SOURCE_FOLDER, "jsp."
						+ basePackage + "." + jetModel.getClassName2().toLowerCase(), editCode, jetModel.getClassName2()
						+ "Edit.jsp");
			}

			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		this.close();
	}

	public boolean checkName(String name) {
		boolean checkResult = true;
		if (Check.isEmpty(name)) {
			FrameMessage.info("字段名不能为空！");
			checkResult = false;
		}
		return checkResult;
	}
}
