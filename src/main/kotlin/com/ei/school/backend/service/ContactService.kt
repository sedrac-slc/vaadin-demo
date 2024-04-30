package com.ei.school.backend.service

import com.ei.school.backend.model.Contact
import com.ei.school.backend.repository.ContactRepository
import org.springframework.stereotype.Service

@Service
class ContactService (
    val contactRepository: ContactRepository
){

    fun findAll(): List<Contact> = contactRepository.findAll()

    fun findByFirstNameContaining(firstName: String) = contactRepository.findByFirstNameContaining(firstName)

    fun count(): Long = contactRepository.count()

    fun save(contact: Contact): Contact = contactRepository.save(contact)

    fun saveOrUpdate(contact: Contact): Contact {
        contactRepository.findByEmail(contact.email).ifPresent { contact.id = it.id }
        return save(contact)
    }

}