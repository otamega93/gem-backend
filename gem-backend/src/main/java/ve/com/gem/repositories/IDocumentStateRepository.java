package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.DocumentState;

@Repository
public interface IDocumentStateRepository extends PagingAndSortingRepository<DocumentState, Long> {

	DocumentState findByName(String name);

	DocumentState findOne(Long id);

	List<DocumentState> findByNameLike(String name, Pageable pageable);

	@SuppressWarnings("unchecked")
	DocumentState save(DocumentState object);

}
