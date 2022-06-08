package br.com.fiap.gs1.controllers;

import br.com.fiap.gs1.dtos.ViagemDTO;
import br.com.fiap.gs1.models.ViagemModel;
import br.com.fiap.gs1.repositories.ViagemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/viagem")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("feature/index");
		List<ViagemDTO> viagens = viagemRepository.findAll()
				.stream()
				.map(model -> modelMapper.map(model, ViagemDTO.class))
				.collect(Collectors.toList());

		viagens.stream().forEach(m -> m.calculaDia());

		modelView.addObject("viagens",viagens);
		return modelView;
	}

	@GetMapping("/viagem/criar")
	public ModelAndView getCriar(){
		ModelAndView modelView = new ModelAndView("feature/criar");
		ViagemDTO viagem = new ViagemDTO();
		modelView.addObject("viagem", viagem);
		return modelView;
	}
	
	@PostMapping("/viagem/criar")
	public ModelAndView Criar(@Valid @ModelAttribute("viagem") ViagemDTO viagem, BindingResult bindingResult, RedirectAttributes attributes) {
		ModelAndView modelView = new ModelAndView("redirect:/viagem");
		if(bindingResult.hasErrors() || !viagem.multiplo3()){
			attributes.addFlashAttribute("mensagem", "Verifique a formatação dos campos! Siga os exemplos: 'Ex.'  ");
			modelView.setViewName("redirect:/viagem/criar");
			return modelView;
		}
		viagemRepository.save(modelMapper.map(viagem, ViagemModel.class));
		return modelView;
	}

	@RequestMapping(value = "viagem/{id}")
	public ModelAndView getUpdate(@PathVariable(name = "id") Long id_viagem){
		ModelAndView modelView = new ModelAndView("feature/update");
		ViagemModel viagemModel = viagemRepository.findById(id_viagem).orElseThrow(null);
		ViagemDTO viagem = modelMapper.map(viagemModel, ViagemDTO.class);
		modelView.addObject("viagem", viagem);
		return modelView;
	}

	@PostMapping("/viagem/update")
	public ModelAndView update(@Valid @ModelAttribute("viagem") ViagemDTO viagem, BindingResult bindingResult, RedirectAttributes attributes) {
		ModelAndView modelView = new ModelAndView("redirect:/viagem");
		if(bindingResult.hasErrors() || viagem.multiplo3()){
			attributes.addFlashAttribute("mensagem", "Verifique a formatação dos campos! Siga os exemplos: 'Ex.'  ");
			modelView.setViewName("redirect:/viagem/update");
			return modelView;
		}
		viagemRepository.save(modelMapper.map(viagem, ViagemModel.class));
		return modelView;
	}

	@RequestMapping(value = "deletar/{id}")
	public ModelAndView deletar(@PathVariable(name = "id") Long id_viagem) {
		ModelAndView modelView = new ModelAndView("redirect:/viagem");
		viagemRepository.deleteById(id_viagem);
		return modelView;
	}


}
