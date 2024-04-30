package com.ei.school.ui

import com.ei.school.backend.model.Contact
import com.ei.school.backend.service.ContactService
import com.vaadin.flow.component.dependency.StyleSheet
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route

@Route("")
@StyleSheet("./css/style.css")
class MainView (
    val contactService: ContactService
): VerticalLayout() {

    private val grid = Grid(Contact::class.java)
    private val textFilter: TextField = TextField()

    init {
        addClassName("list-view")
        setSizeFull()
        configGrid()
        configTextFilter()

        val form = ContactForm()
        val content = Div(grid, form)

        content.setSizeFull()
        content.addClassName("content")

        add(textFilter, content)
        updateGrid()
    }

    fun configTextFilter(){
        textFilter.placeholder = "Digit first name"
        textFilter.isClearButtonVisible = true
        textFilter.valueChangeMode = ValueChangeMode.LAZY
        textFilter.addValueChangeListener { grid.setItems(contactService.findByFirstNameContaining(it.value)) }
    }

    fun configGrid(){
        grid.addClassName("contact-grid")
        grid.setSizeFull()
        grid.removeColumnByKey("company")
        grid.setColumns("firstName","lastName","email", "status")
        grid.addColumn { it.company.name }.setHeader("Company")
        grid.columns.forEach { it.setAutoWidth(true) }
    }

    fun updateGrid(){
        grid.setItems(contactService.findAll())
    }

}