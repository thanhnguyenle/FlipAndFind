package controller;

import javax.swing.SwingWorker;

import constants.PathAudioGame;

public class AudioAction {
	private AudioHandle audioHanle;

	public AudioAction(AudioHandle audioHanle) {
		super();
		this.audioHanle = audioHanle;
	}

	public void changeAudio(String path) {
		audioHanle.setAudioFilePath(path);
	}

	public void stop() {
		audioHanle.stopAudio();
		audioHanle.setPlayCompleted(false);
	}

	public void start(boolean isMute,int volumn) {
		audioHanle.startAudio(isMute,volumn);
	}

	public void setAudio(String pathAudio,boolean loopAudio,boolean checkMute,int volumn) {
		if (!checkMute) {
			String audioFilePath = pathAudio;
			audioHanle.setAudioFilePath(audioFilePath);

			// audio play in background
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					// TODO Auto-generated method stub
					audioHanle.startAudio(loopAudio,volumn);
					return null;
				}
			};
			worker.execute();
		}
	}

}