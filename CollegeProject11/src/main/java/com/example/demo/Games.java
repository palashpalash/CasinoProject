package com.example.demo;

import java.sql.Date;

public class Games {
	private int gameid;
	private String gamename;
	@Override
	public String toString() {
		return "Games [gameid=" + gameid + ", gamename=" + gamename + ", gamedate=" + gamedate + "]";
	}
	private Date gamedate;
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public Date getGamedate() {
		return gamedate;
	}
	public void setGamedate(Date gamedate) {
		this.gamedate = gamedate;
	}

}
