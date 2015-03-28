package com.hh.rdp.dm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

import com.hh.rdp.dm.action.AddAction;
import com.hh.rdp.dm.action.CreateSourceAction;
import com.hh.rdp.dm.action.EditAction;
import com.hh.rdp.dm.action.RemoveAction;
import com.hh.rdp.dm.editor.EditorPart;
import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.image.ImageCache;
import com.hh.rdp.util.image.ImageKeys;

public class PageGrid extends FormPage {
	private EditorPart editorPartMain;
	private TreeViewer viewer;
	private StructuredTextEditor sourceEditor;
	private RemoveAction removeAction;
	private CreateSourceAction createSourceAction;

	private AddAction addAction;
	private EditAction editAction;

	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		createTreePage(managedForm);
	}

	private void createTreePage(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.getBody().setLayout(new FillLayout());
		toolkit.decorateFormHeading(form.getForm());
		Section section = toolkit.createSection(form.getBody(),
				Section.NO_TITLE);
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		toolkit.paintBordersFor(client);
		section.setClient(client);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		client.setLayout(layout);
		Tree tree = toolkit.createTree(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 4; // 水平方向跨2列
		tree.setLayoutData(gd);
		viewer = new TreeViewer(tree);
		viewer.getTree().setHeaderVisible(true);
		viewer.setAutoExpandLevel(2);
		TreeColumn column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("中文名称");
		column.setWidth(200);
		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("英文名称");
		column.setWidth(100);
		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("类型");
		column.setWidth(130);

		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("是否可为空");
		column.setWidth(80);

		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("长度");
		column.setWidth(40);

		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText("默认值");
		column.setWidth(100);

		viewer.setLabelProvider(new PageLabelProvider());
		viewer.setContentProvider(new PageContentProvider());
		createButton(toolkit, client);
		createActions();
		createContextMenu();

		this.getSite().setSelectionProvider(viewer);
	}

	public List<Object> getSelectObjectList() {
		Project project = (Project) viewer.getTree().getItem(0).getData();

		List<Table> tableList = project.getChildren();
		for (Table table : tableList) {
			table.setParent(project);
			List<Column> columns = table.getChildren();
			for (Column column : columns) {
				column.setParent(table);
			}
		}

		List<Object> objectList = new ArrayList<Object>();
		TreeItem[] treeItems = viewer.getTree().getSelection();
		for (TreeItem treeItem : treeItems) {
			objectList.add(treeItem.getData());
		}
		return objectList;
	}

	private void createButton(FormToolkit toolkit, Composite client) {

		final Button add = toolkit.createButton(client, "添加", SWT.PUSH);
		add.setImage(ImageCache.getImage(ImageKeys.database_table_add));
		add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				editAction.run();
			}
		});

		Button delete = toolkit.createButton(client, "删除", SWT.PUSH);
		delete.setImage(ImageCache.getImage(ImageKeys.database_delete));
		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				removeAction.doDelete();
			}
		});

		final Button edit = toolkit.createButton(client, "修改", SWT.PUSH);
		edit.setImage(ImageCache.getImage(ImageKeys.database_edit));
		edit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				editAction.run();
			}
		});

		Button source = toolkit.createButton(client, "生成代码", SWT.PUSH);
		source.setImage(ImageCache.getImage(ImageKeys.database_lightning));
		source.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				createSourceAction.run();
			}
		});

	}

	private void createContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager m) {
				PageGrid.this.fillContextMenu(m);
			}
		});
		Tree tree = viewer.getTree();
		Menu menu = menuMgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	private void fillContextMenu(IMenuManager menuMgr) {
		boolean isEmpty = viewer.getSelection().isEmpty();
		removeAction.setEnabled(!isEmpty);
		List<Object> objectList = getSelectObjectList();

		createSourceAction.setEnabled(false);
		if (objectList.size() > 0 && objectList.get(0) instanceof Table) {
			createSourceAction.setEnabled(true);
		}
		editAction.setEnabled(false);
		if (objectList.size() > 0
				&& (objectList.get(0) instanceof Table || objectList.get(0) instanceof Column)) {
			editAction.setEnabled(true);
		}

		menuMgr.add(addAction);
		menuMgr.add(editAction);

		menuMgr.add(createSourceAction);
		menuMgr.add(removeAction);
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void createActions() {
		removeAction = new RemoveAction(this, "删除");
		createSourceAction = new CreateSourceAction(this);
		addAction = new AddAction(this);
		editAction = new EditAction(this);
	}

	public PageGrid(EditorPart editor, StructuredTextEditor sourceEditor) {
		super(editor, "grid", "编辑");
		this.editorPartMain = editor;
		this.sourceEditor = sourceEditor;
	}

	public StructuredTextEditor getSourceEditor() {
		return sourceEditor;
	}

	public void setSourceEditor(StructuredTextEditor sourceEditor) {
		this.sourceEditor = sourceEditor;
	}

	public EditorPart getEditorPartMain() {
		return editorPartMain;
	}

	public void setEditorPartMain(EditorPart editorPartMain) {
		this.editorPartMain = editorPartMain;
	}

}
