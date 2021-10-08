package com.priceaction.priceAction;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration.ValidationConfiguration;
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

	private int volume1,volume2,volume3;
	
	
	/** 
	 * @param model
	 * @return String
	 */
	@GetMapping("/")
	public String getHome(Model model){
		return "home";
	}

	
	/** 
	 * @param action
	 * @return Retorna uma lista de lista com os valores diários das ações no Formato Gráfico de Candle
	 * 
	 */
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
				if(cont >= 20)	break;
				
				if(cont == 0)	volume1 = data.getValue().getAsJsonObject().get("5. volume").getAsInt();

				SimpleDateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
				Date dataJSON = conversor.parse(data.getKey().toString());
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM");
				String dataFormatada = formatador.format(dataJSON);
				

				ListaCompleta.add(
					List.of(
						dataFormatada,
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
			}
			Collections.reverse(ListaCompleta);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ListaCompleta;
	}

	
	/** 
	 * @param action
	 * @return Retorna uma lista de lista com os valores semanais das ações no Formato Gráfico de Candle
	 * 
	 */
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
				if(cont >= 20)	break;

				if(cont == 0)	volume2 = data.getValue().getAsJsonObject().get("5. volume").getAsInt();

				SimpleDateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
				Date dataJSON = conversor.parse(data.getKey().toString());
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM");
				String dataFormatada = formatador.format(dataJSON);

				ListaCompleta.add(
					List.of(
						dataFormatada,
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
			}
			Collections.reverse(ListaCompleta);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ListaCompleta;
	}

	
	/** 
	 * @param action
	 * @return Retorna uma lista de lista com os valores mensais das ações no Formato Gráfico de Candle
	 */
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
				if(cont >= 20)	break;

				if(cont == 0)	volume3 = data.getValue().getAsJsonObject().get("5. volume").getAsInt();
				
				SimpleDateFormat conversor = new SimpleDateFormat("yyyy-MM-dd");
				Date dataJSON = conversor.parse(data.getKey().toString());
				SimpleDateFormat formatador = new SimpleDateFormat("MM/yyyy");
				String dataFormatada = formatador.format(dataJSON);

				ListaCompleta.add(
					List.of(
						dataFormatada,
						data.getValue().getAsJsonObject().get("3. low").getAsDouble(),
						data.getValue().getAsJsonObject().get("1. open").getAsDouble(),
						data.getValue().getAsJsonObject().get("4. close").getAsDouble(),
						data.getValue().getAsJsonObject().get("2. high").getAsDouble()
					)
				);
				cont++;
				
			}
			Collections.reverse(ListaCompleta);
		}
		catch(Exception e){
			
		}
		return ListaCompleta;
	}

	
	/** 
	 * 
	 * @param lista Lista com os valores das infomações que foram capturadas na API
	 * @return Retorna os valores de fechamento da lista (diários, semanais ou mensais)
	 * 
	 */
	public List<List<Object>> getClose(List<List<Object>> lista){
		List<List<Object>> ListaClose = new ArrayList<>();
		for(List<Object> a : lista){
			ListaClose.add(
				List.of(
					a.get(0),a.get(3)
				)
			);
		}
		return ListaClose;
	}
	
	
	/** 
	 * 
	 * @param lista Lista com os valores das infomações que foram capturadas na API
	 * @return Retorna uma lista com os valores (Máximo, mínimo, variação...) referentes ao último dia/semana/mês 
	 * 
	 */
	public List<Object> getInfo(List<List<Object>> lista){
		List<Object> info = new ArrayList<>();
		
		double high = (double) lista.get(19).get(4);
		double low = (double) lista.get(19).get(1);
		double open = (double) lista.get(19).get(2);
		double close = (double) lista.get(19).get(3);
		double variation = ((close - open) * 100)/open;
		
		DecimalFormat df = new DecimalFormat("0,00");
		
		info.add(df.format(variation));
		info.add(high);
		info.add(low);
		info.add(open);
		info.add(close);
		return info;
	}
	
	
	/** 
	 * 
	 * @param @RequestParam(name="textInput" Captura o post
	 * @param textInput Variável Capturar a entrada do usuário no input de pesquisa do site
	 * @return Nome do arquivo HTML
	 * @throws IOException Pode ser gerado um erro na hora de manipular os dados da API
	 * 
	 */
	@PostMapping("/")
	public String getSearch(@RequestParam(name="textInput", defaultValue = "") String textInput, Model model) throws IOException{
		List<List<Object>> L1 = getDaily(textInput);
		List<List<Object>> L2 = getWeekly(textInput);
		List<List<Object>> L3 = getMonthly(textInput);
		
		model.addAttribute("candleData1", L1);
		model.addAttribute("candleData2", L2);
		model.addAttribute("candleData3", L3);


		if(L1.isEmpty() || L2.isEmpty() || L3.isEmpty()){
			model.addAttribute("dadosOK",-1);
		}
		else{
			List<List<Object>> LC1 = getClose(L1);
			List<List<Object>> LC2 = getClose(L2);
			List<List<Object>> LC3 = getClose(L3);
			model.addAttribute("lineData1",LC1);
			model.addAttribute("lineData2",LC2);
			model.addAttribute("lineData3",LC3);
			model.addAttribute("dadosOK",1);

			List<Object> LI1 = getInfo(L1);

			model.addAttribute("variacao1", LI1.get(0).toString());//Tem q ser String
			model.addAttribute("max1", LI1.get(1));
			model.addAttribute("min1", LI1.get(2));
			model.addAttribute("volume1", volume1);
			model.addAttribute("abertura1", LI1.get(3));
			model.addAttribute("fechamento1", LI1.get(4));

			List<Object> LI2 = getInfo(L2);

			model.addAttribute("variacao2", LI2.get(0).toString());//Tem q ser String
			model.addAttribute("max2", LI2.get(1));
			model.addAttribute("min2", LI2.get(2));
			model.addAttribute("volume2", volume2);
			model.addAttribute("abertura2", LI2.get(3));
			model.addAttribute("fechamento2", LI2.get(4));

			List<Object> LI3 = getInfo(L3);
			
			model.addAttribute("variacao3",LI3.get(0).toString() );//Tem q ser String
			model.addAttribute("max3", LI3.get(1));
			model.addAttribute("min3", LI3.get(2));
			model.addAttribute("volume3", volume3);
			model.addAttribute("abertura3", LI3.get(3));
			model.addAttribute("fechamento3", LI3.get(4));
			model.addAttribute("nomeDaAcao", textInput);
		}
		return "home";
	}

}
