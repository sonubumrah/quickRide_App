package com.sky.QuickRide.quickRide.App.services;

public interface EmailSenderService {
    public  void sendMail(String toEmail,String subject,String body);

    public void sendEmail(String toEmail[],String subject,String body);
}
