package com.hoony.kotlinsample.content_provider.contact

import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hoony.kotlinsample.content_provider.data.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactCursor: Cursor? by lazy {
        application.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
    }

    val contactListLiveData: LiveData<List<Contact>> by lazy {
        contactCursor?.let {

        }
    }
}