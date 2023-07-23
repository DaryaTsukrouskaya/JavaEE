package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.commands.AddProductToCartCommand;
import by.teachmeskills.shop.commands.BaseCommand;
import by.teachmeskills.shop.commands.CategoryProductPageCommand;
import by.teachmeskills.shop.commands.RemoveProductFromCartCommand;
import by.teachmeskills.shop.commands.HomePageCommand;
import by.teachmeskills.shop.commands.RedirectToProductPageCommand;
import by.teachmeskills.shop.commands.RedirectToShoppingCartCommand;
import by.teachmeskills.shop.commands.RegisterCommand;
import by.teachmeskills.shop.commands.SignInCommand;
import by.teachmeskills.shop.enums.CommandsEnum;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private CommandFactory() {

    }

    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.REGISTER_COMMAND.getCommand(), new RegisterCommand());
        COMMAND_LIST.put(CommandsEnum.SIGN_IN_COMMAND.getCommand(), new SignInCommand());
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomePageCommand());
        COMMAND_LIST.put(CommandsEnum.PRODUCT_PAGE_COMMAND.getCommand(), new RedirectToProductPageCommand());
        COMMAND_LIST.put(CommandsEnum.CATEGORY_PAGE_COMMAND.getCommand(), new CategoryProductPageCommand());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_SHOPPING_CART_COMMAND.getCommand(), new RedirectToShoppingCartCommand());
        COMMAND_LIST.put(CommandsEnum.ADD_PRODUCT_TO_CART_COMMAND.getCommand(), new AddProductToCartCommand());
        COMMAND_LIST.put(CommandsEnum.DELETE_PRODUCT_FROM_CART_COMMAND.getCommand(), new RemoveProductFromCartCommand());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter("command");
        if (commandKey == null || commandKey.isBlank()) {
            commandKey = CommandsEnum.SIGN_IN_COMMAND.getCommand();

        }
        return COMMAND_LIST.get(commandKey);
    }
}
