package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Document;

@Repository
public interface IDocumentRepository extends PagingAndSortingRepository<Document, Long> {

	Document findByName(String name);
    
	Document findOne(Long id);
    
    List<Document> findByNameLike(String name, Pageable pageable);
    List<Document> findByNameLike(String name);

    @SuppressWarnings("unchecked")
    Document save (Document object);
}
