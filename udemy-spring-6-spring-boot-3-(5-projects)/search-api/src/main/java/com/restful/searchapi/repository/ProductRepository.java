package com.restful.searchapi.repository;

import com.restful.searchapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Realiza uma busca flexível por produtos no banco de dados com base em uma consulta de texto.
     *
     * @param query Consulta de texto para buscar produtos (correspondência parcial nos campos name e description).
     * @return Lista de produtos que atendem aos critérios de pesquisa.
     */
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchProducts(String query);

    /**
     * Realiza uma busca flexível por produtos no banco de dados usando uma consulta SQL nativa.
     *
     * @param query Consulta de texto para buscar produtos (correspondência parcial nos campos name e description).
     * @return Lista de produtos que atendem aos critérios de pesquisa.
     */
    @Query(value = "SELECT * FROM product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Product> searchProductsSQL(String query);
}
