package org.wflis.dao;

import org.wflis.beans.Pracownik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class PracownikDao {
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Pracownik p) {
        // create a sql insert for Pracownik p parameter
        String sql = "insert into pracownik (nazwisko, pensja, firma) values ('" + p.getNazwisko() + "', " + p.getPensja() + ", '" + p.getFirma() + "')";
        return template.update(sql);
    }

    public List<Pracownik> getAll() {
        return template.query("select * from pracownik",
            new RowMapper<Pracownik>() {
            @Override
            public Pracownik mapRow(ResultSet rs, int row) throws SQLException {
                Pracownik e = new Pracownik();
                e.setId(rs.getInt(1));
                e.setNazwisko(rs.getString(2));
                e.setPensja(rs.getFloat(3));
                e.setFirma(rs.getString(4));

                return e;
            }
        });
    }
    

    // update Pracownik p
    public int update(Pracownik p) {
        String sql = "update pracownik set nazwisko='" + p.getNazwisko() + "', pensja=" + p.getPensja() + ", firma='" + p.getFirma() + "' where id=" + p.getId();
        return template.update(sql);
    }

    // delete Pracownik p
    public int delete(int id) {
        String sql = "delete from pracownik where id=" + id;
        return template.update(sql);
    }

    // get Praocownik p
    public Pracownik get(int id) {
        String sql = "select * from pracownik where id=" + id;
        return template.queryForObject(sql, new RowMapper<Pracownik>() {
            @Override
            public Pracownik mapRow(ResultSet rs, int row) throws SQLException {
                Pracownik e = new Pracownik();
                e.setId(rs.getInt(1));
                e.setNazwisko(rs.getString(2));
                e.setPensja(rs.getFloat(3));
                e.setFirma(rs.getString(4));

                return e;
            }
        });
    }
}
