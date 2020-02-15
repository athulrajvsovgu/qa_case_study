public class Result {

	public String serviceName;
	public int numOfRequests;
	public long maxTime;

	public Result(String serviceName, long timeDelta) {
		super();
		this.serviceName = serviceName;
		this.numOfRequests = 1;
		this.maxTime = timeDelta;
	}

	public Result(ServiceCall serviceCall) {
		this.serviceName = serviceCall.serviceName;
		this.numOfRequests = 1;
		this.maxTime = serviceCall.getTimeDelta();
	}

	public void incrementRequest(long timeDelta) {
		this.numOfRequests++;
		if (timeDelta > this.maxTime)
			this.maxTime = timeDelta;
	}
}
