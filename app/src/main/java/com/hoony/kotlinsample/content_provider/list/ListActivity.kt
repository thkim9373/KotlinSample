package com.hoony.kotlinsample.content_provider.list

import com.hoony.kotlinsample.content_provider.audio.AudioActivity
import com.hoony.kotlinsample.content_provider.contact.ContactActivity
import com.hoony.kotlinsample.content_provider.galley.GalleryActivity
import com.hoony.kotlinsample.content_provider.video.list.VideoActivity
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class ListActivity : AbsListActivity() {

    override fun getTargetDataList(): List<TargetData> {
        return listOf(
            TargetData(
                ContactActivity::class.java,
                "Contacts example"
            ),
            TargetData(
                AudioActivity::class.java,
                "Audio example"
            ),
            TargetData(
                VideoActivity::class.java,
                "Video example"
            ),
            TargetData(
                GalleryActivity::class.java,
                "Gallery example"
            )
        )
    }
}