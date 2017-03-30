package com.koitoer.testing.mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmena on 3/27/17.
 */
public class PasswordRepository {

    public Map<String, String> passwords = new HashMap<>();

    public PasswordRepository(){
        passwords.put("koitoer", "koitoerAB");
        passwords.put("michael", "ABC");
    }

    /**
     * Verify that the passed password match the stored password user
     * @param user
     * @param passwd
     * @return
     */
    public boolean verifyPassword(String user, char[] passwd){
        if(!passwords.isEmpty()){
            String password = passwords.get(user);
            if(password.equals(String.valueOf(passwd))){
                return true;
            }
        }
        return false;
    }


    /**
     * Method that verify the minimum number of characters.
     * @param password
     * @throws IllegalStateException
     */
    public void checkPasswordIntegrity(char [] password) throws IllegalStateException{
        String value = String.valueOf(password);
        if(value.length() < 5) throw new IllegalStateException("No minimum lenght") ;
    }
}
