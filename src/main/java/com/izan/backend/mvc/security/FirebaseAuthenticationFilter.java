package com.izan.backend.mvc.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // DEBUG - Visualiza la ruta y método
        System.out.println(">>> Filtro interceptó: " + method + " " + path);

        // 🟢 Rutas públicas (sin autenticación Firebase)
        if (
            path.startsWith("/auth") ||
            (method.equals("POST") && path.equals("/worktrack/usuarios/registro")) ||
            (method.equals("GET") && path.startsWith("/worktrack/usuarios/email/"))
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔒 Rutas protegidas - requieren token
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("❌ Token no presente o malformado");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = header.replace("Bearer ", "");
        System.out.println("🟡 TOKEN RECIBIDO EN BACKEND: " + token);

        try {
            System.out.println(">>> Validando token Firebase...");
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

            // 🔐 Guardar el email como autenticación en el contexto
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                decodedToken.getEmail(), null, new ArrayList<>()
            );

            System.out.println("✅ Token válido. Email: " + decodedToken.getEmail());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (FirebaseAuthException e) {
            System.out.println("❌ Token inválido: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
