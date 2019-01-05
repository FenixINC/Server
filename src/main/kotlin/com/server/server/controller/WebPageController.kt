package com.server.server.controller

import com.server.server.entity.Page
import com.server.server.extentions.TextUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WebPageController {

    private val mPageList = ArrayList<Page>()

    @GetMapping("/pages")
    fun doPages(model: Model): String {
        model.addAttribute("pageList", mPageList)
        return "pages"
    }

    @GetMapping("/page/create")
    fun doPageCreate(model: Model): String {
        val page = Page()
        model.addAttribute("formPage", page)
        return "/page-create"
    }

    @PostMapping("/page/create")
    fun doPageCreate(@ModelAttribute page: Page, model: Model): String {
        if (!TextUtils.isEmpty(page.title)) {
            val newPage = Page()
            newPage.title = page.title
            mPageList.add(newPage)
            return "redirect:/pages"
        }
        return "/page/create"
    }
}