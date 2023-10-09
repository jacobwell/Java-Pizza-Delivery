/**
 *
 *The PSDGUI class creates the view for our PDS system
 *
 *@authors 	bdk3367 Benjamin Kravitz, jgw765 Jacob Wellinghoff, ijd8975 Ian Dempsey
 */

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.prefs.*;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.Box;
import java.awt.Color;

@SuppressWarnings("serial")
public class PDSGUI extends JFrame  implements ActionListener, Serializable {
	
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//								DATA MEMBERS								 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	
	private Q q; // Processing queue for orders

	private int startedOnOrder = 1;
	private int endedOnOrder = 1;

	private Item pendingItem;
	private Order pendingOrder;
	private java.lang.Class mClass = PDSGUI.class; // mClass holds the reference to the class of PDSGUI for preference saving/reading
	private Preferences prefs = Preferences.userNodeForPackage(mClass); // prefs holds the java prefences for interacting with the PrefObj class
	
	private JPanel startPanel; // startPanel holds the components for the initial screen
	private JPanel centerPaneStart; // panel in the center of startPanel
	private JPanel loginPanel; // a panel for the login
	private JPanel centerPaneLogin; // panel in the center of login Panel
	private JPanel orderPanel; // a panel for the order
	private JPanel toppingsPanel; // a panel for the toppings
	private JPanel toppingsList; // a panel for the toppings lists
	private JPanel centerPaneToppings; // a panel for the toppings lists
	private JPanel southPaneToppings; // a panel for the toppings buttons
	private JPanel homePanel; // a panel for the customer select
	
	private JPanel subPanel; //a panel for splitting the center of the history view?
	private JPanel customerHistoryPanel; // a panel for showing a customer's history
	private JPanel addCustomerPanel; // a panel for the add Customer feature
	private JPanel centerPaneAdd; // a panel in the center of addCustomerPanel
	private JPanel southPaneAdd; // a panel in the bottom of addCustomerPanel
	private Box locationPanel; // a panel for the location
	private JPanel confirmationPanel; // a panel for the confirmation
	private JPanel southPaneConfirm; // a panel for the confirmation
	private JPanel receiptPanel; // a panel for the receipt
	private JPanel configSysPanel; // a panel for the system configuration
	
	private JPanel cartDisplayPanel;
	private JPanel customerQuickPanel;
	private JPanel recieptDualPanel;
	
	//JNOTE: BRB working on making a quick customer info panel
	private JScrollPane cartListScroller;
	private JScrollPane cartListScroller2;
	private JScrollPane toppingCartListScroller;
	private JScrollPane previousOrdersScroller;
	private JTextArea orderTextArea;
	private JList cartList;
	private JList cartList2;
	private JList toppingCartList;
	private JList previousOrdersList;
	//private JList order
	private DefaultListModel cart;
	private DefaultListModel cart2;
	private DefaultListModel toppingCart;
	private DefaultListModel previousOrdersModel;
	private DefaultListModel QListModel;

	private JPanel configItemsPanel; // a panel for the items configuration

	private JPanel centerPaneConfig; // a center panel for the system configuration
	private JPanel southPaneConfig; // a bottom panel for the system configuration
	private JPanel configMenuPanel; // a panel for the menu configuration
	private JPanel centerPaneMenu; // a center panel for the menu configuration
	private JPanel southPaneMenu; // a bottom panel for the menu configuration
	private JPanel existingItemsPanel; // a panel for the existing menu items
	private JPanel addItemsPanel; // a panel for the adding items
	private JPanel centerPaneEnd; //a center panel for the system configuration
	private JPanel menuPanel; // a center panel for the order
	private JPanel southPaneOrder; // a bottom panel for the order
	private JPanel endPanel; // a panel for the end of day feature
	private JLabel welcomeMessage; // a welcome message
	private JLabel loginInputLabel; // a label for the login
	private JLabel nameFieldLabel; // a label for the name
	private	JLabel addCustLabel; // a label for add customer
	private JLabel phoneFieldLabel; // a label for the phone number
	private JLabel locationLabel; // a label for the location
	private JLabel phoneInputLabel;
	private JLabel orderTakerLabel; // a label for the number of order taker input
	private JLabel cooksLabel; // a label for the number of cooks input
	private JLabel driversLabel; // a label for the number of drivers input
	private JLabel ovensLabel; // a label for the number of oven input
	private JLabel addNameLabel;
	private JLabel addPriceLabel; // a label for the price input
	private JLabel toppingPriceLabel; // a label for the toppings price input
	private JLabel addPrepLabel; // a label for the prep time input
	private JLabel addCookLabel; // a label for the cook time input
	private JLabel addOvenSLabel; // a label for the oven space required input
	private JLabel receiptLabel; // a label for the receipt output
	private JLabel recieptTotalLabel;

	private JButton startDay; // starts business day
	private JButton timeWarp;
	private JButton endDay; // ends business day
	private JButton configSys; // button that when clicked takes one to the system config screen
	
	private JPanel centerPanePick; // panel in the center of homePanel
	private JPanel northPanePick;
	private JPanel southPanePick;
	
	
	private JPanel northHistory; //north panel for customerhistory panel
	private JPanel southHistory; //south panel for customerhistory panel
	
	private JButton historyBackButton;
	private JButton quit;
	private JButton loginButton;
	private JButton searchButton; // allows one to search for customers
	private JButton lookupButton; // allows one to lookup customer orders
	private JButton addSubmitButton;
	private JButton addCancelButton;
	private JButton configMenuButton;
	private JButton configSubmitButton;
	private JButton menuAddButton;
	private JButton menuBackButton;
	private JButton menuRemoveButton;
	private JButton loginCancelButton;
	private JButton configCancelButton;
	private JButton orderCancelButton;
	private JButton toppingsSubmitButton;
	private JButton toppingsBackButton;
	private JButton reportsButton;
	private JButton startQuit;
	private JButton receiptButton;
	private JButton orderSubTotalButton;
	private JButton confirmSubmitButton;
	private JButton confirmBackButton;
	
	private GridLayout menuLayout;
	
	private JPanel authenticationPanel;

	private JPasswordField passInput; //a password field that takes in the manager's password
	private JTextField nameField;
	private	JTextField phoneField; // a field that takes in a phone number
	private JTextField numberInput;
	private JTextField orderTakersInput;
	private JTextField cooksInput; // a field that takes in the cooks
	private JTextField driversInput; // a field that takes in the drivers
	private JTextField ovensInput; //  field that takes in the ovens
	private JTextField addNameInput;
	private JTextField addPriceInput;
	private JTextField toppingPriceInput;
	private JTextField addPrepInput;
	private JTextField addCookInput;
	private JTextField addOvenInput;
	
