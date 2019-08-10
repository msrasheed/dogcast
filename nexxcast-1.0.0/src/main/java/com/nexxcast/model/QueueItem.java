package com.nexxcast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.nexxcast.Gson.GsonExclude;

/**
 * Entity implementation class for Entity: QueueItem
 *
 */
@Entity
@Table(name = "QueueItems")
public class QueueItem implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name = "partycode")
	@GsonExclude
	private PartyQueue queue;
	
	private String uri;
	private int votes;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public QueueItem() {
		super();
	}
	
	public QueueItem(String uri) {
		this.uri = uri;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PartyQueue getQueue() {
		return queue;
	}

	public void setQueue(PartyQueue queue) {
		this.queue = queue;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
   
}
