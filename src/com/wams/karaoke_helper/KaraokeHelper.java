package com.wams.karaoke_helper;

/**
 * Karaoke Helper
 * @authoer Wallace Mooncai
 * 
 * @since 0.0
 * 
 * Crossfire Building Mobile Apps Week 10 Homework:
 * 
 * Write an android app called KaraokeHelper
 *   + Project/folder name karaoke_helper
 *   + It should have a button on the UI
 *   + When clicked it will present the next line in a song
 *   + There should be two songs available
 *   + One accessed in landscape orientation, the other accessed in portrait
 *   
 *   BONUS: autopresent lyrics at a steady rate
 *   SUPER BONUS: auto-present speed varies with length of the line presented
 *   
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class KaraokeHelper extends Activity {

	private int mHappyFaceLine = 0;
	private String[] mHappyFaceSong;
	
	private int mBusLine = 0;
	private String[] mBusSong;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke_helper);
        
        Context ctx = getBaseContext();
        final TextView songView = (TextView)findViewById(R.id.songView);
        final TextView verseView = (TextView)findViewById(R.id.verseView);
        
        mBusSong = ctx.getResources().getStringArray(R.array.bus_song);
        final ImageView busButton = (ImageView)findViewById(R.id.busButton);
        busButton.setOnClickListener(
        	new View.OnClickListener()
        	{
        		public void onClick(View v)
        		{
        			songView.setText(mBusSong[mBusLine]);
        			verseView.setText("Verse #: " + mBusLine); // Integer.toString(mBusLine)
        			mBusLine = (mBusLine < (mBusSong.length-1)) ? (mBusLine + 1) : 0;
        		}
        	}
    	);
        
        mHappyFaceSong = ctx.getResources().getStringArray(R.array.happy_face_song);
        final ImageView happyFaceButton = (ImageView)findViewById(R.id.happyFaceButton);
        happyFaceButton.setOnClickListener(
            	new View.OnClickListener()
            	{
            		public void onClick(View v)
            		{
            			songView.setText(mHappyFaceSong[mHappyFaceLine]);
            			verseView.setText("Verse #: " + mHappyFaceLine);
            			mHappyFaceLine = (mHappyFaceLine < (mHappyFaceSong.length-1))
            					? (mHappyFaceLine + 1) : 0;
            		}
            	}
        	);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_karaoke_helper, menu);
        return true;
    }
    

}
