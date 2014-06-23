package com.game.sanguo.ui;

import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.LoginTask;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ShowExportResultDialog
  extends Dialog
{
  private DataBindingContext m_bindingContext;
  private Text text;
  private Text text_1;
  UserBean userBean;
  String s;
  private Combo combo;
  private LoginTask loginTask;
  private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
  
  public ShowExportResultDialog(String s, Shell parentShell)
  {
    super(parentShell);
    this.s = s;
  }
  
  protected Control createDialogArea(Composite parent)
  {
    Composite container = (Composite)super.createDialogArea(parent);
    
    Group grpD = new Group(container, 0);
    grpD.setText("allResource");
    GridData gd_grpD = new GridData(16384, 16777216, false, false, 1, 1);
    gd_grpD.heightHint = 328;
    gd_grpD.widthHint = 599;
    grpD.setLayoutData(gd_grpD);
    
    StyledText styledText = new StyledText(grpD, 2048);
    styledText.setWordWrap(true);
    styledText.setText(this.s);
    styledText.setBounds(10, 20, 585, 313);
    this.formToolkit.adapt(styledText);
    this.formToolkit.paintBordersFor(styledText);
    

    return container;
  }
  
  protected void createButtonsForButtonBar(Composite parent)
  {
    Button button2 = createButton(parent, 0, "Area", 
      true);
    button2.setText("OK");
    button2.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        //ShowExportResultDialog.this.loginTask.doLoginArea();
      }
    });
  }
  
  protected Point getInitialSize()
  {
    return new Point(622, 423);
  }
  
  protected DataBindingContext initDataBindings()
  {
    DataBindingContext bindingContext = new DataBindingContext();
    
    IObservableValue observeTextTextObserveWidget = WidgetProperties.text(24).observe(this.text);
    IObservableValue userNameUserBeanObserveValue = BeanProperties.value("userName").observe(this.userBean);
    bindingContext.bindValue(observeTextTextObserveWidget, userNameUserBeanObserveValue, null, null);
    
    IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(24).observe(this.text_1);
    IObservableValue passwordUserBeanObserveValue = BeanProperties.value("password").observe(this.userBean);
    bindingContext.bindValue(observeTextText_1ObserveWidget, passwordUserBeanObserveValue, null, null);
    
    IObservableValue observeTextComboObserveWidget = WidgetProperties.text().observe(this.combo);
    IObservableValue areaNameUserBeanObserveValue = BeanProperties.value("areaName").observe(this.userBean);
    bindingContext.bindValue(observeTextComboObserveWidget, areaNameUserBeanObserveValue, null, null);
    
    return bindingContext;
  }
}
