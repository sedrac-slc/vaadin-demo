package com.ei.school.backend.component

import com.ei.school.backend.enums.StatusEnum
import com.ei.school.backend.model.Company
import com.ei.school.backend.model.Contact
import com.ei.school.backend.service.CompanyService
import com.ei.school.backend.service.ContactService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataSourceRunner(
    val companyService: CompanyService,
    val contactService: ContactService
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        val africell = companyService.saveOrUpdate(Company("Africell"))
        val unitel = companyService.saveOrUpdate(Company("Unitel"))
        val movicel = companyService.saveOrUpdate(Company("Movicel"))

        mutableListOf(

            Contact( "Sedrac", "Calupeteca","slcsedrac@gmail.com", StatusEnum.CONTACTED, africell),
            Contact( "Pedro", "Lopez","pedrolopez@gmail.com", StatusEnum.CONTACTED, unitel),
            Contact( "Lucas", "Wuma","lucaswuma@gmail.com", StatusEnum.NOT_CONTACTED, movicel),

            Contact( "Ana", "Paula","anapaula@hotmail.com", StatusEnum.CONTACTED, africell),
            Contact( "Linda", "Joana","lindajoana@gmail.com", StatusEnum.CLOSED_LOST, unitel),
            Contact( "Brenda", "Morais","brendamorais@hotmail.com", StatusEnum.NOT_CONTACTED, movicel),

            Contact( "Orlando", "Mágico","orlandomagico@gmail.com", StatusEnum.NOT_CONTACTED, africell),
            Contact( "Vitória", "Wilson","vitoriawilson@yahoo.com", StatusEnum.CLOSED_LOST, unitel),
            Contact( "Wilma", "Soma","wilmasoma@gmail.com", StatusEnum.NOT_CONTACTED, movicel),

            Contact( "Anabela", "Paula","anabelapaula@hotmail.com", StatusEnum.CONTACTED, africell),
            Contact( "Quilson", "Belmo","quilsonbelmo@gmail.com", StatusEnum.CLOSED_LOST, unitel),
            Contact( "Utongo", "Carlos","utongocarlos@hotmail.com", StatusEnum.NOT_CONTACTED, movicel),

            Contact( "Afri", "Money","slcsedrac@gmail.com", StatusEnum.CUSTOMER, africell),
            Contact( "Unitel", "Money","pedrolopez@gmail.com", StatusEnum.CUSTOMER, unitel),
            Contact( "Mov", "Money","lucaswuma@gmail.com", StatusEnum.CUSTOMER, movicel),
            ).forEach {
            contactService.saveOrUpdate(it)
        }

    }

}