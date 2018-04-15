package demo.onlineshoppingbe.dao.impl;

import demo.onlineshoppingbe.dto.Category;
import demo.onlineshoppingbe.dao.CategoryDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    //TODO: for testing purpose
    private static List<Category> categories;

    static {
        categories = new ArrayList<>();

        Category phoneCategory = new Category();
        phoneCategory.setId(1);
        phoneCategory.setName("Phone");
        phoneCategory.setDescription("Phone Description");
        phoneCategory.setImageUrl("CAT_1.png");
        categories.add(phoneCategory);

        Category tvCategory = new Category();
        tvCategory.setId(2);
        tvCategory.setName("Television");
        tvCategory.setDescription("Television Description");
        tvCategory.setImageUrl("CAT_2.png");
        categories.add(tvCategory);

    }

    @Override
    public List<Category> list() {
        return categories;
    }

    @Override
    public Category getById(int id) {
        return categories.stream().filter( item -> item.getId() == id).findAny().orElse(null);
    }


}
