package cn.freeexchange.concurrent.beauty.ch11.seg6;

public class Msg {
	
	private String dataId;
	
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	@Override
	public String toString() {
		return "Msg [dataId=" + dataId + ", body=" + body + "]";
	}
	

}
