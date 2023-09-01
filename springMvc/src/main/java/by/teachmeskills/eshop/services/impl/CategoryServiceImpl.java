package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.enums.PagesPathEnum;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.eshop.services.CategoryService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final Logger log = LogManager.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> read() throws DBConnectionException {
        return categoryRepository.read();
    }

    @Override
    public void create(Category category) throws DBConnectionException, UserAlreadyExistsException {
        categoryRepository.create(category);
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        categoryRepository.delete(id);
    }


    public Category findById(int id) throws DBConnectionException {
        return categoryRepository.findById(id);
    }

    public ModelAndView getCategoriesData() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("categories", categoryRepository.read());
        } catch (DBConnectionException e) {
            log.error(e.getMessage());
        }
        return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);

    }
}
