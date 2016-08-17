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
import ve.com.gem.entities.EndPointServiceAuthentication;
import ve.com.gem.repositories.IEndPointServiceAuthenticationRepository;
import ve.com.gem.services.IEndPointServiceAuthenticationService;

@Transactional(readOnly = true)
@Service
public class EndPointServiceAuthenticationService implements IEndPointServiceAuthenticationService {

	private List<EndPointServiceAuthentication> endPointServiceAuthentication = new ArrayList<EndPointServiceAuthentication>();
	
	@Autowired
	private IEndPointServiceAuthenticationRepository EndPointServiceAuthenticationRepository;
	
	@Transactional(readOnly = false)
	@Override
	public EndPointServiceAuthentication save(EndPointServiceAuthentication endPointServiceAuthentication) {
		if (null !=  endPointServiceAuthentication) {
			return EndPointServiceAuthenticationRepository.save(endPointServiceAuthentication);
		}
		else
			return null;
	}

	@Override
	public Page<EndPointServiceAuthentication> findAll(Pageable pageable) {
		endPointServiceAuthentication = Lists.newArrayList(EndPointServiceAuthenticationRepository.findAll(pageable));
		PageImpl<EndPointServiceAuthentication> endPointServiceAuthenticationPages = new PageImpl<EndPointServiceAuthentication>(endPointServiceAuthentication, pageable, EndPointServiceAuthenticationRepository.count());
		return endPointServiceAuthenticationPages;
	}

	@Override
	public EndPointServiceAuthentication findById(Long id) {
		if(null != id)
			return EndPointServiceAuthenticationRepository.findOne(id);
		else
			return null;
	}

	@Override
	public boolean delete(Long id) {
		if (null != id) {
			EndPointServiceAuthenticationRepository.delete(id);
			return true;
		}
		else
			return false;
	}

}