	// Location radio buttons
	private JRadioButton rit;
	private JRadioButton uOfR;
	private JRadioButton nazareth;
	private JRadioButton stJohn;
	private JRadioButton robWes;
	private JRadioButton mcc;
	
	private JRadioButton left;
	private JRadioButton right;
	private JRadioButton both;
	
	private Box box;
	
	private JPanel toppingSidePanel;
	
	private ButtonGroup toppingSide;
	
	
	private JButton[] toppingsArray; // JButton array for toppings
	private ButtonGroup tGroup; // a group of buttons for toppings
	private ButtonGroup lGroup; // a group of buttons for location
	
	
	private JList menuList; // menu list box
	private JScrollPane menuListScroller;
	
	private String[] orderList;
	private JList QList; // order list box
	private JScrollPane QListScroller; // scroll pane for the existing orders list
	 // scroll pane for the existing menu items list
	 // the order as a string array
	private DefaultListModel menuButtonNames; // the menu as a string array
	private DB db; // a reference to the database
	Hashtable<Long, Order> tempOrderList; // a temporary hashtable mapping the order id to the order
	//Hashtable<String, Item> menuList; // a temporary hashtable mapping the order id to the order
	
	
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//								CONSTRUCTOR									 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //

	public PDSGUI() {
		/*
		 * Set the title and general details for the window
		 */
		 setTitle("Pizza Delivery System");
	     setSize(800, 600);
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     createWindow();
	}
	
