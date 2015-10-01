package Panthera.Controllers;

import Panthera.Services.MailService;
import Panthera.Services.Strategies.Mail.SendgridMailStrategy;
import Panthera.Views.MailDankwoordView;
import Panthera.Views.MailListView;
import Panthera.Views.MailSelectRecipientsView;

public class MailController extends Controller {

    private final MailService mailService;

    public MailController() {
        this.mailService = new MailService(new SendgridMailStrategy());
        this.view = new MailListView(this);
    }

    public void cmdShowDankwoordView() {
        this.view = new MailDankwoordView(this);
        show();
    }

    public void cmdShowSelectRecipients() {
        this.view = new MailSelectRecipientsView(this);
        show();
    }
}
