package com.server.server.repository

import com.server.server.entity.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface PageRepository : JpaRepository<Page, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Page page SET page.title = :title WHERE page.id = :id")
    fun pageUpdate(
            @Param("id") id: Long,
            @Param("title") title: String
    )

    @Modifying
    @Transactional
    @Query("DELETE FROM Page page WHERE page.id = :id")
    fun pageDelete(@Param("id") id: Long)

    @Query("SELECT page FROM Page page WHERE page.id = :id")
    fun getPage(id: Long): Page

    @Query("SELECT page FROM Page page")
    fun getList(): List<Page>
}