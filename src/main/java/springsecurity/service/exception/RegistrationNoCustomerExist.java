package springsecurity.service.exception;

public class RegistrationNoCustomerExist extends Exception{
    private static final long serialVersionUID = 1L;



    public RegistrationNoCustomerExist(String msg) {
        super(msg);
    }

}
