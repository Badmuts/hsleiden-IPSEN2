package Panthera.Controllers;

import Panthera.Services.MailService;

public class MailController extends Controller {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

}
