package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {

    MenuItem add(MenuItem menuItem);
    List<MenuItem> getAll();

    List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement);

    Optional<MenuItem> findById(long id);
}
