USE [FusionRequest]
GO
/****** Object:  StoredProcedure [dbo].[GetFusionRequestDetails]    Script Date: 15/08/2019 16:50:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[GetFusionRequestDetails]
	@FusionRequestId nvarchar(100)
as
begin

	truncate table DCSrequestsUnderTest

	delete from FusionRequestsTests 
	where  FusionRequestId = @FusionRequestId 

	insert into FusionRequestsTests (FusionRequestId, TestTime, TestStatus)
	values  (@FusionRequestId, GetDate(), 0)
	
	-- Get the data
	select 
		FusionRequestId,
		FusionRequestXml,
		FusionResponseXml,
		RequestType
	from 
		FusionRequests
	where FusionRequestId = @FusionRequestId

end
