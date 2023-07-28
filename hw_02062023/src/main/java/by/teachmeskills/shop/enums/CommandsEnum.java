package by.teachmeskills.shop.enums;

public enum CommandsEnum {
    SIGN_IN_COMMAND("sign-in"),
    REGISTER_COMMAND("register"),
    HOME_PAGE_COMMAND("get-homePage"),
    CATEGORY_PAGE_COMMAND("category-redirect"),
    PRODUCT_PAGE_COMMAND("product-redirect"),
    REDIRECT_SHOPPING_CART_COMMAND("redirect-to-shopping-cart"),
    ADD_PRODUCT_TO_CART_COMMAND("add-product-to-cart"),
    DELETE_PRODUCT_FROM_CART_COMMAND("delete-product-from-cart"),
    DELETE_ALL_PRODUCTS_FROM_CART_COMMAND("delete-all-products-from-cart");

    private String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
