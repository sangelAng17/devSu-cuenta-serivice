package com.devsu.cuenta.application;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.ports.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findId(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta insert(Cuenta cuenta){return cuentaRepository.insert(cuenta);}

    public Cuenta update(Cuenta cuenta){return cuentaRepository.update(cuenta);}

    public void delete(Long id){cuentaRepository.delete(id);}

}
