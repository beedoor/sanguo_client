package com.game.sanguo.ui;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ResourceSearchTableContentProvider implements
		IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer paramViewer, Object paramObject1,
			Object paramObject2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			return ((List) inputElement).toArray();
		} else {
			return new Object[0];
		}
	}
}