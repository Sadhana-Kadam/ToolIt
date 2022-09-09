package com.floatinity.toolIt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class ValidationUtil {

	/**
	 * <p>
	 * ^ #start of the line [_A-Za-z0-9-\\+]+ # must start with string in the
	 * bracket [ ], must contains one or more (+) ( # start of group #1
	 * \\.[_A-Za-z0-9-]+ # follow by a dot "." and string in the bracket [ ], must
	 * contains one or more (+) )* # end of group #1, this group is optional (*) @ #
	 * must contains a "@" symbol [A-Za-z0-9-]+ # follow by string in the bracket [
	 * ], must contains one or more (+) ( # start of group #2 - first level TLD
	 * checking \\.[A-Za-z0-9]+ # follow by a dot "." and string in the bracket [ ],
	 * must contains one or more (+) )* # end of group #2, this group is optional
	 * (*) ( # start of group #3 - second level TLD checking \\.[A-Za-z]{2,} #
	 * follow by a dot "." and string in the bracket [ ], with minimum length of 2 )
	 * # end of group #3 $ #end of the line
	 * </p>
	 */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * <p>
	 * ( # Start of group
	 * </p>
	 * <p>
	 * (?=.*\d) # must contains one digit from 0-9
	 * </p>
	 * <p>
	 * (?=.*[a-z]) # must contains one lowercase characters
	 * </p>
	 * <p>
	 * (?=.*[A-Z]) # must contains one uppercase characters
	 * </p>
	 * <p>
	 * (?=.*[@#$%]) # must contains one special symbols in the list "@#$%"
	 * </p>
	 * <p>
	 * . # match anything with previous condition checking
	 * </p>
	 * <p>
	 * {8,20} # length at least 8 characters and maximum of 20
	 * </p>
	 * )
	 * <p>
	 * # End of group
	 * </p>
	 * 
	 */
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

	public static boolean validateEmail(final String emailId) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

	public static boolean validatePassword(final String password) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean validatePhoneNo(String phoneNo) {
		return true;
	}

}
