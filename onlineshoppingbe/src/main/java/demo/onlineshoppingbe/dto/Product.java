package demo.onlineshoppingbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author: b.erden
 * @date: 18.4.2018
 */
@Data
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @NotBlank(message = "Please enter the product name!")
    private String name;

    @NotBlank(message = "Please enter the brand name!")
    private String brand;

    @NotBlank(message = "Please enter the description!")
    @JsonIgnore
    private String description;

    @Column(name = "unit_price")
    @Min(value = 1, message = "Please select at least one value!")
    private double unitPrice;

    private int quantity;

    @Column(name = "is_active")
    private boolean active;

    @JsonIgnore
    @Column(name = "category_id")
    private int categoryId;

    @JsonIgnore
    @Column(name = "supplier_id")
    private int supplierId;

    private int purchases;

    private int views;

    @Transient
    private MultipartFile file;


    public Product() {
        this.code = "PRDCT" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
                + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active=" + active
                + ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", purchases=" + purchases + ", views="
                + views + "]";
    }
}
