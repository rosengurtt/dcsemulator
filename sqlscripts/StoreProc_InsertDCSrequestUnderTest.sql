USE [FusionRequest]
GO
/****** Object:  StoredProcedure [dbo].[InsertDCSrequestUnderTest]    Script Date: 15/08/2019 16:49:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[InsertDCSrequestUnderTest]
	@DCSrequestType nvarchar(200),
	@DcsRequestXml nvarchar(max),
	@DcsRequestIdMatched nvarchar(50) = NULL
as
begin
	insert into DCSrequestsUnderTest (DCSrequestType, DcsRequestxml, DcsRequestIdMatched, TestTime)
	values (@DCSrequestType, @DcsRequestxml, @DcsRequestIdMatched, GetDate())
end