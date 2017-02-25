package com.monadpad.tesseract.dsp;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class Dac extends UGen {
	private final float[] localBuffer;
	private boolean isClean;
	private AudioTrack track;
	private final short [] target = new short[CHUNK_SIZE];
	private final short [] silentTarget = new short[CHUNK_SIZE];

    private boolean goodToGo = false;
	
	public Dac() {
		localBuffer = new float[CHUNK_SIZE];

        setupTrack();
    }

    private boolean setupTrack(){
        int minSize = AudioTrack.getMinBufferSize(
                SAMPLE_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        track = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                Math.max(CHUNK_SIZE *4, minSize),
                AudioTrack.MODE_STREAM);

        try {
            track.play();
            goodToGo = true;
        } catch (IllegalStateException e){
            goodToGo = false;
        }
        return goodToGo;
	}
	
	public boolean render(final float[] _buffer) {
		if(!isClean) {
			zeroBuffer(localBuffer);

			isClean = true;
		}
		// localBuffer is always clean right here, does it stay that way?
		isClean = !renderKids(localBuffer);
		return !isClean; // we did some work if the buffer isn't clean
	}
	
//	public void open() {
//
//		track.play();
//	}
	
	public void tick() {

        if (goodToGo || setupTrack()){
            render(localBuffer);

            if(isClean) {
                // sleeping is messy, so lets just queue this silent buffer
                track.write(silentTarget, 0, silentTarget.length);
            } else {
                for(int i = 0; i < CHUNK_SIZE; i++) {
                    target[i] = (short)(32768.0f*localBuffer[i]);
                }

                track.write(target, 0, target.length);
            }

        }
	}
	
	public void close() {
        if (goodToGo){
            try {
                track.stop();
                track.release();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            goodToGo = false;
        }
	}
    private float atten = 1.0f;
    public void fadeOut(){
        if (goodToGo){
            atten = atten - 0.05f;
            track.setStereoVolume(atten, atten);
        }
    }

    public void fade(float level){
        if (goodToGo){
            atten = 1f - level;
            track.setStereoVolume(atten, atten);
        }
    }

}
