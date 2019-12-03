import java.util.ArrayList;
/**
 * Write a description of class OfficeMan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OfficeMan extends LoginAccount
{
       public OfficeMan(String name, String pass, String first, String last, String email, String type)
       {
           super(name, pass, first, last, email, type);
       }
       
    @Override   
    public String toString()
    {
        String accountDetails = (this.getType()  + ","  + this.getUser()  + ","  + this.getPass()  + ","  + this.getFirstName()  + ","  + this.getLastName() + 
              "," + this.getEmail());
        return accountDetails;
    }
    
    public void enoughParts (WareHouse warehouse, int margin, int increase)
    {
        ArrayList<BikePart> underMin = new ArrayList<BikePart>();
        for (int i = 0; i < warehouse.getInventory().size(); i++)
        {
            if ((warehouse.getInventory().get(i).getQuantity() - margin) <= (warehouse.getInventory().get(i).getMinQuantity()))
            {
                warehouse.getInventory().get(i).quantityUpMulti(increase);
            }
        }
    }
}
