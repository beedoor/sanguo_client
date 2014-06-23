package com.game.sanguo.ui;

import com.game.sanguo.base.domain.UserBean;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ItemTableContentProvider
  implements IStructuredContentProvider
{
  List<String> filterStrList = new ArrayList();
  UserBean userBean = null;
  
  public ItemTableContentProvider(UserBean userBean)
  {
    this.userBean = userBean;
  }
  
  public void dispose() {}
  
  public void inputChanged(Viewer paramViewer, Object paramObject1, Object paramObject2) {}
  
  public Object[] getElements(Object inputElement)
  {
    if ((inputElement instanceof List)) {
      return ((List)inputElement).toArray();
    }
    return new Object[0];
  }
}
