package demo.onlineshoppingbe.dto;

import lombok.Data;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */

@Data
public class Category {

    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean active = true;
}
