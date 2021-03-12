package cz.rk.eshop.entity;

import cz.rk.eshop.utils.WatchFountainConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


/**
 * Watch entity
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Watch {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title must be filled in.")
    @Size(min = 2)
    private String title;

    @NotNull(message = "Price must be filled in.")
    private BigDecimal price;

    private String description;

    @Column(columnDefinition = "BLOB")
    @Convert(converter = WatchFountainConverter.class)
    private String fountain;


    public Watch(Long id, String title){
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Watch [" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", fountain='" + fountain + '\'' +
                ']';
    }
}
