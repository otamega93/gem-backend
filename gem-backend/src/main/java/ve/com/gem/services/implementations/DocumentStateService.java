package ve.com.gem.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ve.com.gem.entities.DocumentState;
import ve.com.gem.repositories.IDocumentStateRepository;
import ve.com.gem.services.IDocumentStateService;

@Transactional(readOnly = true)
@Service
public class DocumentStateService implements IDocumentStateService {

	@Autowired
	private IDocumentStateRepository repository;
	
	private List<DocumentState> objects = new ArrayList<DocumentState>();
	
	@Override
	public Page<DocumentState> findAll(Pageable pageable) {
		
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<DocumentState> documentStatePages = new PageImpl<DocumentState>(objects, pageable, repository.count());
		return documentStatePages;
	}

	@Transactional(readOnly = false)
	@Override
	public DocumentState save(DocumentState object) {
		if (null !=  object) {
			return repository.save(object);
		}
		else
			return null;
	}

	@Override
	public DocumentState findById(Long id) {
		if (null != id) {
			return repository.findOne(id);			 
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(DocumentState object) {
		this.repository.delete(object);
	}

}
