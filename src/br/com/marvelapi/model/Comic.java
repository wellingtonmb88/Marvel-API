package br.com.marvelapi.model;

import java.util.ArrayList;
import java.util.List;

public class Comic {

	private String comicID;
	private String comicTitle;
	private String comicDescription;
	private String comicImage;
	

	private List<Comic> comics = new ArrayList<Comic>();
	
	
	public List<Comic> getComics() {
		return comics;
	}
	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}
	public String getComicID() {
		return comicID;
	}
	public void setComicID(String comicID) {
		this.comicID = comicID;
	}
	public String getComicTitle() {
		return comicTitle;
	}
	public void setComicTitle(String comicTitle) {
		this.comicTitle = comicTitle;
	}
	public String getComicDescription() {
		return comicDescription;
	}
	public void setComicDescription(String comicDescription) {
		this.comicDescription = comicDescription;
	}
	public String getComicImage() {
		return comicImage;
	}
	public void setComicImage(String comicImage) {
		this.comicImage = comicImage;
	}
	
	
}
