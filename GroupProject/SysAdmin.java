import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
/**
 * SysAdmin account class
 *
 * @author (your name)
 * @version 12/3/2019
 */
public class SysAdmin extends LoginAccount
{
    private ArrayList<LoginAccount> newAccounts;

    /**
     * paramaterized constructor using super constructor
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     */
    public SysAdmin(String name, String pass, String first, String last, String email, String type)
    {
        super(name, pass, first, last, email, type);
        newAccounts = new ArrayList<LoginAccount>();
    }

    /**
     * Creates a new sales associate account
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @param salesvan SalesVan, the new account's SalesVan
     * @return SalesAssoc a new sales associate account
     * 
     */
    public SalesAssoc createSalesAssoc(String type, String name, String pass, String first, String last, String email, SalesVan salesvan)
    {
        SalesAssoc newAssoc = new SalesAssoc("salesassoc", name, pass, first, last, email, salesvan);     
        return newAssoc;

    }

    /**
     * Creates a new warehouse manager account
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @param warehouse WareHouse, the new account's SalesVan
     * @return WHMan a new warehouse manager account
     * 
     */
    public WHMan createWHMan(String type, String name, String pass, String first, String last, String email, WareHouse warehouse)
    {
        WHMan newWHMan = new WHMan("warehousemanager", name, pass, first, last, email, warehouse);
        return newWHMan;    
    }    

    /**
     * Creates a new office manager account
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     * @return OfficeMan a new office manager account
     * 
     */
    public OfficeMan createOfficeMan(String type, String name, String pass, String first, String last, String email)
    {
        OfficeMan newOfficeMan = new OfficeMan("officemanager", name, pass, first, last, email);
        return newOfficeMan;    
    }
   
    /**
     * Prints this account's details to a string, overriding the parent class's method.
     * @return String this account's details as a String 
     */  
    @Override
    public String toString()
    {
        String accountDetails = (this.getType() + this.getUser() + this.getPass() + this.getFirstName() + this.getLastName() + this.getEmail());
        return accountDetails;
    }    

}
