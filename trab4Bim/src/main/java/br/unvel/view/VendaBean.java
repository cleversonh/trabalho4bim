package br.unvel.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.unvel.model.Venda;

/**
 * Backing bean for Venda entities.
 * <p/>
 * This class provides CRUD functionality for all Venda entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class VendaBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Venda entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private Venda venda;

   public Venda getVenda()
   {
      return this.venda;
   }

   public void setVenda(Venda venda)
   {
      this.venda = venda;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(unitName = "trab4Bim-persistence-unit", type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      this.conversation.setTimeout(1800000L);
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
         this.conversation.setTimeout(1800000L);
      }

      if (this.id == null)
      {
         this.venda = this.example;
      }
      else
      {
         this.venda = findById(getId());
      }
   }

   public Venda findById(Long id)
   {

      return this.entityManager.find(Venda.class, id);
   }

   /*
    * Support updating and deleting Venda entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.venda);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.venda);
            return "view?faces-redirect=true&id=" + this.venda.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         Venda deletableEntity = findById(getId());

         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching Venda entities with pagination
    */

   private int page;
   private long count;
   private List<Venda> pageItems;

   private Venda example = new Venda();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public Venda getExample()
   {
      return this.example;
   }

   public void setExample(Venda example)
   {
      this.example = example;
   }

   public String search()
   {
      this.page = 0;
      return null;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<Venda> root = countCriteria.from(Venda.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<Venda> criteria = builder.createQuery(Venda.class);
      root = criteria.from(Venda.class);
      TypedQuery<Venda> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<Venda> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String jogo = this.example.getJogo();
      if (jogo != null && !"".equals(jogo))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("jogo")), '%' + jogo.toLowerCase() + '%'));
      }
      String descricao = this.example.getDescricao();
      if (descricao != null && !"".equals(descricao))
      {
         predicatesList.add(builder.like(builder.lower(root.<String> get("descricao")), '%' + descricao.toLowerCase() + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<Venda> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back Venda entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<Venda> getAll()
   {

      CriteriaQuery<Venda> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(Venda.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(Venda.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final VendaBean ejbProxy = this.sessionContext.getBusinessObject(VendaBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((Venda) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private Venda add = new Venda();

   public Venda getAdd()
   {
      return this.add;
   }

   public Venda getAdded()
   {
      Venda added = this.add;
      this.add = new Venda();
      return added;
   }
}
