package ua.in.usv.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.usv.entity.firm.Company;
import ua.in.usv.repository.firm.CompanyRepository;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company findFirst() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.get(0);
    }
}
