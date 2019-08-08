alter procedure GetFusionRequestsToTest
	@Airline nvarchar(2),
	@Airport nvarchar(3),
	@StartDate datetime,
	@EndDate datetime,
	@DatabaseName nvarchar(50),
	@TableName nvarchar(100)

as
begin
	set nocount on;

	declare @sql nvarchar(max)

	set @sql =	'select FusionRequestId ' +
				'from ' + @DatabaseName + '.dbo.' + @TableName + ' ' +
				'where airline like ''' + @Airline + ''' and airport like  ''' + @Airport + ''' and ' +
				'RequestTime >= ''' + convert(varchar, @StartDate, 121)+ ''' AND RequestTime <= ''' + convert(varchar, @EndDate, 121) + ''' ' +
				'order by RequestTime' 
	print @sql

	exec sp_executesql @sql
end
go
