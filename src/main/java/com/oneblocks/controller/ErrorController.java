package com.oneblocks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oneblocks.configuration.http.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/error")
@Tag(name = "Error", description = "Error page")
public class ErrorController {
	
	@GetMapping("/errorLogin")
	@Operation(summary = "error", description = "error")
	public void errorLogin(Model model) {
	}
}
