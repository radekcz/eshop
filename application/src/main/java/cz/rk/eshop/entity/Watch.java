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

    @NotNull
    @Size(min = 2)
    private String title;

    @NotNull
    private BigDecimal price;

    private String description;

    @Column(columnDefinition = "BLOB")
    @Convert(converter = WatchFountainConverter.class)
    private String fountain;


    public Watch(Long id, String title){
        this.id = id;
        this.title = title;
    }
}
