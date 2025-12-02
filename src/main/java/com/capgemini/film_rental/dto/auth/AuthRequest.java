
package com.capgemini.film_rental.dto.auth;
public class AuthRequest {
    private String username;
    private String password;
    public AuthRequest(){}
    public AuthRequest(String u,String p){
        this.username=u;this.password=p;
    } public String getUsername(){return username;} public void setUsername(String v){username=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;} }
