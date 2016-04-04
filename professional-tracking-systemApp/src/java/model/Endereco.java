/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Bruno
 */
@Entity
public class Endereco implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String endereco;
    private int numero;
    private String bairro;
    private String CEP;
    private String cidade;
    private String estado;
    
    
}
