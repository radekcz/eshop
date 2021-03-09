package cz.rk.eshop.entity;

import javax.persistence.Entity;

/**
 * Watch entity
 */
@Entity
public class Watch {

    private long id;
    private String title;
    private long price;
    private String description;


    public Watch(long id, String title){
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
