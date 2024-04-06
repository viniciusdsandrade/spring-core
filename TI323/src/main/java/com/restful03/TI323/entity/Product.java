package com.restful03.TI323.entity;

import com.restful03.TI323.dto.product.DadosAtualizacaoProduct;
import com.restful03.TI323.dto.product.DadosCadastroProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "tb_product",
        schema = "db_api_ti323",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_name", columnNames = "name")
        }
)
@Schema(description = "Entidade que representa um produto")
public class Product implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;

    @CreationTimestamp
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public Product(DadosCadastroProduct dadosCadastroProduct) {
        this.name = dadosCadastroProduct.name();
        this.description = dadosCadastroProduct.description();
        this.price = dadosCadastroProduct.price();
        this.available = dadosCadastroProduct.available();

        Category category = new Category((dadosCadastroProduct.categoryId()));
        this.categories.add(category);

        this.dataCreated = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    public void desativarProduto() {
        this.available = false;
    }

    public void ativarProduto() {
        this.available = true;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoProduct dadosAtualizacaoProduct) {
        this.name = dadosAtualizacaoProduct.name();
        this.description = dadosAtualizacaoProduct.description();
        this.price = dadosAtualizacaoProduct.price();
        this.available = dadosAtualizacaoProduct.available();
    }

    public Product(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");

        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.available = product.getAvailable();

        for (Category category : product.getCategories())
            this.categories.add(new Category(category));

        this.dataCreated = product.getDataCreated();
        this.lastUpdated = product.getLastUpdated();
    }

    @Override
    public Object clone() {
        Product clone = null;
        try {
            clone = new Product(this);
        } catch (Exception ignored) {
        }
        return clone;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
