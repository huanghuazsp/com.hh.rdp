package com.hh.rdp.dm.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

import com.hh.rdp.dm.PageGrid;
import com.hh.rdp.dm.StaticVar;
import com.hh.rdp.dm.model.Project;
import com.hh.rdp.dm.model.Table;
import com.hh.rdp.util.Check;
import com.hh.rdp.util.XmlFormat;

public class EditorPart extends FormEditor implements
		ITabbedPropertySheetPageContributor {

	private PageGrid page = null;
	private StructuredTextEditor editor;
	private boolean isPageModified;

	public IEditorPart getSourceXmlEditorPart() {
		return getEditor(1);
	}

	@Override
	public String getPartName() {
		String title = null;
		if (getEditorInput() != null) {
			title = getEditorInput().getName();
		}
		if (title == null) {
			title = getPartName();
		}
		return title;
	}

	void createPage0() {
		try {
			int index = addPage(editor, getEditorInput());
			setPageText(index, "source");
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}

	private List<Project> getContentUserList2() {
		IDocumentProvider documentProvider = editor.getDocumentProvider();
		String text = documentProvider.getDocument(getEditorInput()).get();
		try {
			if (Check.isEmpty(text)) {
				text = "<?xml version=\"1.0\" encoding=\"utf-8\"?><project name=\""
						+ (getEditor(1).getTitle()
								.replace(StaticVar.ending, ""))
						+ "\" text=\""
						+ (getEditor(1).getTitle()
								.replace(StaticVar.ending, ""))
						+ "\" id=\""
						+ UUID.randomUUID().toString() + "\"></project>";
				documentProvider.getDocument(getEditorInput()).set(text);
			}
			InputStream inputStream = new ByteArrayInputStream(
					text.getBytes("UTF-8"));
			try {
				JAXBContext context = JAXBContext.newInstance(Project.class);
				Unmarshaller um = context.createUnmarshaller();
				Project project = (Project) um.unmarshal(inputStream);
				List<Project> projectList = new ArrayList<Project>();
				projectList.add(project);
				return projectList;
			} catch (JAXBException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void dispose() {
		super.dispose();
	}

	public void doSave(IProgressMonitor monitor) {
		if (this.isPageModified) {
			refreshSourcePage();
		}
		getEditor(1).doSave(monitor);
		this.setPageModified(false);
	}

	public void doSaveAs() {
		if (this.isPageModified) {
			refreshSourcePage();
		}
		IEditorPart editor = getEditor(1);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
		this.setPageModified(false);
	}

	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(1), marker);
	}

	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}

	public boolean isSaveAsAllowed() {
		return true;
	}

	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 0) {
			if (this.page.getViewer() != null) {
				if (!getContentUserList2().equals(
						this.page.getViewer().getInput())) {
					this.page.getViewer().setInput(getContentUserList2());
//					TreeItem treeItem = this.page.getViewer().getTree()
//							.getItem(0);
//					this.page.getViewer().getTree().setSelection(treeItem);
				}
			}
		} else if (newPageIndex == 1) {
			if (this.isPageModified) {
				refreshSourcePage();
			}
		}
	}

	public void refreshSourcePage() {
		Project project = (Project) page.getViewer().getTree().getItem(0).getData();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			JAXBContext context = JAXBContext.newInstance(Project.class);
			Marshaller m = context.createMarshaller();
			IDocumentProvider documentProvider = editor.getDocumentProvider();
			m.marshal(project, outputStream);
			documentProvider.getDocument(getEditorInput()).set(
					XmlFormat.encodeingXmlStr(outputStream.toString(StaticVar.encoding)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void addPages() {
		try {
			editor = new StructuredTextEditor();
			this.page = new PageGrid(this, editor);
			addPage(this.page);
			createPage0();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isDirty() {
		return isPageModified || super.isDirty();
	}

	public boolean isPageModified() {
		return isPageModified;
	}

	public void setPageModified(boolean isPageModified) {
		this.isPageModified = isPageModified;
		editorDirtyStateChanged();
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class)
			return new TabbedPropertySheetPage(this);
		return super.getAdapter(adapter);
	}

	@Override
	public String getContributorId() {
		return getSite().getId();
	}

	public PageGrid getPage() {
		return page;
	}

	public void setPage(PageGrid page) {
		this.page = page;
	}

}