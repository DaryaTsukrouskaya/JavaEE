package by.teachmeskills.eshop.enums;

public enum PagesPathEnum {
    HOME_PAGE("home"),
    REGISTER_PAGE("register"),
    SIGN_IN_PAGE("login"),
    CATEGORY_PAGE("category"),
    CART_PAGE("cart"),
    PRODUCT_PAGE("product"),
    USER_PROFILE_PAGE("userProfile"),
    SEARCH_PAGE("search");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }
}
