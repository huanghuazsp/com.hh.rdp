package com.hh.rdp.dm;

import java.util.UUID;

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

import com.hh.rdp.dm.model.Table;
import com.hh.rdp.model.Column;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.FrameMessage;

public class ColumnEditPage extends Dialog {
	private PageGrid page;
	private Text nameText;
	private Table table;
	private Column column;
	private boolean edit;

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
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("字段名称：");
		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		if (edit) {
			nameText.setText(column.getText());
		}
		return composite;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 120);
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
		page.getEditorPartMain().setPageModified(true);
		// TreeItem treeItem = page.getViewer().getTree().getItem(0);
		// page.getViewer().getTree()
		// .setSelection(treeItem.getItem(treeItem.getItemCount() - 1));
		this.close();
	}

	private void huitian(Column column) {
		column.setId(UUID.randomUUID().toString());
		column.setText(nameText.getText());
		column.setName(nameText.getText());
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
