package org.example.coffeeordersystem.model.entity

import jakarta.persistence.*
import org.example.coffeeordersystem.model.entity.common.BaseEntity
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "account")
@DynamicUpdate
class Account(
    id: Long,
    name: String,
    role: String,
    password: String,
    username: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "username", nullable = false)
    var username: String = username
        protected set

    @Column(name = "password", nullable = false)
    var password: String? = password
        protected set

    @Column(name = "name", nullable = false)
    var name: String? = name
        protected set

    @Column(name = "role", nullable = false)
    var role: String = role
        protected set
}


