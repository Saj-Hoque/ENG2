package uk.ac.york.eng2.products.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import uk.ac.york.eng2.products.domain.OrdersByDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersByDayRepository extends PageableRepository<OrdersByDay, Long> {

    Optional<OrdersByDay> findByProductIdAndDay(Long productId, LocalDate day);
    List<OrdersByDay> findByProductId(Long productId);
    List<OrdersByDay> findByDay(LocalDate day);

}
