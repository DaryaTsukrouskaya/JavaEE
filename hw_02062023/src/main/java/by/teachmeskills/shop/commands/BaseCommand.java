package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface BaseCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
