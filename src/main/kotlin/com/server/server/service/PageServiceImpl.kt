package com.server.server.service

import com.server.server.entity.Page
import com.server.server.repository.PageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PageServiceImpl(@Autowired private val mRepository: PageRepository) : PageService {
    override fun pageCreate(page: Page) {
        mRepository.saveAndFlush(page)
    }

    override fun pageUpdate(id: Long, title: String) {
        mRepository.pageUpdate(id, title)
    }

    override fun pageDelete(id: Long) {
        mRepository.pageDelete(id)
    }

    override fun getList(): List<Page> {
        return mRepository.getList()
    }

    override fun getPage(id: Long): Page {
        return mRepository.getPage(id)
    }
}
