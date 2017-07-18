package com.abnamro.cognitiveservices.FaceAPIDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abnamro.cognitiveservices.FaceAPIDemo.model.FaceAPIModel;

@Controller
public class FaceAPIViewController {

	@GetMapping("/")
	public String init(Model model) {
		model.addAttribute("model", new FaceAPIModel());
		return "detect";
	}

}
