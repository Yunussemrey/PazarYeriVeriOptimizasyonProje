package com.yazilimciAkademisi.marketplace.util;

import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.exception.UnauthorizedAccessException;

public class OwnershipUtil {

    // Check if the currently authenticated user is the owner of the store
    public static void checkStoreOwnership(Store store, Integer currentUserId) {
        if (!store.getUser().getId().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You do not own this store or its products.");
        }
    }

}
