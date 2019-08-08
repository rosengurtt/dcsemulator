alter procedure UpdateTestResult
	@DatabaseName varchar(100),
	@TableName varchar(100),
	@FusionRequestId varchar(50),
	@TestResult nvarchar(max)

as
begin
	declare @sql nvarchar(max)

	set @sql =	'update [' + @DatabaseName + '].dbo.[' + @TableName + '] ' +
				'set TestResult = ''' + @TestResult + ''' ' +
				'where FusionRequestId = ''' + cast(@FusionRequestId as nvarchar(100)) + ''''

	exec sp_executesql @sql
end
go