package com.ei.vaadin.ui.view

import com.ei.vaadin.backend.model.Contact
import com.ei.vaadin.backend.service.CompanyService
import com.ei.vaadin.backend.service.ContactService
import com.ei.vaadin.ui.MainView
import com.ei.vaadin.ui.component.ContactForm
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import java.util.Optional

@PageTitle("Contacts | Vaadin CRM")
@Route(value="", layout = MainView::class)
class ListView (
    private val contactService: ContactService,
    private val companyService: CompanyService
): VerticalLayout() {

    private val grid = Grid(Contact::class.java)
    private val textFilter: TextField = TextField()
    private lateinit var form: ContactForm;

    init {
        addClassName("list-view")
        setSizeFull()
        configGrid()
        configureForm()

        val content = Div(grid, form)

        content.setSizeFull()
        content.addClassName("content")

        add(getToolbar(), content)
        updateGrid()
        closeEditor()
    }

    private fun getToolbar(): HorizontalLayout{
        configTextFilter("Filter by name...")
        val addContactButton = Button("Add contact") { addContact() }
        val toolbar = HorizontalLayout(textFilter, addContactButton)
        toolbar.addClassName("toolbar")
        return toolbar
    }

    private fun addContact() {
        grid.asSingleSelect().clear()
        editContact(Contact())
    }

    private fun updateList() {
        grid.setItems(contactService.findByFirstNameContaining(textFilter.value))
    }

    fun closeEditor() {
        form.setContact(null)
        form.isVisible = false
        removeClassName("editing")
    }

    private fun configureForm() {
        form = ContactForm(companyService.findAll())
        form.addSaveListener(this::saveEvent)
        form.addDeleteListener(this::deleteContact)
        form.addCloseListener{ closeEditor() }
    }

    fun saveEvent(event: ContactForm.ContactFormEvent.SaveEvent){
        contactService.saveOrUpdate(event.contact);
        updateList();
        closeEditor();
    }

    fun deleteContact(event: ContactForm.ContactFormEvent.DeleteEvent){
        contactService.delete(event.contact);
        updateList();
        closeEditor();
    }

    fun configTextFilter(placeholder: String = "Digit first name"){
        textFilter.placeholder = placeholder
        textFilter.isClearButtonVisible = true
        textFilter.valueChangeMode = ValueChangeMode.LAZY
        textFilter.addValueChangeListener { updateList() }
    }

    fun configGrid(){
        grid.addClassName("contact-grid")
        grid.setSizeFull()
        grid.removeColumnByKey("company")
        grid.setColumns("firstName","lastName","email", "status")
        grid.addColumn { it.company.name }.setHeader("Company")
        grid.columns.forEach { it.setAutoWidth(true) }
        grid.asSingleSelect().addValueChangeListener { editContact(it.value) }
    }

    private fun editContact(contact: Contact?) {
        Optional.ofNullable(contact).ifPresentOrElse(
            { form.setContact(it); form.isVisible = true; addClassName("editing") }, { closeEditor() }
        )
    }

    fun updateGrid(){
        grid.setItems(contactService.findAll())
    }

}