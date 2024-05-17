import java.lang.annotation.*;
import java.lang.reflect.*;

public class Address {

    @ProperLength()
    private String street;
    private String street2;
    @ProperLength(max = 40)
    private String city;

    @ProperLength(min = 2, max = 2)
    private String state;
    @ProperLength(min = 5, max = 5)
    private String zip;

    public Address(String street, String street2, String city, String state, String zip) throws IllegalArgumentException {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;

        validateLengths();
    }

    private void validateLengths() throws IllegalArgumentException {
        // YOUR CODE HERE
        /* some pseudocode in case it helps:
         * get the current class
         * get all fields in this class
         * 	  NOTE: use the method myClass.getDeclaredFields() to access private fields;
         *      (getFields() only accesses public fields)
         * check if the ProperLength annotation is present for that field
         * if it is, get the annotation object
         * get the min and max from the annotation
         * then get the value for that field
         *      NOTE: the method myField.get(myObject) can be used to obtain the value of a field for a particular object;
         *      note that this returns an Object, so you'll need to cast it
         * check that the length of the value matches the specifications of the annotation
         * if the length isn't valid, throw an exception with a descriptive message of what field is invalid and why
         */

		Class cluzz= this.getClass();
		Field[] classFields = cluzz.getDeclaredFields();
        for (Field classField : classFields) {
            if (classField.isAnnotationPresent(ProperLength.class)) {
                ProperLength length = classField.getAnnotation(ProperLength.class);
                int minOfTheField = length.min();
                int maxOfTheField = length.max();

                classField.setAccessible(true);

                try {
                    Object value = classField.get(this);
                    if (value instanceof String) {
                        int lengthOfTheField = ((String) value).length();
                        if (lengthOfTheField < minOfTheField  || lengthOfTheField > maxOfTheField) {
                            throw new IllegalArgumentException(
                                "Field " + classField.getName() + " is invalid " +
                                        "because its length " + lengthOfTheField + " is not between " + minOfTheField + " and " + maxOfTheField
                            );
                        }
                    }

                } catch (IllegalAccessException ex) {
                    throw new IllegalArgumentException(
                            "Field " + classField.getName() + "is invalid " +
                                    "because of ", ex
                    );
                }
            }
        }

    }

    @Override
    public String toString() {
        String s = street + (street2.length() > 0 ? "\t" + street2 : "") +
                "\t" + city + ", " + state + " " + zip;
        return s;

    }


}
