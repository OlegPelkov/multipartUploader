package app.data.db.repo;

import app.data.db.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ProductPropertiesRepositoryImpl implements ProductPropertiesRepository<ProductEntity> {

    @PersistenceContext
    EntityManager em;

    public List<ProductEntity> findProductsByParams(Map<String, String> params) throws Exception {
     /*   List<ProductEntity> l = em.createQuery("SELECT p FROM ProductEntity p JOIN p.properties map WHERE ( KEY(m) IN (:names) )", ProductEntity.class)
                .setParameter("names", params.keySet())
                .getResultList();*/

      /*  List<String> l = em.createQuery("SELECT p FROM product_properties p WHERE p.name IN (:names)", String.class)
                .setParameter("names", params.keySet())
                .getResultList();*/


      /*  List<ProductEntity> l2 = em.createQuery("SELECT pe FROM ProductEntity pe, in (pe.properties) p WHERE KEY(p) IN (:names)", ProductEntity.class)
                .setParameter("names", params.keySet())
                .getResultList();*/

     /*   Query query  = em.createQuery(" from ProductEntity pe join pe.properties prop where( (index(prop) = :key  " +
                "and :value in elements(pe.properties)) or (index(prop) = :key2 and :value in elements(pe.properties)))");
*/
        List<ProductEntity> list;


      /*  Query query = em.createQuery("from ProductEntity p JOIN p.properties map where KEY(map)=:p_name");
        List<Object> resultList = new ArrayList<Object>();
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
            String value = next.getValue();
            query.setParameter("p_name", next);
            Object result = (Object) query.getSingleResult();
            resultList.add(result);
        }

        System.out.println(resultList);
*/


              /*Query query  = em.createQuery("Select pe from ProductEntity pe join pe.properties prop where( (index(prop) = :key  " +
                "and :value in elements(pe.properties)))");
        query.setParameter("key", params.keySet());
        query.setParameter("value", params.values());
        list = query.getResultList();*/

       /* CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteria = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> entityRoot = criteria.from(ProductEntity.class);
        criteria.select(entityRoot);
        MapJoin<ProductEntity, String, String> properties = entityRoot.joinMap("properties");
        criteria.multiselect(entityRoot.get("properties"));
       // criteria.where((builder.like(criteria.getParameters("properties"), builder.parameter(String.class, "firstName")));
        return em.createQuery(criteria).getResultList();*/

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteria = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> entity = criteria.from(ProductEntity.class);
        MapJoin<ProductEntity, String, String> mapJoin = entity.joinMap("properties", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Path<String> pkey = mapJoin.key();
        for(String s : params.keySet()){
            predicates.add(cb.equal(pkey, s));
            System.out.println("mapJoin.key() " +pkey + "s "+s);
        }

        Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
        criteria.select(entity);
        //criteria.where(cb.and(p));
        criteria.where(cb.and(cb.equal(pkey, "display"),cb.equal(pkey, "weight")));
        criteria.distinct(true);
        list = em.createQuery(criteria).getResultList();

        return list;
    }
}
