package ve.com.gem.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Phase;

@Repository
public interface IPhaseRepository extends PagingAndSortingRepository<Phase, Long>{

}
