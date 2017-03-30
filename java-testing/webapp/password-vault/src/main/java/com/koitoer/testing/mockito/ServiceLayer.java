package com.koitoer.testing.mockito;

/**
 * Created by mmena on 3/27/17.
 */
public class ServiceLayer {

    private PasswordRepository passwordRepository;

    public ServiceLayer(PasswordRepository passwordRepository){
        this.passwordRepository = passwordRepository;
    }

    /**
     * Method that will verify the password and user information
     * @param user
     * @param passwd
     * @return
     */
    public String checkPassword(String user , char [] passwd){
        passwordRepository.checkPasswordIntegrity(passwd);
        boolean passwordIsCorrect = passwordRepository.verifyPassword(user, passwd);
        if(passwordIsCorrect){
            return "Welcome " + user;
        }else{
            return "Access denied to " + user;
        }
    }

}
