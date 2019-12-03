import java.util.ArrayList;
import java.time.LocalDateTime;  
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
/**
 * Write a description of class SalesAssoc here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SalesAssoc extends LoginAccount
{
    private SalesVan salesvan;
    private double salesTotal;
    private DecimalFormat df2 = new DecimalFormat("#.00");
    
    /**
     * paramaterized constructor with a default total sales of 0
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @param salesvan SalesVan, the new account's SalesVan
     */
    public SalesAssoc(String name, String pass, String first, String last, String email, String type, SalesVan salesvan)
    {
        super(name, pass, first, last, email, type);
        this.salesvan = salesvan;
        this.salesTotal = 0.00;
    }

    /**
     * paramaterized constructor
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @param salesvan SalesVan, the new account's SalesVan
     * @param sales Double, the associate's total sales
     */
    public SalesAssoc(String name, String pass, String first, String last, String email, String type, SalesVan salesvan, double sales)
    {
        super(name, pass, first, last, email, type);
        this.salesvan = salesvan;
        this.salesTotal = sales;
    }    

    /**
     * Getter, returns this account's total sales
     * return double this account's total sales
     */
    public double getSales()
    {
        return this.salesTotal;
    }
    
    /**
     * Setter, sets the account's sales to the given value
     * @param sales double, the new sales value
     */
    public void setSales(double sales)
    {
        this.salesTotal = sales;
    }
    
    /**
     * adds the given value to the account's total sales
     * @param sales double, the value being added to the account's sales total
     */
    public void addSales(double sales)
    {
        this.salesTotal += sales;
    }
    
    /**
     * Getter, returns this account's SalesVan
     * return double this account's SalesVan
     */    
    public SalesVan getSalesVan()
    {
        return this.salesvan;
    }

    /**
     * Prints this account's details to a string, overriding the parent class's method.
     * @return String this account's details as a String 
     */ 
    @Override   
    public String toString()
    {
        String accountDetails = (this.getType()  + ","  + this.getUser()  + ","  + this.getPass()  + ","  + this.getFirstName()  + ","  + this.getLastName()  + ","  + 
                this.getEmail()  + ","  + this.getSalesVan().getName() + "," + df2.format(this.getSales()));
        return accountDetails;
    } 

    /**
     * transfers bikeparts from a source warehouse to this account's salesvan
     * @param transferFile
     * @param readwrite Read
     * @param whFleet ArrayList<WareHouse>, arraylist containing all warehouses in the main program
     */
    public void loadVan(String transferFile, ReaderWriter readwrite, ArrayList<WareHouse> whFleet)
    {
        String[] sourceDest = readwrite.sourceDest(transferFile);
        WareHouse transferSource = new WareHouse();
        WareHouse transferDest = new WareHouse();
        ArrayList<String> transferList = readwrite.inventoryTransfer(transferFile);
        ArrayList<BikePart> movingParts = new ArrayList<BikePart>();
        for (int i = 0; i < whFleet.size(); i++)
        {
            if(sourceDest[0].equals(whFleet.get(i).getName()))
            {
                transferSource = whFleet.get(i);
            }

            if(sourceDest[1].equals(whFleet.get(i).getName()))
            {
                transferDest = whFleet.get(i);
            }
        }
        for (int i = 0; i < transferList.size(); i++)
        {
            String[] part = transferList.get(i).split(",");
            int a = Integer.parseInt(part[1]);
            for (int z = 0; z < transferSource.getInventory().size(); z++)
            {
                if (part[0].equals(transferSource.getInventory().get(z).getName()))
                {
                    BikePart placeHolder = new BikePart();

                    if (a <= transferSource.getInventory().get(z).getQuantity())
                    {
                        transferSource.getInventory().get(z).quantityDownMulti(a);
                        placeHolder.setQuantity(a);
                        placeHolder.setName(transferSource.getInventory().get(z).getName());
                        placeHolder.setNumber(transferSource.getInventory().get(z).getNumber());
                        placeHolder.setPrice(transferSource.getInventory().get(z).getPrice());
                        placeHolder.setSalesPrice(transferSource.getInventory().get(z).getSalesPrice());
                        placeHolder.setOnSale(transferSource.getInventory().get(z).getOnSale());

                        movingParts.add(placeHolder);
                    }
                    else 
                    {
                        placeHolder.setQuantity(transferSource.getInventory().get(z).getQuantity());
                        placeHolder.setName(transferSource.getInventory().get(z).getName());
                        placeHolder.setNumber(transferSource.getInventory().get(z).getNumber());
                        placeHolder.setPrice(transferSource.getInventory().get(z).getPrice());
                        placeHolder.setSalesPrice(transferSource.getInventory().get(z).getSalesPrice());
                        placeHolder.setOnSale(transferSource.getInventory().get(z).getOnSale());

                        movingParts.add(placeHolder);                        
                        transferSource.getInventory().get(z).setQuantity(0);

                    }
                }
            }
        }
        this.salesvan.addToInventory(movingParts);
    }

    /**
     * NEEDS REVISING because need to do dates
     * Prints an invoice for a sale of parts to a bike shop. Also adds the total price to the associate's total sales.
     * @param buyingShop String the name of the shop purchasing the parts
     * @param soldItems ArrayList<BikePart> the list of parts being sold
     * @param quantites ArrayList<Integer> the quantities of parts being sold
     * @param receiver String the name of the individual signing for the purchase
     */
    public void printInvoice(String buyingShop, ArrayList<BikePart> soldItems, ArrayList<Integer> quantities, String receiver)
    {
        long millis=System.currentTimeMillis();  
        double total = 0;
        double bigTotal = 0;
        java.util.Date date=new java.util.Date(millis);
        String invoice = "";
        invoice+= ("Sales Invoice for " + buyingShop + ", " + date + "\r\n");
        invoice+= (String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s", "Part Name", "|", "Part Number", "|", "Price", "|", 
            "Sales Price", "|", "Qnty", "|", "Total Cost" + "\r\n"));
        for (int i = 0; i < soldItems.size(); i++)
        {
            if (soldItems.get(i).getOnSale() == true)
            {
                total = (soldItems.get(i).getSalesPrice() * quantities.get(i));
            }
            else
            {
                total = (soldItems.get(i).getPrice() * quantities.get(i));
            }
            invoice+= (String.format("%10s %10s %10s %11s %10s %10s %10s %11s %10s %10s %10s", soldItems.get(i).getName(), "|", soldItems.get(i).getNumber(), 
                "|", df2.format(soldItems.get(i).getPrice()), "|", df2.format(soldItems.get(i).getSalesPrice()), "|", quantities.get(i), "|", df2.format(total) + 
                "\r\n"));
            bigTotal += total;                    
        }
        invoice+=(String.format("%10s %111s", "Total", df2.format(bigTotal) + "\r\n"));
        invoice+=("Received by signature: " + receiver);
        System.out.println(invoice);
        try{
            FileWriter writer = new FileWriter((this.getUser() + "invoices.txt"), true);
            writer.write(invoice);
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Account invoice file not found.");
        }
        this.addSales(bigTotal);
    }
}
