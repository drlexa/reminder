package com.example.flashcard_reminder;

public class Word {

	private int id;
	private String english;
	private String transcription;
	private String russian;
	private long updated;
	private int error;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getTranscription() {
		return transcription;
	}
	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}
	public String getRussian() {
		return russian;
	}
	public void setRussian(String russian) {
		this.russian = russian;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public Word(int id, String english, String transcription, String russian,
			long updated, int error) {
		super();
		this.id = id;
		this.english = english;
		this.transcription = transcription;
		this.russian = russian;
		this.updated = updated;
		this.error = error;
	}
	
	
}
