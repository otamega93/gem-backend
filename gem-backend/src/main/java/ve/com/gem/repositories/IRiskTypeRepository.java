package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.RiskType;

@Repository
public interface IRiskTypeRepository extends PagingAndSortingRepository<RiskType, Long> {

	RiskType findByName(String name);
    
	RiskType findOne(Long id);
    
    List<RiskType> findByNameLike(String name, Pageable pageable);
    List<RiskType> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    RiskType save (RiskType object);
}
