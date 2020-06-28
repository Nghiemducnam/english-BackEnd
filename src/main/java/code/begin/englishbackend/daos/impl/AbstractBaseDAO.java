package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.BaseDAO;
import code.begin.englishbackend.dtos.BaseSearch;
import code.begin.englishbackend.dtos.OrderDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBaseDAO implements BaseDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public <T> List<T> findAll(Class<T> clazz){
        return getSession().createQuery("select t from " + clazz.getName() + " t", clazz).getResultList();
    }
    public <T> Optional<T> findById(Serializable id, Class<T> clazz){
        return Optional.ofNullable(getSession().get(clazz, id));
    }
    public <T> Serializable save(T entity){
        return getSession().save(entity);
    }
    public <T> void update(T entity){
        getSession().merge(entity);
    }
    public <T> void delete(T entity){
        getSession().delete(entity);
    }

    /**
     * Get current session from EntityManager
     *
     * @return Session
     */
    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return namedParameterJdbcTemplate;
    }


    /**
     * Search record per page and count total record by search condition
     *
     * @param searchDTO search condition extends from SearchDTO
     * @param sqlString query string build with search condition
     * @param parameters parameters passing to Query
     */
    protected <T> void querySearchAndCount(BaseSearch searchDTO, String sqlString, Map<String, Object> parameters, Class<T>  clazz) {
        Query<T> query = getSession().createQuery(sqlString, clazz);
        searchAndCountTotal(searchDTO, sqlString, parameters, query);
    }

    protected String getOrderBy(boolean ascending) {
        return ascending ? OrderDTO.ASC : OrderDTO.DESC;
    }

    private <T> void searchAndCountTotal(BaseSearch searchDTO, String sqlString, Map<String, Object> parameters, Query<T> query) {
        query.setFirstResult(searchDTO.getPage() * searchDTO.getPageSize());
        query.setMaxResults(searchDTO.getPageSize());
        parameters.forEach(query::setParameter);
        searchDTO.setData(query.list());
        Long totalRecords = countTotalRecords(sqlString, parameters);
        countPages(searchDTO, totalRecords);
    }

    /**
     * Search record per page and count total record by search condition
     *
     * @param searchDTO search condition extends from SearchDTO
     * @param sqlString query string build with search condition
     * @param parameters parameters passing to Query
     */
    protected <T> void querySearchAndCount(BaseSearch searchDTO, String sqlString, Map<String, Object> parameters) {
        Query<T> query = getSession().createQuery(sqlString);
        searchAndCountTotal(searchDTO, sqlString, parameters, query);
    }

    protected <T> void searchAndCountTotal(BaseSearch searchDTO, String sqlString, Map<String, Object> parameters, Class<T> clazz){
        Long totalRecords = countNativeTotalRecords(sqlString, parameters);
        countPages(searchDTO, totalRecords);
        sqlString = getSqlPaging(searchDTO, sqlString, parameters);
        searchDTO.setData(namedParameterJdbcTemplate.query(sqlString, parameters, BeanPropertyRowMapper.newInstance(clazz)));
    }

    private void countPages(BaseSearch searchDTO, Long totalRecords) {
        int totalPages = (int) (totalRecords / searchDTO.getPageSize());
        if (totalRecords % Long.valueOf(searchDTO.getPageSize()) != 0) {
            totalPages++;
        }
        searchDTO.setTotalPages(totalPages);
        searchDTO.setTotalRecords(totalRecords);
    }

    protected long getOffset(BaseSearch searchDto){
        if(searchDto.getPage() == 0)
            return 0;
        return searchDto.getPage() * searchDto.getPageSize();
    }

    private String getSqlPaging(BaseSearch searchDto, String sql, Map<String, Object> parameters){
        StringBuilder sqlBuilder = new StringBuilder();
        sql = sql.replaceAll("(?i) from ", " from ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" limit :p_offset, :p_page_size");
        parameters.put("p_offset", getOffset(searchDto));
        parameters.put("p_page_size", searchDto.getPageSize());
        return sqlBuilder.toString();
    }

    /**
     * countTotalRecords
     * @param sqlString String query before build search option
     * @param parameters this parameter passing to query
     * @return total records
     */
    protected Long countTotalRecords(String sqlString, Map<String, Object> parameters) {
        sqlString = sqlString.replaceAll("(?i) from ", " from ");
        sqlString = sqlString.replaceAll("(?i)select ", "select ");
        if (sqlString.startsWith("from")) {
            sqlString = "select count(*) " + sqlString;
        } else {
            sqlString = sqlString.replaceAll("select.*from", "select count(*) from ");
        }
        Query<Long> query = getSession().createQuery(sqlString, Long.class);
        parameters.forEach(query::setParameter);
        return query.uniqueResult();
    }

    protected Long countNativeTotalRecords(String sqlString, Map<String, Object> parameters) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(*) from (").append(sqlString).append(") a");
        return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), parameters, Long.class);
    }

}
