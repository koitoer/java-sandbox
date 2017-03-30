package com.koitoer.testing.mockito;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by mmena on 3/27/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @InjectMocks
    private ServiceLayer serviceLayer;

    @Mock
    private PasswordRepository passwordRepository;

    @Test
    public void checkPasswordPositiveTest() throws Exception {
        Mockito.when(passwordRepository.verifyPassword(Mockito.anyString(), Mockito.any(char[].class))).thenReturn(true);
        String message = serviceLayer.checkPassword("koitoer", "koitoerABXXX".toCharArray());
        Assertions.assertThat(message).isEqualTo("Welcome koitoer");
    }

    @Test
    public void checkPasswordNegativeTest() throws Exception {
        Mockito.when(passwordRepository.verifyPassword(Mockito.anyString(), Mockito.any(char[].class))).thenReturn(false);
        String message = serviceLayer.checkPassword("koitoer", "koit".toCharArray());
        Assertions.assertThat(message).contains("Access denied");
    }

    @Test(expected = RuntimeException.class)
    public void failverification(){
        Mockito.doThrow(new RuntimeException("No complain")).when(passwordRepository).checkPasswordIntegrity(Mockito.any());
        serviceLayer.checkPassword("koitoer", "koit".toCharArray());
    }

    @Test
    public void passVerification(){
        Mockito.doNothing().when(passwordRepository).checkPasswordIntegrity(Mockito.any());
        Mockito.when(passwordRepository.verifyPassword(Mockito.anyString(), Mockito.any(char[].class))).thenReturn(true);
        String message = serviceLayer.checkPassword("koitoer", "koitoerABXXX".toCharArray());
        Assertions.assertThat(message).isEqualTo("Welcome koitoer");
    }

}