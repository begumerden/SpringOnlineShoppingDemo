package demo.onlineshoppingbe.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: b.erden
 * @date: 27.4.2018
 */
@Entity
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please enter address line one!")
    @Column(name = "address_line_one")
    private String addressLineOne;

    @NotBlank(message = "Please enter address line two!")
    @Column(name = "address_line_two")
    private String addressLineTwo;

    @NotBlank(message = "Please enter City!")
    private String city;

    @NotBlank(message = "Please enter State!")
    private String state;

    @NotBlank(message = "Please enter country!")
    private String country;

    @Column(name = "postal_code")
    @NotBlank(message = "Please enter Postal Code!")
    private String postalCode;

    @Column(name = "is_shipping")
    private boolean shipping;

    @Column(name = "is_billing")
    private boolean billing;

    @ManyToOne
    private User user;
}
