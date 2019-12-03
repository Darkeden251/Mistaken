import java.util.Comparator;
/**
 * Write a description of class NumSort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NumSort implements Comparator<BikePart>
{
    
    /**
     * Used for sorting parts by number
     * @param part1 BikePart, the first part being compared
     * @param part2 BikePart, the second part being compared
     * @return int an integer representing which part comes first numerically.
     */
    public int compare(BikePart part1, BikePart part2)
    {
        return part1.getNumber().compareTo(part2.getNumber());
    }
}
