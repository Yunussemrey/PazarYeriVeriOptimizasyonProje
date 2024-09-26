package com.yazilimciAkademisi.marketplace.util;

import com.yazilimciAkademisi.marketplace.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    // Utility method to get the currently logged-in user's ID
    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();  // Get the currently logged-in user's ID
    }

}
