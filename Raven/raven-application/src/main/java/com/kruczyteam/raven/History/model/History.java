package com.kruczyteam.raven.History.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotEmpty
	@Column(name = "service")
	private String service;

	@NotEmpty
	@Column(name = "operation")
	private String operation; //todo enum?

	@Column(name = "data")
	private String data;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public String getOperation()
	{
		return operation;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	public History()
	{

	}

	public History(String service, String operation, String data)
	{
		this.service = service;
		this.operation = operation;
		this.data = data;
	}

	public String getData()
	{

		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}
}
