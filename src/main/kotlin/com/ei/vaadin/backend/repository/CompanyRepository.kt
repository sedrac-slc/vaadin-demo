package com.ei.vaadin.backend.repository

import com.ei.vaadin.backend.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CompanyRepository: JpaRepository<Company, String> {
    fun findByName(name: String): Optional<Company>
}