package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * Implementation class for data access operations related to the Organization entity.
 *
 * This class provides the actual JDBC implementation for CRUD and other operations
 * defined in the OrganizationDAO interface, making use of the JdbcTemplate for database interactions.
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor to set the JdbcTemplate used for database interactions.
     *
     * @param jdbcTemplate The JdbcTemplate instance.
     */
    @Autowired
    public OrganizationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization addOrganization(Organization organization) {
        final String sql = "INSERT INTO Organizations(name, description, address_contact_info) VALUES(?,?,?)";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription(), organization.getAddressContactInfo());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationById(int id) {
        final String sql = "SELECT * FROM Organizations WHERE organization_id = ?";
        return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getAllOrganizations() {
        final String sql = "SELECT * FROM Organizations";
        return jdbcTemplate.query(sql, new OrganizationMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrganization(Organization organization) {
        final String sql = "UPDATE Organizations SET name=?, description=?, address_contact_info=? WHERE organization_id=?";
        jdbcTemplate.update(sql, organization.getName(), organization.getDescription(), organization.getAddressContactInfo(), organization.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOrganizationById(int id) {
        final String sql = "DELETE FROM Organizations WHERE organization_id=?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if(rowsAffected == 0) {
            throw new RuntimeException("Attempted to delete a non-existent organization");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getOrganizationsByHeroId(int heroId) {
        final String sql = "SELECT o.* FROM Organizations o JOIN Hero_Organization ho ON o.organization_id = ho.organization_id WHERE ho.hero_id = ?";
        return jdbcTemplate.query(sql, new OrganizationMapper(), heroId);
    }
    /**
     * RowMapper class to map the result set rows to Organization objects.
     */
    private static final class OrganizationMapper implements RowMapper<Organization> {

        /**
         * Maps a single row of the result set to an Organization object.
         *
         * @param rs The result set.
         * @param index The current row number.
         * @return The mapped Organization object.
         * @throws SQLException In case of any SQL errors.
         */
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
