package com.restful03.TI323.entity;

import com.restful03.TI323.dto.category.DadosAtualizacaoCategory;
import com.restful03.TI323.dto.category.DadosCadastroCategory;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Category")
@Table(name = "tb_category",
        schema = "db_api_ti323")
public class Category implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories")
    @Setter(AccessLevel.NONE)
    private Set<Product> products = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Category(@Valid DadosCadastroCategory dadosCadastroCategory) {
        this.name = dadosCadastroCategory.name();
        this.description = dadosCadastroCategory.description();
    }

    public Category(Long aLong) {
        this.id = aLong;
    }

    public void atualizarInformacoes(DadosAtualizacaoCategory dadosCadastroCategory) {
        this.name = dadosCadastroCategory.name();
        this.description = dadosCadastroCategory.description();
    }

    public Category(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();

        for (Product product : category.getProducts())
            this.products.add(new Product(product));

        this.dataCreated = category.getDataCreated();
        this.lastUpdated = category.getLastUpdated();
    }

    @Override
    public Object clone() {
        Category clone = null;
        try {
            clone = new Category(this);
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
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
