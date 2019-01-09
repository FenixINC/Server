package com.server.server.entity

import lombok.Data
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Data
@Entity
@Table(name = "tblPage", schema = "server")
class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "title", length = 50)
    var title: String? = null

    @Column(name = "action", length = 50)
    var action: String? = null
}