package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Risk;

@Repository
public interface IRiskRepository extends PagingAndSortingRepository<Risk, Long> {

	Risk findByName(String name);
    
	Risk findOne(Long id);
    
    List<Risk> findByNameLike(String name, Pageable pageable);
    List<Risk> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    Risk save (Risk object);
}
