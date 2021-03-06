USE [FusionRequest]
GO
/****** Object:  StoredProcedure [dbo].[UpdateTestResult]    Script Date: 15/08/2019 16:50:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[UpdateTestResult]
	@FusionRequestId varchar(50),
	@TestResult nvarchar(max),
	@TestInfo nvarchar(500),
	@ExpectedResponse nvarchar(max)='',
	@ActualResponse nvarchar(max)='',
	@ActualDCScalls nvarchar(max)=''

as
begin
	declare @sql nvarchar(max)

	update FusionRequestsTests
	set 
		TestResult = replace(@TestResult, '''', ''''''),
		TestStatus = 1,
		TestInfo = replace(@TestInfo, '''', ''''''),
		ExpectedResponse = replace(@ExpectedResponse, '''', ''''''),
		ActualResponse = replace(@ActualResponse, '''', ''''''),
		ActualDCScalls = replace(@ActualDCScalls, '''', '''''')
	where FusionRequestId = @FusionRequestId	

	exec sp_executesql @sql
end
