package uk.ac.york.eng2.products.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import uk.ac.york.eng2.products.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PageableRepository<Product, Long> {

    List<Product> findByTagsId(Long id);

}
