package com.abnamro.cognitiveservices.FaceAPIDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abnamro.cognitiveservices.FaceAPIDemo.config.FaceAPIEnv;
import com.abnamro.cognitiveservices.FaceAPIDemo.model.FaceAPIModel;

@Controller
public class FaceAPIViewController {

	@Autowired
	private FaceAPIEnv faceAPIEnv;

	@GetMapping("/")
	public String init(Model model) {
		FaceAPIModel faceApiModel = new FaceAPIModel();
		faceApiModel.setKey(faceAPIEnv.getKey());
		model.addAttribute("model", faceApiModel);
		return "detect";
	}

}
