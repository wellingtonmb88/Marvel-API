package br.com.marvelapi.controle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.marvelapi.model.Comic;

public class JsonFactory {

	//Metodo recebe um String Json busca os atributos especificos 
	//e retorna uma comic configurada.
	//O Metodo que faz a chamada é o ComicBean.buscarPorID()
	public static Comic criaComicPorID(String retornoRest) {

		Comic c  =new Comic();
		JSONObject obj;

		try {
			obj = new JSONObject(retornoRest);

			JSONObject res = obj.getJSONObject("data");

			JSONObject results = res.getJSONArray("results").getJSONObject(0);

			JSONArray arr = res.getJSONArray("results");

			List<Comic> lista = new ArrayList<Comic>();

			for (int i = 0; i < arr.length(); i++) {

				Comic c2 = new Comic();
				c2.setComicTitle(arr.getJSONObject(i).getString("title"));
				c2.setComicDescription(arr.getJSONObject(i)
						.getString("description").replace("<ul><li>", "")
						.replace("</li><li>", "").replace("</li></ul>", "")
						.replace("<br>", ""));
				c2.setComicImage(UrlFactory.mountImageUrl(arr.getJSONObject(i)
						.getJSONObject("thumbnail").getString("path")));

				lista.add(c2);
			}
			
			String title = results.getString("title");
			String description = results.getString("description");
			
			c.setComicTitle(title);
			c.setComicDescription(description);
			 
			
			c.setComics(lista);
			 
			String imgURL = UrlFactory.mountImageUrl(retornoRest);

			c.setComicImage(imgURL);
			
			return c;
			 

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	//Metodo recebe um String Json busca  os atributos especificos 
		//e retorna uma comic configurada.
	//O Metodo que faz a chamada é o ComicBean.buscarPorData()
	public static Comic criaComicPorData(String retornoRest) {

		Comic c  =new Comic();
		JSONObject obj;

		try {
			obj = new JSONObject(retornoRest);

			JSONObject res = obj.getJSONObject("data");

			JSONObject results = res.getJSONArray("results").getJSONObject(0);

			JSONArray arr = res.getJSONArray("results");

			List<Comic> lista = new ArrayList<Comic>();

			for (int i = 0; i < arr.length(); i++) {

				Comic c2 = new Comic();
				c2.setComicTitle(arr.getJSONObject(i).getString("title")); 
				
				c2.setComicImage(UrlFactory.mountImageUrl(arr.getJSONObject(i)
						.getJSONObject("thumbnail").getString("path")));

				lista.add(c2);
			}
			
			String title = results.getString("title"); 
			
			c.setComicTitle(title);  
			
			c.setComics(lista);
			 
			String imgURL = UrlFactory.mountImageUrl(retornoRest);

			c.setComicImage(imgURL);
			
			return c;
			 

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
