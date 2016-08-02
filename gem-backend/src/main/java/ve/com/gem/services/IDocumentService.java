package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Document;

public interface IDocumentService {
	 
	 Page<Document> findAll(Pageable pageable);
	 Page<Document> findAll(Sort sort);
	 List<Document> search(String key);
	 void addByName(String name);
	 Document save (Document object);
	 Document findById(Long id);
	boolean delete(Document object);
	 
}
