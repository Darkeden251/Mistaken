import java.util.Comparator;
/**
 * Write a description of class NameSort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NameSort implements Comparator<BikePart>
{
    /**
     * Used for sorting parts by name
     * @param part1 BikePart, the first part being compared
     * @param part2 BikePart, the second part being compared
     * @return int an integer representing which part comes first alphabetically.
     */
    public int compare(BikePart part1, BikePart part2)
    {
        return part1.getName().compareTo(part2.getName());
    }       
  
}
