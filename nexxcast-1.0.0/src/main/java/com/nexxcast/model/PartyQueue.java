package com.nexxcast.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Queue
 *
 */
@Entity
@Table(name = "PartyQueues")
public class PartyQueue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;
	
	private boolean createPlaylist;
	private int playIndex;
	
	@OneToMany(mappedBy="queue", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@OrderBy("votes DESC, timeCreated ASC")
	private List<QueueItem> items;

	public PartyQueue() {
		super();
	}
	
	public PartyQueue(String code, boolean createPlaylist) {
		super();
		this.code = code;
		this.createPlaylist = createPlaylist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isCreatePlaylist() {
		return createPlaylist;
	}

	public void setCreatePlaylist(boolean createPlaylist) {
		this.createPlaylist = createPlaylist;
	}

	public List<QueueItem> getItems() {
		return items;
	}

	public void setItems(List<QueueItem> items) {
		this.items = items;
	}
	
	public void addItem(QueueItem item) {
		this.items.add(item);
		item.setQueue(this);
	}
	
	public void removeItem(QueueItem item) {
		this.items.remove(item);
	}
}
