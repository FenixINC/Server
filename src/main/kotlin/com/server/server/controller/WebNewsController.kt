package com.server.server.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebNewsController(/*private val mService: NewsService*/) {

    // Get News:
    @GetMapping("/news")
    fun doNews(model: Model): String {
//        model.addAttribute("newsList", mService.getList())
        return "news"
    }
}