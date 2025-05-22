package uk.ac.york.eng2.products.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import uk.ac.york.eng2.products.domain.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends PageableRepository<Tag, Long> {

    List<Tag> findByProductsId(Long id);
    Optional<Tag> findByName(String name);

}
