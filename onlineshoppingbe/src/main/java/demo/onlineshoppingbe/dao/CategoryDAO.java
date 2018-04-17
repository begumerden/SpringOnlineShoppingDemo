package demo.onlineshoppingbe.dao;

import demo.onlineshoppingbe.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */
@Repository
public interface CategoryDAO {

    List<Category> list();

    Category getById(int id);

    boolean add(Category category);
    boolean update(Category category);
    boolean delete(Category category);
}
