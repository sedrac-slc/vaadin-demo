package com.ei.vaadin.backend.repository

import com.ei.vaadin.backend.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ContactRepository: JpaRepository<Contact, String> {
    fun findByEmail(email: String): Optional<Contact>
    fun findByFirstNameContaining(firstName: String): List<Contact>
}