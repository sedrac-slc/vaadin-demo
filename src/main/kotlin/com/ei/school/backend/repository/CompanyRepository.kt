package com.ei.school.backend.repository

import com.ei.school.backend.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CompanyRepository: JpaRepository<Company, String> {
    fun findByName(name: String): Optional<Company>
}