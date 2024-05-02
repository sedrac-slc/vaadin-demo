package com.ei.vaadin.backend.service

import com.ei.vaadin.backend.model.Company
import com.ei.vaadin.backend.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService (
    val companyRepository: CompanyRepository
){

    fun findAll(): List<Company> = companyRepository.findAll()

    fun count(): Long = companyRepository.count()

    fun save(company: Company): Company = companyRepository.save(company)

    fun saveOrUpdate(company: Company): Company {
        companyRepository.findByName(company.name).ifPresent { company.id = it.id }
        return save(company)
    }

}