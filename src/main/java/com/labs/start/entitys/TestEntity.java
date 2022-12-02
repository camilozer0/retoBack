package com.labs.start.entitys;

import javax.persistence.*;

@Entity
@Table(name="tests")
public class TestEntity {
	
	
	public TestEntity() {}	
	
	public TestEntity(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	@Id
	@Column(name = "idTest")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
				
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