	/**
	 * Intializes and configures jPanels
	 */
	public final void createWindow() {
		//Initialize PDS
		db = new DB();
		try {
			db = (DB)PrefObj.getObject(prefs, "db");
		} catch (Exception e) { 
			//System.out.println("No DB found");
			try { PrefObj.putObject( prefs, "db", db );
			} catch (Exception ex) {
				System.out.println("fatal error 1");
				System.exit(0);
			}	
		}
		//q.db.ordersIn
		q = new Q( db, this );
		
		customerQuickPanel		= new JPanel( new GridLayout(3,1) );
		recieptDualPanel		= new JPanel( new GridLayout(2,1) );
		confirmationPanel		= new JPanel( new BorderLayout() );
		southPaneConfirm		= new JPanel( new GridLayout(2,2) ); 
		receiptPanel			= new JPanel( new BorderLayout() );
		
		centerPaneMenu			= new JPanel( new GridLayout(1,2) );
		southPaneMenu			= new JPanel( new GridLayout(1,2) );
		
		northHistory			= new JPanel( );
		southHistory			= new JPanel( );
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								START PANEL									 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		startPanel 				= new JPanel( new BorderLayout() );
		centerPaneStart 		= new JPanel();
		
		startPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
	
		TitledBorder titleBorder = new TitledBorder("   Welcome to the Pizza Delivery System!   ");
		centerPaneStart.setBorder(titleBorder);
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		
		startDay 	= new JButton("Start Day");
		configSys 	= new JButton("Configure System");
		startQuit	= new JButton("Save & Quit");
		
		startDay.addActionListener(this);
		configSys.addActionListener(this);
		startQuit.addActionListener(this);
		
		startDay.setPreferredSize(new Dimension(200, 100));
		configSys.setPreferredSize(new Dimension(200, 100));
		startQuit.setPreferredSize(new Dimension(200, 100));
		
		centerPaneStart.add(startDay);
		centerPaneStart.add(configSys);
		centerPaneStart.add(startQuit);
		
		startPanel.add(centerPaneStart, BorderLayout.CENTER);
		
		//-Final Configuration
		getContentPane().add(startPanel);
		startPanel.getRootPane().setDefaultButton(startDay);
		startPanel.setVisible(true);		
		
		
		
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//								LOGIN PANEL									 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		loginPanel 				= new JPanel( new BorderLayout() );
		centerPaneLogin			= new JPanel();
		
		loginPanel.setBorder( new EmptyBorder(150, 150, 150, 150) );
		
		TitledBorder aptb = new TitledBorder("   Authentication   ");
		centerPaneLogin.setBorder(aptb);
		aptb.setTitleJustification(TitledBorder.CENTER);
		
		passInput 				= new JPasswordField(35);
		loginButton				= new JButton("Login");
		loginCancelButton		= new JButton("Cancel");
		
		loginButton.addActionListener(this);
		loginCancelButton.addActionListener(this);
		
		//centerPaneLogin.add(box.createRigidArea(new Dimension(500, 70)));
		centerPaneLogin.add(passInput);
		centerPaneLogin.add(loginButton);
		centerPaneLogin.add(loginCancelButton);

		loginPanel.add(centerPaneLogin, BorderLayout.CENTER);
		
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//							ADD CUSTOMER PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		addCustomerPanel 	= new JPanel( new BorderLayout() );
		centerPaneAdd		= new JPanel( new BorderLayout() );
		
		JPanel nameFieldPanel = new JPanel();
		JPanel phoneFieldPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		locationPanel		= box.createVerticalBox();
		
		TitledBorder acb	= new TitledBorder("   Add Customer   ");
		centerPaneAdd.setBorder(acb);
		acb.setTitleJustification(TitledBorder.CENTER);
		
		TitledBorder lb		= new TitledBorder("   Location   ");
		locationPanel.setBorder(lb);
		lb.setTitleJustification(TitledBorder.CENTER);
		
		locationPanel.setSize(new Dimension(150, 200));
		
		addCustomerPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		
		// Labels and textfields
		nameFieldLabel		= new JLabel("Name:");
		phoneFieldLabel		= new JLabel("Phone:");
		//locationLabel		= new JLabel("Location:");
			
		nameField			= new JTextField(20);
		phoneField			= new JTextField(20);
		
		// Location Radio Buttons
		rit					= new JRadioButton("RIT");
		uOfR				= new JRadioButton("University of Rochester");
		nazareth 			= new JRadioButton("Nazareth");
		stJohn	 			= new JRadioButton("St. John Fisher");
		robWes				= new JRadioButton("Roberts Wesleyan");
		mcc					= new JRadioButton("MCC");
		
		lGroup = new ButtonGroup();
		lGroup.add(rit);
		lGroup.add(uOfR);
		lGroup.add(nazareth);
		lGroup.add(stJohn);
		lGroup.add(robWes);
		lGroup.add(mcc);
		
		locationPanel.add(rit);
		locationPanel.add(uOfR);
		locationPanel.add(nazareth);
		locationPanel.add(stJohn);
		locationPanel.add(robWes);
		locationPanel.add(mcc);
		
		rit.setSelected(true);
		
		addSubmitButton		= new JButton("Submit");
		addCancelButton		= new JButton("Cancel");
		
		addSubmitButton.addActionListener(this);
		addCancelButton.addActionListener(this);	
		
		
		nameFieldPanel.add(nameFieldLabel);
		nameFieldPanel.add(nameField);
		
		phoneFieldPanel.add(phoneFieldLabel);
		phoneFieldPanel.add(phoneField);
		
		buttonPanel.add(addSubmitButton);
		buttonPanel.add(addCancelButton);
		
		
		centerPaneAdd.add(phoneFieldPanel, BorderLayout.NORTH);
		centerPaneAdd.add(nameFieldPanel, BorderLayout.CENTER);
		
		centerPaneAdd.add(locationPanel, BorderLayout.SOUTH);
		
		addCustomerPanel.add(centerPaneAdd, BorderLayout.CENTER);
		addCustomerPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//									HOME PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	
		homePanel		= new JPanel( new BorderLayout() );
		centerPanePick	= new JPanel( new GridLayout(1,1) );
		southPanePick	= new JPanel( );
		northPanePick	= new JPanel( );
	
		homePanel.setBorder(new EmptyBorder(50, 50, 50, 50));

		TitledBorder ctb = new TitledBorder("   Order Queue   ");
		centerPanePick.setBorder(ctb);
		ctb.setTitleJustification(TitledBorder.CENTER);

		TitledBorder ntb = new TitledBorder("   Find Customer   ");
		northPanePick.setBorder(ntb);
		ntb.setTitleJustification(TitledBorder.CENTER);

		numberInput = new JTextField("Enter a 10 Digit Phone Number", 25);
		numberInput.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (numberInput.getText().equals("Enter a 10 Digit Phone Number")) {
					numberInput.setText("");
				}
			}
		});
		
		searchButton			= new JButton("Search");
		lookupButton			= new JButton("History");
		endDay					= new JButton("End Day");
		timeWarp				= new JButton("Time Warp");
		QListModel 				= new DefaultListModel();
		QList					= new JList(QListModel);
		QListScroller			= new JScrollPane(QList);


		searchButton.addActionListener(this);
		lookupButton.addActionListener(this);
		
		endDay.addActionListener(this);
		timeWarp.addActionListener(this);
		
		northPanePick.add(numberInput);
		northPanePick.add(searchButton);
		northPanePick.add(lookupButton);
		centerPanePick.add(QListScroller);
		southPanePick.add(endDay);
		southPanePick.add(timeWarp);
		
		
		homePanel.add(northPanePick, BorderLayout.NORTH);
		homePanel.add(centerPanePick, BorderLayout.CENTER);
		homePanel.add(southPanePick, BorderLayout.SOUTH);
	



		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//			    		  	CUSTOMER HISTORY PANEL	    					 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		

		customerHistoryPanel = new JPanel( new BorderLayout( ) );
		//JNOTE: buff up GUI for cust hist panel here
		historyBackButton = new JButton("Back");
		historyBackButton.addActionListener(this);
		customerHistoryPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		subPanel = new JPanel( new GridLayout(1,2) ); // used to be 1,2
		previousOrdersModel = new DefaultListModel();
		previousOrdersList = new JList(previousOrdersModel);
		previousOrdersScroller = new JScrollPane(previousOrdersList);
		orderTextArea = new JTextArea("", 10,10);
		subPanel.add(previousOrdersScroller);
		subPanel.add(orderTextArea);
		customerHistoryPanel.add(subPanel,BorderLayout.CENTER);
		customerHistoryPanel.add(southHistory,BorderLayout.SOUTH);
		//customerHistoryPanel.add(new JButton("temp2"),BorderLayout.SOUTH);
		southHistory.add(historyBackButton);
		
		
		
		
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								CONFIG PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		
		configSysPanel		= new JPanel( new BorderLayout() );
		configItemsPanel	= new JPanel();
		centerPaneConfig 	= new JPanel();
		southPaneConfig		= new JPanel();
		
		configSysPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		
		TitledBorder ertb = new TitledBorder("   Configure System   ");
		centerPaneConfig.setBorder(ertb);
		ertb.setTitleJustification(TitledBorder.CENTER);
		
		
		//-Textfields, labels, and buttons
		cooksLabel				= new JLabel("Cooks:");
		driversLabel			= new JLabel("Drivers:");
		ovensLabel				= new JLabel("Oven Space:");
		
		cooksInput				= new JTextField(3);
		cooksInput.setText(Integer.toString(q.db.cooks));
		driversInput			= new JTextField(3);
		driversInput.setText(Integer.toString(q.db.drivers));
		ovensInput				= new JTextField(3);
		ovensInput.setText(Integer.toString(q.db.ovens));
		
		configMenuButton		= new JButton("Configure Menu");
		configSubmitButton		= new JButton("Submit");
		configCancelButton		= new JButton("Cancel");
		
		configMenuButton.setPreferredSize(new Dimension(150, 70));
		configSubmitButton.setPreferredSize(new Dimension(120, 50));
		configCancelButton.setPreferredSize(new Dimension(120, 50));
		
		
		//-Add action listeners
		configSubmitButton.addActionListener(this);
		configCancelButton.addActionListener(this);
		configMenuButton.addActionListener(this);
		
		//centerPaneAdd.add(box.createRigidArea(new Dimension(700, 5)));
		
		//-Add components to configSysPanel
		centerPaneConfig.add(cooksLabel);
		centerPaneConfig.add(cooksInput);
		
		//centerPaneConfig.add(box.createRigidArea(new Dimension(400, 40)));
		centerPaneConfig.add(driversLabel);
		centerPaneConfig.add(driversInput);
		//centerPaneConfig.add(box.createRigidArea(new Dimension(400, 40)));
		centerPaneConfig.add(ovensLabel);
		centerPaneConfig.add(ovensInput);
		//centerPaneConfig.add(box.createRigidArea(new Dimension(400, 40)));
		
		centerPaneConfig.add(configMenuButton);
		
		southPaneConfig.add(configSubmitButton);
		southPaneConfig.add(configCancelButton);
		southPaneConfig.add(box.createRigidArea(new Dimension(700, 20)));
		
		configSysPanel.add(centerPaneConfig, BorderLayout.CENTER);
		configSysPanel.add(southPaneConfig, BorderLayout.SOUTH);	
		
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//							MENU CONFIG PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //		
		
		configMenuPanel	= new JPanel( new BorderLayout() );
		
		Box addItemBox = box.createVerticalBox();
		
		TitledBorder aib = new TitledBorder("   Add Item   ");
		addItemBox.setBorder(aib);
		aib.setTitleJustification(TitledBorder.CENTER);
		
		configMenuPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		

		menuButtonNames = new DefaultListModel();
		
		Enumeration<String> menu = q.db.getMenu().keys();
		while( menu.hasMoreElements() ){
			menuButtonNames.addElement(menu.nextElement().toString());
		}
		
		//updateMenu();
		
		JPanel nameInputPanel 			= new JPanel();
		JPanel priceInputPanel 			= new JPanel();
		JPanel toppingPriceInputPanel 	= new JPanel();
		JPanel prepInputPanel 			= new JPanel();
		JPanel cookInputPanel 			= new JPanel();
		JPanel ovenInputPanel 			= new JPanel();
		//JPanel confirmationPanel		= new JPanel();
		
		//-Labels, textfields, list, and buttons
		JLabel addNameLabel 		= new JLabel("Name:");
		JLabel addPriceLabel 		= new JLabel("Price:");
		JLabel toppingPriceLabel	= new JLabel("Topping Price:");
		JLabel addPrepLabel			= new JLabel("Prep Time:");
		JLabel addCookLabel			= new JLabel("Cook Time:");
		JLabel addOvenLabel			= new JLabel("Oven Space:");
		
		addNameInput			= new JTextField(15);
		addPriceInput			= new JTextField(15);
		toppingPriceInput		= new JTextField(15);
		addPrepInput			= new JTextField(15);
		addCookInput			= new JTextField(15);
		addOvenInput			= new JTextField(15);
		
		menuAddButton			= new JButton("Add Item");
		menuBackButton			= new JButton("Back");
		menuRemoveButton		= new JButton("Remove Item");
		menuList				= new JList(menuButtonNames);
		menuListScroller		= new JScrollPane(menuList);


		nameInputPanel.add(addNameLabel);
		nameInputPanel.add(addNameInput);
		
		priceInputPanel.add(addPriceLabel);
		priceInputPanel.add(addPriceInput);

		toppingPriceInputPanel.add(toppingPriceLabel);
		toppingPriceInputPanel.add(toppingPriceInput);
		
		prepInputPanel.add(addPrepLabel);
		prepInputPanel.add(addPrepInput);
		
		cookInputPanel.add(addCookLabel);
		cookInputPanel.add(addCookInput);
		
		ovenInputPanel.add(addOvenLabel);
		ovenInputPanel.add(addOvenInput);
		
		addItemBox.add(nameInputPanel);
		addItemBox.add(priceInputPanel);
		addItemBox.add(toppingPriceInputPanel);
		addItemBox.add(prepInputPanel);
		addItemBox.add(cookInputPanel);
		addItemBox.add(ovenInputPanel);
		
		addItemBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		menuListScroller.setPreferredSize(new Dimension(200, 175));
		menuList.setLayoutOrientation(JList.VERTICAL);
		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//-Add action listener
		menuAddButton.addActionListener(this);
		menuBackButton.addActionListener(this);
		menuRemoveButton.addActionListener(this);
		
		southPaneMenu.add(menuAddButton);
		southPaneMenu.add(menuBackButton);
		southPaneMenu.add(menuRemoveButton);
		
		
		configMenuPanel.add(menuListScroller, BorderLayout.EAST);
		configMenuPanel.add(addItemBox, BorderLayout.WEST);
		configMenuPanel.add(southPaneMenu, BorderLayout.SOUTH);
		
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								ORDER PANEL									 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		orderPanel 				= new JPanel( new BorderLayout() );
		southPaneOrder			= new JPanel();
		
		orderPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		
		//-Create all the required menu buttons
		
		menuLayout = new GridLayout(q.db.getMenu().size(), 2);
		
		menuPanel = new JPanel( menuLayout );
		
		int cartSize = 0;
		
		cart 					= new DefaultListModel();
		cart2 					= new DefaultListModel();
		cartList				= new JList(cart);
		cartList2				= new JList(cart2);
		cartListScroller		= new JScrollPane(cartList);
		cartListScroller2		= new JScrollPane(cartList2);
		
		TitledBorder mb = new TitledBorder("   Cart   ");
		cartListScroller.setBorder(mb);
		mb.setTitleJustification(TitledBorder.CENTER);
		
		//-Set list settings
		cartListScroller.setPreferredSize(new Dimension(500, 500));
		cartList.setLayoutOrientation(JList.VERTICAL);
		cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cartListScroller2.setPreferredSize(new Dimension(500, 500));
		cartList2.setLayoutOrientation(JList.VERTICAL);
		cartList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//-Add components to homePanel
		orderPanel.add(cartListScroller, BorderLayout.EAST);
		
		updateMenu();
		/*
		for ( Object key : menuButtonNames.toArray() ) {
			
			JButton item = new JButton( (String)key );
			final String itemName = (String)key;
			item.addActionListener(this);
			if (itemName.equals("Large Pizza") | itemName.equals("Medium Pizza") | itemName.equals("Small Pizza")) {}
			else {
				item.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						//JNOTE debugging doublecheck here
						if (!itemName.equals("Large Pizza") | !itemName.equals("Medium Pizza") | !itemName.equals("Small Pizza")) {
							pendingItem = q.db.findItem(itemName);
							cart.addElement(pendingItem);
							cart2.addElement(pendingItem);
							pendingOrder.addItem(pendingItem);
							confirmSubmitButton.setEnabled(true);
							if (pendingItem != null) pendingItem = new Item(pendingItem,pendingOrder.getID());
							else System.out.println("Error, invalid menu item.");
						}
		       		}
				});
			}
			menuPanel.add(item); 
		}
	*/
		
		//-Create buttons and add action listener then add to orderPanel
		orderSubTotalButton		= new JButton("Sub-Total");
		orderCancelButton		= new JButton("Cancel");
		orderSubTotalButton.addActionListener(this);
		orderCancelButton.addActionListener(this);
		
		southPaneOrder.add(orderSubTotalButton);
		southPaneOrder.add(orderCancelButton);
		//orderPanel.add()
		orderPanel.add(menuPanel, BorderLayout.CENTER);
		orderPanel.add(southPaneOrder, BorderLayout.SOUTH);
		
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								TOPPINGS PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		toppingsPanel 			= new JPanel( new BorderLayout() );
		centerPaneToppings		= new JPanel( new GridLayout(1,2) );
		southPaneToppings		= new JPanel( new GridLayout(1,2) );
		
		toppingsPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		toppingSidePanel = new JPanel();
		
		//-Get toppings and convert to JButtons
		
		both = new JRadioButton("Both");
		left = new JRadioButton("Left");
		right = new JRadioButton("Right");
		
		toppingSide = new ButtonGroup();
		
		toppingSide.add(both);
		toppingSide.add(left);
		toppingSide.add(right);
		
		toppingSidePanel.add(new JLabel("Which Side of Pizza:"));
		toppingSidePanel.add(both);
		toppingSidePanel.add(left);
		toppingSidePanel.add(right);
		
		both.setSelected(true);
		
		toppingCart 			= new DefaultListModel();
		toppingCartList			= new JList(toppingCart);
		toppingCartListScroller	= new JScrollPane(toppingCartList);
		
		TitledBorder tb = new TitledBorder("   Toppings   ");
		toppingCartListScroller.setBorder(tb);
		tb.setTitleJustification(TitledBorder.CENTER);
		
		toppingCartListScroller.setPreferredSize(new Dimension(500, 500));
		toppingCartList.setLayoutOrientation(JList.VERTICAL);
		toppingCartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		Iterator<String> itr 	= q.db.toppings.iterator();
		toppingsList			= new JPanel( new GridLayout(q.db.toppings.size(), 1) );
		toppingsArray			= new JButton[q.db.toppings.size()];
		tGroup					= new ButtonGroup();
		int count3 = 0;
		
		while( itr.hasNext() ){
			final String superTemp = itr.next();
			toppingsArray[count3] = new JButton(superTemp);
			toppingsArray[count3].addActionListener(this);
			toppingsArray[count3].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					if ( both.isSelected() && !toppingCart.contains(superTemp + " B") ) { 
						toppingCart.removeElement(superTemp + " L");
						toppingCart.removeElement(superTemp + " R");
						toppingCart.addElement(superTemp + " B");
						updateItem(pendingItem,superTemp,'B');
					}
					if ( left.isSelected() && !toppingCart.contains(superTemp + " L") ) {
						toppingCart.removeElement(superTemp + " B");
						toppingCart.removeElement(superTemp + " R");
						toppingCart.addElement(superTemp + " L");
						updateItem(pendingItem,superTemp,'L');
					}
					if ( right.isSelected() && !toppingCart.contains(superTemp + " R") ) {
						toppingCart.removeElement(superTemp + " L");
						toppingCart.removeElement(superTemp + " B");
						toppingCart.addElement(superTemp + " R");
						updateItem(pendingItem,superTemp,'R');
					}
	       		}
			});
			toppingsList.add(toppingsArray[count3]);
			++count3;
		}
		
		
		//-Buttons
		toppingsSubmitButton	= new JButton("Add Pizza");
		toppingsBackButton	= new JButton("Back");
		
		//-Add action listeners
		toppingsSubmitButton.addActionListener(this);
		toppingsBackButton.addActionListener(this);
		
		//-Add components to toppingsPanel
		centerPaneToppings.add(toppingsList);
		//centerPaneToppings.add();
		southPaneToppings.add(toppingsSubmitButton);
		southPaneToppings.add(toppingsBackButton);
		toppingsPanel.add(centerPaneToppings, BorderLayout.CENTER);
		toppingsPanel.add(southPaneToppings, BorderLayout.SOUTH);
		toppingsPanel.add(toppingCartListScroller, BorderLayout.EAST);
		toppingsPanel.add(toppingSidePanel, BorderLayout.NORTH);
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								CONFIRM PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		confirmationPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		
		//-Buttons
		confirmSubmitButton		= new JButton("Confirm");
		confirmBackButton		= new JButton("Back");
		recieptTotalLabel 		= new JLabel("Total: ");
		
		//-Set action listener
		confirmSubmitButton.addActionListener(this);
		confirmBackButton.addActionListener(this);
		
		//-Add components to confirmationPanel
		southPaneConfirm.add(customerQuickPanel);
		recieptDualPanel.add(cartListScroller2);
		recieptDualPanel.add(recieptTotalLabel);
		southPaneConfirm.add(recieptDualPanel); //JNOTE: BRB making this panel and add cartListScroller2 and totals to it (frequently)
		southPaneConfirm.add(confirmSubmitButton);
		southPaneConfirm.add(confirmBackButton);
		confirmationPanel.add(southPaneConfirm);
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								RECEIPT PANEL								 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		receiptPanel.setBorder(new EmptyBorder(75, 75, 75, 75));
		
		//-Label, button
		receiptLabel 			= new JLabel("RECEIPT GENERATING FUNCTION GOES HERE");
		receiptButton			= new JButton("Ok");
		
		//-Add action listener
		receiptButton.addActionListener(this);
		
		//-Add components to receiptPanel
		receiptPanel.add(receiptLabel, BorderLayout.CENTER);
		receiptPanel.add(receiptButton, BorderLayout.SOUTH);
		
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		//								END PANEL									 //
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
		
		endPanel				= new JPanel( new BorderLayout() );
		centerPaneEnd			= new JPanel();
		
		endPanel.setBorder( new EmptyBorder(125, 125, 125, 125) );
		
		TitledBorder eptb = new TitledBorder("   Fin.   ");
		centerPaneEnd.setBorder(eptb);
		eptb.setTitleJustification(TitledBorder.CENTER);
		
		quit					= new JButton("Save & Quit");
		reportsButton 			= new JButton("View Report");
		
		quit.setPreferredSize(new Dimension(225, 100));
		reportsButton.setPreferredSize(new Dimension(225, 100));
		
		quit.addActionListener(this);
		reportsButton.addActionListener(this);
		
		centerPaneEnd.add(box.createRigidArea(new Dimension(700, 25)));
		centerPaneEnd.add(reportsButton);
		centerPaneEnd.add(box.createRigidArea(new Dimension(700, 20)));
		centerPaneEnd.add(quit);
		
		endPanel.add(centerPaneEnd, BorderLayout.CENTER);
}
	
	
// - - - - - - - - - - - - - - - END OF GUI COMPONENTS - - - - - - - - - - - - - - - //
	
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//									FUCNTIONS								 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	
	public Item updateItem(Item i, String topper, Character side) {
		if (q.db.toppings.contains(topper)) {
			i.addTopping(topper,side);
		}
		return i;
	
	}
	
	public void refresh() {
		getContentPane().invalidate();
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void updateLog(String s) {
			QListModel.addElement(s);
			this.refresh();
	}
	
	
	
	public void updateMenu() {
		menuPanel = new JPanel( menuLayout );
		
		for ( Object name : menuButtonNames.toArray() ) {
			JButton item = new JButton( (String)name );
			final String itemName = (String)name;
			item.addActionListener(this);
			if (itemName.equals("Large Pizza") | itemName.equals("Medium Pizza") | itemName.equals("Small Pizza")) {}
			else {
				item.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						if (!itemName.equals("Large Pizza") | !itemName.equals("Medium Pizza") | !itemName.equals("Small Pizza")) {
							pendingItem = q.db.findItem(itemName);
							cart.addElement(pendingItem);
							cart2.addElement(pendingItem);
							pendingOrder.addItem(pendingItem);
							confirmSubmitButton.setEnabled(true);
							if (pendingItem != null) pendingItem = new Item(pendingItem,pendingOrder.getID());
							else {} //System.out.println("Error, invalid menu item.");
						}
		       		}
				});
			}
			menuPanel.add(item);
			refresh();
		}
		orderPanel.repaint();
		menuPanel.validate();
		refresh();	
	}
	
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//								ACTION LISTENERS							 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	
	public void actionPerformed(ActionEvent e) {
		
		JButton temp =  (JButton)e.getSource();
		
		String buttonLabel = temp.getText();
		
		
		// = = = = = = = = = = = = = = = //
		// 	 START DAY  ->  HOME PANEL   //
		// = = = = = = = = = = = = = = = //
		if( e.getSource() == startDay ) {
			getContentPane().remove(startPanel);
			getContentPane().add(homePanel);
			numberInput.requestFocusInWindow();
			homePanel.getRootPane().setDefaultButton(searchButton);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	   CONFIG    ->   LOGIN      //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == configSys ){
			getContentPane().remove(startPanel);
			getContentPane().add(loginPanel);
			passInput.requestFocusInWindow();
			loginPanel.getRootPane().setDefaultButton(loginButton);
			refresh();
		}
		
		else if( e.getSource() == timeWarp) {
			q.speedUp(5);
			updateLog("Time has been sped up to: " + q.now());
			refresh();
		}
		
		else if( e.getSource() == lookupButton) {
			getContentPane().remove(homePanel);
			getContentPane().add(customerHistoryPanel);
			try {
				Customer tempCustomer = q.db.findCustomer( Long.valueOf(numberInput.getText()) );
				Stack<Order> tempStack = tempCustomer.getHistory();
				while (!tempStack.isEmpty()) { previousOrdersModel.addElement( tempStack.pop() ); }	
				refresh();
			} catch (Exception f) {
				getContentPane().remove(customerHistoryPanel);
				getContentPane().add(homePanel);
				JOptionPane.showMessageDialog(this, "<html>Please enter an <b>existing</b> customer's 10 digit" +
						" phone number without any spaces or dashes.</html>", "No Customer Found",
						JOptionPane.DEFAULT_OPTION);
			
			}
			refresh();
			/*
			try {JOptionPane.showMessageDialog(this, q.db.findCustomer( Long.valueOf(numberInput.getText()) ).stringOrders(), "Customer Infomration", JOptionPane.DEFAULT_OPTION);
			} catch (Exception f) {
				JOptionPane.showMessageDialog(this, "Please enter a valid 10 digit for an existing customer " +
						" phone number without any spaces or dashes.", "Invalid Customer",
						JOptionPane.DEFAULT_OPTION);
						numberInput.requestFocusInWindow();
						//JNOTE should also refocus on the input field
						
			} 
						//JNOTE: sadly customer history is never saved ergo this will always print no orders and just the customer if found
			*/

		}
		
		else if( e.getSource() == historyBackButton) {
			//JNOTE: what to do here, restore old remove curr
			getContentPane().remove(customerHistoryPanel);
			getContentPane().add(homePanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		//  SEARCH -> FIND/ADD CUSTOMER  //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == searchButton ){
			confirmSubmitButton.setEnabled(false);
			String tempSearchString = numberInput.getText();
			try {
				if (numberInput.getText().length() != 10) {
					JOptionPane.showMessageDialog(this, "Please enter a valid 10 digit" +
							" phone number without any spaces or dashes.", "Invalid Phone Number",
							JOptionPane.DEFAULT_OPTION);
				}
				//JNOTE: q.db.findCustomer is ran thrice here which is sub-optimal for later fixing
				else if(q.db.findCustomer( Long.valueOf(numberInput.getText()) ) != null) {
					Customer who = q.db.findCustomer( Long.valueOf(numberInput.getText()) );
					getContentPane().remove(homePanel);
					getContentPane().add(addCustomerPanel);
					nameField.setText(who.getName());
					phoneField.setText(String.valueOf(who.getPhone()));
					
					if (who.getLocation() == Location.RIT) 		rit.setSelected(true);
					if (who.getLocation() == Location.UR) 		uOfR.setSelected(true);
					if (who.getLocation() == Location.NAZARETH) nazareth.setSelected(true);
					if (who.getLocation() == Location.FISHER) 	stJohn.setSelected(true);
					if (who.getLocation() == Location.RWC) 		robWes.setSelected(true);
					if (who.getLocation() == Location.MCC) 		mcc.setSelected(true);
					refresh();
				}
				else{
					getContentPane().remove(homePanel);
					getContentPane().add(addCustomerPanel);
					nameField.requestFocusInWindow();
					phoneField.setText(numberInput.getText());
					refresh();
				}
				numberInput.setText("Enter a 10 Digit Phone Number");
				//searchButton.requestFocusInWindow(); // CHANGE THIS BACK EVENTUALLY		
			}
			catch (NumberFormatException nFE) {
				JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit" +
						" phone number without any spaces or dashes.", "Invalid Phone Number", JOptionPane.DEFAULT_OPTION);
			}
			
		}
		
		// = = = = = = = = = = = = = = = //
		// 	 SUBTOTAL  ->  CONFIRMATION  //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == orderSubTotalButton ){
			getContentPane().remove(orderPanel);
			getContentPane().add(confirmationPanel);
			//cart2.addElement("Total: $" +  pendingOrder.getTotal());
			recieptTotalLabel.setText("Total: $" +  pendingOrder.getTotal());
			//old total metohd did not work, must be integrated into it elsewhere?
			//JNOTE: here cart2 should get another line, inicating order total
			customerQuickPanel.removeAll();
			customerQuickPanel.add(new JLabel("Name: " + pendingOrder.getCustomer().getName()));
			customerQuickPanel.add(new JLabel("Phone: " + pendingOrder.getCustomer().getPhone()));
			customerQuickPanel.add(new JLabel("Location: " + pendingOrder.getCustomer().getLocation()));
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	 		ORDER CANCEL		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == orderCancelButton ){
			cart.clear();
			cart2.clear();
			getContentPane().remove(orderPanel);
			getContentPane().add(homePanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	 	 REQUEST TOPPINGS		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == toppingsSubmitButton ){
			//JNOTE: here toppings need to be added to pendingItem
			//or maybe they have been already?
			///*
			//cart.removeElement(pendingItem);
			cart.addElement(pendingItem);
			cart2.addElement(pendingItem);
			pendingOrder.addItem(pendingItem);
			confirmSubmitButton.setEnabled(true);
			//pendingItem = 
			//*/
			
			toppingCart.clear();
			getContentPane().remove(toppingsPanel);
			getContentPane().add(orderPanel);
			refresh();
		}
		// = = = = = = = = = = = = = = = //
		// 	 	 CANCEL TOPPINGS		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == toppingsBackButton ){
			toppingCart.clear();
			getContentPane().remove(toppingsPanel);
			getContentPane().add(orderPanel);
			refresh();
		}
		

		else if ( buttonLabel.equals("Small Pizza") ) {
			pendingItem = q.db.findItem("Small Pizza");
			Item tempItem = new Item(pendingItem,pendingOrder.getID());
			pendingItem = tempItem;
			getContentPane().remove(orderPanel);
			getContentPane().add(toppingsPanel);
			refresh();
		}
		else if ( buttonLabel.equals("Medium Pizza") ) {
			pendingItem = q.db.findItem("Medium Pizza");
			pendingItem = new Item(pendingItem,pendingOrder.getID());
			getContentPane().remove(orderPanel);
			getContentPane().add(toppingsPanel);
			refresh();
		}
		else if ( buttonLabel.equals("Large Pizza") ) {
			pendingItem = q.db.findItem("Large Pizza");
			pendingItem = new Item(pendingItem,pendingOrder.getID());
			getContentPane().remove(orderPanel);
			getContentPane().add(toppingsPanel);
			refresh();
		}

		// = = = = = = = = = = = = = = = //
		// 	 	 ADD CUSTOMER BUTTON	 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == addSubmitButton ){
			//JNOTE: here we need to first check if any of the five buttons for location have been selected
			// 			yes this has to be done manually since button groups don't support it otherwise
			// we also have to check for a name enter and that the phone number is still vaild
			// nor may the phone number be already in the system?
			//or skip those latter few checks by disabling the editing of the phonenumber on the add customer panel
			// cheaters test would be pull all values plus some location seleciting method (write in this file) and then
			//try to pass them directly to customer if it fails display a dialog saying invalid customer info
			// duplicate checking in DB would still be required and would require a different error check FIRST
			if (nameField.getText().length() < 3 | nameField.getText().length() > 99 | !nameField.getText().contains(" ")) {
				JOptionPane.showMessageDialog(this, "Please enter a first and last name to continue.", "Invalid Name Entered",
						JOptionPane.DEFAULT_OPTION);
			}
			
			/*
			//JNOTE: this check may be overkill or not even working test it edit: 
			//unncecessary test due to how gui functions should be replaced with a generic number reacceptance test/error dialog here
			else if (q.db.findCustomer( Long.valueOf(phoneField.getText()) ) != null) {
				JOptionPane.showMessageDialog(this, "A user already exists with that specified phone number, please try searching for it again.", "User Exists Already",
						JOptionPane.DEFAULT_OPTION);
			}
			*/
			
			else if (!(rit.isSelected() | uOfR.isSelected() | nazareth.isSelected() 
				| stJohn.isSelected() | robWes.isSelected() | mcc.isSelected())) {
				JOptionPane.showMessageDialog(this, "Error, you must select a location before to continue.",
				 		"No Location Selected", JOptionPane.DEFAULT_OPTION);
			}
			else {
				getContentPane().remove(addCustomerPanel);
				Location location;
				if 		(rit.isSelected())		location = Location.RIT;
				else if (uOfR.isSelected()) 	location = Location.UR;
				else if (nazareth.isSelected()) location = Location.NAZARETH;
				else if (stJohn.isSelected())	location = Location.FISHER;
				else if (robWes.isSelected())	location = Location.RWC;
				else if (mcc.isSelected())		location = Location.MCC;
				else 							location = Location.RIT;
				q.db.addCustomer(new Customer(nameField.getText(),Long.valueOf(phoneField.getText()), location));
				pendingOrder = new Order(q.db.findCustomer( Long.valueOf(phoneField.getText()) ), q.db.ordersIn, q.now());
				//System.out.println("DEBUG: Allegedly on order #: " + q.db.ordersIn);
				//System.out.println("DEBUG: pendingOrder.getID() yields: " + pendingOrder.getID() );
				Order tempOrder = new Order(pendingOrder);
				pendingOrder = tempOrder;
				phoneField.setText("");
				lGroup.clearSelection();
				//JNOTE: the created customer needs to be put here into the db
				updateMenu();
				getContentPane().add(orderPanel);
				refresh();
			}
		}
		
		// = = = = = = = = = = = = = = = //
		// 	 CANCEL ADDING CUSTOMER		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == addCancelButton ){
			nameField.setText("");
			phoneField.setText("");
			lGroup.clearSelection();
			getContentPane().remove(addCustomerPanel);
			getContentPane().add(homePanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	  CONFIRM CONFIG CHANGES	 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == configSubmitButton ){
			int input = 0;
			String cooks = cooksInput.getText();
			String drivers = driversInput.getText();
			String ovens = ovensInput.getText();
			try{
				if (cooks.compareTo("") != 0){
					input = Integer.parseInt(cooks);
					q.db.setCooks(input);
				}
				if (drivers.compareTo("") != 0){
					input = Integer.parseInt(drivers);
					q.db.setDrivers(input);
				}
				if (ovens.compareTo("") != 0){
					input = Integer.parseInt(ovens);
					q.db.setOvens(input);
				}
			}
			catch(java.lang.NumberFormatException notNumber){
				JOptionPane.showMessageDialog(this, "Please enter a numeric value",
						"Invalid Amount", JOptionPane.DEFAULT_OPTION);
			}
			getContentPane().remove(configSysPanel);
			getContentPane().add(startPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	    EDIT MENU BUTTON		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == configMenuButton ){
			getContentPane().remove(configSysPanel);
			getContentPane().add(configMenuPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	   		  LOGIN				 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == loginButton ){
			passInput.setText("");
			getContentPane().remove(loginPanel);
			getContentPane().add(configSysPanel);
			cooksInput.requestFocusInWindow();
			configSysPanel.getRootPane().setDefaultButton(configSubmitButton);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	   	   LOGIN  CANCEL		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == loginCancelButton ){
			passInput.setText("");
			getContentPane().remove(loginPanel);
			getContentPane().add(startPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	   CANCEL CONFIG CHANGES	 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == configCancelButton ){
			cooksInput.setText(Integer.toString(q.db.cooks));
			driversInput.setText(Integer.toString(q.db.drivers));
			ovensInput.setText(Integer.toString(q.db.ovens));
			getContentPane().remove(configSysPanel);
			getContentPane().add(startPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	   		ADD MENU ITEM		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == menuAddButton ){
			
			try {
				String name 	= addNameInput.getText();
				String price 	= addPriceInput.getText();
				String topPrice = toppingPriceInput.getText();
				String prep		= addPrepInput.getText();
				String cook 	= addCookInput.getText();
				String oven 	= addOvenInput.getText();
			
				if (!name.equals("")) {
					double numPrice = 0;
					int numPrep = 0;
					int numCook = 0;
					int numOven = 0;
					double numTop = 0;
					if (!price.equals("")){
						numPrice = Integer.valueOf((Integer.parseInt(price))).doubleValue();
						}
						if (!prep.equals("")){
							numPrep = Integer.parseInt(prep);
						}
						if (!cook.equals("")){
							numCook = Integer.parseInt(cook);
						}
						if (!oven.equals("")){
							numOven = Integer.parseInt(oven);
						}
						if (!topPrice.equals("")){
							numTop = Integer.valueOf((Integer.parseInt(topPrice))).doubleValue();
						}
						Item newItem =	new Item(name, name, numPrice, numPrep, numCook, numOven, numTop, 0);
					
						q.db.addItem(newItem);
						menuButtonNames.addElement(name);
						menuLayout.setRows(menuLayout.getRows() + 1);
						updateMenu();
						System.out.print("New item added!");
						
						//menuLayout.setRows(menuLayout.getRows()+1);
						//updateMenu();
						refresh();
					}
					else{
						JOptionPane.showMessageDialog(this, "Please enter a name for the item",
								"No name", JOptionPane.DEFAULT_OPTION);
					}
				}
				catch(java.lang.NumberFormatException notNumber){
					JOptionPane.showMessageDialog(this, "Please enter a numerical value",
							"Invalid Amount", JOptionPane.DEFAULT_OPTION);
				}
			
			}
		
		// = = = = = = = = = = = = = = = //
		// 	    LEAVE MENU EDITOR		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == menuBackButton ) {
			getContentPane().remove(configMenuPanel);
			getContentPane().add(configSysPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	  	 REMOVE MENU ITEM		 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == menuRemoveButton ) {
			if (menuList.getSelectedValue() != null) {
				q.db.removeItem((String)menuList.getSelectedValue());
				//menuList.remove(menuList.getSelectedIndex());
				menuButtonNames.removeElement(menuList.getSelectedValue() );
				menuLayout.setRows(menuLayout.getRows()-1);
				//menuButtonNames.validate();
				updateMenu();
				refresh();
			}  
		}
		
		
		// = = = = = = = = = = = = = = = //
		// 	   	  CONFIRM ORDER			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == confirmSubmitButton ) {
			getContentPane().remove(confirmationPanel);
			cart.clear();
			cart2.clear();
			//JNOTE: need anymore order wiping here?
			q.locked = true;
			q.orderQ.add(pendingOrder);
			q.db.ordersIn++;
			for (Item item: pendingOrder.getItems()) {
				Date readyTime = q.now();
				readyTime.setTime(readyTime.getTime()+item.getPrepTime()*6);
				item.setNextAdvance(readyTime);
				q.itemQ.add(item);
			}
			q.db.addOrder(pendingOrder);
			//order's customer needs to be found in the db and then
			pendingOrder.getCustomer().addOrder(pendingOrder);
			//pendingOrder
			//JNOTE: BRB
			q.locked = false;
			this.refresh();
			getContentPane().add(homePanel); //JNOTE: used to go to "receiptPanel"
			//q.pdsgui.updateLog(q.db.messages);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	      DECLINE ORDER			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == confirmBackButton ){
			getContentPane().remove(confirmationPanel);
			//need any more advanced clean-up here?
			//JNOTE: Order needs to be wiped here and maybe wipe the main add customer dialog?
			getContentPane().add(orderPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	    	  RECEIPT			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == receiptButton ){
			getContentPane().remove(receiptPanel);
			getContentPane().add(homePanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	    	  END DAY			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == endDay ){
			getContentPane().remove(homePanel);
			getContentPane().add(endPanel);
			refresh();
		}
		
		// = = = = = = = = = = = = = = = //
		// 	    	  REPORT			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == reportsButton ){
			JOptionPane.showMessageDialog(this, "Daily Report: " + dailyReport() , 
					"DAILY REPORT FUNCTION GOES HERE", JOptionPane.DEFAULT_OPTION);
		}
		
		// = = = = = = = = = = = = = = = //
		// 	    	START QUIT			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == startQuit ){
			try { PrefObj.putObject( prefs, "db", q.db );
			} catch (Exception ex) {
				System.out.println("Fatal error! DB not saved");
				System.exit(0);
			}
			System.exit(0);
		}
		// = = = = = = = = = = = = = = = //
		// 	   			QUIT			 //
		// = = = = = = = = = = = = = = = //
		else if( e.getSource() == quit ){
			// db is stored here to preferences before exiting
			db.cooks = Integer.valueOf(cooksInput.getText());
			db.drivers = Integer.valueOf(driversInput.getText());
			db.ovens = Integer.valueOf(ovensInput.getText());
			try { PrefObj.putObject( prefs, "db", q.db );
			} catch (Exception ex) {
				System.out.println("Fatal error! DB not saved.");
				System.exit(0);
			}
			System.exit(0);
			
		}		
	}
	
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	//									MAIN									 //
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = //
	
	public String dailyReport() {
		String report = "";
		for( int i= startedOnOrder; i < endedOnOrder; i++) {
			System.out.println("DEBUG: This ran at least once 1500");
			Order tempOrder = q.db.history.get(Long.valueOf(i));
			report += tempOrder;
		}
		return report;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        PDSGUI new_instance = new PDSGUI();
				
		        new_instance.setVisible(true);
		    }
		});
	}
}
