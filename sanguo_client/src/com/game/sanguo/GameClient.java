package com.game.sanguo;

import java.util.concurrent.TimeUnit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
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

import com.game.sanguo.base.domain.PlayerHerosInfo;
import com.game.sanguo.base.domain.PlayerItemsInfo;
import com.game.sanguo.base.domain.SearchResultSorter;
import com.game.sanguo.base.domain.UserBean;
import com.game.sanguo.base.task.CitySearchAndGoldTask;
import com.game.sanguo.base.task.ContinuousLoginDaysRewardTask;
import com.game.sanguo.base.task.GameHelper;
import com.game.sanguo.base.task.GameNotifyTask;
import com.game.sanguo.base.task.GetWordCityInfoTask;
import com.game.sanguo.base.task.LoginTask;
import com.game.sanguo.base.task.MsgItemSellTask;
import com.game.sanguo.base.util.ItemConfig;
import com.game.sanguo.base.util.ResourceConfig;
import com.game.sanguo.base.util.UserConfig;
import com.game.sanguo.ui.ClientSearchResult;
import com.game.sanguo.ui.LoginDialog;
import com.game.sanguo.ui.ResourceSearchTableContentProvider;
import com.game.sanguo.ui.ResourceSearchTableLabelProvider;
import com.game.sanguo.ui.TableLabelProvider;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;

public class GameClient extends ApplicationWindow {
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

	ClientSearchResult searchResult = null;
	private Table table_5;
	private Button btnCheckButton;
	private Button btnCheckButton_2;
	private Button btnCheckButton_1;
	private Button btnCheckButton_3;

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

		searchResult = new ClientSearchResult(userBean.getConfigure()
				.getScanResource().getSortType());
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
		lblName.setBounds(10, 22, 70, 17);
		lblName.setText("name");

		Label lblNewLabel = new Label(grpBaseinfo, SWT.NONE);
		lblNewLabel.setBounds(10, 59, 70, 17);
		lblNewLabel.setText("gold");

		Label lblGem = new Label(grpBaseinfo, SWT.NONE);
		lblGem.setBounds(10, 100, 70, 17);
		lblGem.setText("gem");

		text = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(129, 22, 130, 27);

		text_1 = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(129, 59, 130, 27);

