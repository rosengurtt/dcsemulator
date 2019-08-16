create proc GetDCSexpectedCallsForFusionRequest
	@FusionRequestId nvarchar(50)
as
begin

		select 
			DcsRequestId
			,FusionRequestId
			,ConfigurationId
			,RequestSourceId
			,SessionId
			,PNR
			,Airline
			,Airport
			,RequestType
			,DcsName
			,DcsVersion
			,DcsRequestXml
			,DcsResponseXml
			,RequestTime
			,ResponseTime
		from DcsRequests
		where FusionRequestId=@FusionRequestId
		order by RequestTime


end