/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecardapio.democardapio.repository;

import com.ecardapio.democardapio.model.CardapioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mathe
 */
@Repository
public class CardapioDAO {
    
    public void adicionar(CardapioBean cardapio) {
        
         try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO itens (nome, preco, categoria, disponivel VALUES (?,?,?,?)");
            stmt.setString(1, cardapio.getNome());
            stmt.setDouble(2, cardapio.getPreco());
            stmt.setString(3, cardapio.getCategoria());
            stmt.setBoolean(4, cardapio.getDisponivel());
            
            stmt.executeUpdate();
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<CardapioBean> ler() {
        List<CardapioBean> itens = new ArrayList();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT * FROM itens");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                CardapioBean cardapio = new CardapioBean();
                cardapio.setId(rs.getInt("id"));
                cardapio.setNome(rs.getString("nome"));
                cardapio.setPreco(rs.getDouble("preco"));
                cardapio.setCategoria(rs.getString("categoria"));
                cardapio.setDisponivel(rs.getBoolean("disponivel"));
           
                itens.add(cardapio);
            }
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return itens;
    }
    
    public void editar(CardapioBean cardapio) {
     
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE itens SET nome=?, preco=?, categoria=? WHERE id=?");
            stmt.setString(1, cardapio.getNome());
            stmt.setDouble(2, cardapio.getPreco());
            stmt.setString(3, cardapio.getCategoria());
            stmt.setInt(4, cardapio.getId());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(int id) {
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("DELETE FROM itens WHERE id = ?");
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}