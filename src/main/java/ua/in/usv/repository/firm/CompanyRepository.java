package ua.in.usv.repository.firm;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.firm.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findById(Long id);
}
