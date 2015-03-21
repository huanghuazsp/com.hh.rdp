package com.hh.rdp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

public class AppUtil {
	public static IProject getProject(IEditorPart part) {
		IProject project = null;
		if (part != null) {
			Object object = part.getEditorInput().getAdapter(IFile.class);
			if (object != null) {
				project = ((IFile) object).getProject();
			}
		}
		if (project == null) {
			ISelectionService selectionService = Workbench.getInstance()
					.getActiveWorkbenchWindow().getSelectionService();
			ISelection selection = selectionService.getSelection();
			if (selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection)
						.getFirstElement();

				if (element instanceof IResource) {
					project = ((IResource) element).getProject();
				} /*
				 * else if (element instanceof PackageFragmentRootContainer) {
				 * IJavaProject jProject = ((PackageFragmentRootContainer)
				 * element) .getJavaProject(); project = jProject.getProject();
				 * }
				 */else if (element instanceof IJavaElement) {
					IJavaProject jProject = ((IJavaElement) element)
							.getJavaProject();
					project = jProject.getProject();
				} else if (element instanceof EditPart) {
					IFile file = (IFile) ((DefaultEditDomain) ((EditPart) element)
							.getViewer().getEditDomain()).getEditorPart()
							.getEditorInput().getAdapter(IFile.class);
					project = file.getProject();
				}
			}
		}
		return project;
	}

	public static IPackageFragment createPackageFragment(IProject project,
			String folder, String packageSrcName) {
		try {
			IJavaProject javaProject = JavaCore.create(project);
			IFolder srcFolder = javaProject.getProject().getFolder(folder);
			// srcFolder.getName();
			if (!srcFolder.exists()) {
				srcFolder.create(true, true, null);
				// this.createFolder(srcFolder);
				// 创建SourceLibrary
				IClasspathEntry srcClasspathEntry = JavaCore
						.newSourceEntry(srcFolder.getFullPath());
				// 得到旧的build path
				IClasspathEntry[] oldClasspathEntries = javaProject
						.readRawClasspath();
				// 添加新的
				List<IClasspathEntry> list = new ArrayList<IClasspathEntry>();
				list.addAll(Arrays.asList(oldClasspathEntries));
				list.add(srcClasspathEntry);
				javaProject.setRawClasspath(
						list.toArray(new IClasspathEntry[list.size()]), null);
			}
			IPackageFragmentRoot packageFragmentRoot = javaProject
					.findPackageFragmentRoot(new Path("/" + project.getName()
							+ "/" + folder));

			if (packageFragmentRoot != null) {
				IPackageFragment packageFragment = packageFragmentRoot
						.getPackageFragment(packageSrcName);
				if (!packageFragment.exists()) {
					packageFragment = packageFragmentRoot
							.createPackageFragment(packageSrcName, true, null);
				}
				return packageFragment;
				// System.out.println(packageFragment.toString());
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static IFolder createSrcFolder(IProject project, String folder) {
		try {
			IJavaProject javaProject = JavaCore.create(project);
			IFolder srcFolder = javaProject.getProject().getFolder(folder);
			if (!srcFolder.exists()) {
				srcFolder.create(true, true, null);
				// this.createFolder(srcFolder);
				// 创建SourceLibrary
				IClasspathEntry srcClasspathEntry = JavaCore
						.newSourceEntry(srcFolder.getFullPath());
				// 得到旧的build path
				IClasspathEntry[] oldClasspathEntries = javaProject
						.readRawClasspath();
				// 添加新的
				List<IClasspathEntry> list = new ArrayList<IClasspathEntry>();
				list.addAll(Arrays.asList(oldClasspathEntries));
				list.add(srcClasspathEntry);
				javaProject.setRawClasspath(
						list.toArray(new IClasspathEntry[list.size()]), null);
			}
			return srcFolder;
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String createCode(IProject project, String folder,
			String packageName, String javaCode, String fileName) {
		try {
			IJavaProject javaPoject = JavaCore.create(project);
			IPackageFragmentRoot packageFragmentRoot = javaPoject
					.findPackageFragmentRoot(new Path("/" + project.getName()
							+ "/" + folder));

			IPackageFragment packageFragment = packageFragmentRoot
					.getPackageFragment(packageName);
			if (!packageFragment.exists()) {
				packageFragment = packageFragmentRoot.createPackageFragment(
						packageName, true, null);
			}
			byte[] bytes = null;
			try {
				bytes = javaCode.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			InputStream stream = new ByteArrayInputStream(bytes);
			FileUtil.saveFileFromInputStream(stream, packageFragment
					.getResource().getLocation().toString(), fileName);
			return packageFragment.getResource().getLocation().toString() + "/"
					+ fileName;
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String classNameTodataBaseName(String className) {
		String[] replaceStrArr = new String[] { "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		for (String string : replaceStrArr) {
			className = className.replace(string, "_" + string);
		}
		if (className.startsWith("_")) {
			className=className.substring(1);
		}
		return className.toUpperCase();
	}

}
