package br.com.marvelapi.controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.marvelapi.model.Comic;

@ManagedBean
@SessionScoped
public class ComicBean {

	// Variaveis privadas
	private HttpURLConnection conn = null;

	
	private String ano_inicio;

	private String ano_fim;

	private String numberOfComics;
 
	private Comic comic = new Comic();

	private List<Comic> comics = new ArrayList<Comic>();

	// Metodo busca Comic por ID
	public String buscarPorID() {

		Comic c = getComic();

		setAno_fim("");
		setAno_inicio("");

		String comicID = c.getComicID();

		try {
			String urlPath = UrlFactory.mountRestUrl(comicID);
			conn = UrlFactory.criaConnection(urlPath);

			if (conn.getResponseCode() != 200) {
				return "error";

			} else {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output = null;
				String retornoRest = "";
				while ((output = br.readLine()) != null) {
					retornoRest += output;
				}

				c = JsonFactory.criaComicPorID(retornoRest);

				setComics(c.getComics());

				return "index";

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return null;
	}

	// Metodo busca uma lista de Comics por data
	public String buscarPorData() {

		Comic c = getComic();

		c.setComicID("");

		int inicAno = 0;
		int fimAno = -1;

		if (ano_inicio.trim().length() > 3 && ano_fim.trim().length() > 3) {

			inicAno = Integer.parseInt(ano_inicio);
			fimAno = Integer.parseInt(ano_fim);
		}

		if (fimAno >= inicAno) {
			try {

				String urlPath = UrlFactory.mountRestUrlData(ano_inicio,
						ano_fim, numberOfComics);
				conn = UrlFactory.criaConnection(urlPath);

				if (conn.getResponseCode() != 200) {

					return "error";

				} else {

					BufferedReader br = new BufferedReader(
							new InputStreamReader((conn.getInputStream())));

					String output = null;
					String retornoRest = "";
					while ((output = br.readLine()) != null) {
						retornoRest += output;
					}

					c = JsonFactory.criaComicPorData(retornoRest);

					setComics(c.getComics());

					return "index";

				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				conn.disconnect();
			}
		} else {

			return "error";
		}

		return null;
	}

	// // Getters And Setters //////

	public String getNumberOfComics() {
		return numberOfComics;
	}

	public void setNumberOfComics(String numberOfComics) {
		this.numberOfComics = numberOfComics;
	}

	public String getAno_inicio() {
		return ano_inicio;
	}

	public void setAno_inicio(String ano_inicio) {
		this.ano_inicio = ano_inicio;
	}

	public String getAno_fim() {
		return ano_fim;
	}

	public void setAno_fim(String ano_fim) {
		this.ano_fim = ano_fim;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public List<Comic> getComics() {
		return comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}

}
