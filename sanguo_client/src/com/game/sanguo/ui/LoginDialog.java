package com.game.sanguo.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

import com.game.sanguo.base.domain.UserBean;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.BeanProperties;

public class LoginDialog extends Dialog {
	private DataBindingContext m_bindingContext;
	private Text text;
	private Text text_1;
	UserBean userBean;
	private Combo combo;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public LoginDialog(UserBean userBean,Shell parentShell) {
		super(parentShell);
		this.userBean = userBean;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Group grpD = new Group(container, SWT.NONE);
		grpD.setText("loginInfo");
		GridData gd_grpD = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpD.heightHint = 221;
		gd_grpD.widthHint = 430;
		grpD.setLayoutData(gd_grpD);
		
		Composite composite = new Composite(grpD, SWT.NONE);
		composite.setBounds(0, 30, 434, 164);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(50, 28, 70, 17);
		lblNewLabel.setText("username");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(50, 73, 70, 17);
		lblNewLabel_1.setText("password");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(50, 118, 70, 17);
		lblNewLabel_2.setText("area");
		
		text = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(245, 28, 150, 27);
		
		text_1 = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.PASSWORD);
		text_1.setBounds(245, 63, 150, 27);
		
		ComboViewer comboViewer = new ComboViewer(composite, SWT.NONE);
		combo = comboViewer.getCombo();
		combo.setBounds(245, 106, 150, 29);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue userNameUserBeanObserveValue = BeanProperties.value("userName").observe(userBean);
		bindingContext.bindValue(observeTextTextObserveWidget, userNameUserBeanObserveValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue passwordUserBeanObserveValue = BeanProperties.value("password").observe(userBean);
		bindingContext.bindValue(observeTextText_1ObserveWidget, passwordUserBeanObserveValue, null, null);
		//
		IObservableValue observeTextComboObserveWidget = WidgetProperties.text().observe(combo);
		IObservableValue userIDUserBeanObserveValue = BeanProperties.value("userID").observe(userBean);
		bindingContext.bindValue(observeTextComboObserveWidget, userIDUserBeanObserveValue, null, null);
		//
		return bindingContext;
	}
}