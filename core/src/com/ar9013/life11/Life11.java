package com.ar9013.life11;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Life11 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	AudioRecorder recorder;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		recorder = Gdx.audio.newAudioRecorder(44100,true); // CD 錄音品質，沒有雙聲道。

		short[] audioBuffer = new short[44100*5]; // 5秒錄音資料存放區域

		recorder.read(audioBuffer,0,audioBuffer.length);

		AudioDevice audioDevice = Gdx.audio.newAudioDevice(44100,true);
		audioDevice.writeSamples(audioBuffer,0,audioBuffer.length);

		Gdx.app.exit(); // 錄音完後離開

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		recorder.dispose();

	}
}
