import java.time.LocalDateTime;  
import java.io.*;
import java.util.*;
/**
 * Main class for group project
 *
 * @author Abdel Hamid Shehata, Alex Barbuzza, Zac Zywusco
 * @version 12/3/2019
 */
public class Main
{
    public static void main(String[] args)
    {
        //objects and variables needed for program functionality
        ReaderWriter readwrite = new ReaderWriter("ReadWrite");
        String filename;
        Scanner input = new Scanner(System.in);
        int a = 0;
        int b = 0;

        //ArrayLists for each type of account, as well as one for all accounts
        ArrayList<LoginAccount> systemAccounts = new ArrayList<LoginAccount>();
        ArrayList<OfficeMan> officeManagerAccounts = new ArrayList<OfficeMan>();
        ArrayList<SalesAssoc> salesAssocAccounts = new ArrayList<SalesAssoc>();
        ArrayList<WHMan> WHManAccounts = new ArrayList<WHMan>();

        //arraylist of SalesVan objects, as well as an arraylist containing all salesvans and the main warehouse
        ArrayList<SalesVan> fleet = new ArrayList<SalesVan>();
        ArrayList<WareHouse> warehouses = new ArrayList<WareHouse>();

        //reading the main warehouse's inventory from the text file, then adds it to the arraylist of warehouses
        ArrayList<BikePart> WHInventory = new ArrayList<BikePart>();
        WHInventory.addAll(readwrite.readFromFile("mainwarehouse.txt"));
        WareHouse mainWareHouse = new WareHouse("mainWareHouse", WHInventory);
        warehouses.add(mainWareHouse);
        //system admin account - doesn't change, hard coded.
        SysAdmin sysAdmin = new SysAdmin("sysAdmin", "admin", "madni", "John", "Smith", "example@bikestore.com");

        //reads the list of accounts from the accounts text file and adds them to the program
        systemAccounts.addAll(readwrite.readAccounts(mainWareHouse));        

        //sorts the accounts into arraylists based on their account type
        for (int i = 0; i < systemAccounts.size(); i++)
        {
            if (systemAccounts.get(i).getType().equalsIgnoreCase("officemanager"))
            {
                officeManagerAccounts.add((OfficeMan)systemAccounts.get(i));
            }
            else if (systemAccounts.get(i).getType().equalsIgnoreCase("salesassoc"))
            {
                salesAssocAccounts.add((SalesAssoc)systemAccounts.get(i));
            }
            else if (systemAccounts.get(i).getType().equalsIgnoreCase("warehousemanager"))
            {
                WHManAccounts.add((WHMan)systemAccounts.get(i));
            }
        }

        //adds salesvans from any sales associate accounts to the fleet.
        for (int i = 0; i < salesAssocAccounts.size(); i++)
        {
            salesAssocAccounts.get(i).getSalesVan().getInventory().addAll(readwrite.readFromFile((salesAssocAccounts.get(i).getSalesVan().getName()) + ".txt"));
            fleet.add(salesAssocAccounts.get(i).getSalesVan());
            warehouses.add(salesAssocAccounts.get(i).getSalesVan());
        }

        //main program while loop, contains login checks and each account's use cases
        while (a < 1)
        {
            System.out.println("Welcome. Please enter your username, or type EXIT to exit the program.");
            String username = input.next();
            b = 0;
            //"exit" ends the program after writing the inventories of all warehouses (including vans) to their respective text files.
            if(username.equalsIgnoreCase("exit"))
            {
                for (int p = 0; p < warehouses.size(); p++)
                {
                    readwrite.writeToFile((warehouses.get(p).getName() + ".txt"), warehouses.get(p).getInventory());
                }
                System.exit(10);
            }
            //takes the username and password. Compares them first to the system admin account, then to each account in each account type's arraylist.
            else
            {
                System.out.println("Please enter your password");
                String password = input.next();

                int found = 0;

                if(sysAdmin.login(username, password))
                {
                    found++;

                    System.out.println("Logged in as admin.");
                    //while logged in as the system administrator, user has permissions to create a new account or to logout.
                    while (b < 1)
                    {                    
                        System.out.println("Options:" + "\r\n" +  "CREATE a new account" + "\r\n" + "LOGOUT");
                        String response = input.next();
                        if(response.equalsIgnoreCase("create"))
                        {
                            System.out.println("Create which type of account? Options:" + "\r\n" + "OFFICEMANAGER" + "\r\n" + "SALESASSOCIATE" + "\r\n" +
                                "WAREHOUSEMANAGER");
                            String response2 = input.next();
                            //creating a new office manager account
                            if(response2.equalsIgnoreCase("officemanager"))
                            {
                                System.out.println("Enter new account username");
                                String user = input.next();
                                System.out.println("Enter new account password");
                                String pass = input.next(); 
                                System.out.println("Enter new account first name");
                                String first = input.next();
                                System.out.println("Enter new account last name");
                                String last = input.next();
                                System.out.println("Enter new account email");
                                String email = input.next();

                                officeManagerAccounts.add(sysAdmin.createOfficeMan("officemanager", user, pass, first, last, email));
                                systemAccounts.add(sysAdmin.createOfficeMan("officemanager", user, pass, first, last, email));
                                System.out.println("New office manager account created.");
                            }
                            //creating a new sales associate account, with a new sales van.
                            else if (response2.equalsIgnoreCase("salesassociate"))
                            {
                                System.out.println("Enter new account username");
                                String user = input.next();
                                System.out.println("Enter new account password");
                                String pass = input.next(); 
                                System.out.println("Enter new account first name");
                                String first = input.next();
                                System.out.println("Enter new account last name");
                                String last = input.next();
                                System.out.println("Enter new account email");
                                String email = input.next();
                                System.out.println("Enter the sales associate's new sales van");
                                String vanName = input.next();
                                SalesVan newSalesVan = new SalesVan(vanName);
                                warehouses.add(newSalesVan);
                                salesAssocAccounts.add(sysAdmin.createSalesAssoc("salesassociate", user, pass, first, last, email, newSalesVan));
                                systemAccounts.add(sysAdmin.createSalesAssoc("salesassociate", user, pass, first, last, email,  newSalesVan));
                                System.out.println("New sales associate account created");                                
                            }
                            //creating a new warehouse manager - only one main warehouse to manage, main one, so doesn't need input for that.
                            else if (response2.equalsIgnoreCase("warehousemanager"))
                            {
                                System.out.println("Enter new account username");
                                String user = input.next();
                                System.out.println("Enter new account password");
                                String pass = input.next(); 
                                System.out.println("Enter new account first name");
                                String first = input.next();
                                System.out.println("Enter new account last name");
                                String last = input.next();
                                System.out.println("Enter new account email");
                                String email = input.next();

                                WHManAccounts.add(sysAdmin.createWHMan("warehousemanager", user, pass, first, last, email, mainWareHouse));
                                systemAccounts.add(sysAdmin.createWHMan("warehousemanager", user, pass, first, last, email, mainWareHouse));
                                System.out.println("New warehouse manager account created");
                            }
                            else
                            //if the user enters an unrecognized command, requests another input.
                            {
                                System.out.println("Error: Command not recognized. Please enter another command.");
                            }
                        }
                        else if (response.equalsIgnoreCase("logout"))
                        {
                            //logs out, writes the accounts to the accounts text file.
                            readwrite.writeAccounts(systemAccounts);
                            System.out.println("Logging out.");
                            b++;
                        }   
                        //if the user enters an unrecognized command, requests another input.
                        else
                        {
                            System.out.println("Error: Command not recognized. Please enter another command.");
                        }

                    }
                }

                if(found == 0)
                {
                    //while logged in as an office manager, user has permissions to place an order to resupply parts under minimum, examine a part, or create a commission
                    //for a sales associate.
                    for (int i = 0; i < officeManagerAccounts.size(); i++)
                    {
                        if(officeManagerAccounts.get(i).login(username, password))
                        {
                            found++;
                            System.out.println("Logged in as " + username);
                            for (int o = 0; o < mainWareHouse.getInventory().size(); o++)
                            {
                                if (mainWareHouse.getInventory().get(o).getQuantity() <= mainWareHouse.getInventory().get(o).getMinQuantity())
                                {
                                    System.out.println("Warning: " + mainWareHouse.getInventory().get(o).getName() + " is at a low quantity. Consider resupply.");
                                }
                            }
                            while(b < 1)
                            {         
                                System.out.println("OPTIONS" + "\r\n" + "Place new ORDER" + "\r\n" + "EXAMINE a part" + "\r\n" + "Create a sales COMMISSION" + 
                                    "\r\n" + "LOGOUT");
                                String response2 = input.next();
                                if (response2.equalsIgnoreCase("order"))
                                {
                                    //takes a margin and a number of units to be ordered, passes them to the enoughParts method which checks
                                    //whether each part in the warehouse needs to be ordered and, if so, orders them.
                                    System.out.println("Enter the margin - items within this number of their minimum will be ordered.");
                                    int margin = Integer.parseInt(input.next());
                                    System.out.println("Enter order number - each item will have this many units ordered");
                                    int increase = Integer.parseInt(input.next());
                                    officeManagerAccounts.get(i).enoughParts(mainWareHouse, margin, increase);
                                }
                                else if (response2.equalsIgnoreCase("examine"))
                                {
                                    System.out.println("Examine by part NAME or by NUMBER?");
                                    String displayCase = input.next();
                                    //searches for a part by name, then displays its information, if found.
                                    if (displayCase.equalsIgnoreCase("name"))
                                    {
                                        System.out.println("Enter the part's name");
                                        String partName = input.next();
                                        int find = 0;
                                        for (int l = 0; l < WHInventory.size(); l++)
                                        {
                                            if (WHInventory.get(l).getName().equals(partName))
                                            {
                                                find++;
                                                if(WHInventory.get(l).getOnSale())
                                                {
                                                    //if the part is on sale, prints it with the sale price included
                                                    System.out.println(WHInventory.get(l).partInfoSale());
                                                }
                                                else 
                                                {
                                                    //if the part is not on sale, prints it with the regular price included
                                                    System.out.println(WHInventory.get(l).partInfoNoSale());
                                                }
                                            }

                                        }
                                        if(find == 0)
                                        {
                                            System.out.println("Part not found in warehouse.");
                                        }

                                    }
                                    //searches for a part by number, then displays its information, if found.
                                    else if (displayCase.equalsIgnoreCase("number"))
                                    {
                                        System.out.println("Enter the part's number");
                                        String partNumber = input.next();
                                        int find = 0;
                                        for (int l = 0; l < WHInventory.size(); l++)
                                        {
                                            if (WHInventory.get(l).getNumber().equals(partNumber))
                                            {
                                                find++;
                                                if(WHInventory.get(l).getOnSale())
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoSale());
                                                }
                                                else 
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoNoSale());
                                                }
                                            }

                                        }
                                        if(find == 0)
                                        {
                                            System.out.println("Part not found in warehouse.");
                                        }
                                    }                                    

                                }
                                else if (response2.equalsIgnoreCase("commission"))
                                {
                                    //commission stuff. oh boy.
                                }
                                else if (response2.equalsIgnoreCase("logout"))
                                {
                                    //logout, return to login menu
                                    b++;
                                }

                            }
                        }
                    }
                }

                if (found == 0)
                {
                    //while logged in as a sales associate, user has permissions to load their own sales van as well as generate an invoice from a sale.
                    for(int i = 0; i < salesAssocAccounts.size(); i++)
                    {
                        if(salesAssocAccounts.get(i).login(username, password))
                        {
                            found++;
                            while(b < 1)
                            {
                                System.out.println("Logged in as " + username);
                                System.out.println("Options: " + "\r\n" + "LOAD your sales van with parts" + "\r\n" + "Create a new sales INVOICE" + 
                                    "\r\n" + "LOGOUT");
                                String response2 = input.next();
                                if (response2.equalsIgnoreCase("load"))
                                {
                                    //transfers inventory from a warehouse (including other salesvans) to the user's salesvan. After transfer, prints out
                                    //the van's inventory
                                    System.out.println("Enter the name of the inventory transfer file you are loading with");
                                    String transFile = input.next();
                                    salesAssocAccounts.get(i).loadVan(transFile, readwrite, warehouses);
                                    for (int q = 0; q < salesAssocAccounts.get(i).getSalesVan().getInventory().size(); q++)
                                    {
                                        System.out.println(salesAssocAccounts.get(i).getSalesVan().getInventory().get(q).partInfo());
                                    }
                                }
                                //generates an invoice from a sale of parts to a bike shop, then prints that invoice both to the screen and to the associate's invoice file.
                                else if (response2.equalsIgnoreCase("invoice"))
                                {
                                    //gets an arraylist of parts and arraylist containing the number of each part being sold
                                    //then gets the name of the purchasing shop as well as the person signing from the user.
                                    ArrayList<BikePart> sellingParts = new ArrayList<BikePart>();
                                    ArrayList<Integer> quantities = new ArrayList<Integer>();
                                    System.out.println("Enter the name of the shop purchasing the parts");
                                    String shop = input.next();
                                    System.out.println("Enter the name of the individual receiving the order");
                                    input.nextLine();
                                    String receiver = input.next();
                                    int z = 0;
                                    while (z < 1){
                                        //user can add parts to the sale or finish the sale and print the invoice.
                                        System.out.println("Enter the part name or part number, or type DONE to end the sale.");
                                        String partID = input.next();
                                        if(partID.equalsIgnoreCase("done"))
                                        {
                                            salesAssocAccounts.get(i).printInvoice(shop, sellingParts, quantities, receiver);
                                            z++;
                                        }
                                        else
                                        {
                                            System.out.println("Enter quantity of part being sold");
                                            int quantity = Integer.parseInt(input.next());
                                            quantities.add(quantity);
                                            salesAssocAccounts.get(i).getSalesVan().sellPart(partID, quantity);
                                            for (int x = 0; x < salesAssocAccounts.get(i).getSalesVan().getInventory().size(); x++)
                                            {
                                                if ((salesAssocAccounts.get(i).getSalesVan().getInventory().get(x).getName().equals(partID)) ||
                                                (salesAssocAccounts.get(i).getSalesVan().getInventory().get(x).getNumber().equals(partID)))
                                                {
                                                    sellingParts.add(salesAssocAccounts.get(i).getSalesVan().getInventory().get(x));
                                                }
                                            }                                            
                                        }
                                    }

                                }
                                //logs out, returning to main menu
                                else if (response2.equalsIgnoreCase("logout"))
                                {
                                    System.out.println("Logging out");
                                    b++;
                                }
                                else
                                {
                                    System.out.println("Error: Command not recognized. Please enter another command.");
                                }                                  
                            }
                        }
                    }
                }                        

                if (found == 0)
                {
                    //while logged in as a warehouse manager, user has permission to update the main warehouse's inventory as well as
                    //examine any bike part in inventory.
                    for(int i = 0; i < WHManAccounts.size(); i++)
                    {
                        if(WHManAccounts.get(i).login(username, password))
                        {
                            found++;                              
                            while(b < 1)
                            {
                                System.out.println("Logged in as " + username);
                                System.out.println("Options:" + "\r\n" + "UPDATE warehouse inventory" + "\r\n" + "EXAMINE a part" + "\r\n" + "LOGOUT");
                                String response = input.next();
                                if (response.equalsIgnoreCase("update"))
                                {
                                    //takes a delivery text file and adds the contents to main inventory.

                                    System.out.println("Please enter the name of the delivery file");
                                    String file = input.next();
                                    ArrayList<BikePart> incomingInventory = new ArrayList<BikePart>();
                                    incomingInventory.addAll(WHManAccounts.get(i).inventoryUpdate(file, readwrite));

                                    for (int j = 0; j < incomingInventory.size(); j++)
                                    {
                                        int found1 = 0;                                        
                                        for (int k = 0; k < WHInventory.size(); k++)
                                        {
                                           //compares incoming parts to the current warehouse inventory - if they are the same, increments the quantity, rather than adding
                                           //a whole new part.
                                            if(incomingInventory.get(j).compareTo(WHInventory.get(k)) == 0)
                                            {
                                                found1++;
                                                WHInventory.get(k).setQuantity(WHInventory.get(k).getQuantity() + incomingInventory.get(j).getQuantity());
                                            }
                                        }
                                        if(found1 == 0)
                                        {
                                            WHInventory.add(incomingInventory.get(j));    
                                        }                                        
                                    }
                                    //prints the current inventory of the warehouse.
                                    System.out.println("Current warehouse inventory:");
                                    for (int z = 0; z < WHInventory.size(); z++)
                                    {
                                        System.out.println(WHInventory.get(z).partInfo());
                                    }                                

                                }
                                else if (response.equalsIgnoreCase("examine"))
                                {
                                    System.out.println("Examine by part NAME or by NUMBER?");
                                    String displayCase = input.next();
                                    //searches for a part by name, then displays its information, if found.
                                    if (displayCase.equalsIgnoreCase("name"))
                                    {
                                        System.out.println("Enter the part's name");
                                        String partName = input.next();
                                        int find = 0;
                                        for (int l = 0; l < WHInventory.size(); l++)
                                        {
                                            if (WHInventory.get(l).getName().equals(partName))
                                            {
                                                find++;
                                                if(WHInventory.get(l).getOnSale())
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoSale());
                                                }
                                                else 
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoNoSale());
                                                }
                                            }

                                        }
                                        if(find == 0)
                                        {
                                            System.out.println("Part not found in warehouse.");
                                        }

                                    }
                                    //searches for a part by number, then displays its information, if found.
                                    else if (displayCase.equalsIgnoreCase("number"))
                                    {
                                        System.out.println("Enter the part's number");
                                        String partNumber = input.next();
                                        int find = 0;
                                        for (int l = 0; l < WHInventory.size(); l++)
                                        {
                                            if (WHInventory.get(l).getNumber().equals(partNumber))
                                            {
                                                find++;
                                                if(WHInventory.get(l).getOnSale())
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoSale());
                                                }
                                                else 
                                                {
                                                    System.out.println(WHInventory.get(l).partInfoNoSale());
                                                }
                                            }

                                        }
                                        if(find == 0)
                                        {
                                            System.out.println("Part not found in warehouse.");
                                        }
                                    }
                                }
                                else if (response.equalsIgnoreCase("logout"))
                                {
                                    readwrite.writeToFile("mainwarehouse.txt", WHInventory);
                                    System.out.println("Logging out");
                                    b++;
                                }
                                else
                                {
                                    System.out.println("Error: Command not recognized. Please enter another command.");
                                }

                            }
                        }
                    }
                }
                //if provided username and password are not found in accounts, prints this.
                if (found == 0)
                {
                    System.out.println("Account not found. Remember, usernames and passwords are case sensitive.");
                }
            }
        }                      
    }   
}

