package co.premier.zuul.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_log_request")
@Getter
@Setter
public class LogEntity extends Auditoria<String>{
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "logrequest_generator", sequenceName = "logrequest_secuence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logrequest_generator")
	private Long id;

	@Column(name = "body")
	@Type(type="text")
	private String body;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "metodo")
	private String metodo;
	
	@Column(name = "tipo")
	private String tipo;

	public LogEntity(Long id, String body, String url, String metodo, String tipo) {
		super();
		this.id = id;
		this.body = body;
		this.url = url;
		this.metodo = metodo;
		this.tipo = tipo;
	}

	public LogEntity() {
		super();
	}
	
	
	
}
