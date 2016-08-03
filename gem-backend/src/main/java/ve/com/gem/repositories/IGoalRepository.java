package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Goal;

@Repository
public interface IGoalRepository extends PagingAndSortingRepository<Goal, Long> {

	Goal findByName(String name);
    
	Goal findOne(Long id);
    
    List<Goal> findByNameLike(String name, Pageable pageable);
    List<Goal> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    Goal save (Goal object);
}
