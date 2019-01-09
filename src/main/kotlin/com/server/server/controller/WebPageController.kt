package com.server.server.controller

import com.server.server.entity.Page
import com.server.server.extentions.TextUtils
import com.server.server.service.PageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WebPageController(private val mService: PageService) {

    // Get Pages:
    @GetMapping("/pages")
    fun doPages(model: Model): String {
        model.addAttribute("pageList", mService.getList())
        return "pages"
    }

    // Page Create:
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
            mService.pageCreate(newPage)
            return "redirect:/pages"
        }
        return "/page/create"
    }

    // Page Update:
    @GetMapping("page/update/{id}/{title}")
    fun doPageUpdate(
            @PathVariable("id") id: Long,
            @PathVariable("title") title: String
    ): String {
        mService.pageUpdate(id, title)
        return "redirect:/pages"
    }

    // Page Delete:
    @GetMapping("/page/delete/{id}")
    fun doPageDelete(model: Model, @PathVariable("id") id: Long): String {
        mService.pageDelete(id)
        return "redirect:/pages"
    }
}