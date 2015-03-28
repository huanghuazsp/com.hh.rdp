package com.hh.rdp.dm;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hh.rdp.dm.model.Column;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.FrameMessage;

public class RemoveAction extends Action {
	private Page page = null;
	private ISelectionChangedListener listener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent e) {
			setEnabled(!e.getSelection().isEmpty());
		}
	};

	public RemoveAction(Page page, String text) {
		super(text, PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setEnabled(false);
		page.getViewer().addSelectionChangedListener(listener);
		ImageDescriptor disableRemoveImage = PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED);
		this.setDisabledImageDescriptor(disableRemoveImage);
		this.page = page;
	}

	public void run() {
		doDelete();
	}

	public void doDelete() {
		TreeViewer viewer = this.page.getViewer();
		TreeItem[] treeItems = viewer.getTree().getSelection();
		Table table = (Table) viewer.getTree().getItem(0).getData();
		if (treeItems.length == 1) {
			String msg = "您确定删除选中的字段吗？";
			if (treeItems[0].getData() instanceof Table) {
				msg = "您确定删除所有的字段吗？";
			}
			if (!FrameMessage.question(msg)) {
				return;
			}
			if (treeItems[0].getData() instanceof Column) {
				Column column = ((Column) treeItems[0].getData());
				for (int i = 0; i < table.getChildren().size(); i++) {
					if (table.getChildren().get(i).equals(column)) {
						table.getChildren().remove(i);
						break;
					}
				}
			} else {
				table.setChildren(new ArrayList<Column>());
			}
			viewer.refresh();
			page.getEditorPartMain().setPageModified(true);
		}
	}
}
