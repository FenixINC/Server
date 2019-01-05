package com.server.server.controller

import com.server.server.entity.Login
import com.server.server.utils.LoggerUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WebUserController {

    private val LOG = LoggerUtils(WebUserController::class.java)

    // LOGIN
    @GetMapping("/login")
    fun doLogin(model: Model): String {
        LOG.info("/login")
        model.addAttribute("login", Login())
        return "login"
    }

    @PostMapping("/login")
    fun doLogin(@ModelAttribute login: Login): String {
        return "home"
    }

    // NAV_BAR
    @GetMapping("/home")
    fun doHome(): String {
        return "home"
    }
}