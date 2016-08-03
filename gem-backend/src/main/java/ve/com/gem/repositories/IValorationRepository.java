package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Valoration;

@Repository
public interface IValorationRepository extends PagingAndSortingRepository<Valoration, Long> {

	Valoration findByName(String name);
    
	Valoration findOne(Long id);
    
    List<Valoration> findByNameLike(String name, Pageable pageable);
    List<Valoration> findByNameLike(String name);
    @SuppressWarnings("unchecked")
    Valoration save (Valoration object);
}
