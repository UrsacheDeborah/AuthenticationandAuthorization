package io.getarrays.JWT.service;

import io.getarrays.JWT.domain.Rol;
import io.getarrays.JWT.domain.Utilizator;

import java.util.List;

public interface UtilizatorService {
    Utilizator saveUser(Utilizator user);
    Rol saveRole(Rol role);
    void adaugaRol(String username, String roleName);
    Utilizator getUser(String username);
    List<Utilizator> getUsers();

}