		text_2 = new Text(grpBaseinfo, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(129, 100, 130, 27);

		Group grpExtendInfo = new Group(composite_1, SWT.NONE);
		grpExtendInfo.setText("extend info");
		grpExtendInfo.setBounds(0, 136, 306, 402);

		TabFolder tabFolder_1 = new TabFolder(grpExtendInfo, SWT.NONE);
		tabFolder_1.setBounds(10, 20, 296, 372);

		TabItem tbtmNewItem_1 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_1.setText("hero");

		heroItemViewer = new TableViewer(tabFolder_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = heroItemViewer.getTable();
		tbtmNewItem_1.setControl(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("name");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("level");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				heroItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("city");

		TabItem tbtmNewItem_2 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNewItem_2.setText("equi");

		equiItemViewer = new TableViewer(tabFolder_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_1 = equiItemViewer.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		tbtmNewItem_2.setControl(table_1);

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				equiItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_4 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("name");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				equiItemViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("hero");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(
				equiItemViewer, SWT.NONE);
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

		measureViewer = new TableViewer(tabFolder_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_2 = measureViewer.getTable();
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		tbtmNewItem_3.setControl(table_2);

		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(
				measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_7 = tableViewerColumn_7.getColumn();
		tblclmnNewColumn_7.setWidth(50);
		tblclmnNewColumn_7.setText("area");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(
				measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_6 = tableViewerColumn_6.getColumn();
		tblclmnNewColumn_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byName");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		tblclmnNewColumn_6.setWidth(80);
		tblclmnNewColumn_6.setText("occu");

		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(
				measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_8 = tableViewerColumn_8.getColumn();
		
		tblclmnNewColumn_8.setWidth(50);
		tblclmnNewColumn_8.setText("heros");

		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(
				measureViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_15 = tableViewerColumn_15.getColumn();
		tblclmnNewColumn_15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getTreasureList(), "byTime");
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		tblclmnNewColumn_15.setWidth(100);
		tblclmnNewColumn_15.setText("endTime");

		TabItem tbtmNewItem_4 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_4.setText("gold");

		goldViewer = new TableViewer(tabFolder_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_3 = goldViewer.getTable();
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		tbtmNewItem_4.setControl(table_3);

		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(
				goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_10 = tableViewerColumn_10.getColumn();
		tblclmnNewColumn_10.setWidth(50);
		tblclmnNewColumn_10.setText("area");

		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(
				goldViewer, SWT.NONE);
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

		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(
				goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_9 = tableViewerColumn_9.getColumn();
		tblclmnNewColumn_9.setWidth(50);
		tblclmnNewColumn_9.setText("heros");

		TableViewerColumn tableViewerColumn_16 = new TableViewerColumn(
				goldViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_16 = tableViewerColumn_16.getColumn();
		tblclmnNewColumn_16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getGoldList(), "byTime");
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		tblclmnNewColumn_16.setWidth(100);
		tblclmnNewColumn_16.setText("endTime");

		TabItem tbtmNewItem_5 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_5.setText("market");

		marketViewer = new TableViewer(tabFolder_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_4 = marketViewer.getTable();
		table_4.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		tbtmNewItem_5.setControl(table_4);

		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(
				marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_13 = tableViewerColumn_13.getColumn();
		tblclmnNewColumn_13.setWidth(50);
		tblclmnNewColumn_13.setText("area");

		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(
				marketViewer, SWT.NONE);
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

		TableViewerColumn tableViewerColumn_14 = new TableViewerColumn(
				marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_14 = tableViewerColumn_14.getColumn();
		tblclmnNewColumn_14.setWidth(50);
		tblclmnNewColumn_14.setText("heros");

		TableViewerColumn tableViewerColumn_17 = new TableViewerColumn(
				marketViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_17 = tableViewerColumn_17.getColumn();
		tblclmnNewColumn_17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getMarketList(), "byTime");
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		tblclmnNewColumn_17.setWidth(100);
		tblclmnNewColumn_17.setText("endTime");

		TabItem tbtmNewItem_6 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmNewItem_6.setText("solider");

		soliderViewer = new TableViewer(tabFolder_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_5 = soliderViewer.getTable();
		table_5.setHeaderVisible(true);
		table_5.setLinesVisible(true);
		tbtmNewItem_6.setControl(table_5);

		TableViewerColumn tableViewerColumn_19 = new TableViewerColumn(
				soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_19 = tableViewerColumn_19.getColumn();
		tblclmnNewColumn_19.setWidth(50);
		tblclmnNewColumn_19.setText("area");

		TableViewerColumn tableViewerColumn_20 = new TableViewerColumn(
				soliderViewer, SWT.NONE);
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

		TableViewerColumn tableViewerColumn_18 = new TableViewerColumn(
				soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_18 = tableViewerColumn_18.getColumn();
		tblclmnNewColumn_18.setWidth(50);
		tblclmnNewColumn_18.setText("heros");

		TableViewerColumn tableViewerColumn_21 = new TableViewerColumn(
				soliderViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_21 = tableViewerColumn_21.getColumn();
		tblclmnNewColumn_21.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SearchResultSorter.sort(searchResult.getSoliderList(), "byTime");
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		tblclmnNewColumn_21.setWidth(100);
		tblclmnNewColumn_21.setText("endtime");

		Button btnNewButton = new Button(grpResource, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameHelper.submit(new GetWordCityInfoTask(userBean,
						resourceConfig, searchResult));
			}
		});

		btnNewButton.setBounds(291, 10, 91, 29);
		btnNewButton.setText("scan");
		
		btnCheckButton = new Button(grpResource, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setTreasure(btnCheckButton.getSelection()?1L:0L);
			}
			
		});
		btnCheckButton.setBounds(21, 23, 63, 16);
		btnCheckButton.setText("measure");
		
		btnCheckButton_1 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setMarket(btnCheckButton.getSelection()?1L:0L);
			}
		});
		btnCheckButton_1.setBounds(146, 23, 57, 16);
		btnCheckButton_1.setText("market");
		
		btnCheckButton_2 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setGold(btnCheckButton.getSelection()?1L:0L);
			}
		});
		btnCheckButton_2.setBounds(90, 23, 45, 16);
		btnCheckButton_2.setText("gold");
		
		btnCheckButton_3 = new Button(grpResource, SWT.CHECK);
		btnCheckButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userBean.getConfigure().getScanResource().setSolider(btnCheckButton.getSelection()?1L:0L);
			}
		});
		btnCheckButton_3.setBounds(204, 23, 63, 16);
		btnCheckButton_3.setText("solider");

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("log");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmNewItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		TextViewer textViewer = new TextViewer(scrolledComposite, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		scrolledComposite.setContent(styledText);
		scrolledComposite.setMinSize(styledText.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));

		initLog(styledText);

		initHeroTable();
		initEquTable();

		soliderViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		soliderViewer
				.setContentProvider(new ResourceSearchTableContentProvider());
		marketViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		marketViewer
				.setContentProvider(new ResourceSearchTableContentProvider());
		goldViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		goldViewer.setContentProvider(new ResourceSearchTableContentProvider());
		measureViewer.setLabelProvider(new ResourceSearchTableLabelProvider());
		measureViewer
				.setContentProvider(new ResourceSearchTableContentProvider());
		initDataBindings();
		return container;
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
					if(itemInfo.getType()==2)
					{
						return itemInfo.getNum()+" 个";
					}else
					{
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
				LoginDialog loginDialog = new LoginDialog(userBean,
						getParentShell());
				int ok = loginDialog.open();
				if (ok == Dialog.OK) {
					// 登录
					GameHelper.submit(new LoginTask(userBean));

					// 维持会话的获取通知信息惹任务
					GameHelper.scheduleAtFixedRate(
							new GameNotifyTask(userBean), 10, TimeUnit.SECONDS);
					// 扫描全图信息
					// exec.submit(new GetTimeZoneTask(userBean));
					// 领取每日登录奖励
					GameHelper.scheduleAtFixedRate(
							new ContinuousLoginDaysRewardTask(userBean), 24,
							TimeUnit.HOURS);
					// 扫描宝山，黑市，兵营，金矿资源定时任务
//					GameHelper.submit(new GetWordCityInfoTask(userBean,
//							resourceConfig, searchResult));
					// 卖东西定时任务
					GameHelper.scheduleAtFixedRate(new MsgItemSellTask(
							userBean, itemConfig), 24, TimeUnit.HOURS);
					// 领取资源，宝箱等定时搜索任务
					GameHelper.scheduleAtFixedRate(new CitySearchAndGoldTask(
							userBean, itemConfig), 10, TimeUnit.MINUTES);
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
	protected DataBindingContext initDataBindings() {
		IObservableValue loginGameInfonameUserBeanObserveValue = BeansObservables
				.observeValue(userBean, "loginGameInfo");
		loginGameInfonameUserBeanObserveValue
				.addChangeListener(new IChangeListener() {
					@Override
					public void handleChange(ChangeEvent arg0) {
						text.setText(userBean.getLoginGameInfo().getName());
						text_1.setText(userBean.getLoginGameInfo().getGold()
								+ "");
						text_2.setText(userBean.getLoginGameInfo().getGem()
								+ "");

						heroItemViewer.setInput(userBean.getLoginGameInfo()
								.getPlayerHerosInfoList());
						equiItemViewer.setInput(userBean.getLoginGameInfo()
								.getPlayerItemsInfoList());
					}
				});

		IObservableValue marketListBeanObserveValue = BeansObservables
				.observeValue(searchResult, "marketList");
		IObservableValue goldListBeanObserveValue = BeansObservables
				.observeValue(searchResult, "goldList");
		IObservableValue soliderListBeanObserveValue = BeansObservables
				.observeValue(searchResult, "soliderList");
		IObservableValue treasureListBeanObserveValue = BeansObservables
				.observeValue(searchResult, "treasureList");
		treasureListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				measureViewer.setInput(searchResult.getTreasureList());
			}
		});
		marketListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				marketViewer.setInput(searchResult.getMarketList());
			}
		});
		goldListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				goldViewer.setInput(searchResult.getGoldList());
			}
		});
		soliderListBeanObserveValue.addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent arg0) {
				soliderViewer.setInput(searchResult.getSoliderList());
			}
		});
		return null;

	}
}
