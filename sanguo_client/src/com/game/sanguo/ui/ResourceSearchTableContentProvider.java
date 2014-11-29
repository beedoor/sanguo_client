package com.game.sanguo.ui;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.UserBean;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ResourceSearchTableContentProvider
  implements IStructuredContentProvider
{
  List<String> filterStrList = new ArrayList();
  UserBean userBean = null;
  
  public ResourceSearchTableContentProvider(UserBean userBean)
  {
    this.userBean = userBean;
  }
  
  public void dispose() {}
  
  public void inputChanged(Viewer paramViewer, Object paramObject1, Object paramObject2) {}
  
  public Object[] getElements(Object inputElement)
  {
    if ((inputElement instanceof List))
    {
      List<Object> filterObject = new ArrayList();
      List<CityInfo> cityInfoList = (List)inputElement;
      if ((this.userBean.getScanExclude() == null) || (this.userBean.getScanExclude().trim().equals(""))) {
        return cityInfoList.toArray();
      }
      for (CityInfo obj : cityInfoList) {
        if (this.userBean.getScanExclude().length() > 1 && this.userBean.getScanExclude().indexOf(obj.getUnionName()) != -1 || this.userBean.getScanExclude().indexOf(obj.getOccupierName()) != -1) {
          filterObject.add(obj);
        }
      }
      return filterObject.toArray();
    }
    return new Object[0];
  }
}
