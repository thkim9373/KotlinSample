package com.hoony.kotlinsample.content_provider.data

data class Contact (
    val name: String,
    val phoneNumber: String
) : Comparable<Contact> {
    override fun compareTo(other: Contact): Int {
        return this.name.compareTo(other.name)
    }
}