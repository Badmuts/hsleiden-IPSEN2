package Panthera.Services.Interfaces;

import Panthera.Models.Email;

public interface MailStrategy {

    public void send(Email email);

}
