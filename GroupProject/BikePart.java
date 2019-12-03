
/**
 * This is the class which defines the characteristics of BikePart objects.
 *
 * @author Abdel Hamid Shehata
 * @version 9/11/2019
 */
public class BikePart implements Comparable<BikePart>
{
    // the instance variables each object neads
    private String partName;
    private String partNumber;
    private double price;
    private double salesPrice;
    private boolean onSale;
    private int quantity;
    private int minQuantity;

    /**
     * Placeholder constructor for BikePart objects, sets all values to a default
     */
    public BikePart()
    {

        this.partName = "placeholder";
        this.partNumber = "1";
        this.price = 00.00;
        this.salesPrice = 00.00;
        this.onSale = false;
        this.quantity = 0;
        this.minQuantity = 2;
    }


    /**
     * Fully paramaterized constructor for a BikePart object where all variables are provided. Minquantity for all parts in this project has been set to
     * two, but in a real-world scenario the minimum quantity of each part would be based on projected demand.
     * @param name a String, name of the part being created
     * @param number, the number of the part being created, stored as a String rather than an int because it makes it easier for some methods
     * @param double price, the regular price of the part
     * @param double salesPrice, the price of the part when it is on sale
     * @param boolean onSale, whether or not the part is on sale
     * @param quantity, the quantity of the part in inventory
     */
    public BikePart(String name, String number, double price, double salesPrice, boolean onSale, int quantity)
    {
        this.partName = name;
        this.partNumber = number;
        this.price = price;
        this.salesPrice = salesPrice;
        this.onSale = onSale;
        this.quantity = quantity;
        this.minQuantity = 2;
    }

    /**
     * This method returns information about a BikePart object
     * useful for displaying inventories
     * @return String all of the part's details
     */
    public String partInfo()
    {
        return(this.partName + "," + this.partNumber + "," + String.format("%.2f",this.price) + "," + String.format("%.2f",this.salesPrice) + "," + this.onSale + "," + this.quantity);
    }

    /**
     * this method displays the relevant details when making a sale of an item that IS at sale price
     * @return String the part name, number, sale price, on-sale status, and quantity of a part in one String
     */
    public String partInfoSale()
    {
        return(this.partName + "," + this.partNumber + "," + String.format("%.2f",this.salesPrice) + "," + this.onSale + "," + this.quantity);
    }

    /**
     * this method displays relevant sale details for an item that is NOT at sale price
     * @return String the part name, number, price, on-sale status, and quantity of a part in one String.
     */
    public String partInfoNoSale()
    {
        return(this.partName + "," + this.partNumber + "," + String.format("%.2f",this.price) + "," + this.onSale + "," + this.quantity);
    }

    /**
     * a shortened version of partInfo(), displaying only the name and quantity of a part.
     * @return String the partName and quantity of a part in one String.
     */
    public String partInfoShort()
    {
        return (this.partName + "," + this.quantity);
    }

    /**
     * Getter method, returns the name of a BikePart
     * @return a String, representing the part's name
     */
    public String getName()
    {
        return this.partName;
    }

    /**
     * Setter method, changes the name of a BikePart to the given string
     * @param name a String which becomes the object's new name
     */
    public void setName(String name)
    {
        this.partName = name;
    }

    /**
     * Getter method, returns the minimum quantity required of a bikepart (in this program, all minimum quantites are defaulted to 2)
     * @return int an integer, the minimum number of this part we need to keep in inventory.
     */
    public int getMinQuantity()
    {
        return this.minQuantity;
    }
    
    
    /**
     * setter method, changes the part's minimum quantity to the integer provided
     * @param minQuantity integer, the number becoming the part's new minimum quantity.
     */
    public void setMinQuantity(int minQuantity)
    {
        this.minQuantity = minQuantity;
    }
    
    /**
     * Getter for the partNumber of a BikePart
     * @return an integer, the part number of the part
     */
    public String getNumber()
    {
        return this.partNumber;
    }

    /**
     * Setter for the partNumber of a BikePart, changes the partNumber to the given integer
     * @param number the number we want to become the new part number
     */
    public void setNumber(String number)
    {
        this.partNumber = number;
    }

    /**
     * Getter for the normal price of a BikePart
     * @return a double, the price of the part
     */
    public double getPrice()
    { 
        return this.price;
    }

    /**
     * Setter for normal price, changes the price to the given double
     * @param price the new price of the part
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Getter for the SalesPrice of a BikePart
     * @return a double, the price of the part when it is on sale
     */
    public double getSalesPrice()
    {
        return this.salesPrice;
    }

    /**
     * Setter for the SalesPrice of a BikePart
     * @param salesPrice the new sales price we want to set
     */
    public void setSalesPrice(double salesPrice)
    {
        this.salesPrice = salesPrice;
    }

    /**
     * Getter, returns whether a bikePart is onSale
     * @return a boolean, whether or not the part is on sale.
     */
    public boolean getOnSale()
    {
        return this.onSale;
    }

    /**
     * Setter, changes whether a bikePart is onSale or not
     * @param onSale true or false depending on if we want the part on sale.
     */
    public void setOnSale(boolean onSale)
    {
        this.onSale = onSale;
    }

    /**
     * Getter, returns the quantity of parts in the system.
     * @return an int, number of this part in inventory.
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Setter, changes the quantity of a part in inventory.
     * @param quantity new quantity of this part 
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * simple method to increment quantity by 1
     * 
     */
    public void quantityUp()
    {
        this.quantity ++;
    }

    /**
     * Simple method to increment quantity by -1
     * 
     */
    public void quantityDown()
    {
        this.quantity --;
    }
    
    /**
     * increases the quantity by a given number
     * @param num int the number the quantity is being changed by
     */
    public void quantityUpMulti(int num)
    {
        this.quantity = (this.quantity + num);
    }

    /**
     * decreases the quantity by a given number
     * @param num int the number the quantity is being changed by
     */
    public void quantityDownMulti(int num)
    {
        this.quantity = (this.quantity - num);
    }
    
    /**
     * compareTo method for comparing two bikeparts
     * @return int, 0 if the parts are the same, -1 or 1 if they are not.
     * @param part BikePart, the part being compared to this BikePart
     */
    public int compareTo(BikePart part)
    {
        return this.partName.compareTo(part.getName());        
    }
    
}
