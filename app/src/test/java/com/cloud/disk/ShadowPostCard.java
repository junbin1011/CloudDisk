package com.cloud.disk;

import com.alibaba.android.arouter.facade.Postcard;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

@Implements(Postcard.class)
public class ShadowPostCard {

    @RealObject
    public Postcard postcard;

    @Implementation
    public Object navigation() {
        if ("/bundle/file".equals(postcard.getPath())) {
            try {
                return Class.forName("com.cloud.disk.bundle.file.FileFragment").newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
