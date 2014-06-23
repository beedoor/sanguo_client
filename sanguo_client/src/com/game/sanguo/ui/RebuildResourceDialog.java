package com.game.sanguo.ui;

import com.game.sanguo.base.domain.UserBean;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RebuildResourceDialog
  extends Dialog
{
  private DataBindingContext m_bindingContext;
  private Text text;
  private Text text_1;
  UserBean userBean;
  
  public RebuildResourceDialog(UserBean userBean, Shell parentShell)
  {
    super(parentShell);
    this.userBean = userBean;
  }
  
  protected Control createDialogArea(Composite parent)
  {
    Composite container = (Composite)super.createDialogArea(parent);
    
    Group grpD = new Group(container, 0);
    grpD.setText("rebuild");
    GridData gd_grpD = new GridData(16384, 16777216, false, false, 1, 1);
    gd_grpD.heightHint = 151;
    gd_grpD.widthHint = 259;
    grpD.setLayoutData(gd_grpD);
    
    Composite composite = new Composite(grpD, 0);
    composite.setBounds(10, 20, 220, 119);
    
    Label lblNewLabel = new Label(composite, 0);
    lblNewLabel.setBounds(50, 28, 30, 17);
    lblNewLabel.setText("areaX");
    
    Label lblNewLabel_1 = new Label(composite, 0);
    lblNewLabel_1.setBounds(50, 73, 38, 17);
    lblNewLabel_1.setText("areaY");
    
    this.text = new Text(composite, 2048);
    this.text.setBounds(105, 28, 83, 27);
    
    this.text_1 = new Text(composite, 2048);
    this.text_1.setBounds(105, 70, 83, 27);
    

    return container;
  }
  
  protected void createButtonsForButtonBar(Composite parent)
  {
    Button button = createButton(parent, 0, IDialogConstants.OK_LABEL, 
      true);
    this.m_bindingContext = initDataBindings();
  }
  
  protected Point getInitialSize()
  {
    return new Point(285, 233);
  }
  
  protected DataBindingContext initDataBindings()
  {
    DataBindingContext bindingContext = new DataBindingContext();
    

    IObservableValue observeTextTextObserveWidget_1 = WidgetProperties.text(24).observe(this.text);
    IObservableValue scanResourceallResourceConfigxPosEndUserBeangetConfigureObserveValue = PojoProperties.value("scanResource.allResourceConfig.xPosEnd").observe(this.userBean.getConfigure());
    bindingContext.bindValue(observeTextTextObserveWidget_1, scanResourceallResourceConfigxPosEndUserBeangetConfigureObserveValue, null, null);
    
    IObservableValue observeTextText_1ObserveWidget_1 = WidgetProperties.text(24).observe(this.text_1);
    IObservableValue scanResourceallResourceConfigyPosEndUserBeangetConfigureObserveValue = PojoProperties.value("scanResource.allResourceConfig.yPosEnd").observe(this.userBean.getConfigure());
    bindingContext.bindValue(observeTextText_1ObserveWidget_1, scanResourceallResourceConfigyPosEndUserBeangetConfigureObserveValue, null, null);
    
    return bindingContext;
  }
}
