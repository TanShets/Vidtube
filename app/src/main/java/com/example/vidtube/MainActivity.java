package com.example.vidtube;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    VideoView vw;
    int currvideo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vw = (VideoView)findViewById(R.id.player);
        setVideo(R.raw.window);
        MediaController mc = new MediaController(this);
        vw.setMediaController(mc);
        mc.setAnchorView(vw);
        //vw.setOnCompletionListener(this);
        //setVideo(R.raw.window);
    }

    public void setVideo(int id){
        String path = "android:resource://" + getPackageName() + "/" + id;
        Uri uri = Uri.parse(path);
        vw.setVideoURI(uri);
        //vw.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        AlertDialog.Builder obj = new AlertDialog.Builder(this);
        obj.setTitle("Playback Finished!");
        obj.setIcon(R.mipmap.ic_launcher);
        MyListener m = new MyListener();
        obj.setPositiveButton("Replay", m);
        obj.setNegativeButton("Next", m);
        obj.setMessage("Want to replay or play next video?");
        obj.show();
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which == -1) {
                vw.seekTo(0);
                vw.start();
            }
            else {
                ++currvideo;
                if (currvideo != 0)
                    currvideo = 0;
                setVideo(R.raw.window);
            }
        }
    }
}
