package ch.hearc.spring.thymeleaf.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

	public static String getLoggedUserName() {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(aut.getName());
		return aut.getName();
	}
}
