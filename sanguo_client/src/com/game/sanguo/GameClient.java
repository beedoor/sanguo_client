package com.game.sanguo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.sanguo.base.domain.CityInfo;
import com.game.sanguo.base.domain.PlayerHerosInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.SearchResultSorter;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
import com.game.sanguo.base.task.ContinuousLoginDaysRewardTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.GetTimeZoneTask;
import com.game.sanguo.base.task.GetWordCityInfoTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.MsgItemSellTask;
import com.game.sanguo.base.task.TaskUnit;
import com.game.sanguo.base.util.GameUtil;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.PipleLineTask;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;
import com.game.sanguo.ui.ClientSearchResult;
import com.game.sanguo.ui.LoginDialog;
import com.game.sanguo.ui.ResourceSearchTableContentProvider;
import com.game.sanguo.ui.ResourceSearchTableLabelProvider;
import com.game.sanguo.ui.TableLabelProvider;

public class GameClient extends ApplicationWindow {
	protected static Logger	logger			= LoggerFactory.getLogger(GameClient.class);
	private Action			action;
	private UserBean		userBean;
	UserConfig				userConfig;
	ResourceConfig			resourceConfig;
	ItemConfig				itemConfig;
	private Text			text;
	private Text			text_1;
	private Text			text_2;
	private Table			table;
	private Table			table_1;
	private Table			table_2;
	private Table			table_3;
	private Table			table_4;

	TableViewer				heroItemViewer;

	TableViewer				equiItemViewer;

	TableViewer				measureViewer;
	TableViewer				marketViewer;
	TableViewer				goldViewer;
	TableViewer				soliderViewer;
	TableViewer				allResourceTableViewer;

	ClientSearchResult		searchResult	= null;
	private Table			table_5;
	private Button			btnCheckButton;
	private Button			btnCheckButton_2;
	private Button			btnCheckButton_1;
	private Button			btnCheckButton_3;
	private Combo			combo;
	private Text			text_3;
	private Text			text_4;
	private Text			text_5;
	private Table			table_6;

	@Override
	public boolean close() {
		GameHelper.terminal();
		return super.close();
	}

	private void gameInit() {
		userConfig = new UserConfig();
		resourceConfig = new ResourceConfig();
		itemConfig = new ItemConfig();

		userConfig.loadUserConfig();
		resourceConfig.loadResourceConfig();

		itemConfig.loadUserConfig();

		userBean = userConfig.getUserConfig(null);
		// 加载资源配置
		userBean.setItemConfig(itemConfig);

		searchResult = new ClientSearchResult(userBean.getConfigure().getScanResource().getSortType());
	}

	/**
	 * Create the application window.
	 */
	public GameClient() {
		super(null);
		gameInit();
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		return doCreateComponent(parent);
	}

