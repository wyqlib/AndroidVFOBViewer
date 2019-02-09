package com.wyqlib.android.app.androidvfobviewer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wyqlib.android.app.androidvfobviewer.R;
import com.wyqlib.android.app.androidvfobviewer.custom.OnDoubleClickListener;
import com.wyqlib.java.utils.base.string.StringUtils;
import com.wyqlib.java.utils.io.file.FileModel;
import com.wyqlib.java.utils.io.file.FileObj;
import com.wyqlib.java.utils.io.serialization.Json.GsonUtils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public String filePath = Environment.getExternalStorageDirectory() + "/WYQDev/WE.json";

    public HashMap<String, Object> ExtImg = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initExtImg();
    }

    public String ViewPath = "Root:\\";
    public FileObj fo;

    public void buttonGo(View v) {
        EditText etPath = (EditText) findViewById(R.id.editText_Path);
        ViewPath = etPath.getText().toString();
        updateViewBar(ViewPath);
    }

    public void buttonBack(View v) {
        String[] vps = ViewPath.split("\\\\");
        if (vps.length <= 2) {
            updateViewBar("Root:\\");
            return;
        }
        String NVP = "";
        for (int i = 0; i < vps.length - 1; i++) {
            NVP += vps[i] + "\\";
        }

        updateViewBar(NVP);
        //Toast.makeText(this, NVP, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 111) {
                Uri uri = data.getData();
                filePath = uri.getPath().toString();
                try {
                    fo = GsonUtils.jsonFileDeserialize(FileObj.class, filePath);
                    updateViewBar("Root:\\");
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "文件无法打开，请重试！！！", Toast.LENGTH_SHORT).show();
                    fo = null;
                }
                if (fo == null)
                    Toast.makeText(MainActivity.this, "文件无法打开，请重试！！！", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void buttonOpen(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 111);
    }

    public void updateViewBar(String viewPath) {
        if (fo == null) return;

        FileModel[] fms = fo.getDirFileModels(viewPath);


        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout ll_Info = (LinearLayout) findViewById(R.id.linearLayout_Info);

        ll_Info.removeAllViews();

        for (int i = 0; i < fms.length; i++) {
            final FileModel fm = fms[i];
            View vbar = inflater.inflate(R.layout.view_file_info_bar, ll_Info, false);
            if (fm.IsFile) {

                TextView tvName = (TextView) vbar.findViewById(R.id.textView_Name);
                tvName.setText(fm.Name);


                TextView tvInfo = (TextView) vbar.findViewById(R.id.textView_Info);
                tvInfo.setText(Formatter.formatFileSize(this, fm.Length));

                ImageView ivIcon = (ImageView) vbar.findViewById(R.id.imageView_Icon);
                String ext = fm.Extension;
                int imgId = R.drawable.format_unkown;
                if (!StringUtils.IsNullOrWhiteSpace(ext)) {
                    for (String key : ExtImg.keySet()) {
                        if (ext.trim().toLowerCase().contains(key)) {
                            imgId = (int) ExtImg.get(key);
                        }
                    }
                }
                ivIcon.setImageResource(imgId);

                vbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "文件名：" + fm.Name + "\n" + "创建时间：" + fm.CreationTime + "\n" + "修改时间：" + fm.LastWriteTime, Toast.LENGTH_LONG).show();
                    }
                });
            } else {

                TextView tvName = (TextView) vbar.findViewById(R.id.textView_Name);
                tvName.setText(fm.Name);

                TextView tvInfo = (TextView) vbar.findViewById(R.id.textView_Info);
                tvInfo.setText(fo.getDirFileModels(fm.FullPath).length + "文件");

                OnDoubleClickListener.registerDoubleClickListener(vbar, new OnDoubleClickListener() {
                    @Override
                    public void OnSingleClick(View v) {
                        super.OnSingleClick(v);
                        Toast.makeText(MainActivity.this, "文件名：" + fm.Name + "\n" +"创建时间：" + fm.CreationTime + "\n" + "修改时间：" + fm.LastWriteTime, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void OnDoubleClick(View v) {
                        super.OnDoubleClick(v);
                        ll_Info.removeAllViews();
                        MainActivity.this.ViewPath = fm.FullPath;
                        MainActivity.this.updateViewBar(fm.FullPath);
                    }
                });
            }
            ll_Info.addView(vbar);
            EditText etPath = (EditText) findViewById(R.id.editText_Path);
            etPath.setText(viewPath);
            ViewPath = viewPath;
        }
    }

    public void buttonRoot(View v) {
        updateViewBar("Root:\\");
    }

    public void initExtImg() {
        ExtImg.put("txt", R.drawable.format_text);
        ExtImg.put("doc", R.drawable.format_word);
        ExtImg.put("xls", R.drawable.format_excel);
        ExtImg.put("pdf", R.drawable.format_pdf);
        ExtImg.put("zip", R.drawable.format_zip);
        ExtImg.put("rar", R.drawable.format_zip);
        ExtImg.put("ppt", R.drawable.format_ppt);
        ExtImg.put("chm", R.drawable.format_chm);
        ExtImg.put("unkown", R.drawable.format_unkown);

        ExtImg.put("mp4", R.drawable.format_media);
        ExtImg.put("avi", R.drawable.format_media);
        ExtImg.put("flv", R.drawable.format_media);
        ExtImg.put("rm", R.drawable.format_media);
        ExtImg.put("rmvb", R.drawable.format_media);
        ExtImg.put("wmv", R.drawable.format_media);
        ExtImg.put("mkv", R.drawable.format_media);


        ExtImg.put("mp3", R.drawable.format_music);
        ExtImg.put("wav", R.drawable.format_music);
        ExtImg.put("ogg", R.drawable.format_music);


        ExtImg.put("png", R.drawable.format_picture);
        ExtImg.put("bmp", R.drawable.format_picture);
        ExtImg.put("jpg", R.drawable.format_picture);
        ExtImg.put("gif", R.drawable.format_picture);
    }
}