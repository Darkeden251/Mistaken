
import java.time.LocalDateTime;  
import java.util.*;

/**
 *
 * @author Alex Barbuzza
 * @version 12/3/2019
 */

public class WareHouse {
    protected String name;
    protected ArrayList<BikePart> inventory;

    /**
     * default constructor for objects of class warehouse - sets fields to placeholder values
     */
    public WareHouse()
    {
        this.name = "test";
        this.inventory = new ArrayList<BikePart>(10);
    }

    /**
     * parameterized constructor for objects of class warehouse
     * @param name a String
     * @param inventory an ArrayList of BikePart objects.
     */
    public WareHouse(String name, ArrayList<BikePart> inventory)
    {
        this.name = name;
        this.inventory = inventory;
    }

    /**
     * getter for warehouse inventory
     * @return an ArrayList containing the BikeParts the warehouse is currently holding
     */
    public ArrayList<BikePart> getInventory()
    {
        return this.inventory;
    }

    /**
     * getter for warehouse name
     * @return a String, the name of the warehouse
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * setter for warehouse inventory
     * @param inventory an ArrayList of BikePart objects
     */
    public void setInventory(ArrayList<BikePart> inventory)
    {
        this.inventory = inventory;
    }

    /**
     * method to check if an item in inventory has a quantity less than or equal to one - if it is, remove that item from inventory. prevents issues with 
     * parts having quantities of zero or less than zero.
     */
    public void stillAround()
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).getQuantity() <= 0)
            {
                inventory.remove(inventory.get(i));
            }
        }
    }

    /**
     * method called when selling a part. Prints the part information, as well as the date and time of the sale.
     * @param partNum integer, the number of the part being sold
     */
    public void sellPart(String partID, int quantity)
    {

        long millis=System.currentTimeMillis();  
        java.util.Date date=new java.util.Date(millis); 

        int found = 0;

        for (int z = 0; z < inventory.size(); z++)
        {
            if ((inventory.get(z).getNumber().equals(partID) || inventory.get(z).getName().equals(partID)))
            {
                found ++;
                inventory.get(z).quantityDownMulti(quantity);
            }        
        }
        if (found == 0)
        {
            System.out.println("Part not found!");
        }
    }

    /**
     * method used to add an arraylist of bikeparts to the main warehouse's inventory. 
     * @param incomingInventory ArrayList of BikePart objects being added to the warehouse inventory.
     */
    public void addToInventory(ArrayList<BikePart> incomingInventory)
    {
        int found = 0;
        for (int i = 0; i < incomingInventory.size(); i++)
        {
            found = 0;
            for (int z = 0; z < inventory.size(); z++)
            {
                if (inventory.get(z).compareTo(incomingInventory.get(i)) == 0)
                {
                    found ++;
                    inventory.get(z).setPrice(incomingInventory.get(i).getPrice());
                    inventory.get(z).setSalesPrice(incomingInventory.get(i).getSalesPrice());
                    inventory.get(z).setOnSale(incomingInventory.get(i).getOnSale());
                    inventory.get(z).setQuantity(inventory.get(z).getQuantity() + incomingInventory.get(i).getQuantity());
                }     
            }
            if (found == 0)
            {
                inventory.add(incomingInventory.get(i));
            }                
        }
    }

    /**
     * method used to remove inventory from a warehouse (or van) for transferring between two warehouses
     * @param outgoingList the ArrayList of BikePart objects being removed.
     */
    public void transferFrom(ArrayList<BikePart> outgoingList)
    {
        int found = 0;
        for (int b = 0; b < inventory.size(); b++)
        {
            for (int c = 0; c < outgoingList.size(); c++)
            {
                if (inventory.get(b).getName().equals(outgoingList.get(c).getName()))
                {   found++;
                    //checks if the quantity being transferred is greater than available quantity. If it is, this will simply transfer all available.
                    if(outgoingList.get(c).getQuantity() > inventory.get(b).getQuantity()) 
                    {
                        outgoingList.get(c).setQuantity(inventory.get(b).getQuantity());
                    }
                    inventory.get(b).setQuantity(inventory.get(b).getQuantity() - outgoingList.get(c).getQuantity());
                }

            }
        }

    }

    /**
     * method used to add inventory to a warehouse or van for a transfer between warehouses. will use the same list as transferFrom() as a parameter, but adding
     * parts instead of removing.
     * @param incomingList an arrayList of BikePart objects being added to inventory.
     */
    public void transferTo(ArrayList<BikePart> incomingList)
    {
        int found = 0;
        for (int b = 0; b < incomingList.size(); b++)
        {

            for (int c = 0; c < inventory.size(); c++)
            {
                if (incomingList.get(b).getName().equals(inventory.get(c).getName()))
                {
                    inventory.get(c).setQuantity(inventory.get(c).getQuantity() + incomingList.get(b).getQuantity());
                    found++;
                }
            }
            if (found == 0)
            {
                inventory.add(incomingList.get(b));
            }

            found = 0;
        }
    }

}