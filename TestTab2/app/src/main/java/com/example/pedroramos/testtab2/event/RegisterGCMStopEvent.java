package com.example.pedroramos.testtab2.event;

/**
 * Created by mleniart on 10.03.16.
 */
public class RegisterGCMStopEvent {
    String token;

    public RegisterGCMStopEvent(String token){
        this.token=token;
    }

    public String getToken() {
        return token;
    }
}
