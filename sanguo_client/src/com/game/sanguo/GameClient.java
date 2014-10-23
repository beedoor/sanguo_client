package com.game.sanguo;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.Configure;
import com.game.sanguo.base.domain.LoginGameInfo;
import com.game.sanguo.base.domain.PlayerHerosInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.ScanResource;
import com.game.sanguo.base.domain.SearchResultSorter;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
import com.game.sanguo.base.task.ContinuousLoginDaysRewardTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.GetTimeZoneTask;
import com.game.sanguo.base.task.GetWordCityInfoTask;
import com.game.sanguo.base.task.MsgItemSellTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.util.GameUtil;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;
import com.game.sanguo.ui.ClientSearchResult;
import com.game.sanguo.ui.ItemTableContentProvider;
import com.game.sanguo.ui.LoginDialog;
import com.game.sanguo.ui.RebuildResourceDialog;
import com.game.sanguo.ui.ResourceSearchTableContentProvider;
import com.game.sanguo.ui.ResourceSearchTableLabelProvider;
import com.game.sanguo.ui.ShowExportResultDialog;
import com.game.sanguo.ui.TableLabelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.swt.SWT;

public class GameClient
  extends ApplicationWindow
{
  protected static Logger logger = LoggerFactory.getLogger(GameClient.class);
  private Action action;
  private UserBean userBean;
  UserConfig userConfig;
  ResourceConfig resourceConfig;
  ItemConfig itemConfig;
  private Text text;
  private Text text_1;
  private Text text_2;
  private Table table;
  private Table table_1;
  private Table table_2;
  private Table table_3;
  private Table table_4;
  TableViewer heroItemViewer;
  TableViewer equiItemViewer;
  TableViewer measureViewer;
  TableViewer marketViewer;
  TableViewer goldViewer;
  TableViewer soliderViewer;
  TableViewer allResourceTableViewer;
  ClientSearchResult searchResult = null;
  private Table table_5;
  private Button btnCheckButton;
  private Button btnCheckButton_2;
  private Button btnCheckButton_1;
  private Button btnCheckButton_3;
  private Combo combo;
  private Table table_6;
  private Action action_1;
  private Action action_2;
  private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
  private Text text_3;
  
  public boolean close()
  {
    GameHelper.terminal();
    return super.close();
  }
  
  private void gameInit()
  {
    this.userConfig = new UserConfig();
    this.resourceConfig = new ResourceConfig();
    this.itemConfig = new ItemConfig();
    
    this.userConfig.loadUserConfig();
    this.resourceConfig.loadResourceConfig();
    
    this.itemConfig.loadUserConfig();
    
    this.userBean = this.userConfig.getUserConfig(null);
    
    this.userBean.setItemConfig(this.itemConfig);
    
    this.searchResult = new ClientSearchResult(this.userBean.getConfigure().getScanResource().getSortType());
  }
  
  public GameClient()
  {
    super(null);
    gameInit();
    createActions();
    addToolBar(8388672);
    addMenuBar();
    addStatusLine();
  }
  
  protected Control createContents(Composite parent)
  {
    return doCreateComponent(parent);
  }
  
  private Control doCreateComponent(Composite parent)
  {
    Composite container = new Composite(parent, 0);
    
    Composite composite = new Composite(container, 0);
    composite.setBounds(0, 0, 738, 638);
    
    TabFolder tabFolder = new TabFolder(composite, 0);
    tabFolder.setBounds(10, 10, 718, 579);
    
    TabItem tbtmSetting = new TabItem(tabFolder, 0);
    tbtmSetting.setText("setting");
    
    Composite composite_1 = new Composite(tabFolder, 0);
    tbtmSetting.setControl(composite_1);
    
    Group grpBaseinfo = new Group(composite_1, 0);
    grpBaseinfo.setText("baseinfo");
    grpBaseinfo.setBounds(0, 0, 306, 57);
    
    Label lblName = new Label(grpBaseinfo, 0);
    lblName.setBounds(10, 22, 39, 17);
    lblName.setText("name");
    
    Label lblNewLabel = new Label(grpBaseinfo, 0);
    lblNewLabel.setBounds(129, 22, 27, 17);
    lblNewLabel.setText("gold");
    
    Label lblGem = new Label(grpBaseinfo, 0);
    lblGem.setBounds(218, 22, 19, 17);
    lblGem.setText("gem");
    
    this.text = new Text(grpBaseinfo, 2056);
    this.text.setBounds(55, 19, 63, 17);
    
    this.text_1 = new Text(grpBaseinfo, 2056);
    this.text_1.setBounds(162, 19, 50, 17);
    
    this.text_2 = new Text(grpBaseinfo, 2056);
    this.text_2.setBounds(243, 19, 53, 17);
    
    Group grpExtendInfo = new Group(composite_1, 0);
    grpExtendInfo.setText("extend info");
    grpExtendInfo.setBounds(0, 63, 306, 475);
    
    TabFolder tabFolder_1 = new TabFolder(grpExtendInfo, 0);
    tabFolder_1.setBounds(10, 20, 296, 445);
    
    TabItem tbtmNewItem_1 = new TabItem(tabFolder_1, 0);
    tbtmNewItem_1.setText("hero");
    
    this.heroItemViewer = new TableViewer(tabFolder_1, 67584);
    this.table = this.heroItemViewer.getTable();
    tbtmNewItem_1.setControl(this.table);
    this.table.setHeaderVisible(true);
    this.table.setLinesVisible(true);
    this.table.setLayoutData(new GridData(1808));
    
    TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(this.heroItemViewer, 0);
    TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
    tblclmnNewColumn_1.setWidth(100);
    tblclmnNewColumn_1.setText("name");
    
    TableViewerColumn tableViewerColumn = new TableViewerColumn(this.heroItemViewer, 0);
    TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
    tblclmnNewColumn.setWidth(100);
    tblclmnNewColumn.setText("level");
    
    TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(this.heroItemViewer, 0);
    TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
    tblclmnNewColumn_2.setWidth(100);
    tblclmnNewColumn_2.setText("city");
    
    TabItem tbtmNewItem_2 = new TabItem(tabFolder_1, 0);
    tbtmNewItem_2.setText("equi");
    
    this.equiItemViewer = new TableViewer(tabFolder_1, 67584);
    this.table_1 = this.equiItemViewer.getTable();
    this.table_1.setLinesVisible(true);
    this.table_1.setHeaderVisible(true);
    tbtmNewItem_2.setControl(this.table_1);
    
    TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(this.equiItemViewer, 0);
    TableColumn tblclmnNewColumn_4 = tableViewerColumn_4.getColumn();
    tblclmnNewColumn_4.setWidth(100);
    tblclmnNewColumn_4.setText("name");
    
    TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(this.equiItemViewer, 0);
    TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
    tblclmnNewColumn_3.setWidth(100);
    tblclmnNewColumn_3.setText("hero");
    
    TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(this.equiItemViewer, 0);
    TableColumn tblclmnNewColumn_5 = tableViewerColumn_5.getColumn();
    tblclmnNewColumn_5.setWidth(100);
    tblclmnNewColumn_5.setText("level");
    
    Group grpResource = new Group(composite_1, 0);
    grpResource.setText("scaninfo");
    grpResource.setBounds(312, 0, 392, 538);
    
    final TabFolder tabFolder_2 = new TabFolder(grpResource, 0);
    tabFolder_2.setBounds(10, 107, 372, 421);
    
    TabItem tbtmNewItem_3 = new TabItem(tabFolder_2, 0);
    tbtmNewItem_3.setText("measure");
    
    this.measureViewer = new TableViewer(tabFolder_2, 67584);
    this.table_2 = this.measureViewer.getTable();
    this.table_2.setLinesVisible(true);
    this.table_2.setHeaderVisible(true);
    tbtmNewItem_3.setControl(this.table_2);
    
    TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(this.measureViewer, 0);
    TableColumn tblclmnNewColumn_7 = tableViewerColumn_7.getColumn();
    tblclmnNewColumn_7.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getTreasureList(), "byId");
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
      }
    });
    tblclmnNewColumn_7.setWidth(50);
    tblclmnNewColumn_7.setText("area");
    
    TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(this.measureViewer, 0);
    TableColumn tblclmnNewColumn_6 = tableViewerColumn_6.getColumn();
    tblclmnNewColumn_6.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getTreasureList(), "byName");
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
      }
    });
    tblclmnNewColumn_6.setWidth(90);
    tblclmnNewColumn_6.setText("occu");
    
    TableViewerColumn tableViewerColumn_27 = new TableViewerColumn(this.measureViewer, 0);
    TableColumn tblclmnNewColumn_27 = tableViewerColumn_27.getColumn();
    tblclmnNewColumn_27.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getTreasureList(), "byUnion");
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
      }
    });
    tblclmnNewColumn_27.setWidth(90);
    tblclmnNewColumn_27.setText("union");
    
    TableColumn tblclmnNewColumn_8 = new TableColumn(table_2, SWT.NONE);
    tblclmnNewColumn_8.setWidth(35);
    tblclmnNewColumn_8.setText("sta");
    
    TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(this.measureViewer, 0);
    TableColumn tblclmnNewColumn_15 = tableViewerColumn_15.getColumn();
    tblclmnNewColumn_15.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getTreasureList(), "byTime");
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
      }
    });
    tblclmnNewColumn_15.setWidth(100);
    tblclmnNewColumn_15.setText("endTime");
    
    TabItem tbtmNewItem_4 = new TabItem(tabFolder_2, 0);
    tbtmNewItem_4.setText("gold");
    
    this.goldViewer = new TableViewer(tabFolder_2, 67584);
    this.table_3 = this.goldViewer.getTable();
    this.table_3.setLinesVisible(true);
    this.table_3.setHeaderVisible(true);
    tbtmNewItem_4.setControl(this.table_3);
    
    TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(this.goldViewer, 0);
    TableColumn tblclmnNewColumn_10 = tableViewerColumn_10.getColumn();
    tblclmnNewColumn_10.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getGoldList(), "byId");
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    tblclmnNewColumn_10.setWidth(50);
    tblclmnNewColumn_10.setText("area");
    
    TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(this.goldViewer, 0);
    TableColumn tblclmnNewColumn_11 = tableViewerColumn_11.getColumn();
    tblclmnNewColumn_11.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getGoldList(), "byName");
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    tblclmnNewColumn_11.setWidth(90);
    tblclmnNewColumn_11.setText("occu");
    


    TableViewerColumn tableViewerColumn_28 = new TableViewerColumn(this.goldViewer, 0);
    TableColumn tblclmnNewColumn_28 = tableViewerColumn_28.getColumn();
    tblclmnNewColumn_28.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getGoldList(), "byUnion");
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    tblclmnNewColumn_28.setWidth(90);
    tblclmnNewColumn_28.setText("union");
    
    TableColumn tblclmnNewColumn_9 = new TableColumn(table_3, SWT.NONE);
    tblclmnNewColumn_9.setWidth(35);
    tblclmnNewColumn_9.setText("sta");
    
    TableViewerColumn tableViewerColumn_16 = new TableViewerColumn(this.goldViewer, 0);
    TableColumn tblclmnNewColumn_16 = tableViewerColumn_16.getColumn();
    tblclmnNewColumn_16.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getGoldList(), "byTime");
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    tblclmnNewColumn_16.setWidth(100);
    tblclmnNewColumn_16.setText("endTime");
    
    TabItem tbtmNewItem_5 = new TabItem(tabFolder_2, 0);
    tbtmNewItem_5.setText("market");
    
    this.marketViewer = new TableViewer(tabFolder_2, 67584);
    this.table_4 = this.marketViewer.getTable();
    this.table_4.setLinesVisible(true);
    this.table_4.setHeaderVisible(true);
    tbtmNewItem_5.setControl(this.table_4);
    
    TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(this.marketViewer, 0);
    TableColumn tblclmnNewColumn_13 = tableViewerColumn_13.getColumn();
    tblclmnNewColumn_13.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getMarketList(), "byId");
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
      }
    });
    tblclmnNewColumn_13.setWidth(50);
    tblclmnNewColumn_13.setText("area");
    
    TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(this.marketViewer, 0);
    TableColumn tblclmnNewColumn_12 = tableViewerColumn_12.getColumn();
    tblclmnNewColumn_12.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getMarketList(), "byName");
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
      }
    });
    tblclmnNewColumn_12.setWidth(90);
    tblclmnNewColumn_12.setText("occu");
    


    TableViewerColumn tableViewerColumn_29 = new TableViewerColumn(this.marketViewer, 0);
    TableColumn tblclmnNewColumn_29 = tableViewerColumn_29.getColumn();
    tblclmnNewColumn_29.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getMarketList(), "byUnion");
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
      }
    });
    tblclmnNewColumn_29.setWidth(90);
    tblclmnNewColumn_29.setText("union");
    
    TableColumn tblclmnNewColumn_14 = new TableColumn(table_4, SWT.NONE);
    tblclmnNewColumn_14.setWidth(35);
    tblclmnNewColumn_14.setText("sta");
    
    TableViewerColumn tableViewerColumn_17 = new TableViewerColumn(this.marketViewer, 0);
    TableColumn tblclmnNewColumn_17 = tableViewerColumn_17.getColumn();
    tblclmnNewColumn_17.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getMarketList(), "byTime");
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
      }
    });
    tblclmnNewColumn_17.setWidth(100);
    tblclmnNewColumn_17.setText("endTime");
    
    TabItem tbtmNewItem_6 = new TabItem(tabFolder_2, 0);
    tbtmNewItem_6.setText("solider");
    
    this.soliderViewer = new TableViewer(tabFolder_2, 67584);
    this.table_5 = this.soliderViewer.getTable();
    this.table_5.setHeaderVisible(true);
    this.table_5.setLinesVisible(true);
    tbtmNewItem_6.setControl(this.table_5);
    
    TableViewerColumn tableViewerColumn_19 = new TableViewerColumn(this.soliderViewer, 0);
    TableColumn tblclmnNewColumn_19 = tableViewerColumn_19.getColumn();
    tblclmnNewColumn_19.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getSoliderList(), "byId");
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
      }
    });
    tblclmnNewColumn_19.setWidth(50);
    tblclmnNewColumn_19.setText("area");
    
    TableViewerColumn tableViewerColumn_20 = new TableViewerColumn(this.soliderViewer, 0);
    TableColumn tblclmnNewColumn_20 = tableViewerColumn_20.getColumn();
    tblclmnNewColumn_20.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getSoliderList(), "byName");
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
      }
    });
    tblclmnNewColumn_20.setWidth(90);
    tblclmnNewColumn_20.setText("occu");
    


    TableViewerColumn tableViewerColumn_30 = new TableViewerColumn(this.soliderViewer, 0);
    TableColumn tblclmnNewColumn_30 = tableViewerColumn_30.getColumn();
    tblclmnNewColumn_30.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getSoliderList(), "byUnion");
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
      }
    });
    tblclmnNewColumn_30.setWidth(90);
    tblclmnNewColumn_30.setText("union");
    
    TableColumn tblclmnNewColumn_18 = new TableColumn(table_5, SWT.NONE);
    tblclmnNewColumn_18.setWidth(35);
    tblclmnNewColumn_18.setText("sta");
    
    TableViewerColumn tableViewerColumn_21 = new TableViewerColumn(this.soliderViewer, 0);
    TableColumn tblclmnNewColumn_21 = tableViewerColumn_21.getColumn();
    tblclmnNewColumn_21.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getSoliderList(), "byTime");
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
      }
    });
    tblclmnNewColumn_21.setWidth(100);
    tblclmnNewColumn_21.setText("endtime");
    
    TabItem tbtmAllitem = new TabItem(tabFolder_2, 0);
    tbtmAllitem.setText("allItem");
    
    this.allResourceTableViewer = new TableViewer(tabFolder_2, 67584);
    this.table_6 = this.allResourceTableViewer.getTable();
    this.table_6.setLinesVisible(true);
    this.table_6.setHeaderVisible(true);
    tbtmAllitem.setControl(this.table_6);
    
    TableViewerColumn tableViewerColumn_22 = new TableViewerColumn(this.allResourceTableViewer, 0);
    TableColumn tblclmnNewColumn_22 = tableViewerColumn_22.getColumn();
    tblclmnNewColumn_22.setWidth(50);
    tblclmnNewColumn_22.setText("area");
    
    TableViewerColumn tableViewerColumn_23 = new TableViewerColumn(this.allResourceTableViewer, 0);
    TableColumn tblclmnNewColumn_23 = tableViewerColumn_23.getColumn();
    tblclmnNewColumn_23.setWidth(50);
    tblclmnNewColumn_23.setText("type");
    
    TableViewerColumn tableViewerColumn_24 = new TableViewerColumn(this.allResourceTableViewer, 0);
    TableColumn tblclmnNewColumn_24 = tableViewerColumn_24.getColumn();
    tblclmnNewColumn_24.setWidth(100);
    tblclmnNewColumn_24.setText("occu");
    
    TableViewerColumn tableViewerColumn_25 = new TableViewerColumn(this.allResourceTableViewer, 0);
    TableColumn tblclmnNewColumn_25 = tableViewerColumn_25.getColumn();
    tblclmnNewColumn_25.setWidth(100);
    tblclmnNewColumn_25.setText("union");
    
    TableViewerColumn tableViewerColumn_26 = new TableViewerColumn(this.allResourceTableViewer, 0);
    TableColumn tblclmnNewColumn_26 = tableViewerColumn_26.getColumn();
    tblclmnNewColumn_26.setWidth(130);
    tblclmnNewColumn_26.setText("occuTime");
    
    Button btnNewButton = new Button(grpResource, 0);
    btnNewButton.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e) {}
    });
    btnNewButton.addMouseListener(new MouseAdapter()
    {
      public void mouseDown(MouseEvent e)
      {
        if (GameClient.this.userBean.getConfigure().getScanResource().getTreasure().longValue() == 1L) {
          tabFolder_2.setSelection(0);
        } else if (GameClient.this.userBean.getConfigure().getScanResource().getGold().longValue() == 1L) {
          tabFolder_2.setSelection(1);
        } else if (GameClient.this.userBean.getConfigure().getScanResource().getMarket().longValue() == 1L) {
          tabFolder_2.setSelection(2);
        } else if (GameClient.this.userBean.getConfigure().getScanResource().getSolider().longValue() == 1L) {
          tabFolder_2.setSelection(3);
        }
        GameHelper.submitTask(new TaskUnit(new GetWordCityInfoTask(GameClient.this.userBean, GameClient.this.resourceConfig, GameClient.this.searchResult,GameClient.this.itemConfig)));
      }
    });
    btnNewButton.setBounds(217, 56, 63, 24);
    btnNewButton.setText("scan");
    
    this.btnCheckButton = new Button(grpResource, 32);
    this.btnCheckButton.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        GameClient.this.userBean.getConfigure().getScanResource().setTreasure(Long.valueOf(GameClient.this.btnCheckButton.getSelection() ? 1L : 0L));
      }
    });
    this.btnCheckButton.setBounds(21, 16, 39, 16);
    this.btnCheckButton.setText("ybs");
    
    this.btnCheckButton_1 = new Button(grpResource, 32);
    this.btnCheckButton_1.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        GameClient.this.userBean.getConfigure().getScanResource().setMarket(Long.valueOf(GameClient.this.btnCheckButton_1.getSelection() ? 1L : 0L));
      }
    });
    this.btnCheckButton_1.setBounds(21, 60, 29, 16);
    this.btnCheckButton_1.setText("hs");
    
    this.btnCheckButton_2 = new Button(grpResource, 32);
    this.btnCheckButton_2.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        GameClient.this.userBean.getConfigure().getScanResource().setGold(Long.valueOf(GameClient.this.btnCheckButton_2.getSelection() ? 1L : 0L));
      }
    });
    this.btnCheckButton_2.setBounds(21, 38, 33, 16);
    this.btnCheckButton_2.setText("jk");
    
    this.btnCheckButton_3 = new Button(grpResource, 32);
    this.btnCheckButton_3.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        GameClient.this.userBean.getConfigure().getScanResource().setSolider(Long.valueOf(GameClient.this.btnCheckButton_3.getSelection() ? 1L : 0L));
      }
    });
    this.btnCheckButton_3.setBounds(21, 78, 39, 16);
    this.btnCheckButton_3.setText("by");
    
    this.combo = new Combo(grpResource, 0);
    this.combo.setToolTipText("扫描间隔：1000为一秒，500为半秒");
    this.combo.setItems(new String[] {"3000","2000", "1000", "500", "100" });
    this.combo.setBounds(140, 56, 63, 20);
    this.combo.setText("1000");
    this.combo.select(0);
    
    Label lblNewLabel_1 = this.formToolkit.createLabel(grpResource, "interval", 0);
    lblNewLabel_1.setBounds(80, 59, 54, 12);
    
    Button btnNewButton_1 = new Button(grpResource, 0);
    btnNewButton_1.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent e)
      {
        GameClient.this.showStr();
      }
    });
    btnNewButton_1.setBounds(306, 56, 63, 24);
    this.formToolkit.adapt(btnNewButton_1, true, true);
    btnNewButton_1.setText("show");
    
    this.text_3 = new Text(grpResource, 2048);
    this.text_3.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent e)
      {
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    this.text_3.setBounds(136, 16, 246, 18);
    this.formToolkit.adapt(this.text_3, true, true);
    
    Label lblNewLabel_2 = this.formToolkit.createLabel(grpResource, "filterUn", 0);
    lblNewLabel_2.setBounds(80, 22, 54, 12);
    
    TabItem tbtmNewItem = new TabItem(tabFolder, 0);
    tbtmNewItem.setText("log");
    
    ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, 2816);
    tbtmNewItem.setControl(scrolledComposite);
    scrolledComposite.setExpandHorizontal(true);
    scrolledComposite.setExpandVertical(true);
    
    TextViewer textViewer = new TextViewer(scrolledComposite, 2048);
    StyledText styledText = textViewer.getTextWidget();
    scrolledComposite.setContent(styledText);
    scrolledComposite.setMinSize(styledText.computeSize(-1, -1));
    
    initLog(styledText);
    
    initHeroTable();
    initEquTable();
    
    this.allResourceTableViewer.setLabelProvider(new ResourceSearchTableLabelProvider()
    {
      public String getColumnText(Object arg0, int columnIndex)
      {
        CityInfo itemInfo = (CityInfo)arg0;
        switch (columnIndex)
        {
        case 0: 
          return itemInfo.getId()+"";
        case 1: 
          return decodeResourceType(itemInfo.getTypeAsInt().longValue());
        case 2: 
          return isNull(itemInfo.getOccupierName()) ? "空资源" : itemInfo.getOccupierName();
        case 3: 
          return isNull(itemInfo.getUnionName()) ? "" : itemInfo.getUnionName();
        case 4: 
          return GameUtil.parseDate(itemInfo.getOccupyTime());
        }
        return "";
      }
      
      private String decodeResourceType(long typeAsInt)
      {
        if (typeAsInt == 6L) {
          return "兵营";
        }
        if (typeAsInt == 5L) {
          return "点将";
        }
        if (typeAsInt == 4L) {
          return "黑市";
        }
        if (typeAsInt == 3L) {
          return "元宝山";
        }
        if (typeAsInt == 2L) {
          return "金矿";
        }
        return "";
      }
    });
    this.allResourceTableViewer.setContentProvider(new ResourceSearchTableContentProvider(this.userBean));
    
    this.soliderViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
    this.soliderViewer.setContentProvider(new ResourceSearchTableContentProvider(this.userBean));
    this.marketViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
    this.marketViewer.setContentProvider(new ResourceSearchTableContentProvider(this.userBean));
    this.goldViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
    this.goldViewer.setContentProvider(new ResourceSearchTableContentProvider(this.userBean));
    this.measureViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
    this.measureViewer.setContentProvider(new ResourceSearchTableContentProvider(this.userBean));
    initDataBindings();
    return container;
  }
  
  protected void showStr()
  {
    Map<String, List<CityInfo>> goldMap = processOne(this.searchResult.getGoldList());
    Map<String, List<CityInfo>> soliderMap = processOne(this.searchResult.getSoliderList());
    Map<String, List<CityInfo>> marketMap = processOne(this.searchResult.getMarketList());
    Map<String, List<CityInfo>> treasureMap = processOne(this.searchResult.getTreasureList());
    
    StringBuffer strb = new StringBuffer();
    extendOne("山", treasureMap, strb);
    extendOne("黑", marketMap, strb);
    extendOne("金", goldMap, strb);
    extendOne("兵", soliderMap, strb);
    
    ShowExportResultDialog showExportResultDialog = new ShowExportResultDialog(strb.toString(), getParentShell());
    int ok = showExportResultDialog.open();
  }
  
  private void extendOne(String tips, Map<String, List<CityInfo>> map, StringBuffer strb)
  {
    if (map.isEmpty()) {
      return;
    }
    Iterator<String> ite = map.keySet().iterator();
    strb.append("\r\n").append(tips).append("\r\n\r\n");
    while (ite.hasNext())
    {
      String usreName = (String)ite.next();
      List<CityInfo> resourceList = (List)map.get(usreName);
      if ((resourceList != null) && (!resourceList.isEmpty()))
      {
        strb.append("\t\t").append(usreName).append(": ");
        for(CityInfo ss:resourceList)
        {
        	if(ss.getHerosInfo() == null || "".equals(ss.getHerosInfo()))
        	{
        		strb.append(ss.getId()).append(getBattleStatus(ss.getStatusAsInt())).append(",");
        	}else
        	{
        		strb.append(ss.getId()).append("\r\n").append("\r\n").append(ss.getHerosInfo());
        	}
        }
        strb.append("\r\n");
      }
    }
  }
  
  private Map<String, List<CityInfo>> processOne(List<CityInfo> cityInfoList)
  {
    Map<String, List<CityInfo>> resultMap = new HashMap();
    for (CityInfo city : cityInfoList) {
    	if(userBean.getScanExclude() != null )
    	{
    		if(city.getUnionName() != null)
    		{
    			if(userBean.getScanExclude().indexOf(city.getUnionName()) != -1)
    			{
    				add(resultMap,city);
    			}
    		}else
    		{
    			add(resultMap,city);
    		}
    	}else
    	{
    		add(resultMap,city);
    	}
    
    }
    return resultMap;
  }
  
  private void add(Map<String, List<CityInfo>> resultMap, CityInfo city) {
	String occupierName = (city.getOccupierName() == null) || (city.getOccupierName().equals("null")) ? "空资源" : city.getOccupierName();
	  if (!resultMap.containsKey(occupierName)) {
          resultMap.put(occupierName, new ArrayList());
        }
       resultMap.get(occupierName).add(city);
       
}

