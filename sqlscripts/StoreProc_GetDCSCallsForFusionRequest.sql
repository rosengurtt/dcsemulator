USE [FusionRequest]
GO
/****** Object:  StoredProcedure [dbo].[GetDCSCallsForFusionRequest]    Script Date: 14/08/2019 14:41:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[GetDCSCallsForFusionRequest]
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
		from 
				DcsRequests
		where 
				FusionRequestId=@FusionRequestId
		order by 
				RequestTime

		select 
				DCSrequestType
				,DcsRequestXml
				,TestTime
				,DcsRequestIdMatched
		from 
				FusionRequest.dbo.DCSrequestsUnderTest 
		order by 
				TestTime

end