	private Control doCreateComponent(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(0, 0, 738, 638);

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setBounds(10, 10, 718, 579);

		TabItem tbtmSetting = new TabItem(tabFolder, SWT.NONE);
		tbtmSetting.setText("setting");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmSetting.setControl(composite_1);

		Group grpBaseinfo = new Group(composite_1, SWT.NONE);
		grpBaseinfo.setText("baseinfo");
		grpBaseinfo.setBounds(0, 0, 306, 127);

		Label lblName = new Label(grpBaseinfo, SWT.NONE);
		lblName.setBounds(10, 22, 39, 17);
		lblName.setText("name");

		Label lblNewLabel = new Label(grpBaseinfo, SWT.NONE);
		lblNewLabel.setBounds(129, 22, 27, 17);
		lblNewLabel.setText("gold");

		Label lblGem = new Label(grpBaseinfo, SWT.NONE);
		lblGem.setBounds(218, 22, 19, 17);
		lblGem.setText("gem");

		text = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(55, 19, 63, 17);

		text_1 = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(162, 19, 50, 17);

		text_2 = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(243, 19, 53, 17);

		final Button btnCheckButton_4 = new Button(grpBaseinfo, SWT.CHECK);
		btnCheckButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getSellConfig().setSell(btnCheckButton_4.getSelection() ? 1L : 0L);
			}
		});
		btnCheckButton_4.setBounds(139, 45, 39, 16);
		btnCheckButton_4.setText("sell");

		text_3 = new Text(grpBaseinfo, SWT.BORDER);
		text_3.setBounds(184, 45, 63, 18);

		Group grpAllresource = new Group(grpBaseinfo, SWT.NONE);
		grpAllresource.setText("allresource");
		grpAllresource.setBounds(10, 45, 108, 72);

		final Button btnCheckButton_5 = new Button(grpAllresource, SWT.CHECK);
		btnCheckButton_5.setBounds(20, 20, 63, 16);
		btnCheckButton_5.setText("IsOpen");
		btnCheckButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().getAllResourceConfig().setIsAllScanOpen(btnCheckButton_5.getSelection() ? 1L : 0L);
			}
		});

		Label lblNewLabel_2 = new Label(grpAllresource, SWT.NONE);
		lblNewLabel_2.setBounds(10, 50, 6, 12);
		lblNewLabel_2.setText("X");

		Label lblNewLabel_3 = new Label(grpAllresource, SWT.NONE);
		lblNewLabel_3.setBounds(62, 50, 6, 12);
		lblNewLabel_3.setText("Y");

		text_4 = new Text(grpAllresource, SWT.BORDER);
		text_4.setBounds(73, 44, 25, 18);

		text_5 = new Text(grpAllresource, SWT.BORDER);
		text_5.setBounds(30, 44, 26, 18);

		Group grpExtendInfo = new Group(composite_1, SWT.NONE);
		grpExtendInfo.setText("extend info");
		grpExtendInfo.setBounds(0, 136, 306, 402);

		TabFolder tabFolder_1 = new TabFolder(grpExtendInfo, SWT.NONE);
		tabFolder_1.setBounds(10, 20, 296, 372);

		TabItem tbtmNewItem_1 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_1.setText("hero");

		heroItemViewer = new TableViewer(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		table = heroItemViewer.getTable();
		tbtmNewItem_1.setControl(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("name");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("level");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("city");

		TabItem tbtmNewItem_2 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_2.setText("equi");

		equiItemViewer = new TableViewer(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = equiItemViewer.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		tbtmNewItem_2.setControl(table_1);

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(equiItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_4 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("name");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(equiItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("hero");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(equiItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_5 = tableViewerColumn_5.getColumn();
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("level");

		Group grpResource = new Group(composite_1, SWT.NONE);
		grpResource.setText("resource");
		grpResource.setBounds(312, 0, 392, 538);

		TabFolder tabFolder_2 = new TabFolder(grpResource, SWT.NONE);
		tabFolder_2.setBounds(10, 50, 372, 478);

		TabItem tbtmNewItem_3 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_3.setText("measure");

		measureViewer = new TableViewer(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_2 = measureViewer.getTable();
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		tbtmNewItem_3.setControl(table_2);

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_7 = tableViewerColumn_7.getColumn();
		tblclmnNewColumn_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byId");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		tblclmnNewColumn_7.setWidth(50);
		tblclmnNewColumn_7.setText("area");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_6 = tableViewerColumn_6.getColumn();
		tblclmnNewColumn_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byName");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("occu");

		TableViewerColumn tableViewerColumn_27 = new TableViewerColumn(measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_27 = tableViewerColumn_27.getColumn();
		tblclmnNewColumn_27.setWidth(60);
		tblclmnNewColumn_27.setText("union");

		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_15 = tableViewerColumn_15.getColumn();
		tblclmnNewColumn_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byTime");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		tblclmnNewColumn_15.setWidth(150);
		tblclmnNewColumn_15.setText("endTime");

		TabItem tbtmNewItem_4 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_4.setText("gold");

		goldViewer = new TableViewer(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_3 = goldViewer.getTable();
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		tbtmNewItem_4.setControl(table_3);

		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_10 = tableViewerColumn_10.getColumn();
		tblclmnNewColumn_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getGoldList(), "byId");
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		tblclmnNewColumn_10.setWidth(50);
		tblclmnNewColumn_10.setText("area");

		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_11 = tableViewerColumn_11.getColumn();
		tblclmnNewColumn_11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getGoldList(), "byName");
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		tblclmnNewColumn_11.setWidth(100);
		tblclmnNewColumn_11.setText("occu");

	

		TableViewerColumn tableViewerColumn_28 = new TableViewerColumn(goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_28 = tableViewerColumn_28.getColumn();
		tblclmnNewColumn_28.setWidth(80);
		tblclmnNewColumn_28.setText("union");

		TableViewerColumn tableViewerColumn_16 = new TableViewerColumn(goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_16 = tableViewerColumn_16.getColumn();
		tblclmnNewColumn_16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getGoldList(), "byTime");
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		tblclmnNewColumn_16.setWidth(150);
		tblclmnNewColumn_16.setText("endTime");

		TabItem tbtmNewItem_5 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_5.setText("market");

		marketViewer = new TableViewer(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_4 = marketViewer.getTable();
		table_4.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		tbtmNewItem_5.setControl(table_4);

		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_13 = tableViewerColumn_13.getColumn();
		tblclmnNewColumn_13.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getMarketList(), "byId");
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		tblclmnNewColumn_13.setWidth(50);
		tblclmnNewColumn_13.setText("area");

		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_12 = tableViewerColumn_12.getColumn();
		tblclmnNewColumn_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getMarketList(), "byName");
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		tblclmnNewColumn_12.setWidth(100);
		tblclmnNewColumn_12.setText("occu");

		

		TableViewerColumn tableViewerColumn_29 = new TableViewerColumn(marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_29 = tableViewerColumn_29.getColumn();
		tblclmnNewColumn_29.setWidth(80);
		tblclmnNewColumn_29.setText("union");

		TableViewerColumn tableViewerColumn_17 = new TableViewerColumn(marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_17 = tableViewerColumn_17.getColumn();
		tblclmnNewColumn_17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getMarketList(), "byTime");
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		tblclmnNewColumn_17.setWidth(150);
		tblclmnNewColumn_17.setText("endTime");

		TabItem tbtmNewItem_6 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_6.setText("solider");

		soliderViewer = new TableViewer(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_5 = soliderViewer.getTable();
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		tbtmNewItem_6.setControl(table_5);

		TableViewerColumn tableViewerColumn_19 = new TableViewerColumn(soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_19 = tableViewerColumn_19.getColumn();
		tblclmnNewColumn_19.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getSoliderList(), "byId");
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		tblclmnNewColumn_19.setWidth(50);
		tblclmnNewColumn_19.setText("area");

		TableViewerColumn tableViewerColumn_20 = new TableViewerColumn(soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_20 = tableViewerColumn_20.getColumn();
		tblclmnNewColumn_20.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getSoliderList(), "byName");
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		tblclmnNewColumn_20.setWidth(100);
		tblclmnNewColumn_20.setText("occu");

	

		TableViewerColumn tableViewerColumn_30 = new TableViewerColumn(soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_30 = tableViewerColumn_30.getColumn();
		tblclmnNewColumn_30.setWidth(80);
		tblclmnNewColumn_30.setText("union");

		TableViewerColumn tableViewerColumn_21 = new TableViewerColumn(soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_21 = tableViewerColumn_21.getColumn();
		tblclmnNewColumn_21.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getSoliderList(), "byTime");
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		tblclmnNewColumn_21.setWidth(150);
		tblclmnNewColumn_21.setText("endtime");

		TabItem tbtmAllitem = new TabItem(tabFolder_2, SWT.NONE);
		tbtmAllitem.setText("allItem");

		allResourceTableViewer = new TableViewer(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_6 = allResourceTableViewer.getTable();
		table_6.setLinesVisible(true);
		table_6.setHeaderVisible(true);
		tbtmAllitem.setControl(table_6);

		TableViewerColumn tableViewerColumn_22 = new TableViewerColumn(allResourceTableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_22 = tableViewerColumn_22.getColumn();
		tblclmnNewColumn_22.setWidth(50);
		tblclmnNewColumn_22.setText("area");

		TableViewerColumn tableViewerColumn_23 = new TableViewerColumn(allResourceTableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_23 = tableViewerColumn_23.getColumn();
		tblclmnNewColumn_23.setWidth(50);
		tblclmnNewColumn_23.setText("type");

		TableViewerColumn tableViewerColumn_24 = new TableViewerColumn(allResourceTableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_24 = tableViewerColumn_24.getColumn();
		tblclmnNewColumn_24.setWidth(100);
		tblclmnNewColumn_24.setText("occu");

		TableViewerColumn tableViewerColumn_25 = new TableViewerColumn(allResourceTableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_25 = tableViewerColumn_25.getColumn();
		tblclmnNewColumn_25.setWidth(50);
		tblclmnNewColumn_25.setText("union");

		TableViewerColumn tableViewerColumn_26 = new TableViewerColumn(allResourceTableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_26 = tableViewerColumn_26.getColumn();
		tblclmnNewColumn_26.setWidth(130);
		tblclmnNewColumn_26.setText("occuTime");

		Button btnNewButton = new Button(grpResource, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameHelper.submitTask(new TaskUnit(new GetWordCityInfoTask(userBean, resourceConfig, searchResult, null)));
				// 扫描全图信息
				if (userBean.getConfigure().getScanResource().getAllResourceConfig().getIsAllScanOpen() == 1) {
					GameHelper.submitTask(new TaskUnit(new GetTimeZoneTask(userBean, searchResult, null)));
				}
			}
		});

		btnNewButton.setBounds(256, 24, 39, 20);
		btnNewButton.setText("scan");

		btnCheckButton = new Button(grpResource, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setTreasure(btnCheckButton.getSelection() ? 1L : 0L);
			}

		});
		btnCheckButton.setBounds(21, 23, 39, 16);
		btnCheckButton.setText("ybs");

		btnCheckButton_1 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setMarket(btnCheckButton_1.getSelection() ? 1L : 0L);
			}
		});
		btnCheckButton_1.setBounds(105, 23, 29, 16);
		btnCheckButton_1.setText("hs");

		btnCheckButton_2 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setGold(btnCheckButton_2.getSelection() ? 1L : 0L);
			}
		});
		btnCheckButton_2.setBounds(66, 23, 33, 16);
		btnCheckButton_2.setText("jk");

		btnCheckButton_3 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setSolider(btnCheckButton_3.getSelection() ? 1L : 0L);
			}
		});
		btnCheckButton_3.setBounds(140, 23, 39, 16);
		btnCheckButton_3.setText("by");

		combo = new Combo(grpResource, SWT.NONE);
		combo.setToolTipText("扫描间隔：1000为一秒，500为半秒");
		combo.setItems(new String[] { "1000", "500", "100" });
		combo.setBounds(185, 22, 50, 20);
		combo.setText("1000");
		combo.select(0);

		Button btnNewButton_1 = new Button(grpResource, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doExport();
			}
		});
		btnNewButton_1.setBounds(312, 22, 43, 22);
		btnNewButton_1.setText("export");

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("log");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmNewItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		TextViewer textViewer = new TextViewer(scrolledComposite, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		scrolledComposite.setContent(styledText);
		scrolledComposite.setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		initLog(styledText);

		initHeroTable();
		initEquTable();

		allResourceTableViewer.setLabelProvider(new ResourceSearchTableLabelProvider() {
			@Override
			public String getColumnText(Object arg0, int columnIndex) {
				CityInfo itemInfo = (CityInfo) arg0;
				switch (columnIndex) {
				case 0:
					return itemInfo.getId() + "";
				case 1:
					return decodeResourceType(itemInfo.getTypeAsInt());
				case 2:
					return isNull(itemInfo.getOccupierName()) ? "空资源" : itemInfo.getOccupierName();
				case 3:
					return isNull(itemInfo.getUnionName()) ? "" : itemInfo.getUnionName();
				case 4:
					return GameUtil.parseDate(itemInfo.getOccupyTime());
				default:
					return "";
				}
			}

			private String decodeResourceType(long typeAsInt) {
				if (typeAsInt == 6) {
					return "兵营";
				} else if (typeAsInt == 5) {
					return "点将";
				} else if (typeAsInt == 4) {
					return "黑市";
				} else if (typeAsInt == 3) {
					return "元宝山";
				} else if (typeAsInt == 2) {
					return "金矿";
				}
				return "";
			}
		});
		allResourceTableViewer.setContentProvider(new ResourceSearchTableContentProvider());

		soliderViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		soliderViewer.setContentProvider(new ResourceSearchTableContentProvider());
		marketViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		marketViewer.setContentProvider(new ResourceSearchTableContentProvider());
		goldViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		goldViewer.setContentProvider(new ResourceSearchTableContentProvider());
		measureViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		measureViewer.setContentProvider(new ResourceSearchTableContentProvider());
		initDataBindings();
		return container;
	}

	protected void doExport() {
		FileWriter fos;
		File f;
		fos = null;
		f = null;
		try {
			String endline = "\r\n";
			f = new File("config/resource.xmlbak");
			if (f.exists())
				f.delete();
			fos = new FileWriter(f);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			fos.write(endline);
			fos.write("<resources>");
			writeOne(fos, searchResult.getTreasureList(), "treasure", endline);
			writeOne(fos, searchResult.getMarketList(), "market", endline);
			writeOne(fos, searchResult.getGoldList(), "gold", endline);
			writeOne(fos, searchResult.getSoliderList(), "solider", endline);
			fos.write("</resources>");
		} catch (Exception e) {
			logger.error("\u5199\u5165\u6587\u4EF6\u5F02\u5E38", e);
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		File ftarget = new File("config/resource.xml");
		if (ftarget.exists())
			ftarget.delete();
		f.renameTo(ftarget);
		return;
	}

	private void writeOne(FileWriter fos, List<CityInfo> targetList, String type, String endline) throws Exception {
		if (targetList == null || fos == null || type == null)
			return;
		for (Iterator<CityInfo> iterator = targetList.iterator(); iterator.hasNext(); fos.write(endline)) {
			CityInfo city = (CityInfo) iterator.next();
			fos.write("<resource>");
			fos.write(endline);
			fos.write(String.format("<type>%s</type>", new Object[] { type }));
			fos.write(endline);
			fos.write(String.format("<areaId>%s</areaId>", new Object[] { city.getId() }));
			fos.write(endline);
			fos.write("</resource>");
		}

	}

	private void initLog(final StyledText styledText) {
	}

	private void initEquTable() {
		equiItemViewer.setLabelProvider(new TableLabelProvider() {
			@Override
			public String getColumnText(Object arg0, int columnIndex) {
				PlayerItemsInfo itemInfo = (PlayerItemsInfo) arg0;
				switch (columnIndex) {
				case 0:
					return itemInfo.getItemName();
				case 1:
					return itemInfo.getHeroName();
				case 2:
					if (itemInfo.getType() == 2) {
						return itemInfo.getNum() + " 个";
					} else {
						return itemInfo.getStrengthenLevel() + " 级";
					}
				default:
					return "";
				}
			}
		});
		equiItemViewer.setContentProvider(new ResourceSearchTableContentProvider());
	}

	private void initHeroTable() {
		heroItemViewer.setLabelProvider(new TableLabelProvider() {
			@Override
			public String getColumnText(Object arg0, int columnIndex) {
				PlayerHerosInfo heroInfo = (PlayerHerosInfo) arg0;
				switch (columnIndex) {
				case 0:
					return heroInfo.getHeroName();
				case 1:
					return heroInfo.getLevel() + "";
				case 2:
					return heroInfo.getCityName();
				default:
					return "";
				}

			}

		});
		heroItemViewer.setContentProvider(new ResourceSearchTableContentProvider());
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		action = new Action("login") {

			@Override
			public void run() {
				super.run();
				LoginDialog loginDialog = new LoginDialog(userBean, getParentShell());
				int ok = loginDialog.open();
				if (ok == Dialog.OK) {

					PipleLineTask pipleLineTask = new PipleLineTask();
					pipleLineTask.add(new TaskUnit(new GameNotifyTask(userBean, pipleLineTask), "0/10 * * * * ?"));
					pipleLineTask.add(new TaskUnit(new ContinuousLoginDaysRewardTask(userBean, pipleLineTask), "0 30 * * * ?"));
					pipleLineTask.add(new TaskUnit(new MsgItemSellTask(userBean, itemConfig, pipleLineTask), "0 40 * * * ?"));
					pipleLineTask.add(new TaskUnit(new CitySearchAndGoldTask(userBean, itemConfig, pipleLineTask), "0 10 * * * ?"));
					// 此任务立刻执行
					GameHelper.submitTask(new TaskUnit(new LoginTask(userBean, pipleLineTask)));
				}
			}
		};
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");

		MenuManager menuManager_1 = new MenuManager("manager");
		menuManager.add(menuManager_1);
		menuManager_1.add(action);
		return menuManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					GameClient window = new GameClient();
					window.setBlockOnOpen(true);
					window.open();
					Display.getCurrent().dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("GameHelper");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(750, 700);
	}

	public Table getTable_6() {
		return table_6;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue loginGameInfonameUserBeanObserveValue = BeansObservables.observeValue(userBean, "loginGameInfo");
		loginGameInfonameUserBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				text.setText(userBean.getLoginGameInfo().getName());
				text_1.setText(userBean.getLoginGameInfo().getGold() + "");
				text_2.setText(userBean.getLoginGameInfo().getGem() + "");
				heroItemViewer.setInput(userBean.getLoginGameInfo().getPlayerHerosInfoList());
				equiItemViewer.setInput(userBean.getLoginGameInfo().getPlayerItemsInfoList());
			}
		});
		IObservableValue allResourceInfoBeanObserveValue = BeansObservables.observeValue(searchResult, "allResourceList");
		allResourceInfoBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				allResourceTableViewer.setInput(searchResult.getAllResourceList());
			}
		});
		IObservableValue marketListBeanObserveValue = BeansObservables.observeValue(searchResult, "marketList");
		IObservableValue goldListBeanObserveValue = BeansObservables.observeValue(searchResult, "goldList");
		IObservableValue soliderListBeanObserveValue = BeansObservables.observeValue(searchResult, "soliderList");
		IObservableValue treasureListBeanObserveValue = BeansObservables.observeValue(searchResult, "treasureList");
		treasureListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byName");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		marketListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				SearchResultSorter.sort(searchResult.getMarketList(), "byName");
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		goldListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				SearchResultSorter.sort(searchResult.getGoldList(), "byName");
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		soliderListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				SearchResultSorter.sort(searchResult.getSoliderList(), "byName");
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		//
		IObservableValue observeTextComboObserveWidget = WidgetProperties.text().observe(combo);
		IObservableValue configurescanResourcewaitTimeUserBeanObserveValue = PojoProperties.value("configure.scanResource.waitTime").observe(userBean);
		bindingContext.bindValue(observeTextComboObserveWidget, configurescanResourcewaitTimeUserBeanObserveValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_3);
		IObservableValue configuresellConfiglevelUserBeanObserveValue = PojoProperties.value("configure.sellConfig.level").observe(userBean);
		bindingContext.bindValue(observeTextText_3ObserveWidget, configuresellConfiglevelUserBeanObserveValue, null, null);

		//
		IObservableValue observeTextText_5ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_5);
		IObservableValue configurescanResourceallResourceConfigxPosEndUserBeanObserveValue = PojoProperties.value("configure.scanResource.allResourceConfig.xPosEnd").observe(userBean);
		bindingContext.bindValue(observeTextText_5ObserveWidget, configurescanResourceallResourceConfigxPosEndUserBeanObserveValue, null, null);
		//
		IObservableValue observeTextText_4ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_4);
		IObservableValue configurescanResourceallResourceConfigyPosEndUserBeanObserveValue = PojoProperties.value("configure.scanResource.allResourceConfig.yPosEnd").observe(userBean);
		bindingContext.bindValue(observeTextText_4ObserveWidget, configurescanResourceallResourceConfigyPosEndUserBeanObserveValue, null, null);
		//
		return bindingContext;
	}
}
