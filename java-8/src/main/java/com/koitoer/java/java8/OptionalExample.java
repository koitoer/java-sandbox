package com.koitoer.java.java8;

import java.util.Optional;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * Created by mmena on 5/3/17.
 */
public class OptionalExample {

    public DeviceType testDevice(String string){
        final DeviceType deviceType = Optional.ofNullable(string)
            .map(x -> UserAgent.parseUserAgentString(x).getOperatingSystem().getDeviceType())
            .orElse(DeviceType.COMPUTER);

        return deviceType;
    }

    class Car{
        Seat type;
    }

    class Seat{
        int number;
    }

    public static void main(String[] args) {
        OptionalExample optionalExample =  new OptionalExample();
        optionalExample.method();
    }

    public void method(){
        Car a = new Car();
        System.out.println(Optional.ofNullable(a).map(c -> c.type).map(x -> x.number).orElse(1));

    }

}
