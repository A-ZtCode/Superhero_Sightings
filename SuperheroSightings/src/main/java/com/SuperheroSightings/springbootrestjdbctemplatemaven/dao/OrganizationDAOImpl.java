package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        final String sql = "INSERT INTO Organizations(name, description, address_contact_info) VALUES(?,?,?)";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription(), organization.getAddressContactInfo());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        return organization;
    }

    @Override
    public Organization getOrganizationById(int id) {
        final String sql = "SELECT * FROM Organizations WHERE organization_id = ?";
        return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String sql = "SELECT * FROM Organizations";
        return jdbcTemplate.query(sql, new OrganizationMapper());
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String sql = "UPDATE Organizations SET name=?, description=?, address_contact_info=? WHERE organization_id=?";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription(), organization.getAddressContactInfo(), organization.getId());
    }

    @Override
    public void deleteOrganizationById(int id) {
        final String sql = "DELETE FROM Organizations WHERE organization_id=?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if(rowsAffected == 0) {
            throw new RuntimeException("Attempted to delete a non-existent organization");
        }
    }


    @Override
    public List<Organization> getOrganizationsByHeroId(int heroId) {
        final String sql = "SELECT o.* FROM Organizations o JOIN Hero_Organization ho ON o.organization_id = ho.organization_id WHERE ho.hero_id = ?";
        return jdbcTemplate.query(sql, new OrganizationMapper(), heroId);
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("organization_id"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setAddressContactInfo(rs.getString("address_contact_info"));
            return org;
        }
    }
}
