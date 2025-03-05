package com.example.gvvfd.erp.Services.Agencies;

import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Models.enums.Platform;
import com.example.gvvfd.erp.Repositories.AgencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository repo;

    public Optional<Agency> findById(Long agencyId) {
        return repo.findById(agencyId);
    }

    public void Update(Agency Obj) {
        repo.saveAndFlush(Obj);
    }

    public List<Agency> GetAll() {
        return repo.findAll();
    }

    @PostConstruct
    public void init() {
        Agency a = new Agency();
        a.setCreated(new Date());
        a.setActive(true);
        a.setAdmin("YoDog117");
        a.setName("Gran Vista Volunteer Fire Department");
        a.setPlatform(Platform.Roblox);
        repo.saveAndFlush(a);
    }
}
