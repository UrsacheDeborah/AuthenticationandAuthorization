package io.getarrays.JWT.service;

import io.getarrays.JWT.domain.Rol;
import io.getarrays.JWT.domain.Utilizator;
import io.getarrays.JWT.repozitor.RolRepo;
import io.getarrays.JWT.repozitor.UtilizatorRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UtilizatorServiceImplementation implements UtilizatorService{
    private final UtilizatorRepo userRepo;
    private final RolRepo roleRepo;
    @Override
    public Utilizator saveUser(Utilizator user) {
        log.info("Se salveaza utilizator {} nou in baza de date", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Rol saveRole(Rol role) {
        log.info("Se salveaza rol {} nou in baza de date", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void adaugaRol(String userName, String roleName) {
        log.info("Se adauga rol nou {} pentru utilizatorul {}", roleName, userName);
        Utilizator utilizator = userRepo.findByUsername(userName);
        Rol role = roleRepo.findByName(roleName);
        utilizator.getRoluri().add(role);

    }

    @Override
    public Utilizator getUser(String userName) {
        log.info("Se returneaza numele utilizatorului {}", userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<Utilizator> getUsers() {
        log.info("Se returneaza numele tuturor utilizatorilor");
        return userRepo.findAll();
    }
}
