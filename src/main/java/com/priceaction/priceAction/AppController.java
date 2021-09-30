package com.priceaction.priceAction;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.ui.Model;

@Controller
public class AppController {
	
	@GetMapping("/")
	public String getHome(Model model){
		return "home";
	}

	@Deprecated
	public List<List<Object>> getDaily(String action){
		List<List<Object>> ListaCompleta = new ArrayList<>();
		try{	
			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+action+"&apikey=X6P8S5TFG9UIQUZ7";
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(text);
			JsonElement datas = json.getAsJsonObject().get("Time Series (Daily)");
			int cont = 0;
			for(Map.Entry<String, JsonElement> data : datas.getAsJsonObject().entrySet()) {
				if(cont == 20)	break;
				ListaCompleta.add(
					List.of(
						data.getKey().toString(),
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
			}
		}
		catch(Exception e){
			System.out.println("Ação nao encontrada");
		}
		return ListaCompleta;
	}

	@Deprecated
	public List<List<Object>> getWeekly(String action){
		List<List<Object>> ListaCompleta = new ArrayList<>();
		try{	
			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="+action+"&apikey=X6P8S5TFG9UIQUZ7";
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(text);
			JsonElement datas = json.getAsJsonObject().get("Weekly Time Series");
			int cont = 0;
			for (Map.Entry<String, JsonElement> data : datas.getAsJsonObject().entrySet()) {
				if(cont == 20)	break;
				ListaCompleta.add(
					List.of(
						data.getKey().toString(),
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
			}
		}
		catch(Exception e){
			System.out.println("Ação nao encontrada");
		}
		return ListaCompleta;
	}

	@Deprecated
	public List<List<Object>> getMonthly(String action){
		List<List<Object>> ListaCompleta = new ArrayList<>();
		try{	
			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol="+action+"&apikey=X6P8S5TFG9UIQUZ7";
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(text);
			JsonElement datas = json.getAsJsonObject().get("Monthly Time Series");
			int cont = 0;
			for (Map.Entry<String, JsonElement> data : datas.getAsJsonObject().entrySet()) {
				if(cont == 20) break;
				ListaCompleta.add(
					List.of(
						data.getKey().toString(),
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
			}
		}
		catch(Exception e){
			System.out.println("Ação nao encontrada");
		}
		return ListaCompleta;
	}

	
	@PostMapping("/")
	public String getSearch(@RequestParam(name="textInput", defaultValue = "") String textInput, Model model) throws IOException{
		
		model.addAttribute("candleData1", getDaily(textInput));
		model.addAttribute("candleData2", getWeekly(textInput));
		model.addAttribute("candleData3", getMonthly(textInput));
		return "home";
	}

	//Os dados podem ser modificados para exibir o gráfico de maneira diferente
	private List<List<Object>> getChartData1() { 
		
		
		return List.of(
				List.of("Mon", 20, 28, 38, 45),
				List.of("Tue", 31, 38, 55, 66),
				List.of("Wed", 50, 55, 77, 80),
				List.of("Thu", 77, 77, 66, 50),
				List.of("Fri", 68, 66, 22, 15),
				List.of("Maria", 30, 49, 55, 60)
			);
    }

	private List<List<Object>> getChartData2() {

		return	List.of(
				List.of("Mon", 30, 35, 40, 60),
				List.of("Tue",28, 41, 55, 66),
				List.of("Wed", 65, 40, 69, 80),
				List.of("Thu", 21, 28, 40, 55),
				List.of("Fri", 38, 40, 45, 70)
			);
	}

	 @GetMapping("graficos")
	 public String getGraphs(@RequestParam(name="nome",defaultValue="") String nome, Model model){
	 	model.addAttribute("chartData1", getChartData1());
	 	model.addAttribute("chartData2", getChartData2());
	 	return "graficos";
	 }
}
