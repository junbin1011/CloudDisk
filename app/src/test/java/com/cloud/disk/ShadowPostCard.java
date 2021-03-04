package com.cloud.disk;

import com.alibaba.android.arouter.facade.Postcard;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import java.util.HashMap;

@Implements(Postcard.class)
public class ShadowPostCard {

    @RealObject
    public Postcard postcard;

    static HashMap<String, Class> map = new HashMap<>();

    static {
        try {
            map.put("/fileBundle/file", Class.forName("com.cloud.disk.bundle.file.FileFragment"));
            map.put("/userBundle/user", Class.forName("com.cloud.disk.bundle.user.UserCenterFragment"));
            map.put("/dynamicBundle/dynamic", Class.forName("com.cloud.disk.bundle.dynamic.DynamicFragment"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Implementation
    public Object navigation() {
        try {
            Class target = map.get(postcard.getPath());
            if (target != null) {
                return target.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
