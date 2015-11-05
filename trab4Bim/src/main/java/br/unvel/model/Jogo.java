package br.unvel.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import br.unvel.model.Categoria;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;

@Entity
public class Jogo implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(length = 60, nullable = false)
   private String nome;

   @ManyToMany
   private Set<Categoria> categoria = new HashSet<Categoria>();

   @Column
   private float valor;

   @Column
   private String descricao;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Jogo))
      {
         return false;
      }
      Jogo other = (Jogo) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public Set<Categoria> getCategoria()
   {
      return this.categoria;
   }

   public void setCategoria(final Set<Categoria> categoria)
   {
      this.categoria = categoria;
   }

   public float getValor()
   {
      return valor;
   }

   public void setValor(float valor)
   {
      this.valor = valor;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nome != null && !nome.trim().isEmpty())
         result += "nome: " + nome;
      result += ", valor: " + valor;
      if (descricao != null && !descricao.trim().isEmpty())
         result += ", descricao: " + descricao;
      return result;
   }
}