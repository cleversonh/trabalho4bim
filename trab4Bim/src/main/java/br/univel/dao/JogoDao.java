package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.model.Jogo;

/**
 *  DAO for Jogo
 */
@Stateless
public class JogoDao
{
   @PersistenceContext(unitName = "trab4Bim-persistence-unit")
   private EntityManager em;

   public void create(Jogo entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      Jogo entity = em.find(Jogo.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public Jogo findById(Long id)
   {
      return em.find(Jogo.class, id);
   }

   public Jogo update(Jogo entity)
   {
      return em.merge(entity);
   }

   public List<Jogo> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Jogo> findAllQuery = em.createQuery("SELECT DISTINCT j FROM Jogo j LEFT JOIN FETCH j.categoria ORDER BY j.id", Jogo.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      return findAllQuery.getResultList();
   }
}
