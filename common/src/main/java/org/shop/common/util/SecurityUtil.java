package org.shop.common.util;

import lombok.extern.log4j.Log4j2;
import org.shop.common.exception.IllegalUserAccessException;
import org.shop.common.security.AuthenticationEntity;
import org.springframework.security.core.context.SecurityContextHolder;

@Log4j2
public final class SecurityUtil {


	public static void checkIfIllegalUser(String customerID) throws IllegalUserAccessException {
		final String id = getAuthenticatedUserID();
		if (!id.equals(customerID)) {
			boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().
					stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			if(!isAdmin){
				log.warn(SecurityContextHolder.getContext().getAuthentication().getDetails());
				throw new IllegalUserAccessException("illegal user: " + id + " trying to access " + customerID + "'s info");
			}
		}
	}

	public static String getAuthenticatedUserID() {
		final AuthenticationEntity details = (AuthenticationEntity) SecurityContextHolder.getContext().getAuthentication().getDetails();
		return details.getId();
	}
	public static AuthenticationEntity getAuthenticatedUser() {
		final AuthenticationEntity details = (AuthenticationEntity) SecurityContextHolder.getContext().getAuthentication().getDetails();
		return details;
	}
}
