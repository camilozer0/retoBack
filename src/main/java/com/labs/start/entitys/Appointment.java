package com.labs.start.entitys;

import java.time.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "appointments")
public class Appointment {
	
		
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date")
	private LocalDate date;
	
	@JsonFormat(pattern = "HH:mm")
	@Column(name = "hour")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hour;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idTest")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TestEntity idTest;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idAffiliate")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Affiliate idAffiliate;
	
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", hour=" + hour + ", idTest=" + idTest + ", idAffiliate="
				+ idAffiliate + "]";
	}
	public Appointment() {
		super();
	}
	public Appointment(long id, LocalDate date, LocalTime hour, TestEntity idTest, Affiliate idAffiliate) {
		super();
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.idTest = idTest;
		this.idAffiliate = idAffiliate;
	}
	public TestEntity getIdTest() {
		return idTest;
	}
	public void setIdTest(TestEntity idTest) {
		this.idTest = idTest;
	}
	public Affiliate getIdAffiliate() {
		return idAffiliate;
	}
	public void setIdAffiliate(Affiliate idAffiliate) {
		this.idAffiliate = idAffiliate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHour() {
		return hour;
	}
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

}
