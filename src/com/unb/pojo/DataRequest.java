package com.unb.pojo;

import java.util.List;

public class DataRequest {

	private String name;
	private String program;
	private String version;
	private String description;
	private String startAtTime;
	private String endAtTime;
	private String software;
	private List<String> request;
	
	public DataRequest() {	
		
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProgram() {
		return program;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStartAtTime() {
		return startAtTime;
	}
	
	public void setStartAtTime(String startAtTime) {
		this.startAtTime = startAtTime;
	}
	
	public String getEndAtTime() {
		return endAtTime;
	}
	
	public void setEndAtTime(String endAtTime) {
		this.endAtTime = endAtTime;
	}
	
	public String getSoftware() {
		return software;
	}
	
	public void setSoftware(String software) {
		this.software = software;
	}

	public List<String> getRequest() {
		return request;
	}

	public void setRequest(List<String> request) {
		this.request = request;
	}
		
}