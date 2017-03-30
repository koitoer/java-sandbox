package com.koitoer.testing.mockito;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mmena on 3/27/17.
 */
public class ServiceLayerTestNoMockito {

    private ServiceLayer serviceLayer;

    private PasswordRepository passwordRepository;

    @Before
    public void init(){
        passwordRepository = new PasswordRepository();
        serviceLayer = new ServiceLayer(passwordRepository);
    }

    @Test
    public void checkPasswordPositiveTest() throws Exception {
        String message = serviceLayer.checkPassword("koitoer", "koitoerAB".toCharArray());
        System.out.println(message);
        Assertions.assertThat(message).isEqualTo("Welcome koitoer");
    }

    @Test
    public void checkPasswordFails(){
        String message = serviceLayer.checkPassword("koitoer", "abdde".toCharArray());
        System.out.println(message);
        Assertions.assertThat(message).contains("Access denied");
    }

    @Test(expected = IllegalStateException.class)
    public void lengthDontComplain(){
        serviceLayer.checkPassword("koitoer", "abd".toCharArray());
    }

}