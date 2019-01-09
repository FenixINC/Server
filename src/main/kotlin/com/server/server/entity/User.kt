package com.server.server.entity

import lombok.Data
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Data
@Entity
@Table(name = "tblUser", schema = "server")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "username", length = 50)
    var username: String? = null

    @Column(name = "userSalt", length = 1024)
    var userSalt: String? = null

    @Column(name = "passwordHash", length = 1024)
    var passwordHash: String? = null
}