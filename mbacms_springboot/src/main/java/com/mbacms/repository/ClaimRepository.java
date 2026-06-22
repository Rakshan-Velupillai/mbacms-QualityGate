package com.mbacms.repository;

import com.mbacms.enums.ClaimStatus;
import com.mbacms.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {

    @Query("""
SELECT c FROM Claim c
 WHERE c.patientInsurancePlan.patient.user.username = :username
  ORDER BY c.submissionDate DESC
""")
    List<Claim> getClaimsByPatientUsername(@Param("username") String username);

    @Query("""
SELECT c FROM Claim c
WHERE c.patientInsurancePlan.insurancePlan.insuranceCompany.user.username = :username
ORDER BY c.submissionDate DESC
""")
    List<Claim> getClaimsByCompanyUsername(@Param("username") String username);

    boolean existsByInvoiceInvoiceNumber(String invoiceNumber);

    long countByClaimStatus(ClaimStatus claimStatus);
}
