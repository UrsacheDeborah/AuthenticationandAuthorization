package io.getarrays.JWT.repozitor;

import io.getarrays.JWT.domain.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizatorRepo extends JpaRepository<Utilizator, Long> {
    Utilizator findByUsername(String username);
}
