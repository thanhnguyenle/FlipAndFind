package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandle  implements LineListener {

	private boolean playCompleted;
	private Clip audioClip;
	private Info info;
	private AudioFormat format;
	private AudioInputStream audioStream;
	private boolean repeated = false;
	private String audioFilePath ;

	public AudioHandle(String audioFilePath) {
		// TODO Auto-generated constructor stub
		this.audioFilePath = audioFilePath;
	}
	
	public AudioHandle() {
		// TODO Auto-generated constructor stub
	}

	public void startAudio(boolean repeated,int volumn) {
		File audioFile = new File(this.audioFilePath);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
			audioClip.open(audioStream);
			
			//volume of audio 
			// Reduce volume by $(volumn) decibels.
			FloatControl gainControl = 
				    (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-volumn);
			
			if (repeated)
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			//run audio
			audioClip.start();
			while (!playCompleted) {
				// wait for the playback completes
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			audioClip.close();
			audioStream.close();
		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
	}

	public void stopAudio() {
		audioClip.stop();
		audioClip.close();
		try {
			audioStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Listens to the START and STOP events of the audio line.
	 */
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if (type == LineEvent.Type.START) {
			System.out.println("Playback started.");
			playCompleted =false;
		} else if (type == LineEvent.Type.STOP) {
			playCompleted = true;
			System.out.println("Playback completed.");
		}
	}
	
	public String getAudioFilePath() {
		return audioFilePath;
	}

	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}
	
	public boolean isPlayCompleted() {
		return playCompleted;
	}

	public void setPlayCompleted(boolean playCompleted) {
		this.playCompleted = playCompleted;
	}

}
