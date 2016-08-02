package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Expert;

@Repository
public interface IExpertRepository extends PagingAndSortingRepository<Expert, Long> {

	Expert findByName(String name);
    
	Expert findOne(Long id);
    
    List<Expert> findByNameLike(String name, Pageable pageable);

    @SuppressWarnings("unchecked")
    Expert save (Expert object);
}
