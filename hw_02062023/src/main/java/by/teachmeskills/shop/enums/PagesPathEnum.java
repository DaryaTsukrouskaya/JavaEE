package by.teachmeskills.shop.enums;

public enum PagesPathEnum {
    HOME_PAGE("home.jsp"),
    REGISTER_PAGE("register.jsp"),
    SIGN_IN_PAGE("login.jsp"),
    CATEGORY_PAGE("category.jsp"),
    CART_PAGE("cart.jsp"),
    PRODUCT_PAGE("product.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }
}
