package cours.uahb.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
//ou @Component
public class CustumUserDetailsService implements UserDetailsService{
	@Autowired
	private IUtilisateur userDAO ;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur user = userDAO.findByLogin(username);
//		if(user != null)
//		{
//			List roles = new ArrayList();
//			roles.add(user.getRole());
//			 User u = new User(user.getLogin(),user.getPwd(),
//					 true,true,true,true,getAuthorities(roles));
//			 return u ;
//		}
		
//		return null;
		return user;
	}
	
//	private Collection getAuthorities(List roles) {
//		List authorities = new ArrayList();
//		for(Object role : roles)
//		{
//			Role l = (Role)role;
//			authorities.add(new SimpleGrantedAuthority(l.getLibRole()));
//		}
//		return authorities ;
//	}

}
