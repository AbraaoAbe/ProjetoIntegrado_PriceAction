package com.priceaction.priceAction;

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

	@PostMapping("/")
	public String getSearch(@RequestParam(name="textInput", defaultValue = "") String textInput, Model model){
		if(textInput.isEmpty() == true) textInput = "DEURUIM";
		System.out.println(textInput);
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

	// @GetMapping("graficos")
	// public String getGraphs(@RequestParam(name="nome",defaultValue="") String nome, Model model){
	// 	model.addAttribute("chartData1", getChartData1());
	// 	model.addAttribute("chartData2", getChartData2());
	// 	return "graficos";
	// }
}
