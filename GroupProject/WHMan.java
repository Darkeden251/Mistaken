import java.util.ArrayList;
/**
 * WareHouse Manager class. 
 *
 * @author Abdel Hamid Shehata
 * @version 12/3/2019
 */
public class WHMan extends LoginAccount
{
    private WareHouse warehouse;
    
    /**
     * Paramaterized constructor, using the constructor from the LoginAccount superclass
     * @param name String, the name of the account
     * @param pass String, the account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @param type String, the type of account this is 
     * @param warehouse WareHouse, this manager's warehouse
     */
    public WHMan(String name, String pass, String first, String last, String email, String type, WareHouse warehouse)
    {
        super(name, pass, first, last, email, type);
        this.warehouse = warehouse;
    }

    /**
     * Getter, returns this account's warehouse
     * @return WareHouse warehouse, this account's warehouse
     */
    public WareHouse getWareHouse()
    {
        return this.warehouse;
    }

    @Override   
    /**
     * Prints this account's details to a string, overriding the parent class's method.
     * @return String this account's details as a String 
     */
    public String toString()
    {
        String accountDetails = (this.getType()  + ","  + this.getUser()  + ","  + this.getPass()  + ","  + this.getFirstName()  + ","  + this.getLastName()  + ","  + 
                this.getEmail()  + ","  + this.getWareHouse().getName());
        return accountDetails;
    }

    /**
     * Updates an inventory file based on a provided delivery file
     * @param file the inventory file being written to
     * @ param readwrite a ReadWriter used to write to the file
     */
    public ArrayList<BikePart> inventoryUpdate(String file, ReaderWriter readwrite)
    {
        ArrayList<BikePart> incomingInventory = new ArrayList<BikePart>();

        incomingInventory = readwrite.readFromFile(file);
        return incomingInventory;
    }

}
