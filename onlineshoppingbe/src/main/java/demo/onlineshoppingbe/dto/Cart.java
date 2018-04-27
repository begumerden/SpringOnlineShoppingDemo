package demo.onlineshoppingbe.dto;

import lombok.Data;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * @author: b.erden
 * @date: 27.4.2018
 */

@Entity
@Data
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @Column(name = "grand_total")
    private double grandTotal;

    @Column(name = "cart_lines")
    private int cartLines;
}
