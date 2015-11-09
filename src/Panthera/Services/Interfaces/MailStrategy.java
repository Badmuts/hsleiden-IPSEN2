package Panthera.Services.Interfaces;

import Panthera.Models.Email;

/**
 * Strategy pattern used to change from MailStrategy during runtime.
 *
 * @author Daan Rosbergen
 */
public interface MailStrategy {

    /**
     * Method used to send a Email object.
     *
     * @param email
     */
    public void send(Email email);

}
