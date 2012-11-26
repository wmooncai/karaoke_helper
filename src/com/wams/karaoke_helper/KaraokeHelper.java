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

import utils.*;

// import com.wams.Utils.WAMSActivity;

import android.os.Bundle;
// import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class KaraokeHelper extends WAMSActivity {

	Context mContext;
	
	private ImageView mHappyFaceButton;
	// private int mHappyFaceLine = 0;
	// private String[] mHappySong;
	
	private ImageView mBusButton;
	private int mBusLine = 0;
	// private String[] mBusSong;
	
	private TextView mSongView;
	private String[] mSong;
	
	private Song mmSong;
	
	private TextView mVerseView;
	private int mVerseDelay;
	// private int mVerseCount;
	
	// private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke_helper);
        
        mContext = getBaseContext();
        
        mSongView = (TextView) findViewById(R.id.songView);
        mVerseView = (TextView) findViewById(R.id.verseView);
        mVerseDelay = mContext.getResources().getInteger(R.integer.scroll_rate);
        
        mmSong = new Song(mSongView, mVerseView
        		,mContext.getResources().getInteger(R.integer.scroll_rate)
        		,mContext.getResources().getInteger(R.integer.scroll_min)
        		, mContext.getResources().getInteger(R.integer.scroll_max));
        
        setupBusSongButton();
        
        setupHappyFaceSongButton();
        
        setupAutoScrollSeeker();

        setupAutoScrollCB();
        
   } // onCreate()

    // ************************************************************************
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_karaoke_helper, menu);
        return true;
    }

    // ########################################################################

    private void setupBusSongButton() {

    		mBusButton = (ImageView) findViewById(R.id.busButton);

        if (mBusButton.isShown()) {
	        // mmSong.setSong(mContext.getResources().getStringArray(R.array.bus_song));
        	mSong = mContext.getResources().getStringArray(R.array.bus_song);
	    	final TextView songView = mSongView;
	    	final TextView verseView = mVerseView;
	        final ImageView busButton = mBusButton;
	        busButton.setOnClickListener(
	        	new View.OnClickListener()
	        	{
	        		public void onClick(View v)
	        		{
	        			// mmSong.singNextVerse();
        			
	        			 songView.setText(mSong[mBusLine]);
	        			 
	        			verseView.setText("Verse #: " + (mBusLine + 1)
	        					+ " / " + mSong.length);
	        			mBusLine = (mBusLine < (mSong.length - 1))
	        						? (mBusLine + 1) : 0;
					
	        		}
	        	}
	    	);
        }
    }
    
    // ------------------------------------------------------------------------
    
    private void setupHappyFaceSongButton() {

    	mHappyFaceButton = (ImageView) findViewById(R.id.happyFaceButton);

    	if (mHappyFaceButton.isShown()) {
	    	// mmSong.setSong(mContext.getResources().getStringArray(R.array.happy_face_song));
    		mSong = mContext.getResources().getStringArray(R.array.happy_face_song);
	    	// final TextView songView = mSongView;
	    	// final TextView verseView = mVerseView;
	        // final ImageView happyFaceButton = mHappyFaceButton;
	        mHappyFaceButton.setOnClickListener(
            	new View.OnClickListener()
            	{
            		public void onClick(View v)
            		{
            		mmSong.singNextVerse();
        			/*
            			songView.setText(mSong[mHappyFaceLine]);
            			verseView.setText("Verse #: " + (mHappyFaceLine + 1)
            					+ " / " + mSong.length);
            			mHappyFaceLine =
            					(mHappyFaceLine < (mSong.length - 1))
            						? (mHappyFaceLine + 1) : 0;
					*/
            		}
            	}
        	);
    	}
    }
    
    // ------------------------------------------------------------------------

    private void setupAutoScrollSeeker() {
    	
        final TextView autoScrollSeekerValue = (TextView) findViewById(R.id.autoScrollSeekerValue);
        final SeekBar autoScrollSeeker = (SeekBar) findViewById(R.id.autoScrollSeeker);
        
        autoScrollSeekerValue.setText(mVerseDelay + " secs.");
        
        autoScrollSeeker.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
        
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress,
        			boolean fromUser) {
        		
        		mVerseDelay = progress;
        		autoScrollSeekerValue.setText(mVerseDelay + " secs.");
        	}

        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar) {}

        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
    
    // ------------------------------------------------------------------------

    private void setupAutoScrollCB() {
    
    	final CheckBox autoScrollCB = (CheckBox) findViewById(R.id.autoScrollCB);
    	final CheckBox autoScrollByVerseLengthCB
    		= (CheckBox) findViewById(R.id.autoScrollByVerseLengthCB);
    	
    	autoScrollCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (autoScrollCB.isChecked()) {
					if (autoScrollByVerseLengthCB.isChecked())
						autoScrollByVerseLengthCB.setChecked(false);
					
					mmSong.singSong();
				}
			}
		});
    }
    

    
    // ------------------------------------------------------------------------
    
/*
    private void playSong() throws InterruptedException {
    	    	
    	if (mBusButton.isShown()) {
    		mSong = mContext.getResources().getStringArray(R.array.bus_song);
    	} else if (mHappyFaceButton.isShown()) {
    		mSong = mContext.getResources().getStringArray(R.array.happy_face_song);
    	} else mSong = mContext.getResources().getStringArray(R.array.happy_face_song);
    	
    	// Sanitize mVerseDelay
    	int minDelay = mContext.getResources().getInteger(R.integer.scroll_min);
    	int maxDelay = mContext.getResources().getInteger(R.integer.scroll_max);
    	if (mVerseDelay < minDelay) {
    		mVerseDelay = minDelay;
    	} else if (mVerseDelay > maxDelay)
    		mVerseDelay = maxDelay;
    	
    	// scheduler.schedule(verseToScreen(mVerseDelay), 0, SECONDS);

    }

    private Runnable verseToScreen(int verseCounter) {
    	
    	final int fVerseCounter = verseCounter;
    	
    	final Runnable toScreen = new Runnable() {
    		public void run() {
    			
    			if (fVerseCounter < mSong.length) {
    				mSongView.setText(mSong[fVerseCounter].toString());
    				mVerseView.setText("Verse #: " + mVerseCount + " / " + mSong.length);
    				
    				final ScheduledFuture<?> toScreenHandle = scheduler.schedule(verseToScreen(fVerseCounter - 1), mVerseDelay, SECONDS);
    			}
    		}
    	};
    	
    	return toScreen;
    }
*/

} // KaraokeHelper.java
