import java.util.*;
class Example {
    private static final Scanner input = new Scanner(System.in);
    private static String [] cred = { "Dasun", "1234" };
	private static String [][] suppliers = {};
	private static String [] addItemCategory = {};
	private static String[][] addItemDetails1 = {};
	private static double[][] addItemDetails2 = {};
    
    private static final void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }
   
    public static void loginPage() {
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                              LOGIN PAGE                               |");
        System.out.println("+-----------------------------------------------------------------------+");
    }

    public static boolean checkUserNameValidity(String userName) {
        return cred[0].equals(userName);
    }

    public static boolean checkPw(String pw) {
        return cred[1].equals(pw);
    }

    public static void homePage() {
        clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                 WELCOME TO IJSE STOCK MANAGEMENT SYSTEM               |");
        System.out.println("+-----------------------------------------------------------------------+");
    }

    public static void optionList() {
		System.out.println();
        System.out.print("[1] Change the Credentials\t\t\t");
        System.out.println("[2] Supplier Manage");
        System.out.print("[3] Stock Manage\t\t\t\t");
        System.out.println("[4] Log out");
        System.out.println("[5] Exit the System");

        System.out.println();
        System.out.print("Enter an option to continue > ");
        int opt = input.nextInt();

        switch (opt) {
            case 1:
                changeCredential();
                break;
            case 2:
                supplierManage();
                break;
            case 3:
                stockManage();
                break;
            case 4:
                logOut();
                break;
            default:
                exit();
        }
    }

    public static void changeCredential() {
        clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                         CHANGE CREDENTIAL                             |");
        System.out.println("+-----------------------------------------------------------------------+");

        while (true) {
            System.out.println();
            System.out.print("Please enter the current username to verify it's you: ");
            String currentUsername = input.next();

            boolean isValidUser = checkUserNameValidity(currentUsername);
            if (isValidUser) {
                System.out.println("Hey " + currentUsername);

                while (true) {
                    System.out.println();
                    System.out.print("Enter your current password: ");
                    String currentPassword = input.next();

                    boolean isCorrectPassword = checkPw(currentPassword);
                    if (isCorrectPassword) {
                        System.out.print("Enter your new password: ");
                        String newPassword = input.next();

                        cred[1] = newPassword;
                        System.out.println();
                        System.out.print("Password changed successfully! ");

                        while (true) {
                            System.out.print("Do you want to go back to the home page (Y/N): ");
                            char op = input.next().charAt(0);
                            if (op == 'Y' || op == 'y') {
                                clearConsole();
                                homePage();
                                optionList();
                                return;
                            } else if (op == 'N' || op == 'n') {
                                return;
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid username. Please try again.");
            }
        }
    }

    public static void supplierManage() {
        clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                          SUPPLIER MANAGE                              |");
        System.out.println("+-----------------------------------------------------------------------+");

        supplierOptions();
    }

    public static void supplierOptions() {
		System.out.println();
        System.out.print("[1] Add Supplier\t\t\t\t");
        System.out.println("[2] Update Supplier");
        System.out.print("[3] Delete Supplier\t\t\t\t");
        System.out.println("[4] View Suppliers");
        System.out.print("[5] Search Supplier\t\t\t\t");
        System.out.println("[6] Home Page");

        System.out.println();
        System.out.print("Enter an option to continue > ");
        int opt = input.nextInt();

        switch (opt) {
            case 1:
                addSupplier();
                break;
            case 2:
                updateSupplier();
                break;
            case 3:
                deleteSupplier();
                break;
            case 4:
                viewSupplier();
                break;
            case 5:
                searchSupplier();
                break;
            case 6:
                homePage();
                optionList();
                return;
            default:
                System.out.println("Not an option. try again.");
        }
        supplierOptions();
    }
	
	public static String [][] grow(String [][] suppliers){
		String [][] temp = new String [suppliers.length + 1][2];
			for (int i = 0; i < suppliers.length; i++){
				temp[i] = suppliers[i];
			}
			return temp;
	}
	
    public static void addSupplier() {
        clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                             ADD SUPPLIER                              |");
        System.out.println("+-----------------------------------------------------------------------+");
		
		L1:
		do{
			System.out.println();
			System.out.print("Supplier Id : ");
			String id = input.next();
			for (int i = 0; i < suppliers.length; i++){
				if (id.equals(suppliers[i][0])){
					System.out.println("Id alredy exists. try another supplier Id!\n");
					continue L1;
				}
			}
			System.out.print("Supplier Name : ");
			String name = input.next();
			
			suppliers = grow(suppliers);
			suppliers[suppliers.length -1][0] = id;
			suppliers[suppliers.length - 1][1] = name;
			System.out.println();
			System.out.print("Supplier added successfully! ");
			
			System.out.print("Do you want to add another supplier (Y/N): ");
            char op = input.next().charAt(0);            
            if (op == 'N' || op == 'n') {
					clearConsole();
					supplierManage();
					supplierOptions();
			}
		} while (true); 
    }
    
    public static void updateSupplier(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           UPDATE SUPPLIER                                |");
		System.out.println("+--------------------------------------------------------------------------+");
		
		boolean supplierFound = false;
		L1:
		do {
			System.out.println();
			System.out.print("Supplier Id: ");
			String id = input.next();
			supplierFound = false;

			for (int i = 0; i < suppliers.length; i++) {
				if (id.equals(suppliers[i][0])) {
					supplierFound = true;
					System.out.println("Supplier name: " + suppliers[i][1]);
					System.out.print("Enter a new supplier name: ");
					String newName = input.next();
					suppliers[i][1] = newName;
				}
			}
			if (!supplierFound) {
				System.out.println("can't find supplier. Please try again!");
				continue L1;
			}
			System.out.println();
			System.out.print("Supplier updated succesfully! Do you want to update another supplier (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
				clearConsole();
				supplierManage();
				supplierOptions();
				return;
			}
		} while (true);
	}
	
	public static void deleteSupplier(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           DELETE SUPPLIER                                |");
		System.out.println("+--------------------------------------------------------------------------+");

		boolean supplierFound = false;
		L1:
		do {
			System.out.println();
			System.out.print("Supplier Id: ");
			String id = input.next();
			supplierFound = false;

			for (int i = 0; i < suppliers.length; i++) {
				if (id.equals(suppliers[i][0])) {
					supplierFound = true;
					String[][] temp = new String[suppliers.length - 1][suppliers[0].length];
					
                int k = 0;
                for (int j = 0; j < suppliers.length; j++) {
                    if (j != i) {
                        temp[k++] = suppliers[j];
                    }
                }
					suppliers = temp;
					System.out.println("Supplier deleted successfully!");
					break;
				}
			}
			if (!supplierFound) {
				System.out.println("can't find supplier. Please try again!");
				continue L1;
			}
			System.out.print("Do you want to delete another supplier (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
				clearConsole();
				supplierManage();
				supplierOptions();
				return;
			}
		} while (true);
	}
	
	public static void viewSupplier(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           VIEW SUPPLIER                                  |");
		System.out.println("+--------------------------------------------------------------------------+");
		
		System.out.println();
		System.out.println("+-----------------------+-------------------------+");
		System.out.printf("|      %-10s      |    %-19s  |\n","SUPPLIER ID","SUPPLIER NAME");
		System.out.println("+-----------------------+-------------------------+");
		
			for (int i = 0; i < suppliers.length; i++){
				System.out.printf("|      %-10s       |      %-19s|\n",suppliers[i][0],suppliers[i][1]);
			}
		System.out.println("+-----------------------+-------------------------+");
        while (true) {
            System.out.print("Do you want to go back to the supplier manage page (Y/N): ");
            char op = input.next().charAt(0);
            if (op == 'Y' || op == 'y') {
				clearConsole();
				supplierManage();
                supplierOptions();
                return;
            } else if (op == 'N' || op == 'n') {
                exit();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
	}
	
	public static void searchSupplier(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           SEARCH SUPPLIER                                |");
		System.out.println("+--------------------------------------------------------------------------+");

		boolean supplierFound = false;
		L1:
		do {
			System.out.println();
			System.out.print("Supplier Id: ");
			String id = input.next();
			supplierFound = false;

			for (int i = 0; i < suppliers.length; i++) {
				if (id.equals(suppliers[i][0])) {
					supplierFound = true;
					System.out.println("Supplier name: " + suppliers[i][1]);
				}
			}
			if (!supplierFound) {
				System.out.println("can't find supplier id. Please try again!");
				continue L1;
			}
			System.out.print("Do you want to add another find (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
				clearConsole();
				supplierManage();
				supplierOptions();
				return;
			}
		} while (true);				
	}

    public static void stockManage() {
        clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                             STOCK MANAGEMENT                          |");
        System.out.println("+-----------------------------------------------------------------------+");
        
        stockManageOptions();
    }
        
    public static void stockManageOptions() {
		System.out.println();
        System.out.print("[1] Mange Item Categories\t\t\t\t");
        System.out.println("[2] Add Item");
        System.out.print("[3] Get Item Supplier Wise\t\t\t\t");
        System.out.println("[4] View Items");
        System.out.print("[5] Rank Items Per Unit Price\t\t\t\t");
        System.out.println("[6] Home Page");

        System.out.println();
        System.out.print("Enter an option to continue > ");
        int opt = input.nextInt();

        switch (opt) {
            case 1:
                manageItemCategories();
                break;
            case 2:
                addItem();
                break;
            case 3:
                getItemSupplierWise();
                break;
            case 4:
                viewItems();
                break;
            case 5:
                rankItemsPerUnitPrice();
                break;
            case 6:
                homePage();
                optionList();
                return;
            default:
                System.out.println("Not an option. try again.");
        }
        stockManageOptions();
    }
    
    public static void manageItemCategories(){
		clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                          MANAGE ITEM CATAGORY                         |");
        System.out.println("+-----------------------------------------------------------------------+");
		
		itemOptionList();
	}
	
	public static void itemOptionList(){
		System.out.println();
        System.out.print("[1] Add New Item Category\t\t\t");
        System.out.println("[2] Delete Item Catogory");
        System.out.print("[3] Update Item Catogory\t\t\t");
        System.out.println("[4] Stock Managenment");

        System.out.println();
        System.out.print("Enter an option to continue > ");
        int opt = input.nextInt();

        switch (opt) {
            case 1:
                addNewItemCategory();
                break;
            case 2:
                deleteItemCategory();
                break;
            case 3:
                updateItemCatogory();
                break;
            case 4:
				clearConsole();
                stockManage();
                stockManageOptions();
                break;
            default:
                System.out.println("Not an option. try again.");
        }
        itemOptionList();				
	}

	public static String []growItem(String [] addItemCategory){
		String [] temp = new String [addItemCategory.length + 1];
			for (int i = 0; i < addItemCategory.length; i++){
				temp[i] = addItemCategory[i];
			}
			return temp;
	}
	
	public static void addNewItemCategory(){
		clearConsole();
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|                          ADD ITEM CATAGORY                            |");
        System.out.println("+-----------------------------------------------------------------------+");
        
		L1:
		do{
			System.out.println();
			System.out.print("Enter the new item category : ");
			String cat = input.next();
			for (int i = 0; i < addItemCategory.length; i++){
				if (cat.equals(addItemCategory[i])){
					System.out.println("category alredy exists. try another category.\n");
					continue L1;
				}
			}
			addItemCategory = growItem(addItemCategory);
			addItemCategory[addItemCategory.length -1] = cat;
			System.out.println();
			System.out.print("added successfully! ");
			
			System.out.print("Do you want to add another category (Y/N): ");
            char op = input.next().charAt(0);      
            if (op == 'N' || op == 'n') {
				clearConsole();
				manageItemCategories();
				itemOptionList();
			}
		} while (true);       
	}
	
	public static void deleteItemCategory() {
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           DELETE ITEM CATEGORY                           |");
		System.out.println("+--------------------------------------------------------------------------+");

		boolean categoryFound = false;
		L1: do {
			System.out.println();
			System.out.print("Enter item category: ");
			String cat = input.next();
			categoryFound = false;

			for (int i = 0; i < addItemCategory.length; i++) {
				if (cat.equals(addItemCategory[i])) {
					categoryFound = true;
					String[] temp = new String[addItemCategory.length - 1];

					int k = 0;
					for (int j = 0; j < addItemCategory.length; j++) {
						if (j != i) {
							temp[k++] = addItemCategory[j];
						}
					}
					addItemCategory = temp;
					System.out.println("Category deleted successfully!");
					break;
				}
			}
			if (!categoryFound) {
				System.out.println("Can't find category. Please try again!");
				continue L1;
			}
			System.out.print("Do you want to delete another category (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
				clearConsole();
				manageItemCategories();
				itemOptionList();
				return;
			}
		} while (true);
	}
	
	public static void updateItemCatogory(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           UPDATE ITEM CATOGORY                           |");
		System.out.println("+--------------------------------------------------------------------------+");

		boolean categoryFound = false;
		L1:
		do {
			System.out.println();
			System.out.print("Enter a Catogory to Update: ");
			String cat = input.next();
			categoryFound = false;

			for (int i = 0; i < addItemCategory.length; i++) {
				if (cat.equals(addItemCategory[i])) {
					categoryFound = true;
					System.out.print("Enter a new category name: ");
					String newName = input.next();
					addItemCategory[i] = newName;
				}
			}
			if (!categoryFound) {
				System.out.println("can't find category. Please try again!");
				continue L1;
			}
			System.out.print("Category updated succesfully! Do you want to update another category (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
                clearConsole();
				manageItemCategories();
                itemOptionList();
				return;
			}
		} while(true);
	}

	public static void addItem() {
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           ADD ITEM                                       |");
		System.out.println("+--------------------------------------------------------------------------+");

		if (addItemCategory.length == 0) {
			System.out.println();
			System.out.println("Oops! It seems like you don't have any item categories in the system.");
			System.out.print("Do you want to add a new item category? (Y/N): ");
			char op = input.next().charAt(0);

			if (op == 'Y' || op == 'y') {
				clearConsole();
				manageItemCategories();
				itemOptionList();
			} else {
				exit();
			}
		}
		if (suppliers.length == 0) {
			System.out.println();
			System.out.println("Oops! It seems like you don't have any suppliers in the system.");
			System.out.print("Do you want to add a new supplier? (Y/N): ");
			char op = input.next().charAt(0);

			if (op == 'Y' || op == 'y') {
				clearConsole();
				addSupplier();
			} else {
				exit();
			}
		}
		L1:
		do {
			System.out.println();
			System.out.print("Item code: ");
			String itemCode = input.next();
				for (int i = 0; i < addItemDetails1.length; i++) {
					if (itemCode.equals(addItemDetails1[i][0])) {
						System.out.println("Item Code already exists. Try another Item Code.");
					continue L1;
				}
			}
			System.out.println();
			System.out.println("Supplier list:");
			System.out.println("+------------------+-----------------------+-------------------------+");
			System.out.printf("|      %4s        |      %-10s      |    %-19s  |\n", "#", "SUPPLIER ID", "SUPPLIER NAME");
			System.out.println("+------------------+-----------------------+-------------------------+");
			int rowCount1 = 1;
			for (int i = 0; i < suppliers.length; i++) {
				System.out.printf("|      %4d        |      %-10s       |      %-19s|\n", rowCount1, suppliers[i][0], suppliers[i][1]);
				rowCount1++;
			}
			System.out.println("+------------------+-----------------------+-------------------------+");
			System.out.println();
			System.out.print("Enter the supplier number > ");
			rowCount1 = input.nextInt();
			
			System.out.println();
			System.out.println("Item Categories:");
			System.out.println("+------------------+-------------------------+");
			System.out.printf("|      %4s        |    %-19s  |\n","#","CATEGORY NAME");
			System.out.println("+------------------+-------------------------+");
			int rowCount2 = 1;
			for (int i = 0; i < addItemCategory.length; i++) {
				System.out.printf("|      %4d        |      %-19s|\n",rowCount2,addItemCategory[i]);
				rowCount2++;
			}
			System.out.println("+------------------+-------------------------+");
			System.out.println();
			System.out.print("Enter the category number > ");
			rowCount2 = input.nextInt();
			
			System.out.println();	
			System.out.print("Description: ");
			String description = input.next();
			System.out.print("Unit Price: ");
			double unitPrice = input.nextDouble();
			System.out.print("Qty On Hand: ");
			double qtyOnHand = input.nextDouble();

			String[][] tempItemDetail1 = new String[addItemDetails1.length + 1][4];
			for (int i = 0; i < addItemDetails1.length; i++) {
				for (int j = 0; j < addItemDetails1[i].length; j++) {
					tempItemDetail1[i][j] = addItemDetails1[i][j];
				}
			}
			tempItemDetail1[addItemDetails1.length][0] = itemCode;
			tempItemDetail1[addItemDetails1.length][1] = description;
			tempItemDetail1[addItemDetails1.length][2] = suppliers[rowCount1 - 1][0];
			tempItemDetail1[addItemDetails1.length][3] = addItemCategory[rowCount2 - 1];
			addItemDetails1 = tempItemDetail1;

			double[][] tempItemDetail2 = new double[addItemDetails2.length + 1][2];
			for (int i = 0; i < addItemDetails2.length; i++) {
				for (int j = 0; j < addItemDetails2[i].length; j++) {
					tempItemDetail2[i][j] = addItemDetails2[i][j];
				}
			}
			tempItemDetail2[addItemDetails2.length][0] = unitPrice;
			tempItemDetail2[addItemDetails2.length][1] = qtyOnHand;
			addItemDetails2 = tempItemDetail2;

			System.out.print("Item Added Successfully! Do you want to add another item? (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'N' || op == 'n') {
				clearConsole();
				stockManage();
				stockManageOptions();
			} else {
				addItem();
			}
		} while (true);
	}

	public static void getItemSupplierWise() {
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           SEARCH SUPPLIER                                |");
		System.out.println("+--------------------------------------------------------------------------+");

		if (suppliers.length == 0) {
			System.out.println();
			System.out.println("Oops! It seems like you don't have any suppliers in the system.");
			System.out.print("Do you want to add a new supplier? (Y/N): ");
			char op = input.next().charAt(0);

			if (op == 'Y' || op == 'y') {
				clearConsole();
				addSupplier();
			} else {
				exit();
			}
		} else {
			boolean supplierFound = false;
			L1: do {
				System.out.println();
				System.out.print("Supplier Id: ");
				String id = input.next();
				supplierFound = false;

			for (int i = 0; i < suppliers.length; i++) {
				if (id.equals(suppliers[i][0])) {
					supplierFound = true;
					System.out.println("Supplier name: " + suppliers[i][1]);
					System.out.println();
					System.out.println("+-----------+-----------------+------------+--------------+-----------------+");
					System.out.printf("| %-8s | %-15s | %-10s | %-12s | %-15s |\n", "ITEM CODE", "  DESCRIPTION", "UNIT PRICE", "QTY ON HAND", "   CATEGORY");
					System.out.println("+-----------+-----------------+------------+--------------+-----------------+");
					
					for (int k = 0; k < addItemDetails1.length; k++) {
						if (id.equals(addItemDetails1[k][2])) {
							System.out.printf("| %-8s  | %-15s |  %-10.2f|  %-12.2f|  %-15s|\n", addItemDetails1[k][0], addItemDetails1[k][1], addItemDetails2[k][0], addItemDetails2[k][1], addItemDetails1[k][3]);
						}
					}
					
					System.out.println("+-----------+-----------------+------------+--------------+-----------------+");
					break L1;
				}
			}
				if (!supplierFound) {
					System.out.println("Invalid Supplier ID. Please try again.");
				}
			} while (true);
			System.out.print("Do you want to search a new supplier? (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'Y' || op == 'y') {
			clearConsole();
			getItemSupplierWise();
			}else if(op == 'N' || op == 'n'){
			clearConsole();
			stockManage();
			stockManageOptions();
			}	
		}
	}

	public static void viewItems(){
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           VIEW ITEM                                      |");
		System.out.println("+--------------------------------------------------------------------------+");		

		for (int i = 0; i < addItemCategory.length; i++){
			System.out.println("\n\n" + addItemCategory[i] + ":");
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");
			System.out.printf("|     %4s        |  %-8s  |   %-15s|   %-10s|     %-12s|    %-15s|\n","SID","CODE","DESC","PRICE","QTY","CAT");
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");
				for (int j = 0; j < addItemDetails1.length ; j++){
					if (addItemCategory[i].equals(addItemDetails1[j][3])){
						System.out.printf("|     %4s        |  %-8s  |   %-15s|   %-10s|     %-12s|    %-15s|\n",addItemDetails1[j][2],addItemDetails1[j][0],addItemDetails1[j][1],addItemDetails2[j][0],addItemDetails2[j][1],addItemDetails1[j][3]);
					}
				}
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");
		}
			System.out.print("Do you want to go back to the stock manage? (Y/N): ");
			char op = input.next().charAt(0);
			if (op == 'Y' || op == 'y') {
			clearConsole();
			stockManage();
			stockManageOptions();
			getItemSupplierWise();
			}else if(op == 'N' || op == 'n'){
			exit();
			}
	}
	
	public static void sortByPrice() {
		int itemCount = addItemDetails1.length;
		
		String[][] tempItemDetail1 = new String[itemCount][4];
		double[][] tempItemDetail2 = new double[itemCount][2];
		for (int i = 0; i < itemCount; i++) {
			tempItemDetail1[i][0] = addItemDetails1[i][0];
			tempItemDetail1[i][1] = addItemDetails1[i][1];
			tempItemDetail1[i][2] = addItemDetails1[i][2];
			tempItemDetail1[i][3] = addItemDetails1[i][3];
			tempItemDetail2[i][0] = addItemDetails2[i][0];
			tempItemDetail2[i][1] = addItemDetails2[i][1];
		}
		for (int i = 0; i < itemCount - 1; i++) {
			for (int j = 0; j < itemCount - i - 1; j++) {
				if (tempItemDetail2[j][0] > tempItemDetail2[j + 1][0]) {
					// Swap prices in tempItemDetail2
					double tempPrice = tempItemDetail2[j][0];
					tempItemDetail2[j][0] = tempItemDetail2[j + 1][0];
					tempItemDetail2[j + 1][0] = tempPrice;
					// Swap details in tempItemDetail1
					String[] tempDetails = tempItemDetail1[j];
					tempItemDetail1[j] = tempItemDetail1[j + 1];
					tempItemDetail1[j + 1] = tempDetails;
				}
			}
		}
		addItemDetails1 = tempItemDetail1;
		addItemDetails2 = tempItemDetail2;
	}

	public static void rankItemsPerUnitPrice() {
		clearConsole();
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println("|                           RANKED UNIT PRICE                              |");
		System.out.println("+--------------------------------------------------------------------------+");
		System.out.println();
		
		sortByPrice();
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");
			System.out.printf("|     %4s        |  %-8s  |   %-15s|   %-10s|     %-12s|    %-15s|\n","SID","CODE","DESC","PRICE","QTY","CAT");
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");
		for (int i = 0; i < addItemDetails1.length; i++) {
			System.out.printf("|     %4s        |  %-8s  |   %-15s|   %-10s|     %-12s|    %-15s|\n",addItemDetails1[i][2],addItemDetails1[i][0], addItemDetails1[i][1],addItemDetails2[i][0], addItemDetails2[i][1], addItemDetails1[i][3]);
		}
			System.out.println("+-----------------+------------+------------------+-------------+-----------------+-------------------+");

		System.out.print("Do you want to go back to the stock manage? (Y/N): ");
		char op = input.next().charAt(0);
		if (op == 'Y' || op == 'y') {
			clearConsole();
			stockManage();
			stockManageOptions();
			getItemSupplierWise();
		}else if(op == 'N' || op == 'n'){
			exit();
			}
		}
	
    public static void logOut() {
        clearConsole();
        main(null);
    }
    public static void exit() {
        System.exit(0);
    }
    public static void main(String args[]) {
        loginPage();
        while (true) {
            System.out.println();
            System.out.print("Enter the username: ");
            String userName = input.next();

            boolean isMatched = checkUserNameValidity(userName);
            if (isMatched) {
                while (true) {
                    System.out.println();
                    System.out.print("Enter the password: ");
                    String password = input.next();

                    boolean isCorrected = checkPw(password);
                    if (isCorrected) {
                        homePage();
                        optionList();
                        break;
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
                break;
            } else {
                System.out.println("Invalid username. Please try again.");
            }
        }
    }
}
