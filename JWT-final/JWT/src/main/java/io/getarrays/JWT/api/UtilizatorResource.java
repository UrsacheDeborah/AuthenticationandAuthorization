package io.getarrays.JWT.api;

import io.getarrays.JWT.domain.Rol;
import io.getarrays.JWT.domain.Utilizator;
import io.getarrays.JWT.service.UtilizatorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/api")
public class UtilizatorResource {
    private final UtilizatorService userService;

    @GetMapping("/utilizatori")
    public ResponseEntity<List<Utilizator>> getUsers()
    {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/utilizator/save")
    public ResponseEntity<Utilizator> saveUser(@RequestBody Utilizator user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/utilizator/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/rol/save")
    public ResponseEntity<Rol> saveRole(@RequestBody Rol role)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rol/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/rol/addtoutilizator")
    public ResponseEntity<?> adaugaRol(@RequestBody formularRolLaUtilizator formular)
    {
        userService.adaugaRol(formular.getUserName(), formular.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class formularRolLaUtilizator
{
    private String userName;
    private String roleName;

}
