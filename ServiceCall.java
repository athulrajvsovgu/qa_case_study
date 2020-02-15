
public class ServiceCall {
	public int serviceId;
	public String serviceName;
	public long entryTime;
	public long exitTime;
	
	public ServiceCall(int serviceId, String serviceName, long entryTime) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.entryTime = entryTime;
		this.exitTime = 0;
	}
	
	public void SetExitTime(long time) {
		exitTime = time;
	}
	
	public long getTimeDetla() {
		if(this.exitTime != 0)
			return (this.exitTime - this.entryTime);
		else return 0;
	}
	
	

}
