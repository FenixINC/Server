package com.server.server.service

import com.server.server.entity.Page

interface PageService {
    fun pageCreate(page: Page)
    fun pageUpdate(id: Long, title: String)
    fun pageDelete(id: Long)
    fun getList(): List<Page>
    fun getPage(id: Long): Page
}