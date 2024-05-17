import java.util.*;

public class AddressTest {

	public static void main(String[] args) {
		
		System.out.println("-------------------------TESTING VALID ADDRESSES-------------------------");
		System.out.println("Two addresses should print:");
		Address validAddress1 = new Address("50 Phelan Ave", "Ocean Campus", "San Francisco", "CA", "94112");
		System.out.println(validAddress1);
		Address validAddress2 = new Address("50 Phelan Ave", "", "San Francisco", "CA", "94112"); // no street2
		System.out.println(validAddress2);
		
		System.out.println("\n-------------------------TESTING INVALID ADDRESSES-------------------------");
		String[][] invalidAddressStringArray = {
				{"50 Phelan Ave", "Ocean Campus", "San Francisco", "California", "94112"}, // too long state
				{"50 Phelan Ave", "Ocean Campus", "San Francisco", "", "94112"}, // no state
				{"", "", "San Francisco", "CA", "94112"}, // no street 1
				{"50 Phelan Ave", "Ocean Campus", "", "", "94112"}, // no city
				{"50 Phelan Ave", "Ocean Campus", "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch", "", "94112"}, // too long city
				{"50 Phelan Ave", "", "San Francisco", "CA", "9411"}, // too short zip
				{"50 Phelan Ave", "", "San Francisco", "CA", "941121"}}; // too long zip
		String[] invalidAddressReasonArray = {"too long state", "no state", "no street 1", "no city", "too long city", "too short zip", "too long zip"};
		for(int i=0; i<invalidAddressStringArray.length; i++) {
			String[] badAddressStringValues = invalidAddressStringArray[i];
			String badAddressReason = invalidAddressReasonArray[i];
			System.out.println("\nBad address because " + badAddressReason + ": " + Arrays.toString(badAddressStringValues));
			System.out.println("Expected: IllegalArgumentException that describes why the address is not valid.");
			Address badAddress = null;
			try {
				badAddress = new Address(badAddressStringValues[0], badAddressStringValues[1], badAddressStringValues[2], badAddressStringValues[3], badAddressStringValues[4]);
				System.out.println("*****Error! No exception was thrown");
			} catch(IllegalArgumentException ex) {
				System.out.print("  Actual: ");
				System.out.println(ex.getMessage());
			} finally {
				System.out.println("Expected value of the Address object: null");
				System.out.println("  Actual value of the Address object: "+ badAddress);
				if(badAddress!=null) {
					System.out.println("*****Error! The address should be null.");
				}
			}
		}
	}
}
