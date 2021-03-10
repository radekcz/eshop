package cz.rk.eshop.entity;

import cz.rk.eshop.utils.WatchFountainConverter;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Watch entity
 */
@Entity
public class Watch {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;

    @Column(columnDefinition = "BLOB")
    @Convert(converter = WatchFountainConverter.class)
    private String fountain;


    public Watch(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Watch() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFountain() {
        return fountain;
    }

    public void setFountain(String fountain) {
        this.fountain = fountain;
    }
}
