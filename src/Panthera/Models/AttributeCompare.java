package Panthera.Models;

import java.util.Comparator;

/**
 * Used to compare Debiteur objects on getNaam().
 * @author Roy
 *
 */
public class AttributeCompare implements Comparator<Debiteur> {
	@Override
	public int compare(Debiteur o1, Debiteur o2) {
		return o1.getNaam().compareTo(o2.getNaam());
	}
}