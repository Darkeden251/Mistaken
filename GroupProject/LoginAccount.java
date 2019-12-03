
/**
 * Write a description of class LoginAccount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LoginAccount implements Comparable<LoginAccount>
{
    private String userName;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    
    /**
     * paramaterized constructor, used as super constructor for child classes
     * @param type String, the type of account being created
     * @param name String, the name of the account being created
     * @param pass String, the new account's password
     * @param first String, the first name of the account's user
     * @param last String, the last name of the account's user
     * @param email String, the email address of the account's user
     */
    public LoginAccount(String type, String name, String pass, String first, String last, String email){
        this.userName = name;
        this.pass = pass;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.type = type;
    }
    
    /**
     * Getter, returns the account's type
     * @return String, this ccount's type
     */
    public String getType()
    {
        return this.type;
    }
    
    /**
     * Getter, returns the account's username
     * @return String this account's username
     */
    public String getUser()
    {
        return this.userName;
    }

    /**
     * setter, changes the account's username
     * @param user String, the account's new username
    */
    public void setUser(String user)
    {
        this.userName = user;
    }

    /**
     * Getter, returns the account's password
     * @return String this account's password
    */
    public String getPass()
    {
        return this.pass;
    }
    
    /**
     * setter, changes the account's password
     * @param pass String, the account's new password
    */    
    public void setPass(String pass)
    {
        this.pass = pass;
    }

    /**
     * Getter, returns the account's first name
     * @return String this account's new first name
    */    
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * setter, changes the account's first name
     * @param pass firstName, the account's new first name
    */    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Getter, returns the account's last name
     * @return String this account's last name
    */    
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * setter, changes the account's last name
     * @param pass lastName, the account's new last name
    */        
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }    

    /**
     * Getter, returns the account's email
     * @return String this account's email
    */      
    public String getEmail()
    {
        return this.email;
    }

    /**
     * setter, changes the account's email
     * @param pass email, the account's new email
    */      
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * method for checking a user's login details
     * @param user String the username input by the user
     * @param pass String the password input by the user
     * @return boolean whether or not the details match the account
     */
    public boolean login(String user, String pass)
    {
        if(user.equals(this.userName) && pass.equals(this.pass))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints this account's information to a string
     * @return String this account's information as a string
     */
    public String toString()
    {
        String accountDetails = (this.type + "," + this.userName + "," + this.pass  + ","  + this.firstName  + ","  + this.lastName  + ","  + this.email);
        return accountDetails;
    }

    /**
     * Compares this account to another account provided
     * @param account LoginAccount, the account this account is being compared to
     * @return int a 0 if the two are the same, a negative or positive number otherwise.
     */
    public int compareTo(LoginAccount account)
    {
        return this.userName.compareTo(account.getUser());
    }
        
}
