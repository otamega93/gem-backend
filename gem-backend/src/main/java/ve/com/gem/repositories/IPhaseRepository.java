package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Phase;

@Repository
public interface IPhaseRepository extends PagingAndSortingRepository<Phase, Long> {

	Phase findByName(String name);

	Phase findOne(Long id);

	List<Phase> findByNameLike(String name, Pageable pageable);

	@SuppressWarnings("unchecked")
	Phase save(Phase object);

}
