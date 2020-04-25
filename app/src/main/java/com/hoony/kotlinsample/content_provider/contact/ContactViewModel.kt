package com.hoony.kotlinsample.content_provider.contact

import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hoony.kotlinsample.content_provider.data.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactCursor: Cursor? by lazy {
        application.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
    }

    val contactListLiveData: LiveData<List<Contact>> = liveData {
        val contactList = ArrayList<Contact>()

        contactCursor?.let {
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                contactList.add(Contact(name, number))
            }
            contactList.sort()
        }

        this.emit(contactList as List<Contact>)
    }
}