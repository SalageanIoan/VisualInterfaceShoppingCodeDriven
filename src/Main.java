import Interfaces.LoginSelection;
import Interfaces.Shop;

public class Main {
    public static void main(String[] args) {
        new LoginSelection();
        Shop.addProductsToList();
    }
}