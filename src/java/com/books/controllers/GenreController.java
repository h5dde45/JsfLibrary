package com.books.controllers;

import com.books.beans.Genre;
import com.books.db.Database;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@ApplicationScoped
public class GenreController implements Serializable{

    private ArrayList<Genre> genreList = new ArrayList<>();

    public GenreController() {
        fillGenresAll();
    }

    private ArrayList<Genre> fillGenresAll() {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = Database.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from genre order by name");
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setName(rs.getString("name"));
                genre.setId(rs.getLong("id"));
                genreList.add(genre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenreController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GenreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return genreList;
    }

    public ArrayList<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        } else {
            return fillGenresAll();
        }
    }
}
