package org.example.coffeeordersystem.model.entity

import jakarta.persistence.*
import org.example.coffeeordersystem.model.entity.common.BaseEntity
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "point")
@DynamicUpdate
class Point(
    id: Long? = null,
    point: Long,
    account: Account
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Column(name = "point", nullable = false)
    var point: Long = point

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    var account: Account = account
        protected set

}