private String getBattleStatus(Long status)
  {
    if (status.longValue() == 4L) {
      return " （战斗中）";
    }
    return "";
  }
  
  private void initLog(StyledText styledText) {}
  
  private void initEquTable()
  {
    this.equiItemViewer.setLabelProvider(new TableLabelProvider()
    {
      public String getColumnText(Object arg0, int columnIndex)
      {
        PlayerItemsInfo itemInfo = (PlayerItemsInfo)arg0;
        switch (columnIndex)
        {
        case 0: 
          return itemInfo.getItemName();
        case 1: 
          return itemInfo.getHeroName();
        case 2: 
          if (itemInfo.getType().longValue() == 2L) {
            return itemInfo.getNum() + " 个";
          }
          return itemInfo.getStrengthenLevel() + " 级";
        }
        return "";
      }
    });
    this.equiItemViewer.setContentProvider(new ItemTableContentProvider(this.userBean));
  }
  
  private void initHeroTable()
  {
    this.heroItemViewer.setLabelProvider(new TableLabelProvider()
    {
      public String getColumnText(Object arg0, int columnIndex)
      {
        PlayerHerosInfo heroInfo = (PlayerHerosInfo)arg0;
        switch (columnIndex)
        {
        case 0: 
          return heroInfo.getHeroName();
        case 1: 
          return heroInfo.getLevel()+"";
        case 2: 
          return heroInfo.getCityName();
        }
        return "";
      }
    });
    this.heroItemViewer.setContentProvider(new ItemTableContentProvider(this.userBean));
  }
  
  private void createActions()
  {
    this.action = new Action("login")
    {
      public void run()
      {
        super.run();
        LoginDialog loginDialog = new LoginDialog(GameClient.this.userBean, GameClient.this.getParentShell());
        int ok = loginDialog.open();
        if (ok == 0)
        {
          List<TaskUnit> pipleLineTask = new ArrayList<TaskUnit>();
          pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(GameClient.this.userBean), "0 30 * * * ?"));
          pipleLineTask.add(new TaskUnit(new MsgItemSellTask(GameClient.this.userBean, GameClient.this.itemConfig), "0 40 * * * ?"));
          pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(GameClient.this.userBean, GameClient.this.itemConfig), "0 10 * * * ?"));
          TaskUnit task = new TaskUnit(new GameNotifyTask(GameClient.this.userBean), "0/10 * * * * ?");
          task.setPipleLineTask(pipleLineTask);
          GameHelper.submitTask(task);
        }
      }
    };
    this.action_2 = new Action("rebuild")
    {
      public void run()
      {
        super.run();
        RebuildResourceDialog rebuildResource = new RebuildResourceDialog(GameClient.this.userBean, GameClient.this.getParentShell());
        int ok = rebuildResource.open();
        if (ok == 0) {
          GameHelper.submitTask(new TaskUnit(new GetTimeZoneTask(GameClient.this.userBean,GameClient.this.searchResult,resourceConfig)));
        }
      }
    };
  }
  
  protected MenuManager createMenuManager()
  {
    MenuManager menuManager = new MenuManager("menu");
    
    MenuManager menuManager_1 = new MenuManager("manager");
    menuManager.add(menuManager_1);
    menuManager_1.add(this.action);
    
    MenuManager menuManager_2 = new MenuManager("resource");
    menuManager.add(menuManager_2);
    menuManager_2.add(this.action_2);
    
    return menuManager;
  }
  
  protected StatusLineManager createStatusLineManager()
  {
    StatusLineManager statusLineManager = new StatusLineManager();
    return statusLineManager;
  }
  
  public static void main(String[] args)
  {
    Display display = Display.getDefault();
    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        try
        {
          GameClient window = new GameClient();
          window.setBlockOnOpen(true);
          window.open();
          Display.getCurrent().dispose();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  protected void configureShell(Shell newShell)
  {
    super.configureShell(newShell);
    newShell.setText("GameHelper");
  }
  
  protected Point getInitialSize()
  {
    return new Point(750, 700);
  }
  
  public Table getTable_6()
  {
    return this.table_6;
  }
  
  protected DataBindingContext initDataBindings()
  {
    DataBindingContext bindingContext = new DataBindingContext();
    IObservableValue loginGameInfonameUserBeanObserveValue = BeansObservables.observeValue(this.userBean, "loginGameInfo");
    loginGameInfonameUserBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        GameClient.this.text.setText(GameClient.this.userBean.getLoginGameInfo().getName());
        GameClient.this.text_1.setText(""+GameClient.this.userBean.getLoginGameInfo().getGold());
        GameClient.this.text_2.setText(""+GameClient.this.userBean.getLoginGameInfo().getGem());
        GameClient.this.heroItemViewer.setInput(GameClient.this.userBean.getLoginGameInfo().getPlayerHerosInfoList());
        GameClient.this.equiItemViewer.setInput(GameClient.this.userBean.getLoginGameInfo().getPlayerItemsInfoList());
      }
    });
    IObservableValue allResourceInfoBeanObserveValue = BeansObservables.observeValue(this.searchResult, "allResourceList");
    allResourceInfoBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        GameClient.this.allResourceTableViewer.setInput(GameClient.this.searchResult.getAllResourceList());
      }
    });
    IObservableValue marketListBeanObserveValue = BeansObservables.observeValue(this.searchResult, "marketList");
    IObservableValue goldListBeanObserveValue = BeansObservables.observeValue(this.searchResult, "goldList");
    IObservableValue soliderListBeanObserveValue = BeansObservables.observeValue(this.searchResult, "soliderList");
    IObservableValue treasureListBeanObserveValue = BeansObservables.observeValue(this.searchResult, "treasureList");
    treasureListBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getTreasureList(), "byName");
        GameClient.this.measureViewer.setInput(GameClient.this.searchResult.getTreasureList());
      }
    });
    marketListBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getMarketList(), "byName");
        GameClient.this.marketViewer.setInput(GameClient.this.searchResult.getMarketList());
      }
    });
    goldListBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getGoldList(), "byName");
        GameClient.this.goldViewer.setInput(GameClient.this.searchResult.getGoldList());
      }
    });
    soliderListBeanObserveValue.addChangeListener(new IChangeListener()
    {
      public void handleChange(ChangeEvent arg0)
      {
        SearchResultSorter.sort(GameClient.this.searchResult.getSoliderList(), "byName");
        GameClient.this.soliderViewer.setInput(GameClient.this.searchResult.getSoliderList());
      }
    });
    IObservableValue observeTextComboObserveWidget = WidgetProperties.text().observe(this.combo);
    IObservableValue configurescanResourcewaitTimeUserBeanObserveValue = PojoProperties.value("configure.scanResource.waitTime").observe(this.userBean);
    bindingContext.bindValue(observeTextComboObserveWidget, configurescanResourcewaitTimeUserBeanObserveValue, null, null);
    






    IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(24).observe(this.text_3);
    IObservableValue scanExcludeUserBeanObserveValue = BeanProperties.value("scanExclude").observe(this.userBean);
    bindingContext.bindValue(observeTextText_3ObserveWidget, scanExcludeUserBeanObserveValue, null, null);
    
    return bindingContext;
  }
}
