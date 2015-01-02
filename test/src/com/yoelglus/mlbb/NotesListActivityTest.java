package com.yoelglus.mlbb;

import android.app.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class NotesListActivityTest {

    @Test
    public void testActivityCreated() {
        Activity activity = Robolectric.buildActivity(NotesListActivity.class).get();
    }
}
