package Managment;

public class ClientPremium extends Client{
    private boolean premium;
    public ClientPremium(String name, String email, boolean premium) {
        super(name, email);
        this.premium = premium;
    }

    public boolean getPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
