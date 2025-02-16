package org.example.coffeeordersystem.model.entity

import jakarta.persistence.*
import org.example.coffeeordersystem.model.entity.common.BaseEntity
import org.hibernate.annotations.DynamicUpdate


@Entity
@Table(name = "menu")
@DynamicUpdate
class Menu(
    description: String,
    price: Long,
    name: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "description")
    var description: String? = description
        protected set

    @Column(name = "price", nullable = false)
    var price: Long = price
        protected set
}
