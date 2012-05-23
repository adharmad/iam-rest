package oracle.iam.webservice.domain;

import java.util.HashMap;

public class ReconEvent {
	private String id;
	private String objName;
	private HashMap<String, Object> data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, Object> getData() {
		return data;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
}
