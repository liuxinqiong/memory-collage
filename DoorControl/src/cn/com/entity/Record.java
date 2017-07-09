package cn.com.entity;

public class Record {
	private int recordId;
	private String userId;
	private String openTime;

	public Record(){
		
	}

	public Record(int recordId, String userId, String openTime) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.openTime = openTime;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	
	
}
