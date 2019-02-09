package com.wyqlib.android.app.androidvfobviewer.custom;


import android.view.View;

import com.wyqlib.java.utils.io.file.FileModel;
import com.wyqlib.java.utils.io.file.FileObj;


/**
 * Created by WYQ on 2017/6/2.
 */

public class CustomClickListener implements View.OnClickListener {
    public FileObj fo;
    public FileModel fm;
    @Override
    public void onClick(View v) {

    }
    public CustomClickListener(FileObj fo,FileModel fm){
        this.fo=fo;
        this.fm=fm;
    }
}
