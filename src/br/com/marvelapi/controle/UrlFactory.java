package br.com.marvelapi.controle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UrlFactory {


	private static final String privateKey = "";
	private static final String apikey = "";
	
	/**
	 * Monta uma Url  com keys e MD5 hash para busca por ID da Comic
	 * Retorna a url  completa para fazer o request
	 * 
	 * @param comicID
	 * @return 
	 */
	public static String mountRestUrl(String comicID) {
		Long timeStamp = new Date().getTime();
		String hash = generateHash(timeStamp + privateKey + apikey);
		String urlCompleta = "http://gateway.marvel.com/v1/public/comics/"
				+ comicID + "?ts=" + timeStamp + "&apikey=" + apikey + "&hash="
				+ hash;
  
		return urlCompleta;
	}

	/**
	 * Monta uma Url  com keys e MD5 hash para busca com data e numero de Comics
	 * Retorna a url  completa para fazer o request
	 * 
	 * @param ano_inicio
	 * @param ano_fim
	 * @param numOfComics
	 * @return 
	 */
	public static String mountRestUrlData(String ano_inicio, String ano_fim , String numOfComics) {
		Long timeStamp = new Date().getTime();
		String hash = generateHash(timeStamp + privateKey + apikey);
		String urlCompleta = "http://gateway.marvel.com/v1/public/comics?format=comic&formatType=comic&noVariants=false&dateRange="
				+ ano_inicio
				+ "-01-01%2C"
				+ ano_fim
				+ "-01-12&limit="+numOfComics
				+ "&ts=" + timeStamp + "&apikey=" + apikey + "&hash=" + hash;
  
		return urlCompleta;
	}

	/** 
	 * Transforma a string em MD5 hash
	 */
	private static String generateHash(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(string.getBytes());
		byte[] bytes = md.digest();

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) {
				s.append('0');
			}
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	/** 
	 * Passa uma string com o caminho da imagem para ser adcionada a extensão
	 */
	public static String mountImageUrl(String imageUrl) {

		String ss = null;

		String thumbnail = imageUrl; 

		ss = thumbnail + "/portrait_xlarge.jpg";

		return ss;
	}
	
	/**
	 * Cria uma HttpURLConnection 
	 * 
	 * @param urlPath
	 * @return 
	 */
	public static HttpURLConnection criaConnection(String urlPath){
		HttpURLConnection conn = null;
		
		URL url;
		try {
			url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;

	}
	
}
