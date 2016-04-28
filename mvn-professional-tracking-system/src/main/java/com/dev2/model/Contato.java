package com.dev2.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Contato implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private String assunto;
    
    @NotEmpty
    private String mensagem;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    
    @NotEmpty
    private String quemRecebe;

    public String       getQuemRecebe()                            { return quemRecebe;                }
    public void         setQuemRecebe(String quemRecebe)           { this.quemRecebe = quemRecebe;     }
    public int          getId()                                    { return id;                        }
    public void         setId(int id)                              { this.id = id;                     }
    public String       getAssunto()                               { return assunto;                   }
    public void         setAssunto(String assunto)                 { this.assunto = assunto;           }
    public String       getMensagem()                              { return mensagem;                  }
    public void         setMensagem(String mensagem)               { this.mensagem = mensagem;         }
    public Cliente      getCliente()                               { return cliente;                   }
    public void         setCliente(Cliente cliente)                { this.cliente = cliente;           }
    public Profissional getProfissional()                          { return profissional;              }
    public void         setProfissional(Profissional profissional) { this.profissional = profissional; }
}
