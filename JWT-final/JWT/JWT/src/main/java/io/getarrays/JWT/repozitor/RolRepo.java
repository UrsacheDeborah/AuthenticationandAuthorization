package io.getarrays.JWT.repozitor;

import io.getarrays.JWT.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Long> {
    Rol findByName(String name);
}
