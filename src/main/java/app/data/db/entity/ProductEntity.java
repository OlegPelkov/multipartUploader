package app.data.db.entity;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    @NotNull(message = "Name may not be null")
    @NotEmpty(message = "Name may not be empty")
    private String name;

    @Column(name = "description", nullable = false)
    @NotNull(message = "Name may not be null")
    private String description;

    @ElementCollection
    @CollectionTable(name = "product_properties", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> properties = new HashMap<>();

    public ProductEntity(Map<String, String> properties) {
        this.properties = properties;
    }

    public ProductEntity() {
    }
}
