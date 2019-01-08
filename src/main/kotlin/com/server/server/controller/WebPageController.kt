package com.server.server.controller

import com.server.server.entity.Page
import com.server.server.extentions.TextUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WebPageController {

    private val mPageList = ArrayList<Page>()

    @GetMapping("/pages")
    fun doPages(model: Model): String {
        model.addAttribute("pageList", mPageList)
        val tmpPage1 = Page()
        tmpPage1.id = 1
        tmpPage1.title = "tmp_title_1"
        mPageList.add(tmpPage1)
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
            mPageList.add(newPage)
            return "redirect:/pages"
        }
        return "/page/create"
    }

    // Page Delete:
    @GetMapping("/page/delete/{id}")
    fun doPageDelete(model: Model, @PathVariable("id") id: Long): String {
        //TODO: implement delete Page

        return "redirect:/pages"
    }

//    @PostMapping("/page/delete/{id}")
//    fun doPageDelete(@PathVariable("id") id: Long, @ModelAttribute("page") page: Page, model: Model): String {
//        model.addAttribute("formPageDelete", page)
//        return "redirect:/pages"
//    }
}