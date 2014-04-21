package org.scuola247.desktop.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.scuola247.desktop.entity.User;
import org.scuola247.desktop.security.Utente;
import org.scuola247.desktop.security.UtenteDetailsService;
import org.scuola247.desktop.security.UtenteDettagli;

public class Util {

	private Util() {
		// do not instantiate this class
	}
/*
	public static void signin(User user) {
		JpaUserDetails principal = new JpaUserDetails(user);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, null,
				principal.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(token);
	}
*/
	public static void signin(Utente utente) {
		UtenteDettagli principal = new UtenteDettagli(utente);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, null,
				principal.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(token);
	}

	
	public static UtenteDettagli getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UtenteDettagli) {
			return (UtenteDettagli) principal;
		}
		return null;
	}
	
	/*
	public static JpaUserDetails getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof JpaUserDetails) {
			return (JpaUserDetails) principal;
		}
		return null;
	}
	*/

	public static boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}

		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return false;
		}

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (role.equals(auth.getAuthority())) {
				return true;
			}
		}

		return false;
	}

}
