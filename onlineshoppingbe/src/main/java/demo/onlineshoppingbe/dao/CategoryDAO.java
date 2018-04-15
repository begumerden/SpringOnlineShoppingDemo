package demo.onlineshoppingbe.dao;

import demo.onlineshoppingbe.dto.Category;

import java.util.List;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */
public interface CategoryDAO {

    List<Category> list();

    Category getById(int id);

    boolean add(Category category);
}
