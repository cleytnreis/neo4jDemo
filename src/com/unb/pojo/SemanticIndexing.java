package com.unb.pojo;

import java.util.List;

public class SemanticIndexing {

	private String name;
	private String Program;
	private String Version;
	private String Description;
	private String StartAtTime;
	private String EndAtTime;
	private String Software;
	private List<String> controledVocabulary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgram() {
		return Program;
	}
	public void setProgram(String program) {
		Program = program;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStartAtTime() {
		return StartAtTime;
	}
	public void setStartAtTime(String startAtTime) {
		StartAtTime = startAtTime;
	}
	public String getEndAtTime() {
		return EndAtTime;
	}
	public void setEndAtTime(String endAtTime) {
		EndAtTime = endAtTime;
	}
	public String getSoftware() {
		return Software;
	}
	public void setSoftware(String software) {
		Software = software;
	}
	public List<String> getControledVocabulary() {
		return controledVocabulary;
	}
	public void setControledVocabulary(List<String> controledVocabulary) {
		this.controledVocabulary = controledVocabulary;
	}
	
}
