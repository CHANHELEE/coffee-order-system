package org.example.coffeeordersystem.common.resolver


import org.example.coffeeordersystem.common.annotation.CurrentUser
import org.example.coffeeordersystem.repository.AccountRepository
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class CurrentUserHandlerMethodArgumentResolver(
    private val accountRepository: AccountRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(CurrentUser::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication.name == "anonymousUser") {
            throw RuntimeException("Not authenticated User")
        }
        return accountRepository.findByUsername(authentication.name)
    }
}