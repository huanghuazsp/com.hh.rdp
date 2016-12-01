package com.hh.rdp.dm;

import java.util.UUID;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hh.rdp.dm.model.Table;
import com.hh.rdp.model.Column;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;
import com.hh.rdp.util.WidgetUtil;

public class ColumnEditPage extends Dialog {
	private PageGrid page;
	private Text nameText;
	private Text textText;
	private Table table;
	private Column column;
	private boolean edit;
	
	private Combo typeText;
	private Combo lengthText;

	public ColumnEditPage(PageGrid page, Shell parentShell, Object object) {
		super(parentShell);
		this.page = page;
		if (object instanceof Table) {
			this.table = (Table) object;
			edit = false;
		} else if (object instanceof Column) {
			this.column = (Column) object;
			edit = true;
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		getShell().setText("字段编辑");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		
		textText = WidgetUtil.createText(composite, "注释：");
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("字段名称：");
		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		
		typeText  = WidgetUtil.createCombo(composite, "类型：", new String[]{"String","int","Date","Long","byte[]"});
		
		lengthText  = WidgetUtil.createCombo(composite, "长度：", new String[]{"16","36","64","128","256","512","1024","2048","大字段"});
		
		
		if (edit) {
			typeText.setText(column.getType());
			lengthText.setText(column.getLength());
			nameText.setText(column.getName());
			textText.setText(column.getText());
		}
		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 210);
	}

	@Override
	protected void okPressed() {
		if (!checkName(nameText.getText())) {
			return;
		}
		if (edit) {
			huitian(column);
		}else {
			Column column = new Column();
			huitian(column);
			table.getChildren().add(column);
		}
		page.getViewer().refresh();
		nameText.setText("");
		textText.setText("");
		typeText.setText("String");
		lengthText.setText("256");
		page.getEditorPartMain().setPageModified(true);
		// TreeItem treeItem = page.getViewer().getTree().getItem(0);
		// page.getViewer().getTree()
		// .setSelection(treeItem.getItem(treeItem.getItemCount() - 1));
		this.close();
	}

	private void huitian(Column column) {
		column.setId(UUID.randomUUID().toString());
		column.setText(textText.getText());
		column.setName(nameText.getText());
		column.setLength(lengthText.getText());
		column.setType(typeText.getText());
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
