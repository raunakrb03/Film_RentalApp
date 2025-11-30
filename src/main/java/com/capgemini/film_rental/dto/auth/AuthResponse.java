
package com.capgemini.film_rental.dto.auth;
public class AuthResponse { private String token; private String role; public AuthResponse(){} public AuthResponse(String t,String r){this.token=t;this.role=r;} public String getToken(){return token;} public void setToken(String v){token=v;} public String getRole(){return role;} public void setRole(String v){role=v;} }
