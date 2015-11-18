package br.unvel.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Venda implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String jogo;

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
      if (!(obj instanceof Venda))
      {
         return false;
      }
      Venda other = (Venda) obj;
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

   public String getJogo()
   {
      return jogo;
   }

   public void setJogo(String jogo)
   {
      this.jogo = jogo;
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
      if (jogo != null && !jogo.trim().isEmpty())
         result += "jogo: " + jogo;
      result += ", valor: " + valor;
      if (descricao != null && !descricao.trim().isEmpty())
         result += ", descricao: " + descricao;
      return result;
   }
}