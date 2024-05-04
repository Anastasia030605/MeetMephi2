package backend.meetmephi2.config

import backend.meetmephi2.database.dao.UserDao
import backend.meetmephi2.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import backend.meetmephi2.database.entity.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val dao : UserDao,
    private val tokenService: TokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if(authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = authHeader!!.extractTokenValue()
        val email = tokenService.extractEmail(jwtToken)

        if(email != null && SecurityContextHolder.getContext().authentication == null) {
            val foundUser = dao.findByEmail(email)

            print("--------------------------------\n")
            println("COOL TOKEN: $jwtToken")
            print("--------------------------------\n")


            if(tokenService.isValid(jwtToken, foundUser)) {
                updateContext(foundUser, request)
            }

            filterChain.doFilter(request, response)
        }
    }

    private fun updateContext(foundUser: User?, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, listOf(Authority.USER.authority))
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun String?.doesNotContainBearerToken() : Boolean =
        this == null || !this.startsWith("Bearer")

    private fun String.extractTokenValue() : String =
        this.substringAfter("Bearer ")
}