package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Indicator;

@Repository
public interface IIndicatorRepository extends PagingAndSortingRepository<Indicator, Long> {

	Indicator findByName(String name);
    
	Indicator findOne(Long id);
    
    List<Indicator> findByNameLike(String name, Pageable pageable);
    List<Indicator> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    Indicator save (Indicator object);
}
