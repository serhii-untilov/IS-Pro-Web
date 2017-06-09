package ua.in.usv.service;

import ua.in.usv.entity.firm.Company;

public interface CompanyService {
    Company findById(Long id);
    Company findFirst();
}
