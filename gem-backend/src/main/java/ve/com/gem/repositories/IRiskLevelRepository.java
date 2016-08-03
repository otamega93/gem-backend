package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.RiskLevel;

@Repository
public interface IRiskLevelRepository extends PagingAndSortingRepository<RiskLevel, Long> {

	RiskLevel findByName(String name);
    
	RiskLevel findOne(Long id);
    
    List<RiskLevel> findByNameLike(String name, Pageable pageable);
    List<RiskLevel> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    RiskLevel save (RiskLevel object);
}
