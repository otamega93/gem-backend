package ve.com.gem.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import ve.com.gem.entities.Organization;
import ve.com.gem.entities.Company;
import ve.com.gem.repositories.IOrganizationRepository;
import ve.com.gem.repositories.ICompanyRepository;
import ve.com.gem.services.IOrganizationService;

@Transactional(readOnly = true)
@Service
public class OrganizationService implements IOrganizationService {
	
	
	@Autowired
	private IOrganizationRepository repository;
	
	@Autowired
	private ICompanyRepository companyRepository;
	
	private List<Organization> objects = new ArrayList<Organization>();

	
	@Transactional(readOnly = false)
	@Override
	public Organization save(Organization organization) {
		
		if (null != organization) {
			organization.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			if(organization.getCompany() !=null){
			Company company = companyRepository.findOne(organization.getCompany().getId());
			organization.setCompany(company);
			companyRepository.save(company);
			}
			organization.setIsActive(true);
			return repository.save(organization);
		}
		
		else
			return null;
	}

	@Override
	public Page<Organization> findAll(Pageable pageable) {
		
		objects = Lists.newArrayList(repository.findAll(pageable));
		PageImpl<Organization> organizationPages = new PageImpl<>(objects, pageable, repository.count());
		return organizationPages;
	}

	@Override
	public Organization findById(Long id) {
		
		if (null != id)
			return repository.findOne(id);
		
		else
			return null;
	}

	@Transactional(readOnly = false)
	@Override
	public boolean delete(Organization object) {
		Long id=0L;
		if(null != object){
			System.out.println("No es nula.");
			id=object.getId();
		}
		repository.delete(id);
		System.out.println(repository.exists(id));
		return !repository.exists(id);
	}

}
