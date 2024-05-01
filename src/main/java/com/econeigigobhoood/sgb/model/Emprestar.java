package com.econeigigobhoood.sgb.model;

//Interface Emprestar
public interface Emprestar {
   void emprestar(String Status, int IdLivro);
   void devolver(String Status,int IdLivro);
}
