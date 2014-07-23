package com.game.sanguo.ui;

import com.game.sanguo.base.domain.GameAreaInfo;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.LoginTask;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends Dialog {
	private DataBindingContext m_bindingContext;
	private Text text;
	private Text text_1;
	UserBean userBean;
	private Combo combo;
	private LoginTask loginTask;

	public LoginDialog(UserBean userBean, Shell parentShell) {
		super(parentShell);
		this.userBean = userBean;
		this.loginTask = new LoginTask(userBean, null);
	}

	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		Group grpD = new Group(container, 0);
		grpD.setText("loginInfo");
		GridData gd_grpD = new GridData(16384, 16777216, false, false, 1, 1);
		gd_grpD.heightHint = 188;
		gd_grpD.widthHint = 411;
		grpD.setLayoutData(gd_grpD);

		Composite composite = new Composite(grpD, 0);
		composite.setBounds(31, 30, 347, 164);

		Label lblNewLabel = new Label(composite, 0);
		lblNewLabel.setBounds(50, 28, 70, 17);
		lblNewLabel.setText("username");

		Label lblNewLabel_1 = new Label(composite, 0);
		lblNewLabel_1.setBounds(50, 73, 70, 17);
		lblNewLabel_1.setText("password");

		Label lblNewLabel_2 = new Label(composite, 0);
		lblNewLabel_2.setBounds(50, 118, 70, 17);
		lblNewLabel_2.setText("area");

		this.text = new Text(composite, 2048);
		this.text.setBounds(168, 28, 150, 27);

		this.text_1 = new Text(composite, 4196352);
		this.text_1.setBounds(168, 63, 150, 27);

		ComboViewer comboViewer = new ComboViewer(composite, 0);
		this.combo = comboViewer.getCombo();
		this.combo.setBounds(168, 106, 150, 29);
		return container;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		final Button button = createButton(parent, 14, "game", true);
		final Button button2 = createButton(parent, 0, "Area", true);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					LoginDialog.this.loginTask.doLoginGame();

					List<GameAreaInfo> gInfoList = LoginDialog.this.userBean
							.getAllGameList();
					String[] s = new String[gInfoList.size()];
					int select = 0;
					for (int i = 0; i < gInfoList.size(); i++) {
						s[i] = ((GameAreaInfo) gInfoList.get(i)).getName();
						if (((GameAreaInfo) gInfoList.get(i)).getId() == LoginDialog.this.userBean
								.getAreaId()) {
							select = i;
						}
					}
					LoginDialog.this.combo.setItems(s);
					LoginDialog.this.combo.select(select);
					button2.setEnabled(true);
					button.setEnabled(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button2.setEnabled(false);
		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				LoginDialog.this.loginTask.doLoginArea();
			}
		});
		this.m_bindingContext = initDataBindings();
	}

	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();

		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(
				24).observe(this.text);
		IObservableValue userNameUserBeanObserveValue = BeanProperties.value(
				"userName").observe(this.userBean);
		bindingContext.bindValue(observeTextTextObserveWidget,
				userNameUserBeanObserveValue, null, null);

		IObservableValue observeTextText_1ObserveWidget = WidgetProperties
				.text(24).observe(this.text_1);
		IObservableValue passwordUserBeanObserveValue = BeanProperties.value(
				"password").observe(this.userBean);
		bindingContext.bindValue(observeTextText_1ObserveWidget,
				passwordUserBeanObserveValue, null, null);

		IObservableValue observeTextComboObserveWidget = WidgetProperties
				.text().observe(this.combo);
		IObservableValue areaNameUserBeanObserveValue = BeanProperties.value(
				"areaName").observe(this.userBean);
		bindingContext.bindValue(observeTextComboObserveWidget,
				areaNameUserBeanObserveValue, null, null);

		return bindingContext;
	}
}
