import java.security.MessageDigest;
import java.util.Arrays;

public class BruteForce {

	/*public static void main(String[] args) {
		
		String password = "aB 3"; // set a test password
	    String hash = hash(password); // hash the test password
	    String crackedPassword = crackPassword(hash); // crack the hash using the brute-force method
	    if (password.equals("")) {
	        System.out.println("You have not entered any password!!");
	    } else if (password.equals(crackedPassword)) { // check if the cracked password matches the original password
	    	System.out.println("Password successfully cracked: " + crackedPassword);
	    } else {
	        System.out.println("Password cracking failed.");
	    }

	}*/
	
	public static String crackPassword(String hash) {
	    String password = null;
	    boolean passwordFound = false;
	    int maxLength = 4; // set the maximum length of the password
	    char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:,.<>? ".toCharArray(); // set the characters to use for brute-forcing
	    
	    for (int length = 1; length <= maxLength; length++) { // loop over all possible lengths of the password
	    	
	        if (passwordFound) // exit the loop if the password has been found
	            break;
	        
	        char[] guess = new char[length]; // initialize the guess array with the current length
	        Arrays.fill(guess, chars[0]); // set all characters in the guess to the first character in the char array
	        while (!passwordFound && increment(guess, chars)) { // loop over all possible combinations of characters for the current length
	            String hashedGuess = hash(String.valueOf(guess)); // hash the current guess
	            if (hashedGuess.equals(hash)) { // check if the hash matches the input hash
	                password = String.valueOf(guess); // set the password to the current guess
	                passwordFound = true; // set the flag to true to exit the loop
	            }
	        }
	    }
	    
	    return password;
	}
	
	private static boolean increment(char[] guess, char[] chars) {
	    int index = guess.length - 1;
	    while (index >= 0) {
	        if (guess[index] == chars[chars.length - 1]) {
	            guess[index] = chars[0];
	            index--;
	        } else {
	            int charIndex = indexOf(chars, guess[index]);
	            guess[index] = chars[charIndex + 1];
	            return true;
	        }
	    }
	    return false;
	}

	private static int indexOf(char[] chars, char c) {
	    for (int i = 0; i < chars.length; i++) {
	        if (chars[i] == c) {
	            return i;
	        }
	    }
	    return -1;
	}

	public static String hash(String input) {
	    /*try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hash) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	        System.err.println("SHA-256 algorithm not found!");
	        return null;
	    }*/
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Use "SHA-256"
	        
			//Applies sha256 to our input 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			//This will contain hash as hexidecimal
			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				
				if (hex.length() == 1)
					hexString.append('0');
				
				hexString.append(hex);
			}
			
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
