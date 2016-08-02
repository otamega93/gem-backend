package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ve.com.gem.controllers.AccountController;
import ve.com.gem.entities.Account;
import ve.com.gem.resources.AccountResource;

@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource>{

	public AccountResourceAssembler () {
		super(AccountController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource resource = new AccountResource();
		resource.setUsername(account.getUsername());
		resource.setPassword(account.getPassword());
		resource.setAuthorities(account.getAuthorities());
		resource.setIds(account.getId());
	    resource.add(linkTo(AccountController.class).slash("").slash(account.getId()).withSelfRel());
	    resource.add(linkTo(AccountController.class).slash("").slash(account.getId()).withRel("account"));
	    return resource;
	}
}
