package com.ei.vaadin.ui.component

import com.ei.vaadin.backend.enums.StatusEnum
import com.ei.vaadin.backend.model.Company
import com.ei.vaadin.backend.model.Contact
import com.vaadin.flow.component.ComponentEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.data.binder.Binder


class ContactForm (
    private var companies: List<Company> = listOf()
): FormLayout() {

    private val firstName: TextField = TextField("First name")
    private val lastName: TextField = TextField("Last name")
    private val email: EmailField = EmailField("Email")
    private val status = ComboBox<StatusEnum>("Status")
    private val company = ComboBox<Company>("Company")

    private val save: Button = Button("Save")
    private val close: Button = Button("Close")
    private val delete: Button = Button("Delete")

    private val binder: Binder<Contact> = BeanValidationBinder(Contact::class.java)

    init {
        addClassName("contact-form")
        status.setItems(StatusEnum.entries)
        company.setItems(companies)
        company.setItemLabelGenerator(Company::name)

        binder.bindInstanceFields(this)
        binder.addStatusChangeListener { save.isEnabled = binder.isValid }

        add(firstName, lastName, email, status, company, createButtonsLayout())
    }

    private fun createButtonsLayout(): HorizontalLayout {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY)
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR)

        save.addClickShortcut(Key.ENTER)
        close.addClickShortcut(Key.ESCAPE)

        save.addClickListener { validateAndSave() }
        delete.addClickListener {  fireEvent(ContactFormEvent.DeleteEvent(this, binder.bean)) }
        close.addClickListener {  fireEvent(ContactFormEvent.CloseEvent(this)) }

        return HorizontalLayout(save, delete, close)
    }

    fun setContact(contact: Contact?){
        binder.bean = contact
    }

    sealed class ContactFormEvent(source: ContactForm, open val contact: Contact?) : ComponentEvent<ContactForm>(source, false) {
        data class SaveEvent(@JvmField var source: ContactForm, override var contact: Contact) : ContactFormEvent(source, contact)
        data class DeleteEvent(@JvmField var source: ContactForm, override var contact: Contact) : ContactFormEvent(source, contact)
        class CloseEvent(source: ContactForm) : ContactFormEvent(source, null)
    }

    fun addDeleteListener(listener: ComponentEventListener<ContactFormEvent.DeleteEvent>) =
        addListener(ContactFormEvent.DeleteEvent::class.java, listener)

    fun addSaveListener(listener: ComponentEventListener<ContactFormEvent.SaveEvent>) =
        addListener(ContactFormEvent.SaveEvent::class.java, listener)

    fun addCloseListener(listener: ComponentEventListener<ContactFormEvent.CloseEvent>) =
        addListener(ContactFormEvent.CloseEvent::class.java, listener)

    private fun validateAndSave() {
        if (binder.isValid()) {
            fireEvent(ContactFormEvent.SaveEvent(this, binder.getBean()))
        }
    }


}