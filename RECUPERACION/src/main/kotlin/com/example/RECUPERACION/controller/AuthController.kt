package com.example.RECUPERACION.controller


import com.example.RECUPERACION.config.JwtUtil
import com.example.RECUPERACION.dto.LoginDto
import com.example.RECUPERACION.dto.RegisterDto
import com.example.RECUPERACION.dto.TokenDto
import com.example.RECUPERACION.service.UserSecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var userSecurityService: UserSecurityService

    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val jwtUtil: JwtUtil? = null

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<*>? {
        val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        val authentication: Authentication = authenticationManager!!.authenticate(login)
        val response = TokenDto().apply { jwt= jwtUtil!!.create(loginDto.username)}
        return ResponseEntity(response, HttpStatus.OK)
    }
    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto): ResponseEntity<*>? {
        val response = userSecurityService.register(registerDto)
        return ResponseEntity(response, HttpStatus.OK)
    }
}

