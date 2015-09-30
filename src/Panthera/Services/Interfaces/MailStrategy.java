package Panthera.Services.Interfaces;

import Panthera.Models.Email;

public interface MailStrategy {

    public void setSMTPServer(String smtpServer);
    public void setUsername(String username);
    public void setPassword(String password);
    public void send(Email email);

}
