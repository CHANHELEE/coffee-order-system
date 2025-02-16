package org.example.coffeeordersystem.repository

import org.assertj.core.api.Assertions.assertThat
import org.example.coffeeordersystem.config.JpaConfig
import org.example.coffeeordersystem.model.entity.Menu
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig::class)
@ActiveProfiles("test")
class MenuRepositoryTest @Autowired constructor(
    private val menuRepository: MenuRepository
) {

    var menu1Id: Long? = 0L

    @BeforeEach
    fun insertTestData() {
        //given
        // 테스트 실행 전 데이터 삽입
        val menu1 = menuRepository.save(Menu(name = "Americano", description = "Hot Americano", price = 3000))
        val menu2 = menuRepository.save(Menu(name = "Latte", description = "Hot Latte", price = 3500))

        menu1Id = menu1.id ?: throw IllegalStateException("ID가 설정되지 않았습니다.")
    }

    @AfterEach
    fun cleanUpDatabase() {
        // 테스트 실행 후 DB 초기화
        menuRepository.deleteAll()
    }

    @Test
    fun `findAll() 실행 시 모든 메뉴 목록을 반환해야 한다`() {

        // when
        val menuList = menuRepository.findAll()

        // then (리스트 크기 및 내용 검증)
        assertThat(menuList).hasSize(2)
        assertThat(menuList.map { it.name }).containsExactlyInAnyOrder("Americano", "Latte")
    }

    @Test
    fun `findById() 실행 시 존재하는 메뉴를 반환해야 한다`() {

        // when
        val foundMenu = menu1Id?.let { menuRepository.findById(it) }

        // then
        assertThat(foundMenu).isPresent
        foundMenu?.orElse(null)?.let {
            assertThat(it.name).isEqualTo("Americano")
        }
    }

    @Test
    fun `findById() 실행 시 존재하지 않는 ID를 조회하면 빈 값이 반환되어야 한다`() {

        // when
        val result = menuRepository.findById(Long.MAX_VALUE)
        // then
        assertThat(result).isEmpty
    }
}

